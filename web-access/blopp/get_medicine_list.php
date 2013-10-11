<?php
	header('Content-Type: application/json; charset=utf-8;');
	
	include 'dbconfig.php';
	
	$mysqli = new mysqli($dburl, $dbuser, $dbpwd, $dbname);
	
	$id = $mysqli->real_escape_string($id);
	$q = "SELECT m.id, m.name, m.instructions_id, m.color, i.completeurl, i.url"
        ." FROM MEDICINES m, MEDICINE_INSTRUCTIONS i"
        ." WHERE m.instructions_id=i.id";
	
	$result = $mysqli->query($q);

	$rows = array();
	
	if ($result) {
		while ($r = $result->fetch_assoc()) {
			$rows[] = $r;
		}
		$result->close();
		$sqlsuccess = true;
	} else {
		$sqlsuccess = false;
	}

	print json_encode(array(
		'sqlsuccess' => $sqlsuccess,
		'query' => $q,
		'medicines' => $rows
	));
	$mysqli->close();
?>