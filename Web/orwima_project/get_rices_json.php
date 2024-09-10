<?php
include("connect_firebase.php");

$rices_snapshot = $database->getReference('rices')->getSnapshot();
$rices = $rices_snapshot->getValue();
$data = [];
foreach($rices as $key => $rice){
    $data[] = [
        "id" => $key,
        "createdBy" => $rice['createdBy'],
        "title" => $rice['title'],
        "repo" => $rice['repo'],
        "img_path" => $rice['img_path'],
    ];
}

$json = json_encode($data);
echo "{rices:".$json."}";
?>