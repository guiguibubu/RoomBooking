<?php

/*
 * Router for api requests
 * All /api/* requests are redirected here by Apache .htaccess in ../
 * Their processing depends on the REQUEST_URI after the prefix /api
 * and on the HTTP method used (GET, POST, PUT, DELETE).
 * 
 * We'll be implementing pseudo-REST and encapsulation by putting the files processing the requests in routes/
 * and let this be an entry point to the API, routing requests to their rightful processing unit
 * 
 * Authentification of users is also done at this level, before any actual processing of the request.
 * It applies to all POST, PUT, DELETE requests and on GET requests specified in $tokenNeeded.
 * 
 * See routes/auth.php for the authentication method
 */

header('Content-Type: application/json');

include "tools/bdd.php";
include "tools/misc.php";


$query = strtok($_SERVER['REQUEST_URI'], '?');
$method = $_SERVER['REQUEST_METHOD'];

$query = explode('/', $query); //split request

array_shift($query);
//get rid of first /

array_shift($query);
//get rid of /api prefix

$queryName = implode('.', $query);


//Validate if the route exists (as no file is where the URI is)
if(!file_exists("routes/" . $queryName . ".php")) {
	echo json_encode(['error' => 'This path does not exists']);
	exit;
}

$_USER = []; //global to store infos about the user (retrieved from the token)

$tokenNeeded = ['bookings.my']; //GET requests needing auth

if ($method === 'GET' && !in_array($queryName, $tokenNeeded)) {
	include "routes/" . $queryName . ".php";
} else {

	$token = !empty($_SERVER['HTTP_X_TOKEN']) ? $_SERVER['HTTP_X_TOKEN'] : '';

	//fetch token
	$req = $db -> prepare('SELECT user_id FROM tokens WHERE token = ?');
	$req -> execute(array($token));
	$token = $req -> fetch(PDO::FETCH_ASSOC);

	if ($token['user_id'] || $query[0] === 'auth') {
		//token found (or uneeded for /auth)
		$_USER['id'] = intval($token['user_id']);
		$body = file_get_contents('php://input');
		
		//define globals corresponding each request type (coherence with $_GET and $_POST)

		if ($method === 'PUT') {
			if (isJSON($body))
				$_PUT = json_decode($body, true);
			else
				parse_str($body, $_PUT);
		} else if ($method === 'DELETE') {
			if (isJSON($body))
				$_DELETE = json_decode($body, true);
			else
				parse_str($body, $_DELETE);
		} else if ($method === 'POST') {
			if (isJSON($body))
				$_POST = json_decode($body, true);
			else
				parse_str($body, $_POST);
		}

		//include the processing
		include "routes/" . $queryName . ".php";
		
	} else {
		echo json_encode(['err' => 'Unable to id user with token']);
	}
}
?>