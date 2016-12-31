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
$email1 = test_input($_POST['email']);

$sql=mysql_query("SELECT * FROM Members WHERE (Username='$username' AND Email='$email1')");

if(mysql_num_rows($sql)==1)
   {
    $msg="This is your new passowrd:abc123"; 

    require_once('phpmailer/class.phpmailer.php');

          $mail = new PHPMailer();
          $mail->IsSMTP();
          
          $mail->CharSet="UTF-8";
          $mail->SMTPSecure = 'tls';
          $mail->Host = 'smtp.gmail.com';
          $mail->Port = 465;
          
          $mail->Username = 'virtuallibrary588@gmail.com';
          $mail->Password = 'rishabhsharma';
          $mail->SMTPAuth = true;

          $mail->From = 'no-reply@vlib.com';
          $mail->FromName = 'Virtual Library';
          $email = "muralikembhavi@gmail.com";
          $mail->AddAddress($email);

          $mail->Subject = "Virtual Library Password Change!";
          $mail->Body = $msg;

          $status = $mail->Send();

          if($status) {

            echo "<script> alert('Password Sent....Check your Email ID!'); window.location='signin.htm'; </script>";
          }
          else {
          
            echo "<center style='margin-top:200px'> Mailer Error:";
            echo $mail->ErrorInfo;            
          }
   }
else
   {
     echo "<script> alert('Wrong Credentials!'); window.location='forgot.htm'; </script>";
	 }

mysql_close();

?>