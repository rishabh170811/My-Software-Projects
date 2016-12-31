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
	 echo "&nbsp;&nbsp;|&nbsp;&nbsp;<a href='signout.php?role=1' style='color:black;'> Sign Out </a>";  
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
		<li><a href="#">Home</a></li>
		<li><a href="#">About Us</a></li>
		<li><a href="#">Downloads</a></li>
		<li><a href="#">Contact Us</a></li>
		<li><a href="#">Uploads</a></li>
		<li><a href="#">Courses</a></li>
	</ul>
</div>

<div id="content">
	
<?php

$coursename=$_GET['j'];
$testno=$_GET['i'];

$us=$_SESSION['username'];

$mysql_hostname = "localhost";
$mysql_user = "root";
$mysql_password = "murali=1256";
$mysql_database = "library";
$prefix = "";

$bd = mysql_connect($mysql_hostname, $mysql_user, $mysql_password) or die("Could not connect database");
$con=mysql_select_db($mysql_database, $bd) or die("Could not select database");

$sqlq=mysql_query("SELECT * FROM result where (Test_Number='$testno' and Coursename='$coursename' and studentname='$us');");
$m=0;


while($row = mysql_fetch_array($sqlq,MYSQL_ASSOC)){
$m=$m+1;
}
if($m>=2)
{
	echo "<script>alert('Cant give more than 2 tests!');window.location.assign('take_test.php?i=" . $coursename . "');</script>";
}
else {

$i=0;
$y=1;

$arr = array();

for ($j=1;$j<=20;$j++) {
    $arr[$j-1] = $j;
}

mysql_query("CREATE TABLE Temp(FinalAnswers CHAR(1));");
shuffle($arr);

echo "<h1>Course Name: " . $coursename . " & Test Number: " . $testno . "</h1></br>";

for($k=0;$k<15;$k++){
$z=1;

$query=mysql_query("SELECT * FROM test where (Test_Number='$testno' and Course_name='$coursename');");

while($row = mysql_fetch_array($query,MYSQL_ASSOC)){

if($arr[$k]==$z){
$a1=$row['Question'];
$a=$row['OptA'];
$b=$row['OptB'];
$c=$row['OptC'];
$d=$row['OptD'];
$e=$row['Answer'];


echo "<form name='tests' method='post' action='testsubmit.php?i=" . $coursename . "&&j=" . $testno . "&&k=" . $k . "'>";
echo "<h4>" . $y .") &nbsp;" . $a1 . "</h4>
<tr>
<td align='left'> <input type='radio' name='A" . $i . "' value='A' style='color:blue;font-weight:bold'>" . $a . " </td>
</tr>
<tr><td align='left'> <input type='radio' name='A" . $i . "' value='B' style='color:blue;font-weight:bold'>" . $b . " </td>
</tr>
<tr><td align='left'> <input type='radio' name='A" . $i . "' value='C' style='color:blue;font-weight:bold'>" . $c . " </td>
</tr>
<tr><td align='left'> <input type='radio' name='A" . $i . "' value='D' style='color:blue;font-weight:bold'>" . $d . " </td></tr>";

$i=$i+1;
$y=$y+1;

mysql_query("INSERT INTO TEMP VALUES('$e');");
break;

}
else{

	$z=$z+1;
}
}
}

echo '</br></br>';

echo "<input type='submit' name='submit' value='Submit Test' style='color:blue;font-weight:bold'>";
}

?>

</div>

<div id="footer">
	<p>Copyright &copy; Murali Kembhavi and Rishabh Sharma</p>
</div>

</body>
</html>