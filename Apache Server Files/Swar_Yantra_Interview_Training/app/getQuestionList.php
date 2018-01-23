<?php
/**
 * Created by PhpStorm.
 * User: Shivam
 * Date: 1/21/2018
 * Time: 12:05 PM
 */
require "../class/iterview_question_class.php";
require "../class/mysql_function.php";

/*$_POST['jsonString']='{
 "lastUpdateDate": ""
 }';
 */
if(isset($_POST['jsonString'])) {
    $db = new Database;
    $question=new InterviewQuestion;
    $questionList=array();
	$arr_response=array();
    $decoded = json_decode($_POST['jsonString']);
    if(!empty($decoded)) {
        $question->setQuestionCreatedAt($decoded->lastUpdateDate);
        $result=$question->selectList($db);
        if(is_array($result)){
            $count=count($result);
            for($i=0;$i<$count;$i++){
                $interviewQuestion=new InterviewQuestion();
                $interviewQuestion->populate($result[$i]);
                $temp=$interviewQuestion->getJsonData();
                array_push($arr_response,$temp);
            }
            $response['questions']=$arr_response;
            $json=json_encode($response);
            echo $json;
        }
        else{
            echo "null";
        }
    }
}
?>