<?php
include('templates/header.php');
include('templates/navbar.php');
?>

<?php if(isset($_SESSION['error'])): ?>
<script>
    alert("<?= $_SESSION['error']; ?>");
</script>
<?php unset($_SESSION['error']); endif; ?>

<link rel="stylesheet" href="styles/addRiceStyle.css"/>

<script>
    function addLoadEventListener(id){
        element = document.getElementById(id);
        element.addEventListener("change", () => {
            changeLoadFileIcon(false, id);
        });
    }

    function changeLoadFileIcon(showDefault, id){
        unloaded_icon = document.getElementById(id + "Unloaded");
        loaded_icon = document.getElementById(id + "Loaded");
        if(!showDefault){
            unloaded_icon.style = "display: none;";
            loaded_icon.style = "display: block;";
        }
        else{
            loaded_icon.style = "display: none;";
            unloaded_icon.style = "display: block;";
        }
    }

    function sendForm(){
        var form = document.getElementById("rice_form");
        var fileInput = document.getElementById("imgInput");
        for(i=0; i<fileInput.files.length; i++ ){
            var newInput = document.createElement("input");
            newInput.setAttribute("type", "file");
            newInput.setAttribute("name", "img" + String(i));
            newInput.setAttribute("id", "img" + String(i));
            newInput.setAttribute("style", "display: none");
            newInput.files = fileInput.files[i];
            form.appendChild(newInput);
        }
        var countInput = document.createElement("input");
        countInput.setAttribute("type", "hidden");
        countInput.setAttribute("name", "count");
        countInput.setAttribute("value", String(fileInput.files.length));
        form.appendChild(countInput);
    }
</script>

<main>
    <a href="index.php">Back to homepage</a>
    <div class="data-input centerHorizontal">
        <form method="post" id="rice_form" action="add_rice_data.php" enctype="multipart/form-data">
            <input type="text" name="titleInput" placeholder="Title" class="input-text" required>
            <input type="text" name="repoInput" placeholder="Github repository" class="input-text" required>
            <input type="file" name="files" id="imgInput" accept="image/*" class="addImg" required style="display: none;">
            <label for="imgInput" class="imgInput">
                    <img src="images/select_file.png", alt="Select file" height="247.5" id="imgInputUnloaded">
                    <img src="images/select_file_selected.png", alt="Select file" height="247.5" id="imgInputLoaded" style="display: none;">
                </label>
            <input type="submit" name="upload_data" value="Upload" class="centerHorizontal">
        </form>
    </div>
</main>

<script>
    addLoadEventListener("imgInput");
</script>

<?php
include('templates/footer.php');
?>