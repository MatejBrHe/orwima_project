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

<link rel="stylesheet" href="styles/indexStyle.css"/>

<main>
    <div class="search centerHorizontal">
        <form method="get" action="search_content.php">
            <input type="search" name="search_text" placeholder="Search">
            <input type="submit" name="search" style="display: none;">
        <form>
    </div>
    <div class=""imgListDiv>
    <ul class="imgList centerHorizontal">
        <?php 
        if(isset($_SESSION['searched'])){ 
            $rices = $_SESSION['searched'];
            unset($_SESSION['searched']);
        } 
        else{
            include('connect_firebase.php');
            $rices_snapshot = $database->getReference('rices')->getSnapshot();
            $rices = $rices_snapshot->getValue();
        }
        foreach($rices as $key => $rice):
        ?>
            <li class="imgListEl">
                <a href="detail.php?id=<?= $key ?>">
                    <div class="card">
                        <img src=<?= $rice['img_path'] ?> alt=<?= $rice['title'] ?> height="351" width="624" class="cardImg"><br>
                        <h3 class="cardTitle"><?= $rice['title'] ?></h3>
                    </div>
                </a>
            </li>
        <?php
        endforeach;
        ?>
    </ul>
    </div>
</main>
<?php
include('templates/footer.php');
?>