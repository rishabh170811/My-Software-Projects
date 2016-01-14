<?php

$mysql_hostname = "localhost";
$mysql_user = "root";
$mysql_password = "murali=1256";
$mysql_database = "library";
$prefix = "";
$bd = mysql_connect($mysql_hostname, $mysql_user, $mysql_password) or die("Could not connect database");
$con=mysql_select_db($mysql_database, $bd) or die("Could not select database");

session_start();

$user=$_SESSION['username'];

$name=$_GET['i'];

$query = mysql_query("SELECT * FROM Enrolled_courses WHERE Username='$user' AND Course_name='$name'");

if(mysql_num_rows($query)==1)
	{ echo "<script> alert('Already Enrolled!'); window.location='courses_student.php'; </script>"; exit(); }

mysql_query("INSERT INTO Enrolled_courses VALUES ('$user', '$name')");

echo "<script> window.location='index_student.php'; </script>";

mysql_close();

?>