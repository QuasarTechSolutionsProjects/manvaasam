<?php
  require("config.php");
  include_once("shaping.php");
  if(
    isset($_POST['fname']) &&
	isset($_POST['fmobile']) &&
	isset($_POST['fadd']) &&
	isset($_POST['fpin']) &&
	isset($_POST['tname']) &&
	isset($_POST['tmobile']) &&
	isset($_POST['tadd']) &&
	isset($_POST['tpin']) &&
	isset($_POST['amt'])
 )
 {
  $fname = $_POST['fname'];
  $fmobile =  $_POST['fmobile'];
  $fadd = $_POST['fadd'];
  $fpin = $_POST['fpin'];
  $tname =  $_POST['tname'];
  $tmobile = $_POST['tmobile'];
  $tadd = $_POST['tadd'];
  $tpin =  $_POST['tpin'];
  $amt = $_POST['amt'];

 if($fname == "" && $fmobile == "" && $fadd == "" && $fpin == "" && $tname == "" && $tmobile && $tadd == "" && $tpin == "" && $amt == "" ){
 echo "Enter the values properly";
 }
else{
  $fname = htmlspecialchars(strip_tags($fname));
  $fmobile = htmlspecialchars(strip_tags($fmobile));
  $fadd = htmlspecialchars(strip_tags($fadd));
  $fpin = htmlspecialchars(strip_tags($fpin));
  $tname = htmlspecialchars(strip_tags($tname));
  $tmobile = htmlspecialchars(strip_tags($tmobile));
  $tadd = htmlspecialchars(strip_tags($tadd));
  $tpin = htmlspecialchars(strip_tags($tpin));
  $amt = htmlspecialchars(strip_tags($amt));


  $fname = mysqli_real_escape_string($conn,$fname);
  $fmobile = mysqli_real_escape_string($conn,$fmobile);
  $fadd = mysqli_real_escape_string($conn,$fadd);
  $fpin = mysqli_real_escape_string($conn,$fpin);
  $tname = mysqli_real_escape_string($conn,$tname);
  $tmobile = mysqli_real_escape_string($conn,$tmobile);
  $tadd = mysqli_real_escape_string($conn,$tadd);
  $tpin = mysqli_real_escape_string($conn,$tpin);
  $amt = mysqli_real_escape_string($conn,$amt);

  date_default_timezone_set('Asia/Kolkata');
   
    $info = getdate();
   
    $date = $info['mday'];
    if(strlen($date)==1)
    {
      $date="0".$date;
    }
    
    $month = $info['mon'];
    if(strlen($month)==1)
    {
      $month="0".$month;
    }
    $year = $info['year'];
    $hour = $info['hours'];
    $min = $info['minutes'];
    $sec = $info['seconds'];
   
    $current_date = "$year-$month-$date";
    $current_time = "$hour:$min:$sec";
    

    $man_const="M";

      $sqlcount ="SELECT count(*) as 'total' FROM package WHERE p_date = '".$current_date."'";
      $result = mysqli_query($conn,$sqlcount);
      while($row = mysqli_fetch_array($result))
      {
          
        $total= $row['total'];

        $total=$total+1;

        if(strlen($total) == '1')
        {
          $id="00".$total;
          
        }
        elseif(strlen($total) == '2')
        {
          $id="0".$total; 
        }
        elseif(strlen($total) == '3')
        {
          $id=$total; 
        }
        else
        {
          $characters = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
          $charactersLength = strlen($characters);
          $randomString = '';
          for ($i = 0; $i < 3; $i++) 
          {
           $randomString .= $characters[rand(0, $charactersLength - 1)];
          }
          $id=$randomString;
        }
        
        
      }

      $newyear=substr($year, -2);
   
    $man_id="$date$month$newyear$man_const$id";


  $man_id = cryptfun('encrypt',$man_id);
  $fname = cryptfun('encrypt',$fname);
  $fmobile = cryptfun('encrypt',$fmobile);
  $fadd = cryptfun('encrypt',$fadd);
  $fpin = cryptfun('encrypt',$fpin);
  $tname = cryptfun('encrypt',$tname);
  $tmobile = cryptfun('encrypt',$tmobile);
  $tadd = cryptfun('encrypt',$tadd);
  $tpin = cryptfun('encrypt',$tpin);
  $amt = cryptfun('encrypt',$amt);

     
  $stmt = $conn->prepare("INSERT INTO package(man_id,f_name,f_mobno,f_addr,f_pcode,t_name,t_mobno,t_addr,t_pcode,p_amt,p_date,p_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
  
  $stmt->bind_param('ssssssssssss', $man_id, $fname, $fmobile, $fadd, $fpin, $tname, $tmobile, $tadd, $tpin, $amt,$current_date,$current_time);
  
  
  
  $stmt->execute();
  
  if($stmt->affected_rows == 1)
  { 
     $b = cryptfun('decrypt',$man_id);
     echo $b;
       
  }
else{
     echo "Connection Unsuccessfully";
  }
  
  $conn->close();
  } 

 }
 else{
	 echo "Enter the values properly";
 }


?>