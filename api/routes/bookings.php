<?php

$df = 'Y-m-d H:i:s'

if ($method === 'GET') {
	$bookingId = $_GET['id'];
	$data = $db -> query('SELECT * FROM bookings JOIN rooms ON (rooms.id = bookings.room_id) WHERE bookings.id = ' . $bookingId)-> fetchAll(PDO::FETCH_ASSOC);
	$out = extractFields($data, ['name', 'type', 'capacity', 'comment', 'floor', 'building', 'location', 'id'], ['room_id', 'width', 'length']);
	$data[0]['room'] = $out;
	echo json_encode($data[0]);

} elseif ($method === 'DELETE') {

	$bookingId = intval($_GET['id']);
	$data = $db -> query('SELECT * FROM bookings WHERE id = ' . $bookingId . ' AND user_id = ' . $_USER['id'])-> fetch(PDO::FETCH_ASSOC);

	if (strtotime(date($df)) < strtotime($data['end'])) {

		if (strtotime(date($df)) < strtotime($data['start'])) {
			$prep = $db -> prepare('DELETE FROM bookings WHERE id = ? AND user_id = ?');
			$res = $prep -> execute([$bookingId, $_USER['id']]);
		} else {
			$prep = $db -> prepare('UPDATE bookings SET "end" = ? WHERE id = ? AND user_id = ?');
			$res = $prep -> execute([date($df), $bookingId, $_USER['id']]);

		}
		echo json_encode(array('done' => $res));

	} else {
		echo json_encode(array('info' => 'This booking has already ended', 'done' => false));

	}

} elseif ($method === 'POST') {

} elseif ($method === 'PUT') {

	$roomId = intval($_GET['room']);
	$bookings = [];

	$req = $db -> query('SELECT * FROM bookings WHERE room_id = ' . $roomId);

	while ($data = $req -> fetch(PDO::FETCH_ASSOC)) {
		$data['start'] = strtotime($data['start']);
		$data['end'] = strtotime($data['end']);
		$bookings[] = $data;
	}

	$start = strtotime($_PUT['start']);
	$end = strtotime($_PUT['end']);

	$pass = 0;
	foreach ($bookings as $booking) {
		if ($booking['start'] <= $end && $booking['end'] >= $start)
			$pass = $booking['id'];
	}

	if ($pass === 0) {
		$prep = $db -> prepare('INSERT INTO bookings (user_id, room_id, "start", "end") VALUES (?, ?, ?, ?)');
		$res = $prep -> execute([$_USER['id'], $roomId, date($df, $start), date($df, $end)]);
		echo json_encode(array('done' => $res));
	} else {
		echo json_encode(array('info' => 'Overlapping date on an existing reservation (id: ' . $pass . ')', 'done' => false));
	}

}
?>
