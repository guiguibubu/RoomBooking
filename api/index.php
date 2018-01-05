<pre>
<?php

/*
 * Router for api requests
 *
 */

include "tools/bdd.php";
include "tools/json.php";
include "tools/misc.php";

$query = strtok($_SERVER['REQUEST_URI'], '?');
$method = $_SERVER['REQUEST_METHOD'];

$query = explode('/', $query);
//echo $method . ' ' . implode('/', $query);


 /*if($query[1] !== 'api') {

 }*/

array_shift($query); //get rid of first /
array_shift($query); //get rid of api prefix




//echo 'managed by : ' . "routes/" . implode('.', $query) . '.php';

include "routes/" . implode('.', $query) . ".php";

/*$json = json_decode(file_get_contents("salles.json"), true);

$prep = $db->prepare('INSERT INTO rooms(name, location, building, floor, comment) VALUES (?, ?, ?, ?, ?)');

foreach ($json['salles'] as $salle) {
	print_r($salle);
	echo "\n";
	
	$prep->execute(array($salle['name'], $salle['num'], $salle['bat'], $salle['floor'], $salle['info']));
}*/
?>
</pre>