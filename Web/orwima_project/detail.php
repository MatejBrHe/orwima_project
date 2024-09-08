<?php
include('templates/header.php');
include('templates/navbar.php');
?>
<link rel="stylesheet" href="styles/detailStyle.css"/>

<?php

include('connect_firebase.php');

if(isset($_GET['id'])){
    $rice_key = $_GET['id'];
    $rice = $database->getReference('rices')->getChild($rice_key)->getValue();
}

?>

<main>
    <a href="index.php">Back to homepage</a>
    <div class="info-box">
    <div class="title centerHorizontal">
        <h1><?= $rice['title'] ?></h1>
        <h2>By <?= $rice['createdBy'] ?></h2>
    </div>

    <div class="images centerHorizontal">
        <div class="image-gallery centerHorizontal">
            <img src=<?= $rice['img_path'] ?> alt=<?= $rice['title'] ?> height="400" width="700">
        </div>
        <a href=<?= $rice['repo'] ?>>Get dotfiles here</a>
    </div>

    <div class="comment-section centerHorizontal">
        <div>
        <h3>Comments:</h3>
        <?php if(isset($_SESSION['users_session_id'])): ?>
        <a href="addComment.php?id=<?= $rice_key ?>"><button class="btn">Add comment</button></a>
        <?php endif; ?>
        </div>
        <ul>
            <?php
            include('connect_firebase.php');
            $comments_snapshot = $database->getReference('comments')->orderByChild('onRice')->equalTo($rice_key)->getSnapshot();
            $comments = $comments_snapshot->getValue();
            
            foreach($comments as $comment):
            ?>
            <li><div class="comment"><p class="username"><?= $comment['createdBy'] ?>:</p><p class="comment-text"><?= $comment['text'] ?></p></div></li>
            <?php
            endforeach;
            ?>
        </ul>
    </div>
    </div>
</main>
<?php
include('templates/footer.php');
?>