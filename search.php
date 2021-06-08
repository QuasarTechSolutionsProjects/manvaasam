<?php
  header('Content-Type: application/json');
  require("config.php");
  include_once("shaping.php");
 
  if(isset($_GET['searchid']))
 {

   $search = $_GET['searchid'];
   $search = htmlspecialchars(strip_tags($search));
   $search = mysqli_real_escape_string($conn,$search);
   
 
$sql = "SELECT * FROM package";
 
$res = mysqli_query($conn,$sql);

 
$packages = array();
 
while($row = mysqli_fetch_array($res))
{

$split=cryptfun('decrypt',$row['man_id']);
$manid=substr($split,0,6);
$stid=$row['st_courid'];

if($stid==null)
{
    $stid="Status Not Updated";
}
else
{
    $stid=cryptfun('decrypt',$stid);
}

if($manid==$search)
{
array_push($packages,array('man_id'=>cryptfun('decrypt',$row['man_id']),
'stc_id'=>$stid
));

}
}
if(empty($packages))
{
    echo "No records Found";
    
}
else
{
echo json_encode(array("packages"=>$packages));
echo "Succesfully Retrieved";
}
 
mysqli_close($conn);
  }

?>