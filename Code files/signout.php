<?php

session_start();
if(!isset($_SESSION['username']))
{ echo "<h1>Access Denied</h1>"; exit(); }

$x=$_GET['role'];
$mysql_hostname = "localhost";
$mysql_user = "root";
$mysql_password = "murali=1256";
$mysql_database = "library";$prefix = "";
$bd = mysql_connect($mysql_hostname, $mysql_user, $mysql_password) or die("Could not connect database");
$con=mysql_select_db($mysql_database, $bd) or die("Could not select database");

$id=$_SESSION['userid'];
if($x==1)
{
	mysql_query("UPDATE Members SET Status=0 WHERE ID=" . $id . ";");
	session_destroy();
	echo "<script> window.location='index.php' </script>";
}	
else if($x==2)
{
	mysql_query("UPDATE Authors SET Status=0 WHERE ID=" . $id . ";");
	session_destroy();
	echo "<script> window.location='index.php' </script>";
}
else 
{
	mysql_query("UPDATE Managers SET Status=0 WHERE ID=" . $id . ";");
	session_destroy();
	echo "<script> window.location='index.php' </script>";
}

?>