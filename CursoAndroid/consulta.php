<?php
include('functions.php');
$rut = $get['rut'];


if($resultset=getSQLResultSet("SELECT * FROM `usuario` WHERE rut='$rut'")){
	while ($row = $resultset->fetch_array(MYSQLI_NUM)){
		echo json_encode($row);
	}
}

?>


