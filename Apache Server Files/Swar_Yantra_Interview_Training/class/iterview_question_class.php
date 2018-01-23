<?php
class InterviewQuestion{
    private $questionId;
    private $question;
    private $questionHint;
    private $questionSoundUrl;
    private $questionCreatedAt;
    private $questionUpdatedAt;


    /**
     * @return mixed
     */
    public function getQuestionId()
    {
        return $this->questionId;
    }

    /**
     * @param mixed $questionId
     */
    public function setQuestionId($questionId)
    {
        $this->questionId = $questionId;
    }

    /**
     * @return mixed
     */
    public function getQuestion()
    {
        return $this->question;
    }

    /**
     * @param mixed $question
     */
    public function setQuestion($question)
    {
        $this->question = $question;
    }

    /**
     * @return mixed
     */
    public function getQuestionHint()
    {
        return $this->questionHint;
    }

    /**
     * @param mixed $questionHint
     */
    public function setQuestionHint($questionHint)
    {
        $this->questionHint = $questionHint;
    }

    /**
     * @return mixed
     */
    public function getQuestionCreatedAt()
    {
        return $this->questionCreatedAt;
    }

    /**
     * @param mixed $questionCreatedAt
     */
    public function setQuestionCreatedAt($questionCreatedAt)
    {
        $this->questionCreatedAt = $questionCreatedAt;
    }

    /**
     * @return mixed
     */
    public function getQuestionSoundUrl()
    {
        return $this->questionSoundUrl;
    }

    /**
     * @param mixed $questionSoundUrl
     */
    public function setQuestionSoundUrl($questionSoundUrl)
    {
        $this->questionSoundUrl = $questionSoundUrl;
    }

    /**
     * @return mixed
     */
    public function getQuestionUpdatedAt()
    {
        return $this->questionUpdatedAt;
    }

    /**
     * @param mixed $questionUpdatedAt
     */
    public function setQuestionUpdatedAt($questionUpdatedAt){
        $this->questionUpdatedAt = $questionUpdatedAt;
    }

    public function insert($db){
        //connection made
        $db->connect();

        $value=array();
        $type='';
        $row='';

        $table='iterview_question_table';

        if(!empty($this->question)){
            if(count($value)>0)
                $row.=',question';
            else
                $row.='question';
            $value[count($value)]=$this->question;
            $type.='s';
        }

        if(!empty($this->questionHint)){
            if(count($value)>0)
                $row.=',hint';
            else
                $row.='hint';
            $value[count($value)]=$this->questionHint;
            $type.='s';
        }

        if(!empty($this->questionSoundUrl)){
            if(count($value)>0)
                $row.=',audio_url';
            else
                $row.='audio_url';
            $value[count($value)]=$this->questionSoundUrl;
            $type.='s';
        }

        file_put_contents('php://stderr', print_r("\nInsertion", TRUE));
        file_put_contents('php://stderr', "\nThis is row".print_r($row,true));
        file_put_contents('php://stderr', "\nThis is value".print_r($value,true));
        file_put_contents('php://stderr', "\nThis is type".print_r($type,true));

        $db->insert($table,$row,$value,$type);
        return(mysqli_insert_id($db->link));

        //connection removed
        $db->disconnect();
    }



    public function selectList($db){
        //connection made
        $db->connect();

        $where=array();
        $value=array();
        $type='';
        $table='iterview_question_table';

        $result=array();


        if(!empty($this->questionCreatedAt)){
            $where[count($where)]='created_at';
            $type.='s';
            $value[count($value)]=$this->questionCreatedAt;
        }

        file_put_contents('php://stderr', "\nSelect");
        file_put_contents('php://stderr', "\nThis is where".print_r($where,true));
        file_put_contents('php://stderr', "\nThis is value".print_r($value,true));
        file_put_contents('php://stderr', "\nThis is type".print_r($type,true));

        $db->select_greater($table,'*',$where,$value,$type);
        $result=$db->result;
        if(empty($result)){
            return null;
        }
        else{
            $count=count($db->result);
            $interviewQuestionList=array();
            for($i=0;$i<$count;$i++){
                $interviewQuestion=new InterviewQuestion();

                if(isset($result[$i]['id'])){
                    $interviewQuestion->setQuestionId($result[$i]['id']);
                }

                if(isset($result[$i]['question'])){
                    $interviewQuestion->setQuestion($result[$i]['question']);
                }

                if(isset($result[$i]['hint'])){
                    $interviewQuestion->setQuestionHint($result[$i]['hint']);
                }

                if(isset($result[$i]['audio_url'])){
                    $interviewQuestion->setQuestionSoundUrl($result[$i]['audio_url']);
                }

                if(isset($result[$i]['created_at'])){
                    $interviewQuestion->setQuestionCreatedAt($result[$i]['created_at']);
                }

                if(isset($result[$i]['updated_at'])){
                    $interviewQuestion->setQuestionUpdatedAt($result[$i]['updated_at']);
                }


                $interviewQuestionList[$i]=$interviewQuestion;
            }
            return $interviewQuestionList;
        }


        //disconnect
        $db->disconnect();
    }




