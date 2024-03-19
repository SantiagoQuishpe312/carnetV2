<?php
// Incluye el archivo de conexión

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    require_once 'conexion.php';
    $nombre = $_POST["nombre_estudiante"];
    $apellido = $_POST["apellido_estudiante"];
    $cargo = $_POST["cargo_estudiante"];
    $foto=$_POST["foto_estudiante"];
    $query = "INSERT INTO estudiante (nombre_estudiante, apellido_estudiante, cargo_estudiante,foto_estudiante)
              VALUES ('$nombre', '$apellido', '$cargo','$foto')";

    $resultado = $conn->query($query);
    
    if ($resultado == true) {
        echo "SE ALMACENO CORRECTAMENTE LOS VALORES";
    } else {
        echo "No se almacenan los valores";
    }
}

// Resto del código para este archivo...
?>
