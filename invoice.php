<?php
require('pdf/fpdf.php');
require("config.php");
include_once("shaping.php");

$mav_id=cryptfun('encrypt', $_GET["manid"]);


$sql = "SELECT * FROM package WHERE man_id = '".$mav_id."' ";
$result = mysqli_query($conn,$sql);
while($row = mysqli_fetch_array($result))
{
          
$fname = cryptfun('decrypt', $row['f_name']);
$f_mobno = cryptfun('decrypt', $row['f_mobno']);
$packtype = $row['pack_type'];
$parcdet = $row['parc_det'];
if($packtype==="" && $parcdet==="")
{
    $packtype="Not Recorded";
    $parcdet="Not Recorded";
}
else
{
    $packtype = cryptfun('decrypt', $row['pack_type']);
    $parcdet = cryptfun('decrypt', $row['parc_det']); 
}

$t_name = cryptfun('decrypt', $row['t_name']);
$t_mobno = cryptfun('decrypt', $row['t_mobno']);
$t_addr = cryptfun('decrypt', $row['t_addr']);
$t_pcode = cryptfun('decrypt', $row['t_pcode']);

$p_amt = cryptfun('decrypt', $row['p_amt']);

$p_date=$row['p_date'];

$mav_id=cryptfun('decrypt', $mav_id);
        
}


     class PDF extends FPDF 
{ 
     
    function Header() 
    { 
         
        $this->Image('logo.jpeg', 10, 8, 20,20); 
          
    
          
        
        $this->SetFont('Times','B',20); 
          
        
        $this->Cell(80); 
          
         
        $this->Cell(30, 20, 'Package Invoice', 0, 2, 'C'); 
          
         
        $this->Ln(5); 
    } 
       
  
    function Footer() 
    { 
        
        $this->SetY(-15); 
          
        
        $this->SetFont('Arial', 'I', 8); 

       
          
       
        $this->Cell(0, 10, 'Page ' . $this->PageNo() . 
              '/{nb}', 0, 0, 'C'); 
    } 
} 
   

$pdf = new PDF(); 
$pdf->AliasNbPages(); 
  

$pdf->AddPage(); 
  
 
$pdf->SetFont('Times','',12); 
  

$pdf->SetFont('Arial','B',14);



$pdf->Cell(120 ,5,'Manvaasam Logistics',0,0);
$pdf->Cell(49 ,8,'Manvaasam Id: '.$mav_id,0,1);


$pdf->SetFont('Arial','',12);


$pdf->Cell(130 ,5,'Contact Details',0,0);
$pdf->Cell(25 ,5,'Date:',0,0);
$pdf->Cell(28 ,5,$p_date,0,1);

$pdf->Cell(130 ,5,'Phone: 63800 91001',0,0);





$pdf->Cell(189 ,10,'',0,1);


$pdf->Cell(100 ,5,'Package From:-',0,1);


$pdf->Cell(10 ,5,'',0,0);
$pdf->Cell(90 ,5,'Name: '.$fname,0,1);

$pdf->Cell(10 ,5,'',0,0);
$pdf->Cell(90 ,5,'Mob no: '.$f_mobno,0,1);


$pdf->Cell(189 ,10,'',0,1);

$pdf->Cell(100 ,5,'Package To:-',0,1);


$pdf->Cell(10 ,5,'',0,0);
$pdf->Cell(90 ,5,'Name: '.$t_name,0,1);

$pdf->Cell(10 ,5,'',0,0);
$pdf->Cell(90 ,5,'Mob no: '.$t_mobno,0,1);

$pdf->Cell(10 ,5,'',0,0);
$pdf->Cell(90 ,5,'Address: '.$t_addr,0,1);

$pdf->Cell(10 ,5,'',0,0);
$pdf->Cell(90 ,5,'Pin Code: '.$t_pcode,0,1);



$pdf->Cell(189 ,10,'',0,1);

$pdf->Cell(100 ,5,'Package Details:-',0,1);

$pdf->Cell(10 ,5,'',0,0);
$pdf->Cell(90 ,5,'Package Type: '.$packtype,0,1);

$pdf->Cell(10 ,5,'',0,0);
$pdf->Cell(90 ,5,'Parcel Details: '.$parcdet,0,1);


$pdf->Cell(189 ,10,'',0,1);


$pdf->SetFont('Arial','B',12);

$pdf->Cell(100 ,5,'Package ID',1,0);
$pdf->Cell(30 ,5,'Qty',1,0);
$pdf->Cell(60 ,5,'         Amount Paid',1,1);

$pdf->SetFont('Arial','',12);



$pdf->Cell(100 ,5,$mav_id,1,0);
$pdf->Cell(30 ,5,'1',1,0);
$pdf->Cell(60 ,5,'Rs '.$p_amt,1,1,'R');



$pdf->Cell(130 ,5,'',0,0);
$pdf->Cell(25 ,5,'Total Amt',0,0);
$pdf->Cell(10 ,5,'Rs',1,0);
$pdf->Cell(25 ,5,$p_amt,1,1,'R');

$pdf->SetFont('Arial', 'I', 10); 

$pdf->Cell(180, 20, 'This is a Computer Generated Invoice', 0, 2, 'C'); 
      
$pdf->Output(); 


?>

