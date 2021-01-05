<?php
session_start();
if(isset($_SESSION['auname']))
{
  session_unset(); 
  session_destroy();
  echo "<script>location.replace('index.php')</script>;";
}
else
{
    echo "<script>location.replace('index.php')</script>;";
}


?>