<?php
session_start();
include('connect_firebase.php');

if(isset($_POST['upload_data'])){

    $img_dir = 'images/';
    $name = $_FILES['imgInput']['name'];
    $tmp_name = $_FILES['imgInput']['tmp_name'];
    $file_name = hash('sha256', date("Y-m-d H:i:s").$name);

    $title = $_POST['titleInput'];
    $repo = $_POST['repoInput'];

    if(move_uploaded_file($tmp_name, $img_dir.$file_name)){
        $rice_info = [
            "createdBy" => $_SESSION['username'],
            "title" => $title,
            "repo" => $repo,
            "img_path" => 'images/'.$file_name,
        ];

        $result = $database->getReference('rices')->push($rice_info);

        if($result){
            header('Location: index.php');
            exit;
        }
        else{
            $_SESSION['error'] = 'Failed to create the rice';
            header('Location: addRice.php');
            exit;
        }
    }
}
else{
    $_SESSION['error'] = 'Failed to upload data';
    header('Location: addRice.php');
    exit;
}
?>