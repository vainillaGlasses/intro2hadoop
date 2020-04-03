<?php 
include('simple_html_dom.php');
// *****************************************************************//
function writeTXT($pLink, $pText){
  $file="/var/www/html/HadoopClimate/WebCrawler/data.txt";
  $myfile = fopen($file, "w") or die("Unable to open file!");
  
  $pText = preg_split("/[\s,:';\.]+/", $pText);

  for($i=0; $i < count($pText);$i++){
    $value = $pText[$i]."\t".$pLink;
    fwrite($myfile, $value. PHP_EOL);
  }

  fclose($myfile);
}
// *****************************************************************//
function readData($url){
    $doc = new DOMDocument();
    libxml_use_internal_errors(true);
    $doc->loadHTMLFile($url);
    $tables = $doc->getElementsByTagName('table');
    echo $table;
    
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