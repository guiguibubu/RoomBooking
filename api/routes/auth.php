<?php
if ($method === 'POST') {
	//log in
	
	//check for all parameters needed to process the request (see function check() in ../tools/misc.php)
	if(!check(['POST' => ['username', 'password']])) {
		echo json_encode(['done' => false, 'info' => 'POST parameters password or login missing']);
		exit;
	}
	
	$username = $_POST['username'];
	$password = $_POST['password'];
	
	//retrieve user infos from login (UNIQUE col)
	$req = $db -> prepare('SELECT * FROM users WHERE login = ?');
	$req -> execute([$username]);
	$row = $req -> fetch(PDO::FETCH_ASSOC);

	//password is already stored as a sha256 in db, salt it with the login
	$challenge = hash('sha256', $row['login'] . ':' . $row['password']);

	//client should meet the challenge by submitting the right string
	//this method is subject to replay attacks as the challenge is always the same
	//a good implementation would be based on a nonce given by the server, in a 2-way handshake via HTTP
	//but no time and increased complexity for the client side
	if ($challenge === $password) {
		//create and save token, linking a random string the the user id
		$token = hash('sha256', bin2hex(random_bytes(16)));
		$db -> prepare('INSERT INTO tokens (user_id, token) VALUES (?, ?)') -> execute([$row['id'], $token]);
		echo json_encode(['done' => true, 'token' => $token, 'displayName' => $row['firstname'] . ' ' . strtoupper($row['lastname'])]);

	} else {
		echo json_encode(['done' => false, 'info' => 'Wrong password or login']);
	}

} else if ($method === 'PUT') {
	//sign up
	
	if(!check([ 'PUT' => ['username', 'password', 'firstname', 'lastname']])) {
		echo json_encode(['done' => false, 'info' => 'PUT parameters password, login, firstname or lastname missing']);
		exit;
	}
	
	$username = $_PUT['username'];
	$password = $_PUT['password'];

	$firstname = htmlspecialchars($_PUT['firstname']);
	$lastname = htmlspecialchars($_PUT['lastname']);
	
	//insert user in db (password is a sha256 of the actual password)
	$req = $db -> prepare('INSERT INTO users (login, password, firstname, lastname) VALUES (?,?,?,?);');
	$res = $req -> execute([$username, $password, $firstname, $lastname]);
	$userId = $db -> lastInsertId();

	if ($res) {

		//immediately log in the user by creating his token
		$token = hash('sha256', bin2hex(random_bytes(16)));
		$ins = $db -> prepare('INSERT INTO tokens (user_id, token) VALUES (?, ?)') -> execute([$userId, $token]);

		echo json_encode(['done' => true, 'token' => $token, 'displayName' => $firstname . ' ' . strtoupper($lastname)]);
	} else {
		echo json_encode(['done' => false, 'info' => 'Duplicated user.']);
	}

}
?>