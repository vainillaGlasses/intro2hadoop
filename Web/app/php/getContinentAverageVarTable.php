<?php
	include('settings.php');

	$jsonData["data"] = array();

	if ($_POST["first"] == "true"){
		echo json_encode($jsonData);
	} else {
		$continentId = $_POST["continentId"] == "all" ? "null" : $_POST["continentId"];
		$decade = $_POST["decade"] == "all" ? "null" : $_POST["decade"];
		$variableId = $_POST["variableId"] == "all" ? "null" : $_POST["variableId"];
		$conn = new mysqli(HOST, USER, PASS, DB);
		$conn->set_charset("utf8");
		$continentsQuery = $conn->query("CALL getContinentAverageVars($continentId, $decade, $variableId)");

		foreach($continentsQuery as $row){
			$tableRow = array();
			array_push($tableRow, $row["continent"]);
			array_push($tableRow, $row["decade"]);
			array_push($tableRow, $row["var"]);
			array_push($tableRow, $row["value"]);
			array_push($jsonData["data"], $tableRow);
		}
		echo json_encode($jsonData);
	}


?>