<?php
include('templates/header.php');
include('templates/navbar.php');
?>
<link rel="stylesheet" href="styles/detailStyle.css"/>

<main>
    <a href="index.php">Back to homepage</a>
    <div class="info-box">
    <div class="title">
        <h1>Title</h1>
        <h2>By Username</h2>
    </div>

    <div class="images centerHorizontal">
        <div class="image-gallery centerHorizontal">
            <img src="images/Wallpaper3.png" alt="placeholder" height="400" width="700">
            <img src="images/Wallpaper3.png" alt="placeholder" height="400" width="700">
            <img src="images/Wallpaper3.png" alt="placeholder" height="400" width="700">
            <img src="images/Wallpaper3.png" alt="placeholder" height="400" width="700">
        </div>
        <a href="">Get dotfiles here</a>
    </div>

    <div class="rating">
        <h3>Rating: 4.7</h3>
        <div class="buttons">
            <input type="button" value="1" class="centerHorizontal">
            <input type="button" value="2" class="centerHorizontal">
            <input type="button" value="3" class="centerHorizontal">
            <input type="button" value="4" class="centerHorizontal">
            <input type="button" value="5" class="centerHorizontal">
        </div>
    </div>

    <div class="comment-section">
        <h3>Comments:</h3>
        <ul>
            <li><div class="comment"><p class="username">Username:</p><p class="comment-text">This is the best rice I have ever seen. Congrats dude.</p></div></li>
            <li><div class="comment"><p class="username">Username:</p><p class="comment-text">Lorem ipsum, dolor sit amet consectetur adipisicing elit. Dolorum cupiditate eligendi consequuntur, quibusdam ratione doloribus vel quaerat dicta rerum esse, magnam, recusandae ducimus? Dolores molestias quaerat iusto, repudiandae commodi magni!</p></div></li>
            <li><div class="comment"><p class="username">Username:</p><p class="comment-text">Nice work.</p></div></li>
        </ul>
    </div>
    </div>
</main>
<?php
include('templates/footer.php');
?>