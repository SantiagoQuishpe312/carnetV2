<?php
$servername = "localhost";  // El nombre del servidor (puede ser localhost si estás trabajando de forma local)
$username = "root";         // El nombre de usuario de MySQL por defecto en XAMPP es "root"
$password = "root";             // Deja la contraseña en blanco por defecto en XAMPP
$database = "app_color";  // Nombre de tu base de datos

// Crear conexión
$conn = new mysqli($servername, $username, $password, $database);

// Verificar la conexión
if ($conn->connect_error) {
    die("Error de conexión: " . $conn->connect_error);
} 
?>