    public function selectQuestion($db){
        //connection made
        $db->connect();

        $where=array();
        $value=array();
        $type='';
        $table='iterview_question_table';

        $result=array();

        if(!empty($this->questionId)){
            $where[count($where)]='id';
            $type.='i';
            $value[count($value)]=$this->questionId;
        }

        file_put_contents('php://stderr', "\nSelect");
        file_put_contents('php://stderr', "\nThis is where".print_r($where,true));
        file_put_contents('php://stderr', "\nThis is value".print_r($value,true));
        file_put_contents('php://stderr', "\nThis is type".print_r($type,true));

        $db->select($table,'*',$where,$value,$type);
        $result=$db->result;
        $count=count($db->result);

        $interviewQuestionList=array();

        for($i=0;$i<$count;$i++){
            $interviewQuestion=new InterviewQuestion();

            if(isset($result[$i]['id'])){
                $interviewQuestion->setQuestionId($result[$i]['id']);
            }

            if(isset($result[$i]['question'])){
                $interviewQuestion->setQuestion($result[$i]['question']);
            }

            if(isset($result[$i]['hint'])){
                $interviewQuestion->setQuestionHint($result[$i]['hint']);
            }

            if(isset($result[$i]['audio_url'])){
                $interviewQuestion->setQuestionSoundUrl($result[$i]['audio_url']);
            }

            if(isset($result[$i]['created_at'])){
                $interviewQuestion->setQuestionCreatedAt($result[$i]['created_at']);
            }

            if(isset($result[$i]['updated_at'])){
                $interviewQuestion->setQuestionUpdatedAt($result[$i]['updated_at']);
            }


            $interviewQuestionList[$i]=$interviewQuestion;
        }
        return $interviewQuestionList;

        //disconnect
        $db->disconnect();
    }

    public function update($db){
        //connection made
        //update interview_opening Set interview_opening_id = ? , opening_question_id = ? , `review_expect_flag = ? where interview_opening_id= 1317
        $db->connect();

        $value=array();
        $type='';
        $where='';
        $row=array();

        $table='iterview_question_table';

        /*if(!empty($this->interviewOpeningId)){
            $row[count($value)]='interview_opening_id';
            $value[count($value)]=$this->interviewOpeningId;
            $type.='i';
        }*/

        if(!empty($this->question)){
            $row[count($value)]='question';
            $value[count($value)]=$this->question;
            $type.='s';
        }

        if(!empty($this->questionHint)){
            $row[count($value)]='hint';
            $value[count($value)]=$this->questionHint;
            $type.='s';
        }

        if(!empty($this->questionSoundUrl)){
            $row[count($value)]='audio_url';
            $value[count($value)]=$this->questionSoundUrl;
            $type.='s';
        }

        if(!empty($this->questionCreatedAt)){
            $row[count($value)]='created_at';
            $value[count($value)]=$this->questionCreatedAt;
            $type.='s';
        }

        $where='id= '.$this->questionId;


        file_put_contents('php://stderr', "\nUpdation");
        file_put_contents('php://stderr', "\nThis is where".print_r($where,true));
        file_put_contents('php://stderr', "\nThis is value".print_r($value,true));
        file_put_contents('php://stderr', "\nThis is type".print_r($type,true));
        file_put_contents('php://stderr', "\nThis is row".print_r($row,true));

        $db->update($table,$row,$value,$where,$type);

        //connection removed
        $db->disconnect();
    }

    public function populate($obj){
        file_put_contents('php://stderr',"Object received".print_r($obj,true));
        if(!empty($obj->questionId))
            $this->setQuestionId($obj->questionId);
        if(!empty($obj->question))
            $this->setQuestion($obj->question);
        if(!empty($obj->questionHint))
            $this->setQuestionHint($obj->questionHint);
        if(!empty($obj->questionSoundUrl))
            $this->setQuestionSoundUrl($obj->questionSoundUrl);
        if(!empty($obj->questionCreatedAt))
        $this->setQuestionCreatedAt($obj->questionCreatedAt);
        if(!empty($obj->questionUpdatedAt))
        $this->setQuestionUpdatedAt($obj->questionUpdatedAt);
    }

    function getJsonData(){
        $var = get_object_vars($this);
        foreach($var as &$value){
            if(is_array($value)){
                $count=count($value);
                for($i=0;$i<$count;$i++){
                    if(method_exists($value[$i],'getJsonData')){
                        $value[$i] = $value[$i]->getJsonData();
                    }
                }
            }
        }
        return $var;
    }
}
?>