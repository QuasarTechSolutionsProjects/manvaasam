<?php
require("config.php");
include_once("shaping.php");

if(isset($_POST['manid'])){
$manid = $_POST['manid'];
$manid = htmlspecialchars(strip_tags($manid));
$manid = mysqli_real_escape_string($conn,$manid);
$manid = cryptfun('encrypt',$manid);
$sql = $conn->prepare("SELECT * from package WHERE man_id = ?");
$sql->bind_param("s",$manid);
     
      if($sql->execute()){
      $result = $sql->get_result();
      $row = $result->fetch_array(MYSQLI_ASSOC);
      $fname = cryptfun('decrypt',$row['f_name']);
      $fmob=   cryptfun('decrypt',$row['f_mobno']);
      $faddr = cryptfun('decrypt',$row['f_addr']);
      $fcode = cryptfun('decrypt',$row['f_pcode']);
      $tname = cryptfun('decrypt',$row['t_name']);
      $tmob=   cryptfun('decrypt',$row['t_mobno']);
      $taddr = cryptfun('decrypt',$row['t_addr']);
      $tcode = cryptfun('decrypt',$row['t_pcode']);
      $cid = cryptfun('decrypt',$row['st_courid']);
      
      if($cid==null)
      {
        $cid="Status Not Updated";
      }
      $details = array("fname" => $fname,
                       "fmobile" => $fmob,
                       "faddr" => $faddr,
                       "fcode" => $fcode,
                       "tname" => $tname,
                       "tmobile" => $tmob,
                       "taddr" => $taddr,
                       "tcode" => $tcode,
                       "cid" => $cid );
       if($fname == false && $faddr == false && $fcode == false && $tcode == false){
            echo "no record";
        }
      else{
        echo json_encode($details); 
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