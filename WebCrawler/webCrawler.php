<?php 
ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);

//Creado por Samantha Arburola y Kevin Lobo
include_once('simple_html_dom.php');

function url_repeater($link){
	$myUrl = file_get_html($link);
	$i = 0;
	while ($myUrl == false && $i != 5){
		$myUrl = file_get_html($link);
		$i++;
	}
	return $myUrl;
}

function get_variables($link, $string, $file){
	echo $link;
	$myUrl = url_repeater($link);
	if ($myUrl != false){
		$paragraph = $myUrl->find('p', 0);
		$station = $paragraph->find('strong', 0)->plaintext;
		$latitud = $paragraph->find('b', 0)->plaintext;
		$longitud = $paragraph->find('b', 1)->plaintext;
		$stationData = $station.';'.$latitud.';'.$longitud.';';

		foreach ($myUrl->find('a[title^=Climate]') as $myLink){
			$year = $myLink->plaintext;
			if ($year == "EN"){
				continue;
			}
			$actualElement = $myLink->parent()->parent();
			$variablesData = "";
			$variablesArray = $actualElement->children();
			unset($variablesArray[0]);
			foreach($variablesArray as $data){
				$variablesData = $variablesData.$data->plaintext.';';
			}
			if ($variablesData != "-;-;-;-;-;-;-;-;-;-;-;"){
				$variablesData = substr($variablesData, 0, -1);
				$variables = $stationData.$year.';'.$variablesData;
		  		fwrite($file, $string.$variables."\n");
		  	}
		}
	} else {
		$error = "Error: Connection 400 In: ";
		fwrite($file, $error.$link."\n");
	}
}

function crawl_stations($link, $string, $file){
	$myUrl = url_repeater($link);
	if ($myUrl != false){
		foreach ($myUrl->find('a[title^=Climate data:]') as $myLink){
			$station = $myLink->plaintext;
		  	$myLink1 = 'http://en.tutiempo.net'.$myLink->href;
		  	get_variables($myLink1, $string.$station.';', $file); 	
		}
		$nextPage = $myUrl->find('li.siguiente',0);
	  	if ($nextPage != null){
	  		$nextPageLink = $nextPage->find('a',0);
	  		$nextPageLink = 'http://en.tutiempo.net'.$nextPageLink->href;
	  		crawl_stations($nextPageLink, $string, $file);
	  	}
	} else {
		$error = "Error: Connection In: ";
		fwrite($file, $error.$link."\n");
	}
}

function crawl_countries($link, $string, $file){
	$myUrl = url_repeater($link);
	if ($myUrl != false){
		foreach ($myUrl->find('a[title^=Climate data:]') as $myLink){
			$country = $myLink->plaintext;
		  	$myLink1 = 'http://en.tutiempo.net'.$myLink->href;
		  	crawl_stations($myLink1, $string.$country.';', $file); 	
		}
	} else {
		$error = "Error: Connection In: ";
		fwrite($file, $error.$link."\n");	
	}
}

function crawl($link){
	$file = fopen('data.txt', 'a');
	$myUrl = file_get_html($link);
	foreach ($myUrl->find('a[title^=Historical weather data in]') as $myLink){
		$continent = $myLink->plaintext;
	  	$myLink1 = 'http://en.tutiempo.net'.$myLink->href;
	  	if ($continent != "Antarctica"){
	  		crawl_countries($myLink1, $continent.';', $file); 
	  	} else {
	  		crawl_stations($myLink1, $continent.';', $file); 	
		}
	}
}
crawl('http://en.tutiempo.net/climate/');
?>