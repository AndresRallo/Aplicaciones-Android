<?php include ('functions.php');
$nombres=$_GET['n'];
$password=$_GET['p'];


ejecutarSQLCommand("INSERT INTO  `usuario` (nombre, pass)
VALUES ('$nombres','$password')");

 ?>