<?php
include("connect_firebase.php");


if(isset($_GET['key'])){
    $rice_key = $_GET['key'];
    $comments_snapshot = $database->getReference('comments')->orderByChild('onRice')->equalTo($rice_key)->getSnapshot();
    $comments = $comments_snapshot->getValue();
    $data = [];
    foreach($comments as $key => $comment){
        $data[] = [
            "createdBy" => $comment['createdBy'],
            "text" => $comment['text'],
        ];
    }
    
    $json = json_encode($data);
    echo "{comments:".$json."}";
}
?>