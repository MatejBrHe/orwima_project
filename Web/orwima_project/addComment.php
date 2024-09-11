<?php
session_start();
include('templates/header.php');
include('templates/navbar.php');
?>

<link rel="stylesheet" href="styles/addComment.css"/>

<main>
    <div class="centerHorizontal main_div">
        <form method="post" action="create_comment.php" class="centerHorizontal">
            <label class="centerHorizontal">Add comment:</label>
            <div class="textInput">
                <textarea name="comment_text" id="comment_text" class="form-control" rows="10"></textarea>
            </div>
            <input type="hidden" name="username" value=<?= $_SESSION['username'] ?>>
            <input type="hidden" name="rice_key" value=<?= $_GET['id'] ?>>
            <input type="submit" name="add_comment" value="Add" class="centerHorizontal submitBtn" id="submit">
        </form>
    </div>
</main>

<?php
include('templates/footer.php');
?>