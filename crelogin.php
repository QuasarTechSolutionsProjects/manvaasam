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
  <meta content="Logistics Admn Panel for Manvaasam Logistics APP" name="description">
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
          <h2>Create Login Credentials</h2>
        </div>

        

        <div class="form">
          <form id="myform"  action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>"  method="post"  autocomplete="off" role="form" class="php-email-form" required>
            <div class="form-group">
            <label for="uname">Enter User Name:-</label>
              <input type="text" name="uname" class="form-control" id="name" placeholder="Enter User Name"  required/>
              <div class="validate"></div>
            </div>
            <div class="form-group">
            <label for="email">Enter User Email:-</label>
              <input type="email" class="form-control" name="email" id="email" placeholder="Your User Email" required/>
              <div class="validate"></div>
            </div>
            <div class="form-group">
            <label for="pass">Enter Password:-</label>
              <input type="password" class="form-control" name="pass" id="pass" placeholder="Enter Password" required/>
              <div class="validate"></div>
            </div>
            <div class="form-group">
            <label for="mob">Enter User Mobno:-</label>
              <input type="text" name="mob" class="form-control" id="mob" placeholder="Enter User Mobno" required/>
              <div class="validate"></div>
            </div>
            <div class="form-group">
            <label for="desg">Enter User Designation:-</label>
            <select name="desg" id="desg" class="form-control" required>
            <option value="">Select the Designation </option>
           <option value="<?php echo "Manvaasam";?>">Manvaasam Team </option>
           <option value="<?php echo "StCourier";?>">St Courier Team</option>
            </select>
              <div class="validate"></div>
            </div>
            
            
            <div class="text-center"><button type="submit" name="submit">Create Credential</button></div>
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
  

  $email =$_POST['email'];
  $email = htmlspecialchars(strip_tags(($email)));
  $email =  mysqli_real_escape_string($conn, $email);
  

  $pass =$_POST['pass'];
  $pass = htmlspecialchars(strip_tags(($pass)));
  $pass =  mysqli_real_escape_string($conn, $pass);
  

  $mob =$_POST['mob'];
  $mob = htmlspecialchars(strip_tags(($mob)));
  $mob =  mysqli_real_escape_string($conn, $mob);
  

  $desg =$_POST['desg'];
  $desg = htmlspecialchars(strip_tags(($desg)));
  $desg =  mysqli_real_escape_string($conn, $desg);

  $email_pattern = '/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/';
    preg_match($email_pattern, $email, $email_matches);
    if (!$email_matches[0])
    {
     echo"<script>window.alert('Pls Enter Valid Email Id')</script>;";
    }
    else
    {
      $count=$count+1;
    }

    if (strlen($pass) <= '8' && strlen($pass) >= '15' ) {
      echo"<script>window.alert('Pls Enter Password from 8 to 15 characters')</script>;";
    } elseif (!preg_match("#[0-9]+#", $pass)) {
      echo"<script>window.alert('Enter atleast one number in Password')</script>;";
    } elseif (!preg_match("#[A-Z]+#", $pass)) {
      echo"<script>window.alert('Enter atleast one uppercase in Password')</script>;";
    } elseif (!preg_match("#[a-z]+#", $pass)) {
      echo"<script>window.alert('Enter atleast one lowercase in Password')</script>;";
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

  if(strlen($mob)==10)
  {
    $count=$count+1;
  }
  else
  {
    echo"<script>window.alert('Pls Enter Valid Mobile Number')</script>;";
  }


  if($desg=="Manvaasam")
  {
    $type=0;
  }
  elseif($desg=="StCourier")
  {
    $type=1;
  }
  else
  {
    echo"<script>window.alert('Select Designation')</script>;";
  }

  if($count==4)
  {
    $uname = cryptfun('encrypt',$uname);

    $sql=mysqli_query($conn,"SELECT count(*) as total from team_login where user = '".$uname."'") or die(mysqli_error($conn));
    $rw=mysqli_fetch_array($sql);
    if($rw['total'] > 0)
    {   
      echo"<script>window.alert('Username Already Exists')</script>;";
    }
    else
    {
    $email = cryptfun('encrypt',$email);
    $mob = cryptfun('encrypt',$mob);
    $pass = cryptfun('encrypt',$pass);
    $sqli = "INSERT INTO team_login(user,pswd,email,mobile,type) VALUES ('$uname','$pass','$email','$mob','$type')";
      $sqlresult = mysqli_query($conn,$sqli);
      if($sqlresult == TRUE)
      {
        echo"<script>window.alert('Record Successfully Created')</script>;";
        echo "<script>location.replace('home.php')</script>;";
        
      }
      else
      {
        echo"<script>window.alert('Try Again')</script>;";
      }

  }

  
}
mysqli_close($conn);
}

?>
<?php
}
else
{
  echo "<script>location.replace('index.php')</script>;";
}
?>