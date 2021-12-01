<?php
include 'Connection.php';
//sql create table
$conn=GetConnection();
// $query = mysql_query("select * from Students", $conn) or  die('Could not get data : '.$conn->error);;

// while ($student = mysql_fetch_array($query)) {
//     echo" Student Id:{$student['Id']}<br>"."Student first_name:{$student['fname']} <br>"."Last_name:{$student['lname']}<br>"."Student Reg:{$student['regno']}";
// }


//echo "Fetched data successfully\n";
$sqlQuery = '';	
	$stQuery = "select * from Students";
		
	$resultData = mysqli_query($conn, $stQuery);
	$stData = array();
	while( $stRecord = mysqli_fetch_assoc($resultData) ) {
		$stData[] = $stRecord;
	}
	header('Content-Type: application/json');
	echo json_encode($stData);

$conn->close();
?>