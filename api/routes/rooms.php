<?php
$fields = ['name', 'type', 'capacity', 'width', 'length', 'comment', 'floor', 'building', 'location'];

if ($method === 'GET') {
	if (empty($_GET)) {

		$req = $db -> query('SELECT * FROM rooms;');
		$out = $req -> fetchAll(PDO::FETCH_ASSOC);

	} elseif (!empty($_GET['id'])) {

		$data = $db -> query('SELECT * FROM rooms LEFT OUTER JOIN bookings ON (bookings.room_id = rooms.id) WHERE rooms.id = ' . intval($_GET['id'])) -> fetchAll(PDO::FETCH_ASSOC);
		$out = extractFields($data, ['name', 'type', 'capacity', 'comment', 'floor', 'building', 'location'], ['width', 'length', 'room_id']);

		if (!empty($data[0]['id']))
			$out['bookings'] = $data;
		else
			$out['bookings'] = [];
	}

	echo json_encode($out);

} elseif ($method === 'POST') {
	//can update any field for a given room
	$id = intval($_GET['id']);
	$fieldsToUpdate = [];
	$values = [];
	foreach ($_POST as $field => $value) {
		if (in_array($field, $fields)) {
			$fieldsToUpdate[] = $field;
			$values[] = htmlspecialchars($value);
		}
	}

	$prep = $db -> prepare(genUpdateSQL('rooms', $fieldsToUpdate, $id));

	echo json_encode(array('done' => ($prep -> execute($values))));

}
?>
