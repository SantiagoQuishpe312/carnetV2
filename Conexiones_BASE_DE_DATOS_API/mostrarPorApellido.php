<?php
// Incluye el archivo de conexión
require_once 'conexion.php';

// Verifica si la solicitud es un GET y si se proporciona un ID
if ($_SERVER["REQUEST_METHOD"] == "GET" && isset($_GET["apellido"])) {
    $apellido_estudiante = $_GET["apellido"];

    // Consulta para obtener un estudiante por ID
    $consulta = "SELECT * FROM estudiante WHERE apellido_estudiante = $apellido_estudiante";
    $resultadoConsulta = $conn->query($consulta);

    // Verifica si hay resultados
    if ($resultadoConsulta->num_rows > 0) {
        // Muestra la información del estudiante
        $fila = $resultadoConsulta->fetch_assoc();
        
$datosJson = json_encode([
    'ID' => $fila["id_estudiante"],
    'Nombre' => $fila["nombre_estudiante"],
    'Apellido' => $fila["apellido_estudiante"],
    'Cargo' => $fila["cargo_estudiante"],
    'Foto' => $fila["foto_estudiante"]
]);

echo $datosJson;
    } else {
        echo "No se encontró ningún estudiante con ese ID.";
    }
} else {
    echo "Solicitud no válida.";
}

// Cierra la conexión
$conn->close();
?>