<?php
include('templates/header.php');
include('templates/navbar.php');
?>
<link rel="stylesheet" href="styles/indexStyle.css"/>

<main>
    <div class="search centerHorizontal">
        <input type="search" placeholder="Search">
    </div>
    <div class="buttons centerHorizontal">
        <input type="button" value="All" class="centerHorizontal">
        <input type="button" value="New" class="centerHorizontal">
        <input type="button" value="Popular" class="centerHorizontal">
    </div>
    <ul class="imgList centerHorizontal">
        <li class="imgListEl">
            <a href="detail.php">
                <div class="card">
                    <img src="images/Wallpaper3.png" alt="placeholder" height="351" width="624" class="cardImg"><br>
                    <h3 class="cardTitle">Title</h3>
                </div>
            </a>
        </li>
        <li class="imgListEl">
            <a href="detail.php">
                <div class="card">
                    <img src="images/Wallpaper3.png" alt="placeholder" height="351" width="624" class="cardImg"><br>
                    <h3 class="cardTitle">Title</h3>
                </div>
            </a>
        </li>
        <li class="imgListEl">
            <a href="detail.php">
                <div class="card">
                    <img src="images/Wallpaper3.png" alt="placeholder" height="351" width="624" class="cardImg"><br>
                    <h3 class="cardTitle">Title</h3>
                </div>
            </a>
        </li>
        <li class="imgListEl">
            <a href="detail.php">
                <div class="card">
                    <img src="images/Wallpaper3.png" alt="placeholder" height="351" width="624" class="cardImg"><br>
                    <h3 class="cardTitle">Title</h3>
                </div>
            </a>
        </li>
    </ul>
</main>
<?php
include('templates/footer.php');
?>