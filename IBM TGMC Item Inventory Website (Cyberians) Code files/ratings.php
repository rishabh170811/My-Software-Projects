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
<meta charset="UTF-8"/>

<link href="default.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">

function validateForm()
    {
    var a=document.forms["rev"]["review"].value;
    var b=document.forms["rev"]["rate"].value;

    if(a==null || a=="")
    {
    alert("Review must be filled!");
    return false;
    }
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
	 echo "&nbsp;&nbsp;|&nbsp;&nbsp;<a href='signout.php' style='color:black'> Sign Out </a>";  
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
		<li><a href="index_student.php" class="active">Home</a></li>
		<li><a href="aboutus.php">About Us</a></li>		
		<li><a href="downloads.php" >Downloads</a></li>		
		<li><a href="contactus.php" >Contact Us</a></li>
		<li><a href="uploads1.php">Uploads</a></li>
		<li><a href="courses_student.php">Courses</a></li>
	</ul>
</div>

<div id="content">
</br>

<?php 

$i = $_GET['i'];

echo '<font style="font-size:20px;font-weight:bold;">Rate and write your review here!</font><br><br>

	<form name="rev" method="post" action="rate-review.php?name=' . $i . '">
		
		<input type="radio" name="rate" value="1">1</input>
		<input type="radio" name="rate" value="2">2</input>
		<input type="radio" name="rate" value="3">3</input>
		<input type="radio" name="rate" value="4">4</input>
		<input type="radio" name="rate" value="5">5</input> &nbsp; (1-Bad, 2-Fair, 3-Good, 4-Very Good, 5-Outstanding) <br><br>

		<textarea cols="24" rows="10" name="review"></textarea></br><br>
    	<input type=submit style="color:blue;font-weight:bold" name=s1 value=" Submit Review " onclick="return validateForm()">
    
    </form>';

?>
    
</div>
</br></br></br>

<div id="footer">
	<p>Copyright &copy; Murali Kembhavi and Rishabh Sharma</p>
</div>
</body>
</html>