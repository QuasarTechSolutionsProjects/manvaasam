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

  <title>Manvaasam Logistics Login Report</title>
  <meta content="Logistics Admin Panel for Manvaasam Logistics APP" name="description">
  <meta content="Manvaasam, Manvaasam Logistics, App" name="keywords">

  <!-- Favicons -->
  <link rel="apple-touch-icon" sizes="180x180" href="assets/img/Favicon/apple-touch-icon.png">
  <link rel="icon" type="image/png" sizes="32x32" href="assets/img/Favicon//favicon-32x32.png">
  <link rel="icon" type="image/png" sizes="16x16" href="assets/img/Favicon//favicon-16x16.png">
  <link rel="manifest" href="assets/img/Favicon//site.webmanifest">
  <meta name="msapplication-TileColor" content="#da532c">
  <meta name="theme-color" content="#ffffff">

<title>Manvaasam Login Report</title>
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
    <h1>Manvaasam Login Details Report<h1>
<table>
<tr>
<th>S.No</th>
<th>UserName</th>
<th>User Password</th>
<th>User Email</th>
<th>User Mobile Number</th>
<th>User Designation</th>
</tr>
<?php


$sql = "SELECT * FROM team_login";
$result = mysqli_query($conn, $sql);

if (mysqli_num_rows($result) > 0) {
   // output data of each row
   $i=1;
  
  while($row = mysqli_fetch_assoc($result)) 
  {
      $desg=$row["type"];
      if($desg=="0")
      {
          $desg="Manvaasam Team";
      }
      else
      {
        $desg="St Courier Team";

      }
    echo "<tr> <td>" . $i . "</td> <td>" . cryptfun('decrypt',$row["user"])."</td> <td>".cryptfun('decrypt',$row["pswd"]) ."</td> <td>".cryptfun('decrypt',$row["email"]) . "</td> <td>".cryptfun('decrypt',$row["mobile"])."</td><td>". $desg. "</td></tr>";
    
    $i++;
    
   
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