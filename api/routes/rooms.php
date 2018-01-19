<?php


if ($method === 'GET') {
	if (empty($_GET)) {
		//called with parameters

		$req = $db -> query('SELECT * FROM rooms;');
		$out = $req -> fetchAll(PDO::FETCH_ASSOC);

	} elseif (!empty($_GET['id'])) {
		//called with ?id=xx
		//get the room and all of its bookings
		$data = $db -> query('SELECT * FROM rooms LEFT OUTER JOIN bookings ON (bookings.room_id = rooms.id) WHERE rooms.id = ' . intval($_GET['id'])) -> fetchAll(PDO::FETCH_ASSOC);
		$out = extractFields($data, ['name', 'type', 'capacity', 'comment', 'floor', 'building', 'location'], ['width', 'length', 'room_id']);

		if (!empty($data[0]['id']))
			$out['bookings'] = $data;
		else
			$out['bookings'] = [];
	}

	echo json_encode($out);

} elseif ($method === 'POST') {
	//update any field for a given room
	if(!check(['GET' => ['id']])) {
		echo json_encode(['done' => false, 'info' => 'Missing GET parameter id']);
		exit;
	}
	
	$id = intval($_GET['id']);
	$fieldsToUpdate = [];
	$fields = ['name', 'type', 'capacity', 'width', 'length', 'comment', 'floor', 'building', 'location'];
	$values = [];
	foreach ($_POST as $field => $value) {
		if (in_array($field, $fields)) {
			$fieldsToUpdate[] = $field;
			$values[] = htmlspecialchars($value);
		}
	}

	$prep = $db -> prepare(genUpdateSQL('rooms', $fieldsToUpdate, $id)); //function in misc tools because it could be reused

	echo json_encode(array('done' => ($prep -> execute($values))));

}
?>
