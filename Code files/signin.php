<?php

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

$username = test_input($_POST['username']);
$password = test_input($_POST['password']);

session_start();

$sql=mysql_query("SELECT * FROM Members WHERE (Username='$username' AND Password='$password')");
if(mysql_num_rows($sql)==1)
   {

    $row = mysql_fetch_array($sql,MYSQL_ASSOC);
    
    $id=$row['ID'];

    $_SESSION['userid']=$row['ID'];
    $_SESSION['role']=1;
    $_SESSION['username']=$row['Username'];
    mysql_query("UPDATE Members SET Status=1 WHERE ID=" . $id . ";");
    echo '<script> alert("Logged in successfully!"); window.location="index_student.php"; </script>';

   }
  else
    {
    
    $sql=mysql_query("SELECT * FROM Authors WHERE (Username='$username' AND Password='$password')");
    if(mysql_num_rows($sql)==1)
    {
    $row = mysql_fetch_array($sql,MYSQL_ASSOC);
    
    $id=$row['ID'];
    
    $_SESSION['userid']=$row['ID'];
    $_SESSION['role']=2;
    $_SESSION['username']=$row['Username'];
    mysql_query("UPDATE Authors SET Status=1 WHERE ID=" . $id . ";");
    echo '<script> alert("Logged in successfully!"); window.location="index_author.php"; </script>';
    
    }
    else {

    $sql=mysql_query("SELECT * FROM Managers WHERE (Username='$username' AND Password='$password')");
    if(mysql_num_rows($sql)==1)
    {
    
    $row = mysql_fetch_array($sql,MYSQL_ASSOC);
    
    $id=$row['ID'];
    
    $_SESSION['userid']=$row['ID'];
    $_SESSION['role']=3;
    $_SESSION['username']=$row['Username'];
    mysql_query("UPDATE Managers SET Status=1 WHERE ID=" . $id . ";");
    echo '<script> alert("Logged in successfully!"); window.location="index_manager.php"; </script>';

    }
    else {

    $sql=mysql_query("SELECT * FROM Admins WHERE (Username='$username' AND Password='$password')");
    if(mysql_num_rows($sql)==1)
    {
    
    $row = mysql_fetch_array($sql,MYSQL_ASSOC);
    
    $id=$row['ID'];
    
    $_SESSION['userid']=$row['ID'];
    $_SESSION['role']=4;
    $_SESSION['username']=$row['Username'];
    mysql_query("UPDATE Admins SET Status=1 WHERE ID=" . $id . ";");
    echo '<script> alert("Logged in successfully!"); window.location="index_admin.php"; </script>';

    } 
    else echo '<script> alert("Wrong Credentials!"); window.location="signin.htm"; </script>';
    }
    }
    
  }

mysql_close();

?>