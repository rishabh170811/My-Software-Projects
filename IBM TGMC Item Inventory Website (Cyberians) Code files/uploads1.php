<?php
session_start();
if($_SESSION['role']!=1)
	{ echo "<h1>Access Denied</h1>"; exit(); }
?>

<html>
<head>
<title>Virtual Library</title>

<meta name="keywords" content="" />
<meta name="description" content="" />

<link href="default.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" >
  function validateForm()
  {
    var a=document.forms["upload"]["file"].value;
    var b=document.forms["upload"]["desc"].value;

    if (a==null || a=="")
    {
    alert("No File Selected!!");
    return false;
    }

    if (b==null || b=="")
    {
    alert("No Resource Description!!");
    return false;
    }
  }
 </script>

</head>

<body onload="document.upload.file.focus()">
<div id="header">
	<h1>Virtual Library</h1>
	<h2>Learning made simple</h2>
	
	<?php
	
	 if(isset($_SESSION['username'])) {
	 echo'<p style="color:black;font-size:11.5px" align="right"> Hi ';	
	 echo $_SESSION['username'];
	 echo "&nbsp;&nbsp;|&nbsp;&nbsp;<a href='signout.php' style='color:black;'> Sign Out </a>";  
	}
	else {
	echo "<h1>Access Denied</h1>";
	}
	
	?>
	    
</div>

<div id="menu">
	<ul>
		<li><a href="index_student.php">Home</a></li>
		<li><a href="aboutus.php">About Us</a></li>		
		<li><a href="downloads.php">Downloads</a></li>		
		<li><a href="contactus.php">Contact Us</a></li>
		<li><a href="#" class="active">Uploads</a></li>
		<li><a href="courses_student.php">Courses</a></li>
		<li><a href="Forum/index.php">Forums</a></li>
	</ul>
</div>


<div id="content">


<font style='font-size:20px;font-weight:bold'>Research papers can be uploaded here! <i>(.doc/.docx)</i></font></br>
<b><i>max size:20 MB</i></b></br></br>

	<form name="upload" action="uploads_students.php" method="post" enctype="multipart/form-data">
		File Name:&nbsp;
		<input type="file" style="color:blue;font-weight:bold" name="file" id="file"></br></br>
		
		Resource Desc.:&nbsp;
		<textarea rows=7 name="desc">Enter resource description!!</textarea></br><br>

		<input type="submit" style="color:blue;font-weight:bold" name="submit" value="Upload Research paper" onclick="return validateForm()">
	</form>	
</div>
</br><br><br><br><br><br>

<div id="footer">
	<p>Copyright &copy; Murali Kembhavi and Rishabh Sharma</p>
</div>

</body>
</html>