<html>
<head>
<title>Virtual Library</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="default.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="header">
	<h1>Virtual Library</h1>
	<h2>Learning made simple</h2>
	
	<?php
	session_start();
	if(isset($_SESSION['username'])) {
	 echo'<p style="color:black;font-size:11.5px" align="right"> Hi ';	
	 echo $_SESSION['username'];
	 echo "&nbsp;&nbsp;|&nbsp;&nbsp;<a href='signout.php' style='color:black;'> Sign Out </a>";  
	}
	else {

	echo '<p style="color:black;font-size:11.5px" align="right">
	<a href="signup.htm" style="color:black">Sign Up</a>&nbsp;&nbsp;|
	<a href="signin.htm" style="color:black">Sign In</a>&nbsp;&nbsp;|
	</p>';
	}
	?>
	
</div>
<div id="menu">
	<ul>
		<?php

		if(isset($_SESSION['username']) && $_SESSION['role']==1) {

		echo '<li><a href="index_student.php">Home</a></li>
		<li><a href="aboutus.php" class="active">About Us</a></li>		
		<li><a href="downloads.php" >Downloads</a></li>
		<li><a href="contactus.php">Contact Us</a></li>
		<li><a href="uploads1.php">Uploads</a></li>
		<li><a href="courses_student.php">Courses</a></li>
		<li><a href="Forum/index.php">Forums</a></li>';

		}
		else if(isset($_SESSION['username']) && $_SESSION['role']==2) {

		echo '<li><a href="index_author.php">Home</a></li>
		<li><a href="aboutus.php" class="active">About Us</a></li>		
		<li><a href="contactus.php">Contact Us</a></li>
		<li><a href="uploads_author.php">Uploads</a></li>
		<li><a href="courses_author.php">Courses</a></li>';

		}
		else if(isset($_SESSION['username']) && $_SESSION['role']==3) {

		echo '<li><a href="index_manager.php">Home</a></li>
		<li><a href="aboutus.php" class="active">About Us</a></li>		
		<li><a href="contactus.php">Contact Us</a></li>
		<li><a href="uploads_manager.php">Uploads</a></li>
		<li><a href="courses_manager.php">Courses</a></li>';

		}
		else if(isset($_SESSION['username']) && $_SESSION['role']==4) {

		echo '<li><a href="index_admin.php">Home</a></li>
		<li><a href="aboutus.php" class="active">About Us</a></li>		
		<li><a href="downloads.php">Downloads</a></li>
		<li><a href="contactus.php">Contact Us</a></li>
		<li><a href="uploads_admin.php">Uploads</a></li>
		<li><a href="courses_admin.php">Courses</a></li>';

		}
		else {

		echo '<li><a href="index.php">Home</a></li>
		<li><a href="aboutus.php" class="active">About Us</a></li>		
		<li><a href="contactus.php">Contact Us</a></li>';

		}

		?>
	</ul>
</div>

<div id="content">
<font style='font-size:25px;font-weight:bold'>
<center>Welcome to Virtual Library!</center></font></br>
<font style='font-size:20px'>

<p style="color:green">
Virtual Library is an online library. All the registered users can download various library resources such as pdfs, slides, lecture videos and research papers. Users can even enroll for various available courses that are created based upon some similar resources. There is a discussion forum for each course, where users can discuss their doubts and raise their concerns!</p>

<p style="color:green">Users can even upload their research papers, which will be made public after the admin's approval. The library resources are constantly monitored for any objectionable content. Users can even rate resources and recommend courses to others!</p>

</font>
</div></br></br></br></br><br><br><br>

<div id="footer">
	<p>Copyright &copy; Murali Kembhavi and Rishabh Sharma</p>
</div>
</body>
</html>