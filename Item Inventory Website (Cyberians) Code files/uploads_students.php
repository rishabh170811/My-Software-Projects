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

  $allowedExts = array("doc","docx");
  $temp = explode(".", $_FILES["file"]["name"]);

  $extension = end($temp);

  $docx="application/vnd.openxmlformats-officedocument.wordprocessingml.document";
  $doc="application/msword";

  if((($_FILES["file"]["type"] == $docx) || ($_FILES["file"]["type"] == $doc)) && ($_FILES["file"]["size"] < 200000000000) && in_array($extension, $allowedExts))
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
    echo " <a href='uploads1.php'> [Back to Uploads] </a> </h3>";
   
    /*the folder of upload which must be in the same folder as the webpage where you would 
    put this code by default we use "upload" change to yours :) make sure you create this folder!*/
    
    if (file_exists("upload/" . $_FILES["file"]["name"]))
      {
      echo $_FILES["file"]["name"] . " already exists. ";
      }
    else
      {
      move_uploaded_file($_FILES["file"]["tmp_name"],
      "upload/" . $_FILES["file"]["name"]);
    
      }
    }
  }
  else
  {
    //error message if the extension is not allowed
      echo "<script> alert('Invalid File!'); window.location='uploads1.php'; </script>";
      exit(); 
  }
    $filename = preg_replace('/[^A-Z0-9]/','',$_FILES["file"]["name"]) . "." . $extension;
    $link = "upload/" . $_FILES['file']['name'];
    $file_name=$_FILES["file"]["name"];
    $id=0;
    $user=$_SESSION['username'];
    $desc=$_POST['desc'];

    $strSQL = "INSERT INTO Pending_resources VALUES('$id', '$user', '$file_name', '$link', '$desc')";

	  mysql_query($strSQL) or die(mysql_error());
    mysql_close();
	
  ?>  