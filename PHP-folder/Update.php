<?php
$fname=$_POST['fname'];
$lname=$_POST['lname']; 
$regno=$_POST['regno'];
$departement=$_POST['department'];
include "Connection.php"; // Using database connection file here

$conn=GetConnection();
echo "REG: ".$regno;
$stQuery = "select * from Students WHERE regno='$regno'";
$resultData = mysqli_query($conn, $stQuery);
$stData = array();
while( $stRecord = mysqli_fetch_assoc($resultData) ) {
    $stData[] = $stRecord;
}
if($stData!=[]){
$sql = "UPDATE Students SET fname='$fname',lname='$lname',department='$departement' WHERE regno='$regno'";

if (mysqli_query($conn, $sql)=== TRUE) {
  echo "Record updated successfully";
} else {
  echo "Error updating record: " . $conn->error;
}
}
else{
    echo "User not found!";
}
$conn->close();
?>