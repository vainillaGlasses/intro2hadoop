<?php
	include('settings.php');

	$conn = new mysqli(HOST, USER, PASS, DB);
	$conn->set_charset("utf8");

	$continentsQuery = $conn->query('CALL getContinents()');
	echo '<option value="all">Todos</option>';
	foreach($continentsQuery as $row){
		echo '<option value="'.$row["idContinent"].'">'.$row["continent"].'</option>';
	}


?>