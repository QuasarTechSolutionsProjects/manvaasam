  <?php
  require("config.php");
  include_once("shaping.php");
 
  if($conn){
 $packages = array(); 
 $sql = "SELECT man_id, st_courid FROM package  WHERE st_courid IS NOT NULL";
$stmt = $conn->prepare($sql);
$stmt->execute();
$stmt->bind_result($manid,$courid);
while($stmt->fetch()){
 $temp = [
 'manid'=>cryptfun('decrypt',$manid),
 'courid'=>cryptfun('decrypt',$courid)
 ];
 array_push($packages, $temp);
} 
print_r(json_encode($packages)); 
   }
else{
   echo "Please try after some time";
}

?>