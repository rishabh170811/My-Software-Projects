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
<script type="text/javascript" src="slider.js"> </script>
</head>
<body>
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

	echo '<p style="color:black;font-size:11.5px" align="right">
	<a href="signup.htm">Sign Up</a>&nbsp;&nbsp;|
	<a href="signin.htm">Sign In</a>&nbsp;&nbsp;|
	</p>';
	}
	
	?>
	
</div>
<div id="menu">
	<ul>
		<?php

		if(isset($_SESSION['username']) && $_SESSION['role']==2) {

		echo '<li><a href="index_author.php" class="active">Home</a></li>
		<li><a href="aboutus.php" >About Us</a></li>		
		<li><a href="contactus.php">Contact Us</a></li>
		<li><a href="uploads_author.php">Uploads</a></li>
		<li><a href="courses_author.php">Courses</a></li>';

		}
		else if(isset($_SESSION['username']) && $_SESSION['role']==3) {

		echo '<li><a href="index_manager.php" class="active">Home</a></li>
		<li><a href="aboutus.php" >About Us</a></li>		
		<li><a href="contactus.php">Contact Us</a></li>
		<li><a href="uploads_manager.php">Uploads</a></li>
		<li><a href="courses_manager.php">Courses</a></li>';

		}
		?>
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

<font style='font-size:20px;font-weight:bold'>&nbsp;&nbsp;&nbsp;&nbsp;The following resources are available!</font>

<font style='font-size:20px'>

<ul type="disc">
<li><a href="resources_pdf.php"style="color:green">E-books (Pdfs)</a></li></br> 
<li><a href="resources_ppts.php" style="color:green">Lecture Slides</a></li></br>
<li><a href="resources_videos.php" style="color:green">Lecture Videos</a></li></br>
<li><a href="resources_docs.php" style="color:green">Research Papers</a></li></br>
</ul></font>

</div>


<div id="footer">
	<p>Copyright &copy; Murali Kembhavi and Rishabh Sharma</p>
</div>
</body>
</html>