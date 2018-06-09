<?php
	$con = mysqli_connect("localhost","rebelfre_sam","sam140234","rebelfre_AssistanTeacher");
	
	
$name = $_POST["name"];
	
$user_name = $_POST["username"];
	
$user_pass = $_POST["password"];

	
$statement = mysqli_prepare($con, "INSERT INTO UP(FullName, Username,Password) VALUES ('$name','$user_name','$user_pass')");
	//mysqli_stmt_bind_param($statement, "siss", $name, $user_name, $user_pass);



/*if(mysqli_query($con,$sql_query))
	
{
	
	echo"<h3> Data insert successfull.....</h3>";
	
}
	
else
	
{
	
	echo"Data insertion error....".mysqli_error($con);*/

mysqli_stmt_execute($statement);

	
mysqli_stmt_close($statement);

	
mysqli_close($con);

?> 
?> 