<?php
if ($method === 'GET') {
	$data = $db -> query('SELECT *, b.id AS bid, r.id AS rid FROM bookings AS b JOIN rooms AS r ON (r.id = b.room_id) WHERE b.end > NOW() AND b.user_id = ' . $_USER['id']) -> fetchAll(PDO::FETCH_ASSOC);
	$out = [];
	foreach ($data as $booking) {
		$tmp = [];
		$room = [];
		$roomFields = ['name', 'type', 'capacity', 'comment', 'floor', 'building', 'location', 'rid'];
		$ignoredFields = ['room_id', 'width', 'length', 'id'];
		foreach ($booking as $key => $value) {

			if (in_array($key, $roomFields)) {
				$room[$key] = $value;
			} elseif (!in_array($key, $ignoredFields)) {
				$tmp[$key] = $value;
			}
		}
		$tmp['id'] = $tmp['bid'];
		$room['id'] = $room['rid'];
		unset($room['rid']);
		unset($tmp['bid']); 
		$tmp['room'] = $room;
		$out[] = $tmp;

	}

	//$data[0]['room'] = $out;
	echo json_encode($out);
}
?>
