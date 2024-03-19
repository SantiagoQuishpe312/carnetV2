<?php
// Incluye el archivo de conexión
require_once 'conexion.php';

// Verifica si la solicitud es un GET y si se proporciona un ID
if ($_SERVER["REQUEST_METHOD"] == "GET" && isset($_GET["id"])) {
    $idcolor = $_GET["id"];

    // Consulta para obtener un estudiante por ID
    $consulta = "SELECT * FROM imagen WHERE idcolor = $idcolor";
    $resultadoConsulta = $conn->query($consulta);

    if ($resultadoConsulta) {
        $data = array();

        while ($row = $resultadoConsulta->fetch_assoc()) {
            $data[] = $row;
        }

        echo json_encode($data);
    } else {
        echo "Solicitud no válida";
    }

    }
    ?>