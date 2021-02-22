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
  <style>
   table
    {
     border-collapse: collapse;
     width: 100%;
     color: #588c7e;
     font-family: monospace;
     font-size: 15px;
     text-align: left;
     border: 1px solid black;
    }
  th 
  {
    background-color: #588c7e;
    color: white;
    border: 1px solid black;
  }
  td
  {
    border: 1px solid black;;
  }
tr:nth-child(even) {background-color: #f2f2f2}
</style>

 
</head>
<?php
require("config.php");
require("shaping.php");
$admintype=0;
$retrieve= mysqli_query($conn,"SELECT * from team_login WHERE type = '".$admintype."'") or die(mysqli_error($conn));

$option = '';
while($row = mysqli_fetch_array($retrieve))
{
 $prodname=$row['user'];
 
 $prodname=cryptfun('decrypt',$prodname);
 
 $option .= '<option value = "'.$prodname.'">'.$prodname.'</option>';
}
?>

<body>


  <section id="contact" class="contact">
      <div class="container">
        

        <div class="section-title">
        <h1>Manvaasam Logistics</h1>
          <h2>Download Reports</h2>
        </div>

        

        <div class="form">
          <form id="myform"  action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>"  method="post"  autocomplete="off" role="form" class="php-email-form" >
           
            <div class="form-group">
            <label for="mname">Select Manvaasam Admin Name:-<span class="required">*</span></label>
            <select name="mname" id="mname" class="form-control" required>
            <option value="">Select the Admin Name</option>
            <option value = "All">All Admin Reports</option>
                   <?php echo $option; ?> 
            
            </select>
              <div class="validate"></div>
            </div>
                   <div class="form-group ">
                    <label for="date" >Package Start Date <span class="required">*</span></label>
                    
                    <input type="date" class="form-control" name="sdate" id="sdate" required>
                  
                    </div>
                    <div class="form-group ">
                    <label for="date" >Package End Date <span class="required">*</span></label>
                    
                    <input type="date" class="form-control" name="edate" id="edate" required>
                  
                    </div>
            
            
            <center><div class="col-lg-10"><button type="submit" name="view">View Reports</button>
            <button type="submit" name="down">Download Reports</button></div>
            </center>
            
            
            
          </form>
        </div>
        </div>
    </section>
      
<?php

  
if(isset($_POST['view']))
{
  echo'<table>';
  echo '<tr>';
  echo '<th>S.No</th>';
  echo '<th>Manvaasam ID</th>';
  echo '<th>Sender Name</th>';
  echo '<th>Sender Mobile Number</th>';
  echo '<th>Sender Address</th>';
  echo '<th>Sender Pincode</th>';
  echo '<th>Receiver Name</th>';
  echo '<th>Receiver Mobile Number</th>';
  echo '<th>Receiver Address</th>';
  echo '<th>Receiver Pincode</th>';
  echo '<th>Package Amount</th>';
  echo '<th>Package Date</th>';
  echo '<th>Package Time</th>';
  echo '<th>Package Created By:-</th>';
  echo '<th>St Courier ID</th>';
  echo '</tr>';
  $mname=$_POST['mname'];
  $sdate=$_POST['sdate'];
  $edate=$_POST['edate'];
  
  if($mname!="All")
  {
    $mname=cryptfun('encrypt',$mname);


  $sql = "SELECT * FROM package WHERE c_uname ='".$mname."' AND p_date BETWEEN '$sdate' AND '$edate' ";

  $result = mysqli_query($conn, $sql);
  
  if (mysqli_num_rows($result) > 0) {
    
     $i=1;
    
    while($row = mysqli_fetch_assoc($result)) 
    {
        $sid=$row["st_courid"] ;
        if($sid==null)
        {
            $sid="Status Not Updated";
        }
        else
        {
          $sid=cryptfun('decrypt',$sid);  
        }
      echo "<tr> <td>" . $i . "</td> <td>" . cryptfun('decrypt',$row["man_id"])."</td> <td>".cryptfun('decrypt',$row["f_name"]) ."</td> <td>".cryptfun('decrypt',$row["f_mobno"]) . "</td> <td>".cryptfun('decrypt',$row["f_addr"]). "</td> <td>" . cryptfun('decrypt',$row["f_pcode"]). "</td> <td>" . cryptfun('decrypt',$row["t_name"])."</td> <td>".cryptfun('decrypt',$row["t_mobno"]) ."</td> <td>".cryptfun('decrypt',$row["t_addr"]) . "</td> <td>".cryptfun('decrypt',$row["t_pcode"])."</td> <td>"."Rs ".cryptfun('decrypt',$row["p_amt"]) . "</td> <td>" .$row["p_date"] . "</td> <td>" .$row["p_time"] . "</td> <td>" .cryptfun('decrypt',$row["c_uname"])."</td> <td>".$sid . "</td></tr>";
    
   $i++;
}
    echo "</table>";
    }
 else
{
  echo "0 results";
}
  

mysqli_close($conn);
  }


  else
  {
    $sql = "SELECT * FROM package WHERE sno > 1 AND p_date BETWEEN '$sdate' AND '$edate'";
  $result = mysqli_query($conn, $sql);
  
  if (mysqli_num_rows($result) > 0) {
    // output data of each row
     $i=1;
    
    while($row = mysqli_fetch_assoc($result)) 
    {
        $sid=$row["st_courid"] ;
        if($sid==null)
        {
            $sid="Status Not Updated";
        }
        else
        {
          $sid=cryptfun('decrypt',$sid);  
        }
      echo "<tr> <td>" . $i . "</td> <td>" . cryptfun('decrypt',$row["man_id"])."</td> <td>".cryptfun('decrypt',$row["f_name"]) ."</td> <td>".cryptfun('decrypt',$row["f_mobno"]) . "</td> <td>".cryptfun('decrypt',$row["f_addr"]). "</td> <td>" . cryptfun('decrypt',$row["f_pcode"]). "</td> <td>" . cryptfun('decrypt',$row["t_name"])."</td> <td>".cryptfun('decrypt',$row["t_mobno"]) ."</td> <td>".cryptfun('decrypt',$row["t_addr"]) . "</td> <td>".cryptfun('decrypt',$row["t_pcode"])."</td> <td>"."Rs ".cryptfun('decrypt',$row["p_amt"]) . "</td> <td>" .$row["p_date"] . "</td> <td>" .$row["p_time"] . "</td> <td>" .cryptfun('decrypt',$row["c_uname"])."</td> <td>".$sid . "</td></tr>";
    
   $i++;
}
    echo "</table>";
    }
 else
{
  echo "0 results";
}
  

mysqli_close($conn);
  }

}

?>

      <script>
      (function(window, location) {
history.replaceState(null, document.title, location.pathname+"#!/history");
history.pushState(null, document.title, location.pathname);

window.addEventListener("popstate", function() {
  if(location.hash === "#!/history") {
    history.replaceState(null, document.title, location.pathname);
    setTimeout(function(){
      location.replace("home.php");
    },10);
  }
}, false);
}(window, location));
      </script>
    
</body>
</html>
<?php
if(isset($_POST['down']))
{
  $mname=$_POST['mname'];
  $sdate=$_POST['sdate'];
  $edate=$_POST['edate'];
  $_SESSION['sdate'] = $sdate;
  $_SESSION['edate'] = $edate;
  if($mname!="All")
  {
  $_SESSION['mname'] = $mname;
  
  echo "<script>location.replace('packreportdown.php')</script>";
  }
  else
  {
    echo "<script>location.replace('packallreport.php')</script>";
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