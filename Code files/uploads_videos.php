<?php
	
  $mysql_hostname = "localhost";
	$mysql_user = "root";
	$mysql_password = "murali=1256";
	$mysql_database = "library";
	$prefix = "";
	$bd = mysql_connect($mysql_hostname, $mysql_user, $mysql_password) or die("Could not connect database");
	$con=mysql_select_db($mysql_database, $bd) or die("Could not select database"); 

session_start();

$allowedExts = array("mp4","3gp");
$temp = explode(".", $_FILES["file"]["name"]);

$extension = end($temp);

if((($_FILES["file"]["type"] == "video/mp4") || ($_FILES["file"]["type"] == "video/3gpp")) && ($_FILES["file"]["size"] < 200000000000) && in_array($extension, $allowedExts))
  {
  if ($_FILES["file"]["error"] > 0)
    {
    echo "Return Code: " . $_FILES["file"]["error"] . "<br>";
    }
  else
    { 
    echo "<h3>"; 
    echo "Your file " . $_FILES["file"]["name"] . " successfully uploaded!!<br>";
    echo "Details :</br>";    
    echo "Upload: " . $_FILES["file"]["name"] . "<br>";
    echo "Type: " . $_FILES["file"]["type"] . "<br>";
    echo "Size: " . ($_FILES["file"]["size"] / 1024) . " kB<br>";
    echo " <a href='uploads.htm'> [Back to Uploads] </a> </h3>";
   
    /*the folder of upload which must be in the same folder as the webpage where you would 
    put this code by default we use "upload" change to yours :) make sure you create this folder!*/
    
    if (file_exists("upload_CourseVideos/" . $_FILES["file"]["name"]))
      {
      echo $_FILES["file"]["name"] . " already exists. ";
      }
    else
      {
      move_uploaded_file($_FILES["file"]["tmp_name"],
      "upload_CourseVideos/" . $_FILES["file"]["name"]);
    
      }
    }
  }
else
  {
    //error message if the extension is not allowed
      echo "<script> alert('Invalid File!'); window.location='uploads_videos.php'; </script>";
      exit(); 
  }
    $filename = preg_replace('/[^A-Z0-9]/','',$_FILES["file"]["name"]) . "." . $extension;
    $link = "upload_CourseVideos/" . $_FILES['file']['name'];
    $file_name=$_FILES["file"]["name"];
    
    $author=$_SESSION['username'];
    $coursename=$_POST['coursename'];
    $desc=$_POST['desc'];
    
    $id=0;

    $strSQL = "INSERT INTO Course_videos VALUES('$coursename','$author','$link', '$file_name', '$desc', '$id')";

	  mysql_query($strSQL) or die(mysql_error());
    mysql_close();
	
  ?>  