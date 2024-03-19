<?php
// Incluye el archivo de conexión
require_once 'conexion.php';

// Verifica si la solicitud es un GET
if ($_SERVER["REQUEST_METHOD"] == "GET") {
    $query = "SELECT * FROM color";
    $resultado = $conn->query($query);

    // Verifica si se obtuvieron resultados
    if ($resultado) {
        $data = array();

        while ($row = $resultado->fetch_assoc()) {
            $data[] = $row;
        }

        echo json_encode($data);
    } else {
        echo "Solicitud no válida";
    }

    // Cierra la conexión
    $conn->close();
}
?>
