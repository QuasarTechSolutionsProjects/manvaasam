  <?php
  require("config.php");
  include_once("shaping.php");

  if(
     isset($_POST['username'])&&
     isset($_POST['userpas']) &&
     isset($_POST['tablevalue'])
  ){
    $name = $_POST['username'];
    $pas =  $_POST['userpas'];
    $table = $_POST['tablevalue'];
    $name = htmlspecialchars(strip_tags($name));
    $pass = htmlspecialchars(strip_tags($pas));
    $name = mysqli_real_escape_string($conn,$name);
    $pass = mysqli_real_escape_string($conn,$pass);
    $name = cryptfun('encrypt',$name);
    $pass = cryptfun('encrypt',$pass);
    $res = 0;
    if($table == "98765")
    {
    $res = 1;
     }
    //   echo $table;
    //   echo $res;
      $sql = $conn->prepare("SELECT * from team_login WHERE user = ? AND pswd = ? AND type = ?");
      $sql->bind_param("sss",$name,$pass,$res);
     
      $sql->execute();
      $result = $sql->get_result();
      $row = $result->fetch_array(MYSQLI_ASSOC);
      $uname = $row['user'];
      $passcod=$row['pswd'];
  
       
         if($name == $uname && $pass == $passcod)
       {
       echo "Successfull Login";
          } 
         else
         {
         echo  "Username and Password Incorrect";
         }  
   
  }
?>