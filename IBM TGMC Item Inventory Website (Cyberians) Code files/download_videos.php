<?php

// place this code inside a php file and call it f.e. "download.php"
session_start();

$i=$_GET['id'];
// connect to mysql server
$mysql_hostname = "localhost";
$mysql_user = "root";
$mysql_password = "murali=1256";
$mysql_database = "library";$prefix = "";
$bd = mysql_connect($mysql_hostname, $mysql_user, $mysql_password) or die("Could not connect database");
$con=mysql_select_db($mysql_database, $bd) or die("Could not select database");

$query1='SELECT Link FROM Videos where ID='.$i.';'; 
$query=mysql_query($query1);
$row = mysql_fetch_array($query,MYSQL_ASSOC);
$content = $row['Link'];

$filename = $content;
// Validate the filename (You so don't want people to be able to download
// EVERYTHING from your site...)

if (!file_exists($filename))
{
    header("HTTP/1.0 404 Not Found");
    die();
}
// A check of filemtime and IMS/304 management would be good here

// Be sure to disable buffer management if needed
while(ob_get_level())
   ob_end_clean();

Header('Content-Type: application/download');
Header("Content-Disposition: attachment; filename=\"$filename\"");
header('Content-Transfer-Encoding: binary'); // Not really needed
Header('Content-Length: ' . filesize($filename));
Header("Cache-Control: must-revalidate, post-check=0, pre-check=0");

readfile($filename);
?>