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
		<li><a href="#" class="active">Home</a></li>
		<li><a href="aboutus.php">About Us</a></li>
		<li><a href="downloads.php">Downloads</a></li>
		<li><a href="contactus.php">Contact Us</a></li>
		<li><a href="uploads1.php">Uploads</a></li>
		<li><a href="courses_student.php">Courses</a></li>
		<li><a href="courses_stats.php">Course Stats</a></li>
	</ul>
</div>

<div id="content">
</br>

<?php
$mysql_hostname = "localhost";
$mysql_user = "root";
$mysql_password = "murali=1256";
$mysql_database = "library";
$prefix = "";
$bd = mysql_connect($mysql_hostname, $mysql_user, $mysql_password) or die("Could not connect database");
$con=mysql_select_db($mysql_database, $bd) or die("Could not select database");

$username=$_SESSION['username'];

$query = mysql_query("SELECT * FROM Enrolled_courses where Username='" . $username . "';");

$cnt = mysql_num_rows($query);

if($cnt!=0)
	echo "<div class='CSS_Table_Example' style='width:800px;height:50px'> <table name='header'> <tr> <td> You have enrolled for following courses!</td> </tr> </table> </div>";
else
	echo "<div class='CSS_Table_Example' style='width:800px;height:50px'> <table name='header'> <tr> <td> No enrolled courses found!</td> </tr> </table> </div>";

while($row = mysql_fetch_array($query,MYSQL_ASSOC)){

$name = $row['Course_name'];

echo "<div class='CSS_Table_Example' style='width:800px;height:100px'><table name='courses'>
<tr>
<td align='left'>" . $name . "</td>
<td align='left'> <a  href='view_course.php?i=" . $name . "' target='_blank' style='color:black'>View </td>
<td align='left'> <input type='button' name='test' value='Take Test' style='color:blue;font-weight:bold' onclick=window.location='take_test.php?i=" . $name . "'></td>
<td align='left'> <input type='button' name='unenroll' value='Un-enroll' style='color:blue;font-weight:bold' onclick=window.location='unenroll.php?i=" . $name . "'></td>
<td align='left'> <input type='button' name='rate' value='Rate & Review ' style='color:blue;font-weight:bold' onclick=window.location='ratings.php?i=" . $name . "'></td>
</td>
</tr>
</table></div>";

}

?>

</div>
</br></br></br></br></br></br></br></br></br></br></br></br></br></br></br><br><br>

<div id="footer">
	<p>Copyright &copy; Murali Kembhavi and Rishabh Sharma</p>
</div>

</body>
</html>