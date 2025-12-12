-- Cargar datos iniciales para la tabla 'usuario'
INSERT INTO roles (Nombre, descripcion) VALUES('ADMIN', 'Administrador del sistema con todos los privilegios');
INSERT INTO roles (Nombre, descripcion) VALUES('USER', 'Usuario con privilegios limitados');
-- Nota: columnas alineadas con la entidad Usuario (sin 'edad')
--contraseña cifrada con BCrypt
--contraseña password1 = $2a$12$wz5ArHFOrxWgCqHxZ8tydOBeDg/x62AgjijWXvEM4fswARedzOZiC
INSERT INTO usuario (rut, nombre, apellido, contraseña, email, activo) VALUES('12345678','Alejandro','gambin','$2a$12$wz5ArHFOrxWgCqHxZ8tydOBeDg/x62AgjijWXvEM4fswARedzOZiC','ale@gmail.com',true);
INSERT INTO usuario (rut, nombre, apellido, contraseña, email, activo) VALUES('USR0003','Luis','Rodriguez','$2a$12$wz5ArHFOrxWgCqHxZ8tydOBeDg/x62AgjijWXvEM4fswARedzOZiC','luis.rodriguez@example.com',true);
INSERT INTO usuario (rut, nombre, apellido, contraseña, email, activo) VALUES('USR0004','Ana','Martinez','$2a$12$wz5ArHFOrxWgCqHxZ8tydOBeDg/x62AgjijWXvEM4fswARedzOZiC','ana.martinez@example.com',true);
INSERT INTO usuario (rut, nombre, apellido, contraseña, email, activo) VALUES('USR0005','Carlos','Sanchez','$2a$12$wz5ArHFOrxWgCqHxZ8tydOBeDg/x62AgjijWXvEM4fswARedzOZiC','carlos.sanchez@example.com',true);
INSERT INTO usuario (rut, nombre, apellido, contraseña, email, activo) VALUES('USR0006','Laura','Fernandez','$2a$12$wz5ArHFOrxWgCqHxZ8tydOBeDg/x62AgjijWXvEM4fswARedzOZiC','laura.fernandez@example.com',true);
INSERT INTO usuario (rut, nombre, apellido, contraseña, email, activo) VALUES('USR0007','Pedro','Lopez','$2a$12$wz5ArHFOrxWgCqHxZ8tydOBeDg/x62AgjijWXvEM4fswARedzOZiC','pedro.lopez@example.com',true);
INSERT INTO usuario (rut, nombre, apellido, contraseña, email, activo) VALUES('USR0008','Sofia','Diaz','$2a$12$wz5ArHFOrxWgCqHxZ8tydOBeDg/x62AgjijWXvEM4fswARedzOZiC','sofia.diaz@example.com',true);
INSERT INTO torre (nombre,num_torre, direccion) VALUES('Torre A',1,'Calle Falsa 123');


INSERT INTO usuario_roles (usuario_id, roles_id ) VALUES (1,1);
INSERT INTO usuario_roles (usuario_id, roles_id ) VALUES (2,2);
INSERT INTO usuario_roles (usuario_id, roles_id ) VALUES (3,2);
INSERT INTO usuario_roles (usuario_id, roles_id ) VALUES (4,2);
INSERT INTO usuario_roles (usuario_id, roles_id ) VALUES (5,2);
INSERT INTO usuario_roles (usuario_id, roles_id ) VALUES (6,2);
INSERT INTO usuario_roles (usuario_id, roles_id ) VALUES (7,2);
INSERT INTO usuario_roles (usuario_id, roles_id ) VALUES (8,2);