<?php
	include('settings.php');

	$conn = new mysqli(HOST, USER, PASS, DB);
	$conn->set_charset("utf8");

	$countryId = $_POST["countryId"];

	$stationsQuery = $conn->query("CALL getCountryStations('$countryId')");
	echo '<option value="all">Todos</option>';
	foreach($stationsQuery as $row){
		echo '<option value="'.$row["idStation"].'">'.$row["stationName"].'</option>';
	}


?>