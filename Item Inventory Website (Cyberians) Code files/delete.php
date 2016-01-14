<?php

$mysql_hostname = "localhost";
$mysql_user = "root";
$mysql_password = "murali=1256";
$mysql_database = "library";
$prefix = "";
$bd = mysql_connect($mysql_hostname, $mysql_user, $mysql_password) or die("Could not connect database");
$con=mysql_select_db($mysql_database, $bd) or die("Could not select database");

session_start();

$i=$_GET['id'];
$role=$_GET['role'];

if($role==1) {
	mysql_query("DELETE FROM Members where ID=" . $i . ";");
	echo "<script> window.location='users.php'; </script>";
}
else if($role==2) {
		mysql_query("DELETE FROM Authors where ID=" . $i . ";");
		echo "<script> window.location='authors.php'; </script>";
	}
	 else {
	 	mysql_query("DELETE FROM Managers where ID=" . $i . ";");	
	 	echo "<script> window.location='managers.php'; </script>";
	 }

?>