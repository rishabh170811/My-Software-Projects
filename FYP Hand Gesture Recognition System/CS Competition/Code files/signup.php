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


<?php
session_start();

function test_input($data)
{
  $data = trim($data);
  $data = stripslashes($data);
  $data = htmlspecialchars($data);
  return $data;
}

$fname = test_input($_POST["fname"]);
if (!preg_match("/^[a-zA-Z]*$/",$fname))
  {
  echo "<script> alert('First Name should contain only alphabets.....Registeration Failed!'); window.location='signup.htm'; </script>";
  }

$lname = test_input($_POST["lname"]);
if (!preg_match("/^[a-zA-Z]*$/",$lname))
  {
  echo "<script> alert('Last Name should contain only alphabets.....Registeration Failed!'); window.location='signup.htm'; </script>";
  }

$username = test_input($_POST["username"]);
if (!preg_match("/^[a-zA-Z0-9]*$/",$username))
  {
  echo "<script> alert('Username should contain only alphabets and numbers.....Registeration Failed!'); window.location='signup.htm'; </script>";
  }

$email = test_input($_POST["email"]);
if (!preg_match("/([\w\-]+\@[\w\-]+\.[\w\-]+)/",$email))
  {
  echo "<script> alert('Invalid Email ID.....Registeration Failed!'); window.location='signup.htm'; </script>";
  }

$password = test_input($_POST["password"]);  

$mobno = test_input($_POST["mobno"]);
if (!preg_match("/[1-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]/",$mobno))
  {
  echo "<script> alert('Invalid Mobile No. .....Registeration Failed!'); window.location='signup.htm'; </script>";
  }

$role = $_POST["role"];

if($role==1) {
  $id=0;
  $status=0;
  $sql=mysql_query("SELECT * FROM Members WHERE (Username='$username' OR Email='$email')");

  if(mysql_num_rows($sql)==1)
   {
    echo "<script> alert('Username or Email ID already exists!'); window.location='signup.htm'; </script>";
   }
   else {
    mysql_query("INSERT INTO Members VALUES('$id','$fname', '$lname', '$email', '$username', '$password', '$mobno', $status)");
    echo "<script> alert('Registered successfully!'); window.location='signin.htm'; </script>";
    }
}
else 
  if($role==2) {
  $id=0;
  $sql=mysql_query("SELECT * FROM Authors WHERE (Username='$username' OR Email='$email')");
  
  $sql1=mysql_query("SELECT * FROM Pending_authors WHERE (Username='$username' OR Email='$email')");

  if(mysql_num_rows($sql)==1 || mysql_num_rows($sql1)==1)
   {
    echo "<script> alert('Username or Email ID already exists!'); window.location='signup.htm'; </script>";
   }
   else {
    mysql_query("INSERT INTO Pending_authors VALUES('$id', '$fname', '$lname', '$email', '$username', '$password', '$mobno')");
    echo "<script> alert('Pending admin approval for the postion of author!'); window.location='index_student.htm'; </script>";
    }
  }
  else {
  $id=0;
  $sql=mysql_query("SELECT * FROM Managers WHERE (Username='$username' OR Email='$email')");

  $sql1=mysql_query("SELECT * FROM Pending_managers WHERE (Username='$username' OR Email='$email')");

  if(mysql_num_rows($sql)==1 || mysql_num_rows($sql1)==1)
   {
    echo "<script> alert('Username or Email ID already exists!'); window.location='signup.htm'; </script>";
   }
   else {
    $id=0;
    mysql_query("INSERT INTO Pending_managers VALUES('$id', '$fname', '$lname', '$email', '$username', '$password', '$mobno')");
    echo "<script> alert('Pending admin approval for the postion of the Content Manager!'); window.location='index_student.htm'; </script>";
    }
  }

mysql_close($con);
?>