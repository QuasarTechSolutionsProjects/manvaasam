<?php
session_start();
if(isset($_SESSION['auname']))
{
    
?>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>Manvaasam Logistics</title>
  <meta content="Logistics Admn Panel for Manvaasam Logistics APp" name="description">
  <meta content="Manvaasam, Manvaasam Logistics, App" name="keywords">

  <!-- Favicons -->
  <link rel="apple-touch-icon" sizes="180x180" href="assets/img/Favicon/apple-touch-icon.png">
  <link rel="icon" type="image/png" sizes="32x32" href="assets/img/Favicon//favicon-32x32.png">
  <link rel="icon" type="image/png" sizes="16x16" href="assets/img/Favicon//favicon-16x16.png">
  <link rel="manifest" href="assets/img/Favicon//site.webmanifest">
  <meta name="msapplication-TileColor" content="#da532c">
  <meta name="theme-color" content="#ffffff">



  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,600,600i,700,700i" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="assets/vendor/icofont/icofont.min.css" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link href="assets/css/style.css" rel="stylesheet">

 
</head>

<body>


  <section id="contact" class="contact">
      <div class="container">
        

        <div class="section-title">
        <h1>Manvaasam Logistics</h1>
          <h2>Update Login Credentials</h2>
        </div>

        

        <div class="form">
          <form id="myform"  action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>"  method="post"  autocomplete="off" role="form" class="php-email-form" required>
            <div class="form-group">
            <label for="uname">Enter User Name:-</label>
              <input type="text" name="uname" class="form-control" id="name" placeholder="Enter User Name" required/>
              <div class="validate"></div>
            </div>
            
            <div class="form-group">
            <label for="pass">Enter New Password:-</label>
              <input type="password" class="form-control" name="pass" id="pass" placeholder="Enter Password" data-rule="password" data-msg="Please enter a correct password" required/>
              <div class="validate"></div>
            </div>
           
            
            
            
            <div class="text-center"><button type="submit" name="submit">Update Credential</button></div>
          </form>
        </div>

      </div>
    </section>
    <!-- ======= Footer ======= -->
  <footer id="footer">
  
  <div class="container"><h6 style="color:white;">
  <a href="logout.php">Logout</a></h6>
    <div class="copyright">
      &copy; Copyright <strong><span>Quasar Tech Solutions</span></strong>. All Rights Reserved
    </div>
    <div class="credits">
     
      Designed and Developed by <a href="https://quasartechsolutions.in/" target="_blank">Quasar Tech Solutions</a>
    </div>
  </div>
</footer><!-- End #footer -->
</body>
</html>
<?php
require("config.php");
require("shaping.php");
if(isset($_POST['submit']))
{
  $count=0;

  $uname =$_POST['uname'];
  $uname = htmlspecialchars(strip_tags(($uname)));
  $uname =  mysqli_real_escape_string($conn, $uname);

  $pass =$_POST['pass'];
  $pass = htmlspecialchars(strip_tags(($pass)));
  $pass =  mysqli_real_escape_string($conn, $pass);

  if (strlen($pass) < '4') 
  {
    echo"<script>window.alert('Pls Enter Password with atleast 4 characters')</script>;";
  }  
 else 
{
  $count=$count+1;
}


if(strlen($uname)!=0)
{
  $count=$count+1;
}
else
{
  echo"<script>window.alert('Pls Enter Valid Username')</script>;";
}

if($count==2)
{
  $uname = cryptfun('encrypt',$uname);
  $sqli=mysqli_query($conn,"SELECT count(*) as total from team_login where user = '".$uname."'") or die(mysqli_error($conn));
    $rw=mysqli_fetch_array($sqli);
    if($rw['total'] == 1)
    {   
      $pass = cryptfun('encrypt',$pass);

      $sql = "UPDATE team_login set pswd = '".$pass."' WHERE user = '".$uname."' ";
      $sqlresult = mysqli_query($conn,$sql);
      if($sqlresult == TRUE)
      {
        echo"<script>window.alert('Record Updated Successfully')</script>;";
        echo "<script>location.replace('home.php')</script>;";
      }
      else
      {
        echo"<script>window.alert('Database Problem, Try after some time')</script>;";
      }
    }
    else
    {
      echo"<script>window.alert('User Does not Exists... ')</script>;";

    }
  
    mysqli_close($conn);
}

}

?>
<?php
}
else
{
  echo "<script>location.replace('index.php')</script>;";
}
?>
