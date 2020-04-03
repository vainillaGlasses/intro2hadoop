<?php
	ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);
	include('settings.php');

	$conn = new mysqli(HOST, USER, PASS, DB);
	$conn->set_charset("utf8");

	$continentsQuery = $conn->query('CALL getVariables()');
	echo '<option value="all">Todas</option>';
	foreach($continentsQuery as $row){
		echo '<option value="'.$row["idVariable"].'">'.$row["var"].'</option>';
	}


?>