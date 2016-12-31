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

		if(isset($_SESSION['username'])) {

		echo '<li><a href="index_student.php" >Home</a></li>
		<li><a href="aboutus.php" >About Us</a></li>		
		<li><a href="downloads.php" class="active">Downloads</a></li>
		<li><a href="contactus.php">Contact Us</a></li>
		<li><a href="uploads1.php">Uploads</a></li>
		<li><a href="courses_student.php">Courses</a></li>';

		}
		else {

		echo '<li><a href="index.php" class="active">Home</a></li>
		<li><a href="aboutus.php" >About Us</a></li>		
		<li><a href="contactus.php">Contact Us</a></li>';
		}

		?>
	</ul>
</div>

<div id="content">

<?php

$mysql_hostname = "localhost";
$mysql_user = "root";
$mysql_password = "murali=1256";
$mysql_database = "library";
$prefix = "";
$bd = mysql_connect($mysql_hostname, $mysql_user, $mysql_password) or die("Could not connect database");
$con=mysql_select_db($mysql_database, $bd) or die("Could not select database");

$i=$_GET['id'];
$type=$_GET['type'];

if($type==1)
	$query = mysql_query("SELECT * FROM E_books where ID=" . $i . ";");
else if($type==2)
		$query = mysql_query("SELECT * FROM Slides where ID=" . $i . ";");
	 else if($type==3)
	 		 $query = mysql_query("SELECT * FROM Research_papers where ID=" . $i . ";");
	 	  else
			 $query = mysql_query("SELECT * FROM Videos where ID=" . $i . ";");

$row = mysql_fetch_array($query,MYSQL_ASSOC);
	
$name = $row['Name'];
$desc = $row['Description'];

echo "<h1><font style='color:black'>Resource Name: " . $name . "</h1></br>";
echo "<h2>Resource Description:</br><p>" . $desc . "</p></h2></br></font>";

?>

</div>
</br><br><br><br><br><br></br></br></br></br></br></br></br></br></br></br></br>

<div id="footer">
	<p>Copyright &copy; Murali Kembhavi and Rishabh Sharma</p>
</div>

</body>
</html>