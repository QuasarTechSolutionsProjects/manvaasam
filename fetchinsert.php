<?php
require("config.php");
include_once("shaping.php");
if(
     isset($_POST['manid']) && isset($_POST['scanid'])
){
 $manid = $_POST['manid'];
 $scanid = $_POST['scanid'];
 $manid = htmlspecialchars(strip_tags($manid));
 $manid = mysqli_real_escape_string($conn,$manid);
 $scanid = htmlspecialchars(strip_tags($scanid));
 $scanid = mysqli_real_escape_string($conn,$scanid);
 $manid =  cryptfun('encrypt',$manid);
 $scanid = cryptfun('encrypt',$scanid);
 $thing = 0;
 $check = "SELECT * FROM package WHERE man_id = '".$manid."' ";
 $check2 = mysqli_query($conn,$check);
 if($check2 == TRUE){
 $sql = "UPDATE package SET st_courid = '".$scanid."' WHERE man_id = '".$manid."'";
 $sqlcheck = mysqli_query($conn,$sql); 
 if($sqlcheck == TRUE){
    $thing = cryptfun('decrypt',$scanid);
    echo $thing;
      }
    else
    {
    echo "fails to update";
    } 
   }
   else{
    echo "incorrect";
   }

}
else{
    echo "enter the values";
}

$conn->close(); 
?>
