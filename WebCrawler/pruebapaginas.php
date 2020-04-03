<?php
include_once('simple_html_dom.php');
function crawl($link){
	$myUrl = file_get_html($link);
	$link = $myUrl->find('p',0);
	echo $link->find('strong',0)->plaintext;
}

function validation($url){
    $h = get_headers($url);
    $status = array();
    preg_match('/HTTP\/.* ([0-9]+) .*/', $h[0] , $status);
    echo $status[1];
}

function url_repeater($link){
	$myUrl = file_get_html($link);
	$i = 0;
	while ($myUrl == false && $i != 5){
		$myUrl = file_get_html($link);
		$i++;
	}
	return $myUrl;
}

//validation('http://en.tutiempo.net/climate/ws-156110.html');
//echo file_get_contents('http://en.tutiempo.net/climate/ws-105670.html');
var_dump(url_repeater('http://en.tutiempo.net/climate/us-virgin-islands.html'));
?>