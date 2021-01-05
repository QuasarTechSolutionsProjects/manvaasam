<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>Manvaasam Logistics</title>
  <meta content="Logistics Admin Panel for Manvaasam Logistics APP" name="description">
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

  <style>
  .login-form 
  {
    width: 340px;
    margin: 50px auto;
  	font-size: 15px;
  }
  
  
.login-form form 
  {
    margin-bottom: 15px;
    background: #f7f7f7;
    box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
    padding: 30px;
  }
  .login-form .btn:hover
{
  background-image:none !important;
 background-color:#ff00ff !important;
} 
  
  </style>

 
</head>

<body>

  

  <!-- ======= Hero Section ======= -->
  <section id="hero">
    
    <div class="hero-container">
      <h1>     </h1>
      <br>
      
    
       
    
      
       <div class="login-form">
       <h2><b>Manvaasam Logistics</b></h2>
       
      <h2>Master Admin Panel Login</h2>

      <?php if(isset($_SESSION['error'])){ ?>
                    <p style="color: red; font-size:13.5px;"><?php echo $_SESSION['error']; ?></p>
            <?php  unset($_SESSION['error']);}?> 
       
        <form id="myform"  action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>"  method="post"  autocomplete="off" role="form" class="php-email-form" required>
          <div class="form-group">
          <h5 style="color:blueviolet;">User Name</h5>
            <input type="text" name="uname"  id="name" placeholder="Enter User Name" required/>
            <div class="validate"></div>
          </div>
          
          <div class="form-group">
          <h5 style="color:blueviolet;">Password</h5>
            <input type="password"  name="pass" id="pass" placeholder="Enter Password" data-rule="password" data-msg="Please enter a correct password" required/>
            <div class="validate"></div>
          </div>
         
          <button type="submit" name="submit" class="btn btn-primary btn-lg">Login</button>
          <p style="font-size:10px; color:green">Any Issues Mail: contact@quasartechsolutions.in</p>
        </form>
        
      </div>
       
    </div>
     
    
  </section><!-- End Hero -->

  <main id="main">
    
    
  </main><!-- End #main -->

  <!-- ======= Footer ======= -->
  <footer id="footer">
    <div class="container">
      <div class="copyright">
        &copy; Copyright <strong><span>Quasar Tech Solutions</span></strong>. All Rights Reserved
      </div>
      <div class="credits">
       
        Designed and Developed by <a href="https://quasartechsolutions.in/" target="_blank">Quasar Tech Solutions</a>
      </div>
    </div>
  </footer><!-- End #footer -->

  <!-- Vendor JS Files -->
  <script src="assets/vendor/jquery/jquery.min.js"></script>
  
  <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>


  

</body>

</html>

<?php
require("config.php");
require("shaping.php");

if(isset($_POST['submit']))
{
  $uname =$_POST['uname'];
  $uname = htmlspecialchars(strip_tags(($uname)));
  $uname =  mysqli_real_escape_string($conn, $uname);
  

  $pass =$_POST['pass'];
  $pass = htmlspecialchars(strip_tags(($pass)));
  $pass =  mysqli_real_escape_string($conn, $pass);
  


      $uname = cryptfun('encrypt',$uname);
  
      $pass = cryptfun('encrypt',$pass);

      $sql = $conn->prepare("SELECT * from mas_ad_det WHERE a_uname = ? AND a_pass = ?");
      $sql->bind_param("ss",$uname,$pass);
     
      $sql->execute();
      $result = $sql->get_result();
      $row = $result->fetch_array(MYSQLI_ASSOC);
      $aname = cryptfun('decrypt',$row['a_uname']);
     
      $passcod = cryptfun('decrypt',$row['a_pass']);
      
  
      
       if(strlen($passcod)>0 && strlen($aname)>0) 
         {
           echo "<script>location.replace('home.php')</script>;";
           $aname=cryptfun('encrypt',$aname);
           $apass=cryptfun('encrypt',$passcod);
           session_start();
           $_SESSION["auname"] = $aname;
           $_SESSION["apass"] = $apass;
         }
        
      else
      {
        
        echo "<script>window.alert('Wrong Credentials')</script>;";
       
      }
      mysqli_close($conn);
  }
    

?>



