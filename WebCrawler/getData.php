<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>My WebCrawler - Caeli</title>
</head>
<body>
<div>
		<?php 
		error_reporting(E_ALL);

		inlude('simple_html_dom.php');
		// include('simple_html_dom.php');
			function validation($url){
			    $h = get_headers($url);
			    $status = array();
			    preg_match('/HTTP\/.* ([0-9]+) .*/', $h[0] , $status);
			    return ($status[1] == 200);
			}
			function printFromFile($myFileName){
				echo "<br>file is $myFileName<br><br>";
				$myfile = fopen($myFileName, "r") or die("Unable to open file!");
				while(!feof($myfile)) {
				  $myLine = fgets($myfile) . "<br>";
					echo $myLine;
				}
				fclose($myfile);
			}

			function getLinks($myURL){
				inlude('simple_html_dom.php');

				$MyLinks = array();
				$html = file_get_html($myURL);
				$collection = $html->find('a');
				print_r(collection_values($collection));

				$file="/var/www/html/HadoopClimate/WebCrawler/data.txt";
				$myfile = fopen($file, "w") or die("Unable to open file!");

				for ($i = 0; $i <  count($collection); $i++) {
				    $key=key($collection);
				    $val=$collection[$key];
				    $val=$val->getAttribute('href');
				    if ($val<> ' ') {
				      fwrite($myfile, $val. PHP_EOL);
				      array_push($MyLinks, $val); 
				    }
				     next($collection);
				}
				fclose($myfile);
				return $MyLinks;
			}

			if(validation("http://en.tutiempo.net/climate")){
				echo "Good Connection";
			}else{
				echo "No Connected";
			}
			echo "<br>==== Data from file ====<br>";
			getLinks("http://en.tutiempo.net/climate");
			printFromFile("/var/www/html/HadoopClimate/WebCrawler/data.txt");
			echo "<br>========= FIN ==========<br>";

		?>
</div>
</body>
</html>
