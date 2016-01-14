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

$i=0;

$allowedExts = array("pdf","doc","docx","ppt","pptx","mp4","mpeg","3gp","mpg");
$temp = explode(".", $_FILES["file"]["name"]);

echo $_FILES["file"]["type"];

$extension = end($temp);
$pptx="application/vnd.openxmlformats-officedocument.presentationml.presentation";
$docx="application/vnd.openxmlformats-officedocument.wordprocessingml.document";

if((($_FILES["file"]["type"] == "application/pdf")
  || ($_FILES["file"]["type"] == "application/vnd.ms-powerpoint")
  || ($_FILES["file"]["type"] == $pptx) || ($_FILES["file"]["type"] == "application/msword")
  || ($_FILES["file"]["type"] == "application/download") || ($_FILES["file"]["type"] == "image/jpg")
  || ($_FILES["file"]["type"] == $docx) || ($_FILES["file"]["type"] == "video/mp4")
  || ($_FILES["file"]["type"] == "video/3gpp") || ($_FILES["file"]["type"] == "video/mpeg")
  || ($_FILES["file"]["type"] == "video/mpg") || ($_FILES["file"]["type"] == "application/octet-stream"))
  && ($_FILES["file"]["size"] < 200000000000) && in_array($extension, $allowedExts))
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
    echo " <a href='uploads_resources.php'> [Back to Uploads] </a> </h3>";
   
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
      echo "<script> alert('Invalid File!');  </script>";
      exit(); 
  }
    $filename = preg_replace('/[^A-Z0-9]/','',$_FILES["file"]["name"]) . "." . $extension;
    $link = "upload/" . $_FILES['file']['name'];
    $file_name=$_FILES["file"]["name"];
    $id=0;
    $status=0;
    $desc=$_POST['desc'];

    if($extension=='pdf')
        $strSQL = "INSERT INTO E_books VALUES('$id','$file_name','$link','$desc','$status')";
    else
      if($extension=='doc' || $extension=='docx')
          $strSQL = "INSERT INTO Research_papers VALUES('$id','$user','$file_name','$link','$desc','$status')";
      else
        if($extension=='ppt' || $extension=='pptx')
            $strSQL = "INSERT INTO Slides VALUES('$id','$file_name','$link','$desc','$status')";
        else
         if($extension=='mp4' || $extension=='3gp')
            $strSQL = "INSERT INTO Videos VALUES('$id','$file_name','$link','$desc','$status')";
         
  
	  mysql_query($strSQL) or die(mysql_error());
    mysql_close();
	
?>  