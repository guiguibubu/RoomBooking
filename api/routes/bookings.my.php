<?php
if ($method === 'GET') {
	$data = $db -> query('SELECT * FROM bookings JOIN rooms ON (rooms.id = bookings.room_id) WHERE user_id = ' . $_USER['id']) -> fetchAll(PDO::FETCH_ASSOC);
	$out = extractFields($data, ['name', 'type', 'capacity', 'comment', 'floor', 'building', 'location', 'id'], ['room_id', 'width', 'length']);
	$data[0]['room'] = $out;
	echo json_encode($data[0]);
}
?>
