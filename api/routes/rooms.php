<?php

function stripRoom($roomRow) {
	$out = array();

}

$fields = ['name', 'type', 'capacity', 'width', 'length', 'comment', 'floor', 'building', 'location'];

if ($method === 'GET') {
	if (empty($_GET)) {

		$req = $db -> query('SELECT * FROM rooms;');
		$data = $req -> fetchAll(PDO::FETCH_ASSOC);

	} elseif (!empty($_GET['id'])) {

		$req = $db -> query('SELECT * FROM rooms WHERE id = ' . intval($_GET['id']));
		$data = $req -> fetch(PDO::FETCH_ASSOC);

	}

	echo json_encode($data);

} elseif ($method === 'POST') {
	
	//can update any field for a given room

	$id = intval($_GET['id']);
	$fieldsToUpdate = [];
	$values = [];

	foreach ($_POST as $field => $value) {
		if (in_array($field, $fields)) {
			
			$fieldsToUpdate[] = $field . ' = ?';
			$values[] = htmlspecialchars($value);
			
		}
	}

	$queryStr = implode(',', $fieldsToUpdate);

	$prep = $db -> prepare('UPDATE rooms SET ' . $queryStr . ' WHERE id = ' . $id);
	$prep -> execute($values);

}
?>
