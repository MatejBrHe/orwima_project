<?php
session_start();
include('connect_firebase.php');

if(isset($_POST['sign_up'])){
    $username = $_POST['username'];
    $email = $_POST['email'];
    $password = $_POST['password'];
    $password_confirm = $_POST['password_confirm'];

    if($password == $password_confirm){
        $password_hash = hash('sha256', $password);

        $user_info = [
            'username' => $username,
            'email' => $email,
            'password' => $password_hash,
        ];

        $result = $database->getReference('users')->push($user_info);

        if($result){
            header('Location: login.php');
            exit;
        }
        else{
            $_SESSION['error'] = 'Failed to create the user';
            header('Location: signup.php');
            exit;
        }
    }
    else{
        $_SESSION['error'] = 'Passwords do not match';
        header('Location: signup.php');
        exit;
    }
}
else{
    $_SESSION['error'] = 'Failed to create the user';
    header('Location: index.php');
    exit;
}
?>