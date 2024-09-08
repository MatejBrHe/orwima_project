<?php
session_start();
include('templates/header.php');
include('templates/navbar.php');
?>

<?php if(isset($_SESSION['error'])): ?>
<script>
    alert("<?= $_SESSION['error']; ?>");
</script>
<?php unset($_SESSION['error']); endif; ?>

<link rel="stylesheet" href="styles/login-signup.css"/>

<main>
    <a href="index.php">Back to homepage</a>
    <div class="login">
        <form method="post" action="user_signup.php">
            <input type="text" name="username" placeholder="Username" required>
            <input type="email" name="email" placeholder="E-mail" required>
            <input type="password" name="password" placeholder="Password" required>
            <input type="password" name="password_confirm" placeholder="Confirm password" required>
            <input type="submit" name="sign_up" value="Sign up" class="centerHorizontal">
        </form>
    </div>
</main>
<?php
include('templates/footer.php');
?>