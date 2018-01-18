<?php

function res($data) {
	$type = gettype($data);
	echo $data;
	//if($type === "string")
	//echo json_encode()
}

function extractFields(&$data, $fields, $ignoredFields) {
	$out = [];
	foreach ($fields as $field) {
		$out[$field] = $data[0][$field];
		for ($i = 0; $i < count($data); $i++)
			unset($data[$i][$field]);
	}
	foreach ($ignoredFields as $field) {
		for ($i = 0; $i < count($data); $i++)
			unset($data[$i][$field]);
	}
	return $out;
}

function genUpdateSQL($table, $fields, $id) {
	$fieldsStr = [];
	$values = [];

	foreach ($fields as $field) {
		$fieldsStr[] = $field . ' = ?'; 
	}

	return 'UPDATE ' . $table . ' SET ' . implode(',', $fieldsStr) . ' WHERE id = ' . $id;
}



function isJSON($string) {
 json_decode($string);
 return (json_last_error() == JSON_ERROR_NONE);
}



?>