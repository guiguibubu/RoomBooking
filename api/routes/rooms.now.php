<?php

if ($method === 'GET') {
	$rooms = $db->query('SELECT * FROM rooms;')->fetchAll(PDO::FETCH_ASSOC);
	$bookings = $db->query('SELECT * FROM bookings WHERE start < now() AND "end" > now();')->fetchAll(PDO::FETCH_ASSOC);
	
	$usedRooms = [];
	$freeRooms = [];
	
	foreach ($bookings as $booking) {
		$usedRooms[$booking['room_id']] = true; 
	}
	
	foreach ($rooms as $room) {
		if(empty($usedRooms[$room['id']]))
			$freeRooms[] = $room;
	}
	
	shuffle($freeRooms);
	
	echo json_encode($freeRooms, true);

}
	
?>
