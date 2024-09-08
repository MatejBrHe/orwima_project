<?php
session_start();

unset($_SESSION['users_session_id']);
unset($_SESSION['username']);
header('Location: index.php');
exit;
?>