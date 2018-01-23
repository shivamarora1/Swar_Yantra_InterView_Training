<html>
<body>
<?php
$localhost="localhost";
$username="root";
$db_name="swar_yantra";
$password=""; 

$uname=$_POST["uname"];
$pass=$_POST["psw"];
	$conn=new mysqli($localhost,$username,$password,$db_name);
	if($conn->connect_error)
	{
	die("Connection Failed with database".$conn->connect_error); 
	}
	$sql="SELECT * FROM admin_relation WHERE uname='".$uname."' AND password='".$pass."'";
	$res=$conn->query($sql);
	if($res->num_rows>0)
	{
		$row=$res->fetch_assoc();
	echo "Log in Succesful...";
	echo "<script> window.location.href='main_dashboard.php';</script>";
	}
	else{
		echo "<script>
alert('User Id password not correct...');
window.location.href='index.html';
</script>";
		
	}
	
	?>
</body>
</html>