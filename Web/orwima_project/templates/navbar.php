<?php
session_start();
?>
<header>
    <h1>Ricing Library</h1>
    <?php if(isset($_SESSION['users_session_id'])): ?>
    <div id="user" class="user" onclick="showMenu(true)" style="">
        <h3 id="username" style=""><?= $_SESSION['username']; ?></h3>
    </div>
    <?php else: ?>
    <div class="user">
        <h3><a href="login.php">Login</a><h3>
    <div>
    <?php endif; ?>
</header>

<div class="shadow" onclick="showMenu(false)"></div>

<div class="userMenu-active">
    <ul>
        <li class="centerHorizontal"><a href="addRice.php">Add new rice</a></li>
        <li class="centerHorizontal"><a href="listSite.php">My rices</a></li>
        <?php if(isset($_SESSION['users_session_id'])): ?>
        <li class="centerHorizontal"><a href="user_logout.php">Logout</a></li>
        <?php else: ?>
        <li class="centerHorizontal"><a href="login.php">Login</a></li>
        <li class="centerHorizontal"><a href="signup.php">Sign up</a></li>
        <?php endif; ?>
    </ul>
</div>

<script>
    showMenu(false);

    function showMenu(menuDisplayed){
        if(!menuDisplayed){
            menuDisplayed = !menuDisplayed;
            document.getElementsByClassName("userMenu-active")[0].setAttribute("style", "display: none");
            document.getElementsByClassName("shadow")[0].setAttribute("style", "display: none");
            document.documentElement.style.overflow = "scroll";
        }
        else{
            menuDisplayed = !menuDisplayed;
            document.getElementsByClassName("userMenu-active")[0].setAttribute("style", "display: block");
            document.getElementsByClassName("shadow")[0].setAttribute("style", "display: block");
            document.documentElement.style.overflow = "hidden";
            scrollToTop();
        }
    }
</script>