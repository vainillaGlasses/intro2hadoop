<?php 
error_reporting(E_ALL);
include('simple_html_dom.php');

function printData($myFileName){
	echo "myfile is $myFileName <br><br>";
	$myfile = fopen($myFileName, "r") or die("Unable to open file!");
	while(!feof($myfile)) {
	  $myLine = fgets($myfile) . "<br>";
		echo $myLine;
	}
	fclose($myfile);
}

function validationLink($url){
    $h = get_headers($url);
    $status = array();
    preg_match('/HTTP\/.* ([0-9]+) .*/', $h[0] , $status);
    return ($status[1] == 200);
}

function readData($url){
    $doc = new DOMDocument();
    libxml_use_internal_errors(true);
    $doc->loadHTMLFile($url);
    $tables = $doc->getElementsByTagName('div');
    var_dump($tables);
}
// *****************************************************************//
function start($url){
	if (validationLink($url)==1) {
		echo 'Getting Data';
		readData($url);
	}else{
		echo 'Error: Validation Negative';
	}

	echo "Data in File\n";
	printData("data.txt");
	echo "==FIN==";
}
// *****************************************************************//

start('http://en.tutiempo.net/climate');
 ?>