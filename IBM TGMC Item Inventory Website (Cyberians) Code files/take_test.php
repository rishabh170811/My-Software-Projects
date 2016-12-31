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
<link rel="stylesheet" href="table.css" type="text/css"/>

</head>
<body background="images/lib.jpg">

<div id="header">
	<h1>Virtual Library</h1>
	<h2>Learning made simple</h2>
	
	<?php

	if(isset($_SESSION['username'])) {
	 echo'<p style="color:black;font-size:11.5px" align="right"> Hi ';	
	 echo $_SESSION['username'];
	 echo "&nbsp;&nbsp;|&nbsp;&nbsp;<a href='signout.php?role=1' style='color:black'> Sign Out </a>";  
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
		<li><a href="#" class="active">Home</a></li>
		<li><a href="aboutus.php">About Us</a></li>
		<li><a href="downloads.php">Downloads</a></li>
		<li><a href="contactus.php">Contact Us</a></li>
		<li><a href="uploads1.php">Uploads</a></li>
		<li><a href="courses_student.php">Courses</a></li>
	</ul>
</div>

<div id="content">


<?php

$coursename=$_GET['i'];

$mysql_hostname = "localhost";
$mysql_user = "root";
$mysql_password = "murali=1256";
$mysql_database = "library";
$prefix = "";
$bd = mysql_connect($mysql_hostname, $mysql_user, $mysql_password) or die("Could not connect database");
$con=mysql_select_db($mysql_database, $bd) or die("Could not select database");

// create the query

$query = mysql_query("SELECT Test_Number FROM test where Course_Name='$coursename';" );

echo "<h1>The following tests are available for this course!</h1>";
echo "<h2><font color='green'>You cannot give any test more than two times!</h2></br>";

$j=0;

while($row = mysql_fetch_array($query,MYSQL_ASSOC)){

$i=$row['Test_Number'];
if($i>$j)
	$j=$i;
}

for($i=1;$i<=$j;$i++){

echo "<a href='testss.php?i=" . $i . "&&j=" . $coursename . "' style='color:green;font-size:20px;' target='_blank'> Click here to take test " . $i . "</a>";

}

?>

</div>
</br><br><br><br><br><br></br></br></br></br></br></br></br></br></br>

<div id="footer">
	<p>Copyright &copy; Murali Kembhavi and Rishabh Sharma</p>
</div>

</body>
</html>