<?php

session_start();

$mysql_hostname = "localhost";
$mysql_user = "root";
$mysql_password = "murali=1256";
$mysql_database = "library";
$prefix = "";
$bd = mysql_connect($mysql_hostname, $mysql_user, $mysql_password) or die("Could not connect database");
$con=mysql_select_db($mysql_database, $bd) or die("Could not select database");

function test_input($data)
{
  $data = trim($data);
  $data = stripslashes($data);
  $data = htmlspecialchars($data);
  return $data;
}

$no = $_GET['i'];
$name = $_GET['name'];

$ques = test_input($_POST['ques']);
$opta = test_input($_POST['ques1']);
$optb = test_input($_POST['ques2']);
$optc = test_input($_POST['ques3']);
$optd = test_input($_POST['ques4']);
$ans = test_input($_POST['ans']);

if($ques==""||$opta==""||$optb==""||$optc==""||$optd==""||$ans=="") {

	echo "<script> alert('type all fields author');window.location='course_test.php';</script>";
}

else {

	   mysql_query("INSERT INTO test VALUES('$no', '$name', '$ques','$ans','$opta', '$optb', '$optc','$optd')");
     echo "<script> alert('successfully added question'); window.location='course_test.php; </script>";
}

$i=1;
$query=mysql_query("SELECT * from test where Course_Name='$name' AND Test_Number='$no'");
echo "<h1>".$name."</h1></br></br>";
while($row = mysql_fetch_array($query,MYSQL_ASSOC)){

$a1=$row['Question'];
$a=$row['OptA'];
$b=$row['OptB'];
$c=$row['OptC'];
$d=$row['OptD'];
$e=$row['Answer'];

echo "<h4>(".$i.")".$a1."</h4>
<tr>
<td align='left'><p>A)".$a."</p></td>
</tr>
<tr><td align='left'><p>B)".$b."</p> </td>
</tr>
<tr><td align='left'><p>C) ".$c."</td>
</tr>
<tr><td align='left'><p>D)".$d."</td></tr>
<tr><td align='left'> <p>Answer:".$e."</td></tr>";

$i=$i+1;
}

?>