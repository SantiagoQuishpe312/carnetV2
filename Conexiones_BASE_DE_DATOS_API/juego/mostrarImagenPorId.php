<?php
// Incluye el archivo de conexión
require_once 'conexion.php';

// Verifica si la solicitud es un GET y si se proporciona un ID
if ($_SERVER["REQUEST_METHOD"] == "GET" && isset($_GET["idimagen"])) {
    $idimagen = $_GET["idimagen"];

    // Consulta para obtener un estudiante por ID
    $consulta = "SELECT * FROM imagen WHERE idimagen= $idimagen";
    $resultadoConsulta = $conn->query($consulta);

    // Verifica si hay resultados
    if ($resultadoConsulta->num_rows > 0) {
        // Muestra la información del estudiante
        $fila = $resultadoConsulta->fetch_assoc();
        $datosJson = json_encode([
            'idimagen' => $fila["idimagen"],
            'imagenURL' => $fila["imagenURL"],
            'idcolor' => $fila["idcolor"],
        ]);
        echo $datosJson;
    } else {
        echo "No se encontró  con ese ID.";
    }
} else {
    echo "Solicitud no válida.";
}
?>