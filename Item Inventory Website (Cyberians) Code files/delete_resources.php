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
$type=$_GET['type'];

if($type==1) {
	mysql_query("DELETE FROM E_books where ID=" . $i . ";");
	echo "<script> window.location='resources_pdf.php'; </script>";
}
else if($type==2) {
		mysql_query("DELETE FROM Slides where ID=" . $i . ";");
		echo "<script> window.location='resources_ppts.php'; </script>";
	}
	 else if($type==3) {
	 	mysql_query("DELETE FROM Research_papers where ID=" . $i . ";");	
	 	echo "<script> window.location='resources_docs.php'; </script>";
	 }
		  else {
		  	mysql_query("DELETE FROM Videos where ID=" . $i . ";");	
		 	echo "<script> window.location='resources_videos.php'; </script>";
		  }

?>