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
	
	<p style="color:black;font-size:11.5px" align="right">
	<a href="signup.htm" style="color:black">Sign Up</a>&nbsp;&nbsp;|
	<a href="signin.htm" style="color:black">Sign In</a>&nbsp;&nbsp;|</p>
	
</div>
<div id="menu">
	<ul>
		<li><a href="index.php" class="active">Home</a></li>
		<li><a href="aboutus.php">About Us</a></li>		
		<li><a href="contactus.php" >Contact Us</a></li>
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

$status=1;
$query = mysql_query("SELECT * FROM Research_papers WHERE Status='$status'");

echo "<div class='CSS_Table_Example' style='width:800px;height:50px'> <table name='header'> <tr> <td> The following are sample Research papers!</td> </tr> </table> </div>";

$i=0;
while($row = mysql_fetch_array($query,MYSQL_ASSOC)){

$file_name = $row['Name'];
$i=$row['ID'];

echo "<div class='CSS_Table_Example' style='width:800px;height:100px'><table name='videos'>
<tr>
<td align='left'>" . $file_name . "</td>
<td align='left'> <a  href='view_resources.php?id=" . $i . "&type=2' target='_blank' style='color:black'>View</td>
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