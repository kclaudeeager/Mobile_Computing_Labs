<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json; charset=UF-8');
header('Access-Control-Allow-Methods:POST');
header('Access-Control-Allow-Headers:Access-Control-Allow-Headers,Content_type,Access-Control-Allow-Methods,Authorization,X-Requested-With');

// include_once "../../config/Database.php";
include 'Connection.php';
//sql create table
$conn=GetConnection();
$table="Students";

$fname=$_POST['fname'];
$lname=$_POST['lname']; 
$regno=$_POST['regno'];
$department=$_POST['department'];
$create_table="CREATE TABLE IF NOT EXISTS $table(Id int(5) primary key AUTO_INCREMENT,fname varchar(20), lname varchar(20),regno Integer(10) UNIQUE not null,department varchar(30))";
if($conn->query($create_table)==TRUE)
{
    echo "Table Students created successfully!";
}
else{
    echo"Error in creating table Students: " .$conn->error;
}
$_POST=json_decode(file_get_contents("php://input"),true);
if(!empty($fname)&& !empty($lname)&&!empty($regno)&& !empty($department)) {
    //  $fname=$_POST['fname'];
    // $lname=$_POST['lname'];
    // $regno=$_POST['regno']; 
    // $department=$_POST['department'];
    echo "department: ".$department;
    if(create($conn,$table,$fname,$lname,$regno,$department)){
        http_response_code(201);
        echo json_encode (array('message'=>'Insertion done!'));
     }
     else{
        http_response_code(503);
         echo json_encode (array('message'=>'Insertion is not done!'));
     }
    }
    else{
        http_response_code(400);
        echo"error:".$conn->error;
        // echo $_POST;
        echo json_encode (array('message'=>'Insertion is not done data are not complete!'));
    }
 
function create($conn,$table,$fname,$lname,$regno,$department){

    $addNew='INSERT INTO Students(fname,lname,regno,department) values(?,?,?,?)';
    //prepare statement
    $stmt=$conn->prepare($addNew);
    //$stmt->store_result();
    $fname=htmlspecialchars(strip_tags($fname));
    $lname=htmlspecialchars(strip_tags($lname));
    $regno=htmlspecialchars(strip_tags($regno));
    $department=htmlspecialchars(strip_tags($department));
    $stmt->bind_param("ssss",$fname,$lname,$regno,$department);

    if($stmt->execute()){
        //echo json_encode( $stmt);
        return true;
    }
   
    return false;
  
}
$conn->close();

?>