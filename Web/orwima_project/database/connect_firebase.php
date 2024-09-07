<?php
	require(__DIR__.'/vendor/autoload.php');
	use Kreait\Firebase\Factory;
	
	$firebase = (new Factory)
		->withServiceAccount('orwima-project-mbh-firebase-adminsdk-dsirb-5d27941997.json')
		->withDatabaseUri('https://orwima-project-mbh-default-rtdb.europe-west1.firebasedatabase.app/');

	$database = $firebase->getDatabase();
?>
