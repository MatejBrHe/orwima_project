<?php
	require __DIR__.'/vendor/autoload.php';

	use Kreait\Firebase\Factory;

	$firebase = (new Factory)
		->withServiceAccount('orwima-project-mbh-firebase-adminsdk-dsirb-565892b115.json')  				//This json file has been removed from the repository because Google disables access to a firebase databes if it is uploaded to public repository.
		->withDatabaseUri('https://orwima-project-mbh-default-rtdb.europe-west1.firebasedatabase.app/');	//To access the database you need to create your own json file inside the firebase web interface and paste the name here.

	$database = $firebase->createDatabase();

?>
