<?php

function GetConnection(){
    $servername="localhost";
    $username="root";
    $password="";
    $dbname="Lab_User";
    //Create connection
    $conn=new mysqli($servername,$username,$password,$dbname);
    if($conn->connect_error){
        die("Connection Failed: " .$conn->connect_error);
    }
    return $conn;
}

?>