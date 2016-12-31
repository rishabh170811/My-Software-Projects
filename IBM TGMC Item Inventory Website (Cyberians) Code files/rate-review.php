<?php

$mysql_hostname = "localhost";
$mysql_user = "root";
$mysql_password = "murali=1256";
$mysql_database = "library";
$prefix = "";
$bd = mysql_connect($mysql_hostname, $mysql_user, $mysql_password) or die("Could not connect database");
$con=mysql_select_db($mysql_database, $bd) or die("Could not select database");

session_start();

$rev = $_POST['review'];
$rate = $_POST['rate'];

$name = $_GET['name'];
$user=$_SESSION['username'];

$sql = mysql_query("SELECT * FROM Reviews WHERE Username='$user' AND Course_name='$name'");

if(mysql_num_rows($sql)==1)
	{ echo " <script> alert('Already reviewed!!'); window.location='enrolled_courses.php'; </script> "; exit(); }

mysql_query("INSERT INTO Reviews VALUES('$user', '$name', '$rev', '$rate')");
echo "<script> alert('Successfully reviewed!!'); window.location='enrolled_courses.php'; </script>";

mysql_close();

?>