<?php
	require __DIR__.'/vendor/autoload.php';

	use Kreait\Firebase\Factory;

	$firebase = (new Factory)
		->withServiceAccount('orwima-project-mbh-firebase-adminsdk-dsirb-565892b115.json')
		->withDatabaseUri('https://orwima-project-mbh-default-rtdb.europe-west1.firebasedatabase.app/');

	$database = $firebase->createDatabase();

?>
