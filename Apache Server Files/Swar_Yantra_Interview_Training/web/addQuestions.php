<html>
<head>

<link rel="stylesheet" type="text/CSS" href="main.css">
<title>
Add new question
</title>
</head>
<body>

<?php

require "../class/iterview_question_class.php";
require "../class/mysql_function.php";

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $questionTitle = $_POST["question_title"];
    $questionHint = $_POST["question_hint"];
    $questionAudioUrl = $_FILES["fileToUpload"]["name"];
    $extension_arr = array("wav", "mp3", "ogg", "amr");
    $temp = explode(".", $questionAudioUrl);
    $audio_type = strtolower(end($temp));
    $newfile_name = round(microtime(true)) . "." . end($temp);
    $tmp_name = $_FILES["fileToUpload"]["tmp_name"];
    move_uploaded_file($tmp_name, "../data/" . $newfile_name);

    if (in_array($audio_type, $extension_arr) === FALSE) {
        echo "<script>alert('Only mp3,ogg,wav,amr extension allowed...Question not inserted');
	location.href='main_dashboard.php';</script>";
    } else {
        $db = new database;
        $obj = new InterviewQuestion;
        $obj->setQuestion($questionTitle);
        $obj->setQuestionHint($questionHint);
        $obj->setQuestionSoundUrl($newfile_name);
        $obj->insert($db);
        echo "<script>alert('Data inserted');
	location.href='main_dashboard.php';</script>";
    }
}
?>


<form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>" method="post" enctype="multipart/form-data">
  <div class="imgcontainer">
    <img src="question.png" alt="Avatar" class="avatar">
  </div>

  <div class="container">
    <label><b>Question:</b></label>
    <input type="text" placeholder="Enter Question" name="question_title" required>

    <label><b>Hint</b></label>
    <input type="text" placeholder="Enter Question Hint" name="question_hint" required>
	<label><b>Audio</b></label>
    <input type="file" placeholder="Enter file to upload" name="fileToUpload" required style="width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    box-sizing: border-box;">
	
    <button type="submit">Add Question</button>
  </div>
</form>

</body>
</html>