<?php
//Host
$host = "localhost";
$db="hostel_db";
$user = "root";
$pass ="";
//Connection
$conn = new mysqli($host,$user,$pass,$db) or die('Database not connected');

/*
function cud(sql){
    $result = mysqli_query(sql) or die('Data not inserted or updated!');
}

function retrive(sql){
    $result = mysqli_query(sql) or die('Query not executed');
    if(mysqli_num_rows($result) > 0)
        return $result;
}
*/
//mysqli_close($conn);
?>