<?php
	include('settings.php');

	$conn = new mysqli(HOST, USER, PASS, DB);
	$conn->set_charset("utf8");

	$continentId = $_POST["continentId"];

	$continentsQuery = $conn->query("CALL getContinentStations('$continentId')");
	echo '<option value="all">Todos</option>';
	foreach($continentsQuery as $row){
		echo '<option value="'.$row["idStation"].'">'.$row["stationName"].'</option>';
	}


?>