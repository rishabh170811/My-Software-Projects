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

mysql_query("DELETE FROM Enrolled_courses WHERE Username='" . $user . "' AND Course_name='" . $name . "';");

echo "<script> window.location='index_student.php'; </script>";

mysql_close();

?>