<?php 
ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);

/*
a) valida si el link esta disponible
b) muestra el textplaint de la pagina
c) para cada instancia de la etiqueta <a></a> va a mostrar el atributo href
*/

include_once('simple_html_dom.php');

function validation($url){
    $h = get_headers($url);
    $status = array();
    preg_match('/HTTP\/.* ([0-9]+) .*/', $h[0] , $status);
    return ($status[1] == 200);
}

echo "<br>Start Operation<br>";
if (validation('http://en.tutiempo.net/climate')) {
	$myUrl = file_get_html('http://tutiempo.net/clima');
	echo $myUrl->plaintext;
	echo "<br><br>All text</b><br><br><b>";
	foreach ($myUrl->find('a') as $myLink){
		echo "<br>element<br>";
	  	echo $myLink->href."\n";
	}} else {
	echo "Error: Connection.";
}
echo "<br>End Operation<br>";

?>