<?php
if ($method === 'POST') {
	$username = $_POST['username'];
	$password = $_POST['password'];

	$row = $db -> prepare('SELECT * FROM users WHERE login = ?') -> execute([$username]) -> fetch(PDO::FETCH_ASSOC);

	$challenge = hash('sha256', $row['login'] . ':' . $row['password']);

	if ($challenge === $password) {

		$token = hash('sha256', bin2hex(random_bytes(16)));
		$db -> prepare('INSERT INTO tokens (user_id, token) VALUES (?, ?)') -> execute([$row['id'], $token]);
		echo json_encode(['done' => true, 'token' => $token]);

	} else {
		echo json_encode(['done' => false, 'info' => 'Wrong password or login']);
	}

} else if ($method === 'PUT') {
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

		echo json_encode(array('done' => true, 'token' => $token));
	} else {
		echo json_encode(array('done' => false, 'info' => 'Duplicated user.'));
	}

}
?>