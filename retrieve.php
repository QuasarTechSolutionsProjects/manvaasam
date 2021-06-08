  <?php
  header('Content-Type: application/json');
  require("config.php");
  include_once("shaping.php");
 
  if($conn){
 
$sql = "SELECT man_id, st_courid FROM package  WHERE st_courid IS NOT NULL ORDER BY p_date DESC LIMIT 50";
 
$res = mysqli_query($conn,$sql);

 
$packages = array();
 
while($row = mysqli_fetch_array($res))
{
array_push($packages,array('man_id'=>cryptfun('decrypt',$row['man_id']),
'stc_id'=>cryptfun('decrypt',$row['st_courid'])
));
}
 
echo json_encode(array("packages"=>$packages));
 
mysqli_close($conn);
  }

?>