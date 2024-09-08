<?php
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
        <form method="post" action="database/user_login.php">
            <input type="text" name="username" placeholder="Username" required>
            <input type="password" name="password" placeholder="Password" required>
            <input type="submit" name="login" value="Login" class="centerHorizontal">
        </form>
        <p class="centerHorizontal">Don't have an account? Sign up <a href="signup.php">here</a>.</p>
    </div>
</main>
<?php
include('templates/footer.php');
?>