<?php
session_start();
if(isset($_SESSION['auname']))
{
    
?>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>Manvaasam Logistics Package Report</title>
  <meta content="Logistics Admin Panel for Manvaasam Logistics APP" name="description">
  <meta content="Manvaasam, Manvaasam Logistics, App" name="keywords">

  <!-- Favicons -->
  <link rel="apple-touch-icon" sizes="180x180" href="assets/img/Favicon/apple-touch-icon.png">
  <link rel="icon" type="image/png" sizes="32x32" href="assets/img/Favicon//favicon-32x32.png">
  <link rel="icon" type="image/png" sizes="16x16" href="assets/img/Favicon//favicon-16x16.png">
  <link rel="manifest" href="assets/img/Favicon//site.webmanifest">
  <meta name="msapplication-TileColor" content="#da532c">
  <meta name="theme-color" content="#ffffff">

<title>Manvaasam Logistics Package Report</title>
<style>
table {
border-collapse: collapse;
width: 100%;
color: #588c7e;
font-family: monospace;
font-size: 15px;
text-align: left;
border: 1px solid black;
}
th {
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
<body>
<?php 
require("shaping.php");
require("config.php");
?>
    <h1>Manvaasam Logistics Packages Report<h1>
<table>
<tr>
<th>S.No</th>
<th>Manvaasam ID</th>
<th>Sender Name</th>
<th>Sender Mobile Number</th>
<th>Sender Address</th>
<th>Sender Pincode</th>
<th>Receiver Name</th>
<th>Receiver Mobile Number</th>
<th>Receiver Address</th>
<th>Receiver Pincode</th>
<th>Package Amount</th>
<th>Package Date</th>
<th>Package Time</th>
<th>St Courier ID</th>
</tr>
<?php


$sql = "SELECT * FROM package";
$result = mysqli_query($conn, $sql);

if (mysqli_num_rows($result) > 0) {
  // output data of each row
  
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
    echo "<tr> <td>" . $row["sno"]. "</td> <td>" . cryptfun('decrypt',$row["man_id"])."</td> <td>".cryptfun('decrypt',$row["f_name"]) ."</td> <td>".cryptfun('decrypt',$row["f_mobno"]) . "</td> <td>".cryptfun('decrypt',$row["f_addr"]). "</td> <td>" . cryptfun('decrypt',$row["f_pcode"]). "</td> <td>" . cryptfun('decrypt',$row["t_name"])."</td> <td>".cryptfun('decrypt',$row["t_mobno"]) ."</td> <td>".cryptfun('decrypt',$row["t_addr"]) . "</td> <td>".cryptfun('decrypt',$row["t_pcode"])."</td> <td>"."Rs ".cryptfun('decrypt',$row["p_amt"]) . "</td> <td>" .$row["p_date"] . "</td> <td>" .$row["p_time"] . "</td> <td>" .$sid . "</td></tr>";
    
    
}
    echo "</table>";
    }
 else
{
  echo "0 results";
}

mysqli_close($conn);



?>

</body>
</html>
<?php
}
else
{
  echo "<script>location.replace('index.php')</script>;";
}
?>