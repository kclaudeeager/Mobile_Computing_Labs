<?php
  $dbname="Lab_User";
  $url = 'http://localhost/PHP-folder/Delete.php?regno=';
include "Connection.php"; // Using database connection file here
$conn=GetConnection();
// $id=$_POST['regno'] ; // get id through query string
// $parsed = parse_url($url);
// $query = $parsed['query'];
$id1=$_POST['regno'];
// parse_str($query, $params);
// $url+=$id;
//$url_components = parse_url($url);
echo"id ". $id1;
//echo $url;
// $_DELET=json_decode(file_get_contents("php://input"),true);
// $id=$_DELET->regno;
$del = $conn->query("delete from Students where regno = '$id1'"); // delete query

if($del)
{
    echo "Successfully deleted! ";
    $conn->close();// Close connection
   // header("location:all_records.php"); // redirects to all records page
    exit;	
}
else
{
    echo "Error deleting record".$conn->error; // display error message if not delete
}
?>