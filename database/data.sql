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


INSERT INTO servicios (nombre, descripcion, categoria, precio_base, duracion_estimada_minutos, activo) VALUES
( 'Consulta General', 'Evaluacion medica completa de la mascota, diagnostico y recomendaciones.', 'Consultas', 50000, 30, TRUE),
('Procedimiento Quirurgico', 'Costo base para intervenciones quirurgicas. El valor final puede variar.', 'Cirugia', 250000, 90, TRUE),
('Aplicacion de Vacuna', 'Servicio de aplicacion de una dosis de vacuna. No incluye el costo de la vacuna.', 'Prevencion', 15000, 10, TRUE),
('Consulta de Urgencia', 'Atencion medica prioritaria fuera del horario habitual o sin cita previa.', 'Urgencias', 85000, 45, TRUE),
('Control Post-Operatorio', 'Revision de la mascota despues de un procedimiento quirurgico para monitorear la recuperacion.', 'Seguimiento', 30000, 20, TRUE),
('Limpieza Dental (Profilaxis)', 'Procedimiento para eliminar el sarro y la placa bacteriana de los dientes.', 'Odontologia', 120000, 60, TRUE);



INSERT INTO proveedores (nombre_empresa, contacto, telefono, email, direccion, activo) VALUES
('DistriPet S.A.S.', 'Juan Carlos Mendoza', '3105551234', 'ventas@distripet.com', 'Calle 10 # 20-30 Bogota', TRUE),
('NutriCan Alimentos', 'Ana Maria Rojas', '3205555678', 'pedidos@nutrican.com', 'Carrera 15 # 45-60 Medellin', TRUE),
('Insumos Vet Colombia', 'Luis Fernando Diaz', '3155559012', 'contacto@insumosvet.co', 'Avenida 6 # 12N-34 Cali', TRUE);


INSERT INTO inventario (nombre_producto, producto_tipo_id, descripcion, fabricante, proveedor_id, lote, cantidad_stock, stock_minimo, unidad_medida, fecha_vencimiento, precio_compra, precio_venta, requiere_receta, activo) VALUES
('Rimadyl 100mg (Anti-inflamatorio)', 1, 'Tabletas masticables para el alivio del dolor y la inflamacion en perros.', 'Zoetis', 2, 'LOTE-A112', 50, 10, 'caja x 14 tabletas', '2026-12-31', 45000, 85000, TRUE, TRUE),
('NexGard (Antipulgas)', 1, 'Tableta masticable para el tratamiento de pulgas y garrapatas en perros.', 'Boehringer Ingelheim', 3, 'LOTE-B234', 80, 20, 'tableta', '2027-08-15', 25000, 45000, FALSE, TRUE),
('Vacuna Puppy DP (Parvo/Distemper)', 2, 'Vacuna para la inmunizacion de cachorros contra moquillo y parvovirus.', 'MSD Animal Health', 3, 'VAC-001', 100, 30, 'dosis', '2026-05-20', 18000, 35000, FALSE, TRUE),
('Vacuna Triple Felina', 2, 'Inmunizacion contra Rinotraqueitis, Calicivirus y Panleucopenia felina.', 'Virbac', 3, 'VAC-F05', 75, 25, 'dosis', '2026-07-10', 22000, 42000, FALSE, TRUE),
('Jeringas Desechables 5ml', 3, 'Paquete de jeringas esteriles para administracion de medicamentos.', 'Becton Dickinson', 3, NULL, 200, 50, 'caja x 100', NULL, 30000, 50000, FALSE, TRUE),
('Gasas Esteriles 10x10cm', 3, 'Paquete de gasas para curaciones.', 'Johnson & Johnson', 3, NULL, 150, 40, 'paquete x 100', NULL, 15000, 25000, FALSE, TRUE),
('Hills Science Diet Adult Small Paws', 4, 'Alimento seco para perros adultos de razas pequeñas.', 'Hills Pet Nutrition', 2, 'HSD-5501', 30, 5, 'bulto 7.5kg', '2027-01-30', 120000, 210000, FALSE, TRUE),
('Royal Canin Feline Health Nutrition Kitten', 4, 'Alimento seco para gatitos de 2 a 12 meses.', 'Royal Canin', 2, 'RC-K202', 40, 10, 'bulto 3kg', '2026-11-25', 80000, 150000, FALSE, TRUE),
('Bravecto (10-20kg)', 1, 'Tableta masticable contra pulgas y garrapatas de larga duracion (3 meses).', 'MSD Animal Health', , 'LOTE-C555', 60, 15, 'tableta', '2028-02-10', 70000, 110000, FALSE, TRUE);



INSERT INTO veterinarios (nombre_completo, documento_identidad, licencia_profesional, especialidad, telefono, email, fecha_contratacion, activo) 
VALUES ('Dr. Carlos Rivera', '1020304050', 'VET-COL-001', 'Medicina General', '3101234567', 'carlos.rivera@happyfeet.com', '2020-05-10', TRUE);

INSERT INTO veterinarios (nombre_completo, documento_identidad, licencia_profesional, especialidad, telefono, email, fecha_contratacion, activo) 
VALUES ('Dra. Ana Martinez', '1098765432', 'VET-COL-002', 'Cirugia de Tejidos Blandos', '3209876543', 'ana.martinez@happyfeet.com', '2021-09-01', TRUE);

INSERT INTO veterinarios (nombre_completo, documento_identidad, licencia_profesional, especialidad, telefono, fecha_contratacion, activo) 
VALUES ('Dr. Jorge Diaz', '1055667788', 'VET-COL-003', 'Animales Exoticos', '3151112233', '2023-01-20', TRUE);









