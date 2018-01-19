<?php

$df = 'Y-m-d H:i:s'; //date format we'll be using everywhere (postgres timestamp format)

if ($method === 'GET') {
	//get infos about a booking (with its room)
	
	if (!check(['GET' => ['id']])) {
		echo json_encode(['done' => false, 'info' => 'Missing GET parameter id']);
		exit ;
	}
	
	//get infos and split them between room and booking
	$bookingId = $_GET['id'];
	$data = $db -> query('SELECT * FROM bookings JOIN rooms ON (rooms.id = bookings.room_id) WHERE bookings.id = ' . $bookingId) -> fetchAll(PDO::FETCH_ASSOC);
	$out = extractFields($data, ['name', 'type', 'capacity', 'comment', 'floor', 'building', 'location', 'id'], ['room_id', 'width', 'length']);
	$data[0]['room'] = $out;
	echo json_encode($data[0]);

} elseif ($method === 'DELETE') {
	//delete a booking
	//if it has started : update end date
	//if it has not started : delete completely
	
	if (!check(['GET' => ['id']])) {
		echo json_encode(['done' => false, 'info' => 'Missing GET parameter id']);
		exit ;
	}

	$bookingId = intval($_GET['id']);
	$data = $db -> query('SELECT * FROM bookings WHERE id = ' . $bookingId . ' AND user_id = ' . $_USER['id']) -> fetch(PDO::FETCH_ASSOC);

	if (strtotime(date($df)) < strtotime($data['end'])) { //check if booking is still live

		if (strtotime(date($df)) < strtotime($data['start'])) { //not started
			$prep = $db -> prepare('DELETE FROM bookings WHERE id = ? AND user_id = ?');
			$res = $prep -> execute([$bookingId, $_USER['id']]);
		} else { //started
			$prep = $db -> prepare('UPDATE bookings SET "end" = ? WHERE id = ? AND user_id = ?');
			$res = $prep -> execute([date($df), $bookingId, $_USER['id']]);

		}
		echo json_encode(['done' => $res]);

	} else {
		echo json_encode(['info' => 'This booking has already ended', 'done' => false]);

	}

} elseif ($method === 'POST') {
	
	//Update booking (modify start or end date)
	
	if (!check(['GET' => ['id'], 'POST' => ['start', 'end']])) {
		echo json_encode(['done' => false, 'info' => 'Missing GET parameter id or POST params start or end']);
		exit ;
	}

	$start = strtotime($_POST['start']);
	$end = strtotime($_POST['end']);

	if ($start > $end) { //coherence check
		echo json_encode(['done' => false, 'info' => 'Start date after end date !']);
		exit ;
	}

	// check if times are not overlapping another booking
	
	$bookings = [];
	$bookingId = intval($_GET['id']);

	$req = $db -> query('SELECT * FROM bookings WHERE id = ' . $bookingId);
	$current = $req -> fetch(PDO::FETCH_ASSOC);

	$roomId = $current['room_id'];

	$req = $db -> query('SELECT * FROM bookings WHERE id <> ' . $bookingId . ' AND room_id = ' . $roomId);

	while ($data = $req -> fetch(PDO::FETCH_ASSOC)) {
		$data['start'] = strtotime($data['start']);
		$data['end'] = strtotime($data['end']);
		$bookings[] = $data;
	}

	$pass = 0;
	foreach ($bookings as $booking) {
		if ($booking['start'] <= $end && $booking['end'] >= $start)
			$pass = $booking['id'];
	}
	
	//success 

	if ($pass === 0) {
		$prep = $db -> prepare('UPDATE bookings SET start = ?, "end" = ? WHERE id = ?'); //end in quotes because it is a SQL keyword ...
		$res = $prep -> execute([date($df, $start), date($df, $end), $bookingId]);
		echo json_encode(array('done' => $res));
	} else {
		echo json_encode(array('info' => 'Overlapping date on an existing reservation (id: ' . $pass . ')', 'done' => false));
	}

} elseif ($method === 'PUT') {
	//create a booking
	//pretty much the same as for POST

	if (!check(['GET' => ['room'], 'PUT' => ['start', 'end']])) {
		echo json_encode(['done' => false, 'info' => 'Missing GET parameter room or PUT params start or end']);
		exit ;
	}

	$start = strtotime($_PUT['start']);
	$end = strtotime($_PUT['end']);

	if ($start > $end) {
		echo json_encode(['done' => false, 'info' => 'Start date after end date !']);
		exit ;
	}

	$roomId = intval($_GET['room']);
	$bookings = [];

	$req = $db -> query('SELECT * FROM bookings WHERE room_id = ' . $roomId);

	while ($data = $req -> fetch(PDO::FETCH_ASSOC)) {
		$data['start'] = strtotime($data['start']);
		$data['end'] = strtotime($data['end']);
		$bookings[] = $data;
	}

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
