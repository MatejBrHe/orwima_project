<?php
include('templates/header.php');
include('templates/navbar.php');
?>
<link rel="stylesheet" href="styles/addRiceStyle.css"/>

<main>
    <a href="index.php">Back to homepage</a>
    <div class="data-input centerHorizontal">
        <form method="post" action="" enctype="multipart/form-data">
            <input type="text" placeholder="Title" class="input-text">
            <input type="text" placeholder="Github repository" class="input-text">
            <input type="file" name="inputImages" accept="image/*" multiple class="addImg">
            <input type="submit" value="Upload" class="centerHorizontal">
        </form>
    </div>
</main>
<?php
include('templates/footer.php');
?>