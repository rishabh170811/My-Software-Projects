<?php

$mysql_hostname = "localhost";
$mysql_user = "root";
$mysql_password = "murali=1256";
$mysql_database = "library";
$prefix = "";
$bd = mysql_connect($mysql_hostname, $mysql_user, $mysql_password) or die("Could not connect database");
$con=mysql_select_db($mysql_database, $bd) or die("Could not select database");

session_start();

$i=$_GET['name'];

$type=$_GET['type'];

mysql_query("DELETE FROM Course where course_name='" . $i . "';");

mysql_query("DELETE FROM Course_videos where Course_Name='" . $i . "';");

mysql_query("DELETE FROM Enrolled_courses where Course_name='" . $i . "';");


if($type==2)
	echo "<script> window.location='courses.php'; </script>";
else if($type==3)
		echo "<script> window.location='courses_manager_display.php'; </script>";

mysql_close();

?>