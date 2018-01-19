<?php
if ($method === 'POST') {
	
	if(!check(['POST' => ['username', 'password']])) {
		echo json_encode(['done' => false, 'info' => 'POST parameters password or login missing']);
		exit;
	}
	
	$username = $_POST['username'];
	$password = $_POST['password'];
	
	

	$req = $db -> prepare('SELECT * FROM users WHERE login = ?');
	$req -> execute([$username]);
	$row = $req -> fetch(PDO::FETCH_ASSOC);

	$challenge = hash('sha256', $row['login'] . ':' . $row['password']);

	if ($challenge === $password) {

		$token = hash('sha256', bin2hex(random_bytes(16)));
		$db -> prepare('INSERT INTO tokens (user_id, token) VALUES (?, ?)') -> execute([$row['id'], $token]);
		echo json_encode(['done' => true, 'token' => $token, 'displayName' => $row['firstname'] . ' ' . strtoupper($row['lastname'])]);

	} else {
		echo json_encode(['done' => false, 'info' => 'Wrong password or login']);
	}

} else if ($method === 'PUT') {
	
	if(!check([ 'PUT' => ['username', 'password', 'firstname', 'lastname']])) {
		echo json_encode(['done' => false, 'info' => 'PUT parameters password, login, firstname or lastname missing']);
		exit;
	}
	
	//can update any field for a given room
	$username = $_PUT['username'];
	$password = $_PUT['password'];

	$firstname = htmlspecialchars($_PUT['firstname']);
	$lastname = htmlspecialchars($_PUT['lastname']);

	$req = $db -> prepare('INSERT INTO users (login, password, firstname, lastname) VALUES (?,?,?,?);');
	$res = $req -> execute([$username, $password, $firstname, $lastname]);
	$userId = $db -> lastInsertId();

	if ($res) {

		$token = hash('sha256', bin2hex(random_bytes(16)));

		$ins = $db -> prepare('INSERT INTO tokens (user_id, token) VALUES (?, ?)') -> execute([$userId, $token]);

		echo json_encode(['done' => true, 'token' => $token, 'displayName' => $firstname . ' ' . strtoupper($lastname)]);
	} else {
		echo json_encode(['done' => false, 'info' => 'Duplicated user.']);
	}

}
?>