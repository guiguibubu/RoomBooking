<?php

/*
 * Router for api requests
 */

header('Content-Type: application/json');

include "tools/bdd.php";
include "tools/misc.php";


$query = strtok($_SERVER['REQUEST_URI'], '?');
$method = $_SERVER['REQUEST_METHOD'];

$query = explode('/', $query);




array_shift($query);
//get rid of first /

array_shift($query);
//get rid of api prefix

if(!file_exists("routes/" . implode('.', $query) . ".php")) {
	echo json_encode(['error' => 'This path does not exists']);
	exit;
}

$_USER = [];

$tokenNeeded = ['bookings.my'];

if ($method === 'GET' && !in_array(implode('.', $query), $tokenNeeded)) {
	include "routes/" . implode('.', $query) . ".php";
} else {

	$token = !empty($_SERVER['HTTP_X_TOKEN']) ? $_SERVER['HTTP_X_TOKEN'] : '';

	$req = $db -> prepare('SELECT user_id FROM tokens WHERE token = ?');
	$req -> execute(array($token));
	$token = $req -> fetch(PDO::FETCH_ASSOC);

	if ($token['user_id'] || $query[0] === 'auth') {

		$_USER['id'] = intval($token['user_id']);
		$body = file_get_contents('php://input');

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

		include "routes/" . implode('.', $query) . ".php";
		
	} else {
		echo json_encode(['err' => 'Unable to id user with token']);
	}
}
?>