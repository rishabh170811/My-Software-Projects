<?php

session_start();
if(!isset($_SESSION['username']))
	{ echo "<h1>Access Denied</h1>"; exit(); }

$mysql_hostname = "localhost";
$mysql_user = "root";
$mysql_password = "murali=1256";
$mysql_database = "library";
$prefix = "";
$bd = mysql_connect($mysql_hostname, $mysql_user, $mysql_password) or die("Could not connect database");
$con=mysql_select_db($mysql_database, $bd) or die("Could not select database");

?>

<html>
<head>
<title>Virtual Library</title>

<meta name="keywords" content="" />
<meta name="description" content="" />

<link href="default.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="table.css" type="text/css"/>

<script type="text/javascript">
    function validateForm()
    {

    var c=document.forms["test"]["ques"].value;
    var d=document.forms["test"]["ques1"].value;
    var e=document.forms["test"]["ques2"].value;
    var f=document.forms["test"]["ques3"].value;
    var g=document.forms["test"]["ques4"].value;
    
    if ((c==null || c=="") && (d==null || d=="") && (e==null || e=="") && (f==null || f=="") && (g==null || g==""))
    {
    alert("All the Fields must be filled!");
    return false;
    }
    
    if (c==null || c=="")
    {
    alert("Question must be filled!");
    return false;
    }
       
    if (d==null || d=="")
    {
    alert("Options must be filled!");
    return false;
    }

    if (e==null || e=="")
    {
    alert("Options must be filled!");
    return false;
    }

    if (f==null || f=="")
    {
    alert("Options must be filled!");
    return false;
    }


    if (g==null || g=="")
    {
    alert("Options must be filled!");
    return false;
    }
    
    }
    </script>

</head>

<body background="images/lib.jpg" onload="document.test.reset(); document.test.ques.focus();">

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
	<a href="test.htm">Sign Up</a>&nbsp;&nbsp;|
	<a href="signin.htm">Sign In</a>&nbsp;&nbsp;|
	</p>';
	}
	?>

	
</div>

<div id="menu">
	<ul>
		<li><a href="index_author.php" class="active">Home</a></li>
		<li><a href="aboutus.php">About Us</a></li>
		<li><a href="contactus.php">Contact Us</a></li>
		<li><a href="uploads_author.php">Uploads</a></li>
		<li><a href="courses_author.php">Courses</a></li>
	</ul>
</div>

<div id="content">

<?php

    
    $name1 = $_GET['name'];

    $i=1;

    $query = mysql_query("SELECT * FROM test WHERE Course_name='$name1'");
    
    while($row = mysql_fetch_array($query,MYSQL_ASSOC)){
        if($i<$row['Test_Number'])
            $i=$row['Test_Number'];

    }

    $sqla = mysql_query("SELECT * FROM test WHERE(Course_name='$name1' AND Test_Number='$i')");
    
    $j=0;
    while($row = mysql_fetch_array($sqla,MYSQL_ASSOC)){
    $j++;
    }
    if($j>=20){
        $i=$i+1;
    }

    echo"<form name='test' method='post' action='submitques.php?name=" . $name1 . "&i=" . $i . "'>";
    
    echo"<h5>Test Number:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type='text' name='number' size=10 value='" . $i . "' disabled></br>
 	<h5>Course_Name:&nbsp;&nbsp;
    <input type=text name='coursename' size=40 value='" . $name1 . "' disabled>";
	
    echo"<h5>Question:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type=text name='ques' size=40></br></br>";
	
    echo"<h5>Option A:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type=text name='ques1' size=10></br></br>";
	
    echo"<h5>Option B:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type=text name='ques2' size=10></br></br>";
	
    echo"<h5>Option C:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type=text name='ques3' size=10></br></br>";
	
    echo"<h5>Option D:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type=text name='ques4' size=10></br></br>
	<h5>ANSWER:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	
    echo   "<select name='ans'>
   		   <option value='A'>A</option>
   		   <option value='B'>B</option>
   		   <option value='C'>C</option>
   		   <option value='D'>D</option></select></br></br>
           <input type='submit' name='submitb' value='Submit Questions' size=30 style='color:blue;font-weight:bold'></br></form>";	 

?>

</div><br>

<div id="footer">
	<p>Copyright &copy; Murali Kembhavi and Rishabh Sharma</p>
</div>

</body>
</html>