<?php

session_start();
if($_SESSION['role']!=3)
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
	<a href="signup.htm" style="color:black;">Sign Up</a>&nbsp;&nbsp;|
	<a href="signin.htm" style="color:black;">Sign In</a>&nbsp;&nbsp;|
	</p>';
	}
	
	?>

</div>
<div id="menu">
	<ul>
		<li><a href="index_manager.php" class="active">Home</a></li>
		<li><a href="aboutus.php">About Us</a></li>		
		<li><a href="contactus.php">Contact Us</a></li>
		<li><a href="uploads_manager.php">Uploads</a></li>
		<li><a href="courses_manager.php">Courses</a></li>
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
// create the query
$query = mysql_query("SELECT * FROM Pending_resources");

echo "<div class='CSS_Table_Example' style='width:800px;height:50px'> <table name='header'> <tr> <td> List of pending Resources!</td> </tr> </table> </div>";
echo "<div class='CSS_Table_Example' style='width:800px;height:50px'> <table name='header1'>
 <tr> 
 <td> First Name </td>
 <td> Last Name </td>
 <td> Username </td>
 <td colspan=2> Approve/Reject </td> 
 </tr> 
 </table> </div>";

//echo "<input type='text' name='search' value='Search here' /> <input type='submit' name='submitb' value='Search' style='font-weight:bold;color:blue' /></br></br></br>";
$i=0;
while($row = mysql_fetch_array($query,MYSQL_ASSOC)){

$i = $row['ID'];
$Name = $row['Name'];

echo "<div class='CSS_Table_Example' style='width:800px;height:100px'><table name='members'>
<tr>
<td align='left'>" . $Name . "</td>
<td align='left'> <input type='button' name='approveb' value='Approve' style='color:blue;font-weight:bold' onclick=window.location='approve.php?id=" . $i . "&role=4'></td>
<td align='left'> <input type='button' name='rejectb' value='Reject' style='color:blue;font-weight:bold' onclick=window.location='reject.php?id=" . $i . "&role=4'></td>
</tr>
</table></div>";
}

?>

</div>
</br><br><br><br><br><br></br></br></br></br></br></br></br></br></br></br></br>

<div id="footer">
	<p>Copyright &copy; Murali Kembhavi and Rishabh Sharma</p>
</div>

</body>
</html>