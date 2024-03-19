<?php
// Incluye el archivo de conexión
require_once 'conexion.php';

// Verifica si la solicitud es un GET y si se proporciona un ID
if ($_SERVER["REQUEST_METHOD"] == "GET" && isset($_GET["letra"])) {
    // Sanitiza el valor de letra para prevenir inyecciones SQL
    $letra_Inicial = $conn->real_escape_string($_GET["letra"]);

    // Consulta para obtener la cuenta de estudiantes que tienen el apellido que comienza con la letra proporcionada
    $consulta = "SELECT COUNT(*) as cuenta FROM estudiante WHERE apellido_estudiante LIKE '$letra_Inicial%'";
    $resultadoConsulta = $conn->query($consulta);

    // Verifica si hay resultados
    if ($resultadoConsulta) {
        $fila = $resultadoConsulta->fetch_assoc();
        $datosJson = json_encode(['cuenta' => $fila["cuenta"]]);
        echo $datosJson;
    } else {
        // Si hay un error en la consulta, puedes manejarlo de la manera que prefieras
        echo "Error en la consulta: " . $conn->error;
    }
} else {
    echo "Solicitud no válida.";
}

// Cierra la conexión
$conn->close();
?>
