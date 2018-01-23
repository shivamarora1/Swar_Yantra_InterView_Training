<html>
<head>

<link rel="stylesheet" type="text/CSS" href="main.css">
<title>
Update question
</title>
</head>
<body>

<?php

require "../class/iterview_question_class.php";
require "../class/mysql_function.php";
if(isset($_GET["id_name"])) {
    $id = $_GET["id_name"];
    $db = new database;
    $obj = new InterviewQuestion;
    $obj->setQuestionId($id);
    $result = $obj->selectQuestion($db);
    $count = count($result);
    for ($i = 0; $i < $count; $i++) {
        $interviewQuestion = new InterviewQuestion();
        $interviewQuestion->populate($result[$i]);
    }
    $question_title = $interviewQuestion->getQuestion();
    $question_hint = $interviewQuestion->getQuestionHint();
    $question_audio = $interviewQuestion->getQuestionSoundUrl();
}
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    ///To update Database Code....
    $obj=new InterviewQuestion;
    $obj->setQuestionId($_POST["question_id"]);
    $obj->setQuestion($_POST["question_title"]);
    $obj->setQuestionHint($_POST["question_hint"]);
    $obj->setQuestionSoundUrl($_FILES["fileToUpload"]["name"]);
    date_default_timezone_set('Asia/Kolkata');
    $obj->setQuestionCreatedAt(date("Y-m-d H:i:s"));
    $tmp_name=$_FILES["fileToUpload"]["tmp_name"];
    move_uploaded_file($tmp_name,"../data/".$question_audio);
    $obj->update($db);
    echo "Data Updated Succesfully....";
    echo "<script> location.href='main_dashboard.php'; </script>";
}
?>

<form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>" method="post" enctype="multipart/form-data">
  <div class="imgcontainer">
    <img src="question.png" alt="Avatar" class="avatar">
  </div>

  <div class="container">
    <label><b>Question Id:<?php echo $id ?></b></label>
	<br>
    <input type="hidden" value="<?php echo $id ?>" name="question_id" hidden>
    <label><b>Question:</b></label>
    <input type="text" value="<?php echo $question_title ?>" name="question_title" required>
    <label><b>Hint</b></label>
    <input type="text" value="<?php echo $question_hint ?>" name="question_hint" required>
	<label><b>Audio</b></label>
    <input type="file" placeholder="Enter file to upload" name="fileToUpload" style="width: 100%;
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