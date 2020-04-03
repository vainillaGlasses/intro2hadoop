<?php
	include('settings.php');

	$jsonData["data"] = array();

	if ($_POST["first"] == "true"){
		echo json_encode($jsonData);
	} else {
		$continentId = $_POST["continentId"] == "all" ? "null" : $_POST["continentId"];
		$countryId = $_POST["countryId"] == "all" ? "null" : $_POST["countryId"];
		$variableId = $_POST["variableId"] == "all" ? "null" : $_POST["variableId"];
		$maxMinOpt = $_POST["maxMin"] == "both" ? "null" : "'".$_POST["maxMin"]."'";
		$conn = new mysqli(HOST, USER, PASS, DB);
		$conn->set_charset("utf8");
		$continentsQuery = $conn->query("CALL getYearMaxMinVars($continentId, $countryId, $variableId, $maxMinOpt)");

		foreach($continentsQuery as $row){
			$tableRow = array();
			array_push($tableRow, $row["continent"]);
			array_push($tableRow, $row["country"]);
			array_push($tableRow, $row["year"]);
			array_push($tableRow, $row["var"]);
			array_push($tableRow, $row["value"]);
			array_push($tableRow, $row["MaxMin"]);
			array_push($jsonData["data"], $tableRow);
		}
		echo json_encode($jsonData);
	}


?>