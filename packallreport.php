<?php
session_start();
if(isset($_SESSION['auname']))
{
  header('Content-type: application/vnd-ms-excel');
  $filename="Manvaasam_allPackreport.xls";
  header('Content-Disposition: attachment; filename='.$filename);
  $data = '<html xmlns:x="urn:schemas-microsoft-com:office:excel">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <!--[if gte mso 9]>
    <xml>
        <x:ExcelWorkbook>
            <x:ExcelWorksheets>
                <x:ExcelWorksheet>
                    <x:Name>Sheet 1</x:Name>
                    <x:WorksheetOptions>
                        <x:Print>
                            <x:ValidPrinterInfo/>
                        </x:Print>
                    </x:WorksheetOptions>
                </x:ExcelWorksheet>
            </x:ExcelWorksheets>
        </x:ExcelWorkbook>
    </xml>
    <![endif]-->
</head>'


  
 
    
?>
<style>
th
{
  border:1px solid black;

}
td
{
  border:1px solid black;
}
</style>

<h1>Manvaasam Logistics Packages Report<h1>
<table class="table table-bordered">
<thead>
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
<th>Package Amount(Rs)</th>
<th>Package Date</th>
<th>Package Time</th>
<th>Package Created By</th>
<th>St Courier ID</th>

</tr></thead>
<tbody>
<?php 
require("shaping.php");
require("config.php");
$sdate=$_SESSION["sdate"];
$edate=$_SESSION["edate"];

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
      echo "<tr> <td>" . $i . "</td> <td>" . cryptfun('decrypt',$row["man_id"])."</td> <td>".cryptfun('decrypt',$row["f_name"]) ."</td> <td>".cryptfun('decrypt',$row["f_mobno"]) . "</td> <td>".cryptfun('decrypt',$row["f_addr"]). "</td> <td>" . cryptfun('decrypt',$row["f_pcode"]). "</td> <td>" . cryptfun('decrypt',$row["t_name"])."</td> <td>".cryptfun('decrypt',$row["t_mobno"]) ."</td> <td>".cryptfun('decrypt',$row["t_addr"]) . "</td> <td>".cryptfun('decrypt',$row["t_pcode"])."</td> <td>".cryptfun('decrypt',$row["p_amt"]) . "</td> <td>" .$row["p_date"] . "</td> <td>" .$row["p_time"] . "</td> <td>" .cryptfun('decrypt',$row["c_uname"])."</td> <td>".$sid . "</td></tr>";
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


</tbody>
</table>


<?php
}
else
{
  echo "<script>location.replace('index.php')</script>;";
}
?>
    

