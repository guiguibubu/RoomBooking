<?php

/*
 * Included everywhere by index.php 
 * 
 * sets errors on
 * creates a global $db for any db operation (PDO instance)
 * */

ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);

$db = new PDO("pgsql:dbname=jic;host=localhost", "jic", "yYchCcCa");


?>