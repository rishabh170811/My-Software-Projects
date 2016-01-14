<?php

session_start();

if($_SESSION['role']!=2 && $_SESSION['role']!=3 && $_SESSION['role']!=4)
	{ echo "<h1>Access Denied</h1>"; exit(); }

$mysql_hostname = "localhost";
$mysql_user = "root";
$mysql_password = "murali=1256";
$mysql_database = "library";
$prefix = "";
$bd = mysql_connect($mysql_hostname, $mysql_user, $mysql_password) or die("Could not connect database");
$con=mysql_select_db($mysql_database, $bd) or die("Could not select database");

?>

<html>
<head>
<title>Virtual Library</title>

<meta name="keywords" content="" />
<meta name="description" content="" />

<link href="default.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
    function validateForm()
    {
    var a=document.forms["upload"]["file"].value;

    if (a==null || a=="")
    {
    alert("No File Selected!!");
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
	 echo "&nbsp;&nbsp;|&nbsp;&nbsp;<a href='signout.php?role=3' style='color:black;'> Sign Out </a>";  
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


<div id="content">
</br>
<font style='font-size:20px;font-weight:bold'>Course videos can be uploaded here!</font></br>
<b>max size:20 MB</b></br></br>

	<form name="upload" action="uploads_videos.php" method="post"
	enctype="multipart/form-data">
	File Name:&nbsp;
	<input type="file" style="color:blue;font-weight:bold" name="file" id="file"></br></br>
	<textarea name="desc">Enter video description here!</textarea>
	</br></br>

	<font style='font-size:20px;font-weight:bold'>Select the course name!</font></br><br>

    <?php
    $i=0;
    
    echo "<select name='coursename'>";
    $query=mysql_query('SELECT * FROM Course');

    while($row = mysql_fetch_array($query,MYSQL_ASSOC)){
    $ax=$row['course_name'];
    echo "<option value=" . $ax . ">" . $ax . "</option>";
  	}
  	
	echo "</select></br></br>";
	?>
	
	<input type="submit" style="color:blue;font-weight:bold" name="submit" value="Upload" onclick="return validateForm()"></br></br>
	</form>

</div>
</br></br><br><br>

<div id="footer">
	<p>Copyright &copy; Murali Kembhavi and Rishabh Sharma</p>
</div>

</body>
</html>