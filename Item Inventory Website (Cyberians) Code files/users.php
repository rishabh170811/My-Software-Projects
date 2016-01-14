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
	<p style="color:black;font-size:18x" align="right">
	<a href="signup.htm">Sign out</a>&nbsp;&nbsp;|
	<a href="signin.htm">Hello muralik</a>
	</p>
	
</div>
<div id="menu">
	<ul>
		<li><a href="index_admin.php">Home</a></li>
		<li><a href="aboutus.htm">About Us</a></li>		
		<li><a href="downloads.php" class="active">Downloads</a></li>
		<li><a href="contactus.htm">Contact Us</a></li>
		<li><a href="uploads.htm">Uploads</a></li>
		<li><a href="courses_student.htm">Courses</a></li>
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
$query = mysql_query("SELECT * FROM Members");
session_start();

echo "<div class='CSS_Table_Example' style='width:800px;height:50px'> <table name='header'> <tr> <td> List of Users!</td> </tr> </table> </div>";
echo "<div class='CSS_Table_Example' style='width:800px;height:50px'> <table name='header1'>
 <tr> 
 <td> First Name </td>
 <td> Last Name </td>
 <td> Username </td>
 <td> Block/Delete </td> 
 </tr> 
 </table> </div>";

$i=0;
while($row = mysql_fetch_array($query,MYSQL_ASSOC)){

$i = $row['ID'];
$Fname = $row['F_Name'];
$Lname = $row['L_Name'];
$username = $row['Username'];

echo "<div class='CSS_Table_Example' style='width:800px;height:100px'><table name='members'>
<tr>
<td align='left'>" . $Fname . "</td>
<td align='left'>" . $Lname . "</td>
<td align='left'>" . $username . "</td>
<td align='left'> <input type='button' name='block' value='Block' style='color:blue;font-weight:bold' onclick=window.location='delete.php?id=" . $i . "&role=1'></td>
</tr>
</table></div>";

}
?>

</div>
</br></br></br></br></br></br></br></br></br></br>

<div id="footer">
	<p>Copyright &copy; Murali Kembhavi and Rishabh Sharma</p>
</div>

</body>
</html>