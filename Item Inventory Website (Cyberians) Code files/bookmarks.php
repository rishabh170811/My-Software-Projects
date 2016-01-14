<?php

session_start();
if(!isset($_SESSION['username']))
{ echo "<h1>Access Denied</h1>"; exit(); }


$mysql_hostname = "localhost";
$mysql_user = "root";
$mysql_password = "murali=1256";
$mysql_database = "library";
$prefix = "";
$bd = mysql_connect($mysql_hostname, $mysql_user, $mysql_password) or die("Could not connect database");
$con=mysql_select_db($mysql_database, $bd) or die("Could not select database");


$user = $_SESSION['username'];
$name = $_GET['name'];
$type = $_GET['type'];
$id = $_GET['id'];

//echo $user;echo $name;echo $type;echo $id;

$sql=mysql_query("SELECT * FROM Bookmarks WHERE (Username='$user' AND Name='$name')");

  if(mysql_num_rows($sql)==1)
   {
    echo "<script> alert('Already Bookmarked!'); window.location='index_student.php'; </script>";
    exit();
   }
  
mysql_query("INSERT INTO Bookmarks VALUES('$id', '$name', '$user', '$type')");

echo "<script> window.location='index_student.php'; </script>";

mysql_close();

?>