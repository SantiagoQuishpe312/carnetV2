<?php
// Incluye el archivo de conexión
require_once 'conexion.php';

// Consulta para obtener el valor mínimo y máximo de la columna idcolor
$consulta = "SELECT MIN(idimagen) AS min_id, MAX(idimagen) AS max_id FROM imagen";

// Ejecutar la consulta
$resultadoConsulta = $conn->query($consulta);

if ($resultadoConsulta) {
    // Obtener los valores mínimo y máximo
    $row = $resultadoConsulta->fetch_assoc();
    $minID = $row['min_id'];
    $maxID = $row['max_id'];

    // Preparar los datos de respuesta
    $response = array(
        "min_id" => $minID,
        "max_id" => $maxID
    );

    // Enviar la respuesta como JSON
    echo json_encode($response);
} else {
    // Enviar un mensaje de error si la consulta no fue exitosa
    echo json_encode(array("error" => "Error al ejecutar la consulta"));
}
?>
