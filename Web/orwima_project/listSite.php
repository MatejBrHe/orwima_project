<?php
session_start();
include('templates/header.php');
include('templates/navbar.php');
?>
<link rel="stylesheet" href="styles/indexStyle.css"/>

<main>
    <a href="index.php">Back to homepage</a>

    <ul class="imgList centerHorizontal">
        <?php
        include('connect_firebase.php');
        $rices_snapshot = $database->getReference('rices')->getSnapshot();
        $rices = $rices_snapshot->getValue();

        foreach($rices as $key => $rice):
        ?>
        <?php if($rice['createdBy'] == $_SESSION['username']): ?>
        <li class="imgListEl">
            <a href="detail.php?id=<?= $key ?>">
                <div class="card">
                    <img src=<?= $rice['img_path'] ?> alt=<?= $rice['title'] ?> height="351" width="624" class="cardImg"><br>
                    <h3 class="cardTitle"><?= $rice['title'] ?></h3>
                </div>
            </a>
        </li>
        <?php endif; ?>
        <?php
        endforeach;
        ?>
    </ul>
</main>
<?php
include('templates/footer.php');
?>