<?php
session_start();
if(!isset($_SESSION['username']))
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
	 echo "&nbsp;&nbsp;|&nbsp;&nbsp;<a href='signout.php?role=2' style='color:black;'> Sign Out </a>";  
	}
	else {
	echo '<p style="color:black;font-size:11.5px" align="right">
	<a href="signup.htm" style="color:black;">Sign Up</a>&nbsp;&nbsp;|
	<a href="signin.htm" style="color:black;">Sign In</a>&nbsp;&nbsp;|
	</p>';
	}

	?>
	
</div>

<div id="menu">
	<ul>
		<?php
		
		if(isset($_SESSION['username']) && $_SESSION['role']==2) {

		echo '<li><a href="index_author.php">Home</a></li>
		<li><a href="aboutus.php" >About Us</a></li>		
		<li><a href="contactus.php">Contact Us</a></li>
		<li><a href="#" class="active">Uploads</a></li>
		<li><a href="courses_author.php">Courses</a></li>';

		}
		else if(isset($_SESSION['username']) && $_SESSION['role']==3) {

		echo '<li><a href="index_manager.php">Home</a></li>
		<li><a href="aboutus.php">About Us</a></li>		
		<li><a href="contactus.php">Contact Us</a></li>
		<li><a href="#" class="active">Uploads</a></li>
		<li><a href="courses_manager.php">Courses</a></li>';

		}
		else {

		echo '<li><a href="index_admin.php">Home</a></li>
		<li><a href="aboutus.php">About Us</a></li>		
		<li><a href="contactus.php">Contact Us</a></li>
		<li><a href="#" class="active">Uploads</a></li>
		<li><a href="courses_admin.php">Courses</a></li>';

		}

		?>

	</ul>
</div>


<div id="content" >

<font style='font-size:20px;font-weight:bold'>Resources can be uploaded here!</font></br>
<b><i>max size:20 MB</i></b></br></br>

	<form name="upload" action="uploads.php" method="post"
		enctype="multipart/form-data">
		File Name:&nbsp;
		<input type="file" style="color:blue;font-weight:bold" name="file" id="file"></br></br>
		
		Resource Desc.:&nbsp;
		<textarea rows=7 name="desc">Enter resource description!!</textarea></br><br>

		<input type="submit" style="color:blue;font-weight:bold" name="submit" value="Upload Resource" onclick="return validateForm()"></br></br>
	</form>

</div>
</br><br><br><br><br>

<div id="footer">
	<p>Copyright &copy; Murali Kembhavi and Rishabh Sharma</p>
</div>

</body>
</html>