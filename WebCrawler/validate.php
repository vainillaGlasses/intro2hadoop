<?php 
ini_set('display_error', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);

function validationLink($url){
    $h = get_headers($url);
    $status = array();
    preg_match('/HTTP\/.* ([0-9]+) .*/', $h[0] , $status);
    return ($status[1] == 200);
}

if(validationLink("http://en.tutiempo.net/climate")){
	echo "Good Connection";
}else{
	echo "No Connected";
}
?>