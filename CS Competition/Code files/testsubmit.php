<?php

session_start();

$coursename=$_GET['i'];
$testno=$_GET['j'];
$k=$_GET['k'];

$mysql_hostname = "localhost";
$mysql_user = "root";
$mysql_password = "murali=1256";
$mysql_database = "library";
$prefix = "";

$bd = mysql_connect($mysql_hostname, $mysql_user, $mysql_password) or die("Could not connect database");
$con=mysql_select_db($mysql_database, $bd) or die("Could not select database");

$query=mysql_query("SELECT * from Temp ;");
$i=0;
$count=0;

$us=$_SESSION['username'];

while($row = mysql_fetch_array($query,MYSQL_ASSOC)){

$a=$_POST['A'.$i];
echo $a;
$ans=$row['FinalAnswers'];
if($ans==$a)
	$count++;
$i=$i+1;

}

mysql_query("INSERT INTO Result VALUES('$us','$coursename','$testno','$count')");
$sqla=mysql_query("SELECT studentname,Coursename,Test_Number,AVG(Marks) FROM Result GROUP BY studentname,Coursename,Test_Number;");

while($row = mysql_fetch_array($sqla,MYSQL_ASSOC)){

$usa=$row['studentname'];
$cs=$row['Coursename'];
$tno=$row['Test_Number'];
$avg=$row['AVG(Marks)'];

$i=0;
$sqlb=mysql_query("SELECT * FROM final_result WHERE (studentname='$usa' AND coursename='$cs' AND Test_Number='$tno');");

while($row = mysql_fetch_array($sqlb,MYSQL_ASSOC)){

$i=$i+1;
}

if($i>0)
	mysql_query("UPDATE  final_result SET Student_Avg='$avg' WHERE (studentname='$usa' AND coursename='$cs' AND Test_Number='$tno');");
else
	mysql_query("INSERT INTO final_result VALUES('$usa','$cs','$tno','$avg')");
}

mysql_query("DROP TABLE temp;");

echo "<script>alert('Congrats!! Test successfully Submitted!'); location.assign('index_student.php'); </script>";

?>