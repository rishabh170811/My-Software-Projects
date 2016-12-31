<?php
	
    $mysql_hostname = "localhost";
	  $mysql_user = "root";
	  $mysql_password = "murali=1256";
	  $mysql_database = "library";
	  $prefix = "";
	  $bd = mysql_connect($mysql_hostname, $mysql_user, $mysql_password) or die("Could not connect database");
	  $con=mysql_select_db($mysql_database, $bd) or die("Could not select database"); 

	  session_start();

	  $name = $_POST["name"];
	  $desc = $_POST["desc"];
	  $sdate = $_POST["sdate"];
	  $edate = $_POST["edate"];

	  $id=0;
	  $role=$_GET['role'];
	  $author=$_SESSION['username'];

    $sql=mysql_query("SELECT * FROM Course WHERE course_name='" . $name . "';");

    if(mysql_num_rows($sql)==1 && $role==2)
    {
    echo "<script> alert('Course Name already exists!'); window.location='courses_author.php'; </script>";
    exit();
    }
    else if(mysql_num_rows($sql)==1 && $role==3)
    {
    echo "<script> alert('Course Name already exists!'); window.location='courses_manager.php'; </script>";
    exit();
    }

    $allowedExts = array("jpg","jpeg");
    $temp = explode(".", $_FILES["file"]["name"]);

    $extension = end($temp);

    if(($_FILES["file"]["type"] == "image/jpeg") && ($_FILES["file"]["size"] < 200000000000) && in_array($extension, $allowedExts))
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
    
    /*the folder of upload which must be in the same folder as the webpage where you would 
    put this code by default we use "upload" change to yours :) make sure you create this folder!*/
    
    if (file_exists("upload/" . $_FILES["file"]["name"]))
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
    if($_SESSION['role']==2)
      echo "<script> alert('Invalid File!');  window.location='courses_author.php'; </script>";
    else if($_SESSION['role']==3)
            echo "<script> alert('Invalid File!');  window.location='courses_manager.php'; </script>";
        else
            echo "<script> alert('Invalid File!');  window.location='courses_admin.php'; </script>";
      exit(); 
    }

    $filename = preg_replace('/[^A-Z0-9]/','',$_FILES["file"]["name"]) . "." . $extension;
    
    $link = "upload_CourseVideos/" . $_FILES['file']['name'];
  			
       mysql_query("INSERT INTO Course VALUES ('$id', '$name', '$desc', '$author', '$sdate', '$edate', '$link')");   	
				
				if($role==3)
					echo "<script> alert('Course created successfully!'); window.location='courses_manager.php'; </script>";
				else if($role==2)
						    echo "<script> alert('Course created successfully!'); window.location='courses_author.php'; </script>";
					   else
						    echo "<script> alert('Course created successfully!'); window.location='courses_admin.php'; </script>";

    mysql_close();
  
?>