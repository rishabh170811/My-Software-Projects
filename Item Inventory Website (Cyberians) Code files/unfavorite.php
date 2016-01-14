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

$id = $_GET['id'];
$type = $_GET['type'];
$status=0;

if($type==1)
	{ mysql_query("UPDATE E_books SET Status='$status' WHERE ID='$id'"); echo "<script> window.location='resources_pdf.php'; </script>"; }
else if($type==2)
		{ mysql_query("UPDATE Slides SET Status='$status' WHERE ID='$id'"); echo "<script> window.location='resources_ppts.php'; </script>"; }
	  else 
	  		{ mysql_query("UPDATE Research_papers SET Status='$status' WHERE ID='$id'"); echo "<script> window.location='resources_docs.php'; </script>"; }
		   
mysql_close();

?>