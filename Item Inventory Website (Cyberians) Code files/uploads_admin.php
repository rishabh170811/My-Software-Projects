<?php
session_start();
if($_SESSION['role']!=4)
	{ echo "<h1>Access Denied</h1>"; exit(); }

?>

<html>
<head>
<title>Virtual Library</title>

<meta name="keywords" content="" />
<meta name="description" content="" />

<link href="default.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="slider.js"> </script>
</head>

<body onload="document.upload.file.focus()">
<div id="header">
	
	<h1>Virtual Library</h1>
	<h2>Learning made simple</h2>
	
	<?php
	
	if(isset($_SESSION['username'])) {
	 echo'<p style="color:black;font-size:11.5px" align="right"> Hi ';	
	 echo $_SESSION['username'];
	 echo "&nbsp;&nbsp;|&nbsp;&nbsp;<a href='signout.php?role=3' style='color:black;'> Sign Out </a>";  
	}
	else {
	echo '<p style="color:black;font-size:11.5px" align="right">
	<a href="signup.htm">Sign Up</a>&nbsp;&nbsp;|
	<a href="signin.htm">Sign In</a>&nbsp;&nbsp;|
	</p>';
	}

	?>
	
</div>

<div id="menu">
	<ul>
		<li><a href="index_admin.php">Home</a></li>
		<li><a href="aboutus.php">About Us</a></li>	
		<li><a href="downloads.php" >Downloads</a></li>			
		<li><a href="contactus.php">Contact Us</a></li>
		<li><a href="#" class="active">Uploads</a></li>
		<li><a href="courses_admin.php">Courses</a></li>
	</ul>
</div>


<div id="content" style="margin-left:0px;padding: 20px 0px 20px 0px;">

<img src="images/lib.jpg" name="slide" border=0 width=1350 height=230>

<script type="text/javascript">

slideshowimages("images/lib1.jpg","images/lib2.jpg","images/lib3.jpg","images/lib4.jpg","images/lib5.jpg","images/lib6.jpg","images/lib7.jpg","images/lib9.png","images/lib10.jpg","images/lib11.jpg","images/lib12.jpg");

var slideshowspeed=2000

var whichlink=0
var whichimage=0

function slideit(){
if (!document.images)
return
document.images.slide.src=slideimages[whichimage].src
whichlink=whichimage
if (whichimage<slideimages.length-1)
whichimage++
else
whichimage=0
setTimeout("slideit()",slideshowspeed)
}

slideit();

</script>
</br><br>

<font style='font-size:20px'>
<ul type="disc">
<li><a href="uploads_resources.php" style="color:green">Upload library resources</a></li></br> 
<li><a href="uploads_courses.php" style="color:green">Upload videos for courses</a></li></br>
</ul></font>

</div>
</br><br><br>

<div id="footer">
	<p>Copyright &copy; Murali Kembhavi and Rishabh Sharma</p>
</div>

</body>
</html>