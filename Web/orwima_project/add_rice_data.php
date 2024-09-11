<?php
session_start();
include('connect_firebase.php');

if(isset($_POST['upload_data'])){

    $title = $_POST['titleInput'];
    $repo = $_POST['repoInput'];

    $img_dir = 'images/';
    $name = $_FILES['files']['name'];
    $tmp_name = $_FILES['files']['tmp_name'];
    $file_name = date("Y-m-d_H:i:s").str_replace(" ", "_", $name);

    if(move_uploaded_file($tmp_name, $img_dir.$file_name)){
        $rice_info = [
            "createdBy" => $_SESSION['username'],
            "title" => $title,
            "repo" => $repo,
            "img_path" => $img_dir.$file_name,
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
    else{
        $_SESSION['error'] = 'Failed to upload an image';
        header('Location: addRice.php');
        exit;
    }
}
else{
    $_SESSION['error'] = 'Failed to upload data';
    header('Location: addRice.php');
    exit;
}
?>