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

 
</head>

<body>
  
  
  
  <!-- ======= Hero Section ======= -->
  <section id="hero">
    <br>
    <div class="hero-container">
      <h1>Manvaasam Logistics</h1>
      <h2>Master Admin Panel</h2>
      
      
       <div class="container">
       <button type="button" onclick="window.location.href='crelogin.php'"class="btn btn-primary btn-lg btn-block">Create Login Credentials</button>
       <br>
      
      
       <button type="button" onclick="window.location.href='changepass.php'" class="btn btn-primary btn-lg btn-block">Change Password</button>
        <br>

        <button type="button" onclick="window.location.href='invreport.php'" class="btn btn-primary btn-lg btn-block">Download Invoice</button>
        <br>

        <button type="button" onclick="window.location.href='packreport.php'" class="btn btn-primary btn-lg btn-block">Packages Report</button>
        <br>

        <button type="button" onclick="window.location.href='loginreport.php'" class="btn btn-primary btn-lg btn-block">Manvaasam Login Report</button>
        <br>

      
      </div>
       
    </div>
    
    
  </section><!-- End Hero -->

  <main id="main">
    
    
  </main><!-- End #main -->

  <!-- ======= Footer ======= -->
  <footer id="footer">
  
    <div class="container"><h6 style="color:white;">
  <button type="button" class="btn btn-danger"><a href="logout.php" style="color: white">Logout</a></h6></button>
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
}
else
{
  echo "<script>location.replace('index.php')</script>;";
}
?>
