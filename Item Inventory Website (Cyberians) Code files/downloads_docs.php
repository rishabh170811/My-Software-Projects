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

<script type="text/javascript">

function showUser(str)
{
if (str=="")
  {
  document.getElementById("txtHint").innerHTML="";
  return;
  }
if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
  xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
xmlhttp.onreadystatechange=function()
  {
  if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
    document.getElementById("txtHint").innerHTML=xmlhttp.responseText;
    }
  }
xmlhttp.open("GET","getresource.php?q="+str+"&type=3",true);
xmlhttp.send();
}

</script>

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

		if(isset($_SESSION['username']) && $_SESSION['role']==1) {

		echo '<li><a href="index_student.php">Home</a></li>
		<li><a href="aboutus.php">About Us</a></li>		
		<li><a href="downloads.php" class="active">Downloads</a></li>
		<li><a href="contactus.php" >Contact Us</a></li>
		<li><a href="uploads1.php">Uploads</a></li>
		<li><a href="courses_student.php">Courses</a></li>';

		}
		else if(isset($_SESSION['username']) && $_SESSION['role']==4) {

		echo '<li><a href="index_admin.php">Home</a></li>
		<li><a href="aboutus.php" >About Us</a></li>
		<li><a href="downloads.php" class="active">Downloads</a></li>		
		<li><a href="contactus.php" >Contact Us</a></li>
		<li><a href="uploads_admin.php">Uploads</a></li>
		<li><a href="courses_admin.php">Courses</a></li>';

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

// create the query
$query = mysql_query("SELECT * FROM Research_papers");

$cnt = mysql_num_rows($query);

echo "<p align='right'>Search: <input type='text' name='search' onchange='showUser(this.value)' /></p>";

if($cnt==0)
	echo "<div class='CSS_Table_Example' style='width:800px;height:50px'> <table name='header'> <tr> <td> No research papers available!</td> </tr> </table> </div>";
else
	echo "<div class='CSS_Table_Example' style='width:800px;height:50px'> <table name='header'> <tr> <td> The following research papers are available for download!</td> </tr> </table> </div>";

while($row = mysql_fetch_array($query,MYSQL_ASSOC)){

$file_name = $row['Name'];
$i=$row['ID'];

echo "<div class='CSS_Table_Example' style='width:800px;height:100px'><table name='docs'>
<tr>
<td align='left'>" . $file_name . "</td>
<td align='left'> <a  href='view_resources.php?id=" . $i . "&type=3' target='_blank' style='color:black'>View </td>
<td align='left'> <input type='button' name='download' value='Download' style='color:blue;font-weight:bold' onclick=window.location='download_docs.php?id=" . $i . "'></td>
<td align='left'> <input type='button' name='bmark' value='Bookmark' style='color:blue;font-weight:bold' onclick=window.location='bookmarks.php?name=" . $file_name . "&type=2&id=" . $i . "'></td>
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