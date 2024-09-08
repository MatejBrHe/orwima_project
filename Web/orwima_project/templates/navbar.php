<?php
session_start();
?>
<header>
    <h1>Ricing Library</h1>
    <?php if(isset($_SESSION['users_session_id'])): ?>
    <div id="user" class="user" onclick="toggleSideMenu()" style="">
        <h3 id="username" style=""><?= $_SESSION['username']; ?></h3>
    </div>
    <?php else: ?>
    <div class="user">
        <h3><a href="login.php">Login</a><h3>
    <div>
    <?php endif; ?>
</header>

<aside style="display: none;" id="sideMenu">
    <div>   
        <ul class="centerHorizontal">
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
</aside>

<script>
    function toggleSideMenu() {
        if(document.getElementById("sideMenu").style.display=="none") {
            document.getElementById("sideMenu").style.display="block";
        }
        else {
            document.getElementById("sideMenu").style.display="none";
        }
    }
</script>