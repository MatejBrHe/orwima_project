<?php
session_start();
include('connect_firebase.php');

if(isset($_POST['add_comment'])){
    $username = $_POST['username'];
    $text = $_POST['comment_text'];
    $rice_key = $_POST['rice_key'];

    $comment_info = [
        'createdBy' => $username,
        'text' => $text,
        'onRice' => $rice_key,
    ];

    $result = $database->getReference('comments')->push($comment_info);

    if($result){
        header('Location: detail.php?id='.$rice_key);
        exit;
    }
    else{
        $_SESSION['error'] = 'Failed to create comment';
        header('Location: create_comment.php?id='.$rice_key);
        exit;
    }
}
else{
    $_SESSION['error'] = 'Failed to create comment';
    header('Location: create_comment.php?id='.$rice_key);
    exit;
}
?>