<?php
session_start();
if($_SESSION['role']!=4)
    { echo "<h1>Access Denied</h1>"; exit(); }

?>

<html>
<head>

<script type="text/javascript">
    function validateForm()
    {
    var a=document.forms["courses"]["name"].value;
    var b=document.forms["courses"]["sdate"].value;
    var c=document.forms["courses"]["edate"].value;
    var d=document.forms["courses"]["desc"].value;
    var e=document.forms["courses"]["file"].value;

    if ((a==null || a=="") && (b==null || b=="") && (c==null || c=="") && (d==null || d=="") && (e==null || e==""))
    {
    alert("All the Fields must be filled!");
    return false;
    }

    if (a==null || a=="")
    {
    alert("Course name must be filled!");
    return false;
    }
    
    if (b==null || b=="")
    {
    alert("Start date must be filled!");
    return false;
    }

    if (c==null || c=="")
    {
    alert("End date must be filled!");
    return false;
    }

    if (d==null || d=="")
    {
    alert("Course Description must be filled!");
    return false;
    }


    if (c-b<30)
    {
    alert("Invalid start and end dates!");
    return false;
    }
    
    }
</script>
    
<title>Virtual Library</title>

<meta name="keywords" content="" />
<meta name="description" content="" />

<link href="default.css" rel="stylesheet" type="text/css" />

</head>
<body onload="document.courses.name.focus()">
<div id="header">
    <h1>Virtual Library</h1>
    <h2>Learning made simple</h2>

    <?php
    
    if(isset($_SESSION['username'])) {

     echo'<p style="color:black;font-size:12px" align="right"> Hi ';  
     echo $_SESSION['username'];
     echo "&nbsp;&nbsp;|&nbsp;&nbsp;<a href='signout.php?role=3' style='color:black;'> Sign Out </a></p>";  
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
        <li><a href="index_admin.php">Home</a></li>
        <li><a href="aboutus.php">About Us</a></li>     
        <li><a href="downloads.php">Downloads</a></li>
        <li><a href="contactus.php">Contact Us</a></li>
        <li><a href="uploads_admin.php">Uploads</a></li>
        <li><a href="#" class="active">Courses</a></li>
    </ul>
</div>

<div id="content">
<h3 style="color:black;font-weight:bold">Create a course here!</h3>

<form name="courses" method="post" action="insertcourse.php?role=4" enctype="multipart/form-data" >

<h5>Course Name:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type=text name="name" size=30>

<h5>Course Desc:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<textarea name="desc">Enter course description here!!</textarea>

<h5>Start Date:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type=date name="sdate" size=30>

<h5>End Date:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type=date name="edate" size=30></br>

<h5>Course Image:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="file" style="color:blue;font-weight:bold" name="file" id="file"></br></br>

<input type=submit style="color:blue;font-weight:bold" name="submitb" value="Create Course" onclick="return validateForm()" /></br>
<b> *All the fields are required </b>
</form>
</div>
<br><br><br>

<div id="footer">
    <p>Copyright &copy; Murali Kembhavi and Rishabh Sharma</p>
</div>

</body>
</html>