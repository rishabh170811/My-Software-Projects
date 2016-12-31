<?php

session_start();

$q = $_GET['q'];

$type = $_GET['type'];

$mysql_hostname = "localhost";
$mysql_user = "root";
$mysql_password = "murali=1256";
$mysql_database = "library";
$prefix = "";

$bd = mysql_connect($mysql_hostname, $mysql_user, $mysql_password) or die("Could not connect database");
$con=mysql_select_db($mysql_database, $bd) or die("Could not select database");

if($type==5) {
$sqla=mysql_query("SELECT * FROM Course WHERE course_name LIKE '%" . $q . "%' ;");

while($row = mysql_fetch_array($sqla,MYSQL_ASSOC)){
	
  $a=$row['course_name'];
  echo "</br><a href='view_course.php?i=" . $a . "'>" .$a. "</a>";    
}
}
else {
 if($type==4)
		$sqla=mysql_query("SELECT * FROM Videos WHERE Name LIKE '%" . $q . "%' ;");
	 else if($type==3)
			$sqla=mysql_query("SELECT * FROM Research_papers WHERE Name LIKE '%" . $q . "%' ;");
		  else if($type==2)
				 $sqla=mysql_query("SELECT * FROM Slides WHERE Name LIKE '%" . $q . "%' ;");
			   else
			   	 $sqla=mysql_query("SELECT * FROM E_books WHERE Name LIKE '%" . $q . "%' ;");
}

while($row = mysql_fetch_array($sqla,MYSQL_ASSOC)){
	
  $a = $row['Name'];
  $id = $row['ID'];

  echo "</br><a href='view_resources.php?id=" . $id . "&type=" . $type . "'>" . $a . "</a>";    
}

//mysql_close();

?> 