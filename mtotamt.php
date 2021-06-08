<?php
header('Content-Type: application/json');
  require("config.php");
  include_once("shaping.php");
$totalamt=0;
if(isset($_GET['logid']))
{
    $logid = cryptfun('encrypt',$_GET['logid']);
 
    $sql = "SELECT p_amt FROM package  WHERE c_uname = '$logid'";

    $res = mysqli_query($conn,$sql);

    
    $packages = array();
    
    while($row = mysqli_fetch_array($res))
    {
    $rowamount=cryptfun('decrypt',$row['p_amt']);
    $totalamt=$totalamt+$rowamount;
    }
    
    array_push($packages,array('logid'=>$logid,
    'totamt'=>$totalamt)
    );
    echo json_encode(array("packages"=>$packages));
  

    
    mysqli_close($conn);
  }

?>