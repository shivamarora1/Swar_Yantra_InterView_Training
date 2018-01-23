<html>
<head>
<link rel="stylesheet" type="text/CSS" href="main.css">
<title>Welcome...</title>
<style>
table,th,tr{
	border: 1px solid #DDD;
}
body{
	    font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
		font-size:14px !important;
}
</style>
</head>
<body>
<table style="width=100%">
<tr style="background:#DDD;">
<th colspan="7" style="padding: 5px;font-weight:  normal;">Question Table</th>
</tr>

<tr>
<td colspan="2">
<a style="font-size: 17px;padding:  10px;" href="addQuestions.php">+ Add Question...</a>
</td>
</tr>

<tr>
<th style="
    padding: 10px;text-align:left
">ID</th>
<th style="
    padding: 10px;text-align:left
">Question</th>
<th style="
    padding: 10px;text-align:left
">Hint</th>
<th style="
    padding: 10px;text-align:left
">Sound Url</th>
<th style="
    padding: 10px;text-align:left
">Created At</th>
<th style="
    padding: 10px;text-align:left
">Updated At</th>
<th style="
    padding: 10px;text-align:left
">Operation</th>
</tr>
    <?php
    require "../class/iterview_question_class.php";
    require "../class/mysql_function.php";
    $db=new database;
    $question=new InterviewQuestion;
    $result=$question->selectList($db);
    $count=count($result);
    for($i=0;$i<$count;$i++){
        $interviewQuestion=new InterviewQuestion();
        $interviewQuestion->populate($result[$i]);
        echo "<tr class=\"ques_row\"><td>".$interviewQuestion->getQuestionId()."</td><td>".$interviewQuestion->getQuestion()."</td><td>".$interviewQuestion->getQuestionHint()."</td><td>".$interviewQuestion->getQuestionSoundUrl()."</td><td>".$interviewQuestion->getQuestionCreatedAt()."</td><td>".$interviewQuestion->getQuestionUpdatedAt()."</td>
		<td><a href=\"updateQuestion.php?id_name=".$interviewQuestion->getQuestionId()."\">Update</a></td></tr>";
    }
    ?>
</table>


</body>
</html>