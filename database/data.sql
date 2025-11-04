-- =========== DATOS INICIALES (SOLO LOOKUP TABLES) ===========
-- Estos son datos básicos necesarios para que el sistema funcione.
-- El estudiante debe poblar las demás tablas con datos de prueba.

-- Especies

INSERT INTO especies (nombre, descripcion) VALUES
('Perro', 'Canis lupus familiaris'),
('Gato', 'Felis catus'),
('Ave', 'Aves domésticas'),
('Roedor', 'Pequeños mamíferos roedores'),
('Reptil', 'Reptiles domésticos'),
('Conejo', 'Oryctolagus cuniculus');


-- Razas (ejemplos básicos)

INSERT INTO razas (especie_id, nombre, caracteristicas) VALUES

(1, 'Labrador Retriever', 'Tamaño grande, amigable, energético'),
(1, 'Golden Retriever', 'Tamaño grande, inteligente, leal'),
(1, 'Pastor Alemán', 'Tamaño grande, inteligente, protector'),
(1, 'Bulldog Francés', 'Tamaño pequeño, cariñoso, juguetón'),
(1, 'Chihuahua', 'Tamaño muy pequeño, alerta, valiente'),
(1, 'Poodle', 'Inteligente, hipoalergénico'),
(1, 'Mestizo', 'Cruza de razas'),
(2, 'Persa', 'Pelo largo, tranquilo'),
(2, 'Siamés', 'Vocal, social, activo'),
(2, 'Maine Coon', 'Grande, peludo, amigable'),
(2, 'Bengalí', 'Activo, manchas de leopardo'),
(2, 'Mestizo', 'Cruza de razas'),
(3, 'Canario', 'Pequeño, cantante'),
(3, 'Periquito', 'Pequeño, social'),
(3, 'Loro', 'Inteligente, longevo'),
(4, 'Hámster Sirio', 'Pequeño, nocturno'),
(4, 'Cobayo', 'Mediano, social'),
(5, 'Iguana Verde', 'Herbívoro, grande'),
(5, 'Tortuga', 'Lenta, longeva'),
(6, 'Conejo Enano', 'Pequeño, dócil'),
(6, 'Conejo Gigante', 'Grande, tranquilo');


-- Tipos de productos

INSERT INTO producto_tipos (nombre, descripcion) VALUES
('Medicamento', 'Fármacos y medicinas veterinarias'),
('Vacuna', 'Vacunas y biológicos'),
('Insumo Médico', 'Material médico y quirúrgico'),
('Alimento', 'Alimento para mascotas'),
('Accesorio', 'Accesorios y productos de cuidado');


-- Tipos de eventos médicos

INSERT INTO evento_tipos (nombre, descripcion) VALUES
('Vacunación', 'Aplicación de vacunas'),
('Consulta General', 'Consulta veterinaria general'),
('Cirugía', 'Procedimiento quirúrgico'),
('Desparasitación', 'Tratamiento antiparasitario'),
('Control de Peso', 'Seguimiento de peso'),
('Examen de Sangre', 'Análisis de laboratorio'),
('Radiografía', 'Estudio de imagen'),
('Emergencia', 'Atención de emergencia'),
('Limpieza Dental', 'Profilaxis dental');

-- Estados de citas

INSERT INTO cita_estados (nombre, descripcion) VALUES
('Programada', 'Cita agendada pendiente'),
('Confirmada', 'Cita confirmada por el cliente'),
('En Proceso', 'Mascota siendo atendida'),
('Finalizada', 'Cita completada'),
('Cancelada', 'Cita cancelada'),
('No Asistió', 'Cliente no se presentó');

INSERT INTO servicios (id, nombre, descripcion, categoria, precio_base, duracion_estimada_minutos, activo) VALUES
(1, 'Consulta General', 'Evaluacion medica completa de la mascota, diagnostico y recomendaciones.', 'Consultas', 50000, 30, TRUE),
(2, 'Procedimiento Quirurgico', 'Costo base para intervenciones quirurgicas. El valor final puede variar.', 'Cirugia', 250000, 90, TRUE),
(3, 'Aplicacion de Vacuna', 'Servicio de aplicacion de una dosis de vacuna. No incluye el costo de la vacuna.', 'Prevencion', 15000, 10, TRUE),
(4, 'Consulta de Urgencia', 'Atencion medica prioritaria fuera del horario habitual o sin cita previa.', 'Urgencias', 85000, 45, TRUE),
(5, 'Control Post-Operatorio', 'Revision de la mascota despues de un procedimiento quirurgico para monitorear la recuperacion.', 'Seguimiento', 30000, 20, TRUE),
(6, 'Limpieza Dental (Profilaxis)', 'Procedimiento para eliminar el sarro y la placa bacteriana de los dientes.', 'Odontologia', 120000, 60, TRUE);