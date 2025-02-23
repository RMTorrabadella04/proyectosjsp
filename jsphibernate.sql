-- Crear la base de datos
CREATE DATABASE jsphibernateraul;

-- Usar la base de datos
USE jsphibernateraul;

-- Crear la tabla 'proyectos'
CREATE TABLE proyectos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_proyecto VARCHAR(100) NOT NULL,
    descripcion TEXT,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE,
    estado ENUM('en curso', 'completado') NOT NULL
);

-- Crear la tabla 'tareas'
CREATE TABLE tareas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_proyecto INT NOT NULL,
    descripcion_tarea TEXT NOT NULL,
    responsable VARCHAR(100) NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE,
    estado ENUM('pendiente', 'en progreso', 'completada') NOT NULL,
    FOREIGN KEY (id_proyecto) REFERENCES proyectos(id) ON DELETE CASCADE
);