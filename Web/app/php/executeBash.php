<?php
	ini_set('display_errors', 1);
	ini_set('display_startup_errors', 1);
	error_reporting(E_ALL);
	$old_path = getcwd();
	chdir('/var/www/html/HadoopClimate/');
	$output = shell_exec('bash auto.sh');
	chdir($old_path);
	echo var_dump($output);
	
?>