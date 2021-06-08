<?php
header('Content-Type: application/json');
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
      $tname = cryptfun('decrypt',$row['t_name']);
      $tmob=   cryptfun('decrypt',$row['t_mobno']);
      $taddr = cryptfun('decrypt',$row['t_addr']);
      $tcode = cryptfun('decrypt',$row['t_pcode']);
      $pack_type = cryptfun('decrypt',$row['pack_type']);
      $cid = cryptfun('decrypt',$row['st_courid']);
      $amt = cryptfun('decrypt',$row['p_amt']);
      
      if($cid==null)
      {
        $cid="Status Not Updated";
      }
      $parc_det="";
      if($pack_type=="Parcel")
      {
        $parc_det = cryptfun('decrypt',$row['parc_det']);
      }
      else
      {
         $parc_det="NA";
      }
      if($pack_type==false)
      {
        $pack_type="Packtype Not Assigned";
      }
      $details = array("fname" => $fname,
                       "fmobile" => $fmob,
                       "tname" => $tname,
                       "tmobile" => $tmob,
                       "taddr" => $taddr,
                       "tcode" => $tcode,
                       "packtype" => $pack_type,
                       "parcdet" => $parc_det,
                       "amt" => $amt,
                       "cid" => $cid );
       if($fname == false && $tname == false && $taddr == false && $tcode == false)
       {
            echo "No record";
        }
      else{
        echo json_encode($details); 
       }
        
      
      }
     else{ 
      echo "Incorrect";
       }


}

else{
    echo "Enter the values";
}
$conn->close();
?>