<?php
session_start();
include('connect_firebase.php');

if(isset($_POST['login'])){
    $username = $_POST['username'];
    $password = $_POST['password'];

    $password_hash = hash('sha256', $password);

    $user_snapshot = $database->getReference('users')->orderByChild('username')->equalTo($username)->getSnapshot();
    $users = $user_snapshot->getValue();

    if($users > 0){
        foreach($users as $key => $user){
            if($user['password'] == $password_hash){
                $_SESSION['users_session_id'] = hash('sha256', $user['username'].date("Y-m-d H:i:s"));
                $_SESSION['username'] = $user['username'];
                header('Location: index.php');
                exit;
            }
        }
        $_SESSION['error'] = 'Incorrect username or password';
        header('Location: login.php');
        exit;
    }
    else{
        $_SESSION['error'] = 'Failed to login';
        header('Location: login.php');
        exit;
    }
}
else{
    $_SESSION['error'] = 'Failed to login';
    header('Location: login.php');
    exit;
}
?>