<?php
session_start();
include('connect_firebase.php');

if(isset($_GET['search'])){
    $search_text = $_GET['search_text'];

    if($search_text == "\n"){
        if(isset($_SESSION['searched'])){
            unset($_SESSION['searched']);
        }
        header('Location: index.php');
        exit;
    }
    else{
        $rices_snapshot = $database->getReference('rices')->orderByChild('title')->equalTo($search_text)->getSnapshot();
        $rices = $rices_snapshot->getValue();

        if($rices > 0){
            $_SESSION['searched'] = $rices;
            header('Location: index.php');
            exit;
        }
        else{
            $_SESSION['error'] = 'No resault';
            unset($_SESSION['searched']);
            header('Location: index.php');
            exit;
        }
    }
}
?>