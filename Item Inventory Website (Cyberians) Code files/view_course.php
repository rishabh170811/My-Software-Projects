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
		<li><a href="index_student.php">Home</a></li>
		<li><a href="aboutus.php">About Us</a></li>		
		<li><a href="downloads.php">Downloads</a></li>
		<li><a href="contactus.php">Contact Us</a></li>
		<li><a href="uploads1.php">Uploads</a></li>
		<li><a href="#" class="active">Courses</a></li>
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

$query = mysql_query('SELECT * FROM Course  WHERE Course_Name="' . $coursename . '";');

while($row = mysql_fetch_array($query,MYSQL_ASSOC)){

	$img=$row['Image'];
	$name=$row['course_name'];
	$desc=$row['Course_desc'];
	$sdate=$row['start_date'];
	$edate=$row['end_date'];
	$author_name=$row['Author_name'];

	echo "<img src='" . $img . "' width='400' height='200' border='40px'></img> <h2> Course Name:" . $name . "</br></br> Course Description:</br><p>" . $desc . "</p></br></br> Course Created By:" . $author_name . "
	</br></br> Course Start Date:" . $sdate . "</br></br> Course End Date:" . $edate . "</h2>";

}

$query1 = mysql_query('SELECT * FROM Course_videos  WHERE Course_Name="' . $coursename . '";');

echo "</br><h2>Course Contents:</h2>";

while($row = mysql_fetch_array($query1,MYSQL_ASSOC)){
	
	$videoname=$row['Video_Name'];
	$link=$row['Link'];

	echo "<h2><a href='view_course_video.php?link=" . $link . "' target='_blank'>" . $videoname . "</a></h2>";
}

$query2 = mysql_query('SELECT * FROM Reviews  WHERE Course_name="' . $coursename . '";');

echo "</br><h2>Course Reviews:</h2>";

while($row = mysql_fetch_array($query2,MYSQL_ASSOC)){
	
	$user = $row['Username'];
	$review = $row['Review'];

	echo "<h2>" . $user . " --> " . $review . " </h2>";
}

$query3 = mysql_query('SELECT AVG(Rate) AS AVERAGE FROM Reviews  WHERE Course_name="' . $coursename . '";');

$row = mysql_fetch_array($query3,MYSQL_ASSOC);
$rating = $row['AVERAGE'];

echo "</br><h2>Overall Rating: " . $rating . "</h2>";

?>

</div><br><br>

<div id="footer">
	<p>Copyright &copy; Murali Kembhavi and Rishabh Sharma</p>
</div>

</body>
</html>