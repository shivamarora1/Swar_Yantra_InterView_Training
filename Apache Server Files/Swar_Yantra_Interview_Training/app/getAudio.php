<?php
/**
 * Created by PhpStorm.
 * User: Shivam
 * Date: 1/20/2018
 * Time: 7:44 PM
 */

require "../class/iterview_question_class.php";
require "../class/mysql_function.php";

/*$_POST['jsonString']='{
 "lastUpdateDate": ""
 }';
 */
if (isset($_POST['jsonString'])) {
    $db = new Database;
    $zipName = "audio.zip";
    $question = new InterviewQuestion;
    $questionAudioList = array();
    $decoded = json_decode($_POST['jsonString']);
    if (!empty($decoded)) {
        $question->setQuestionCreatedAt($decoded->lastUpdateDate);
        $result = $question->selectList($db);
        $count = count($result);
        for ($i = 0; $i < $count; $i++) {
            $interviewQuestion = new InterviewQuestion();
            $interviewQuestion->populate($result[$i]);
            $questionAudioList[$i] = $interviewQuestion->getQuestionSoundUrl();
        }
        zipFiles($questionAudioList, $zipName);
    }
}
function zipFiles($questionAudioList, $zipName)
{
    $count = count($questionAudioList);
    $zip = new ZipArchive;
    if ($zip->open($zipName, ZipArchive::CREATE) === TRUE) {
        for ($i = 0; $i < $count; $i++) {
            $zip->addFile("../data/" . $questionAudioList[$i], $questionAudioList[$i]);
        }
        $zip->close();
    }
    header('Content-disposition: attachment; filename=audio.zip');
    header('Content-type: application/zip');
    readfile($zipName);
    unlink($zipName);
}

?>