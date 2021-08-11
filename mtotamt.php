<?php
header('Content-Type: application/json');
  require("config.php");
  include_once("shaping.php");

if(isset($_GET['logid']))
{
    $logid = cryptfun('encrypt',$_GET['logid']);
 
    $sql = "SELECT COUNT('man_id') AS total_packages FROM package  WHERE c_uname = '$logid'";

    $res = mysqli_query($conn,$sql);

    
    $packages = array();
    
    $row = mysqli_fetch_assoc($res);

    $totalpackages = $row['total_packages'];
    
    array_push($packages,array('logid'=>$logid,
    'totpack'=>$totalpackages)
    );
    
    echo json_encode(array("packages"=>$packages));
  

    
    mysqli_close($conn);
  }

?>