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


  <section id="contact" class="contact">
      <div class="container">
        

        <div class="section-title">
        <h1>Manvaasam Logistics</h1>
          <h2>Download Invoice </h2>
        </div>

        

        <div class="form">
          <form id="myform"  action="invoice.php"  method="get"  autocomplete="off" role="form" class="php-email-form" required>
            <div class="form-group">
            <label for="pid">Enter Manvaasam Package Id:-</label>
              <input type="text" name="manid" class="form-control" id="pid" placeholder="Enter Manvaasam Package Id"  required/>
              <div class="validate"></div>
            </div>
            
        
            <div class="text-center"><button type="submit">View Invoice</button></div>
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
}
else
{
  echo "<script>location.replace('index.php')</script>;";
}
?>