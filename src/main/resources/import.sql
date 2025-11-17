-- Cargar datos iniciales para la tabla 'usuario'
-- Nota: columnas alineadas con la entidad Usuario (sin 'edad')
INSERT INTO usuario (rut, nombre, apellido, contraseña, correo, rol) VALUES('12345678','Alejandro','gambin','123456','ale@gmail.com','ADMIN');
INSERT INTO usuario (rut, nombre, apellido, contraseña, correo, rol) VALUES('USR0003','Luis','Rodriguez','password3','luis.rodriguez@example.com','USER');
INSERT INTO usuario (rut, nombre, apellido, contraseña, correo, rol) VALUES('USR0004','Ana','Martinez','password4','ana.martinez@example.com','USER');
INSERT INTO usuario (rut, nombre, apellido, contraseña, correo, rol) VALUES('USR0005','Carlos','Sanchez','password5','carlos.sanchez@example.com','USER');
INSERT INTO usuario (rut, nombre, apellido, contraseña, correo, rol) VALUES('USR0006','Laura','Fernandez','password6','laura.fernandez@example.com','ADMIN');
INSERT INTO usuario (rut, nombre, apellido, contraseña, correo, rol) VALUES('USR0007','Pedro','Lopez','password7','pedro.lopez@example.com','USER');
INSERT INTO usuario (rut, nombre, apellido, contraseña, correo, rol) VALUES('USR0008','Sofia','Diaz','password8','sofia.diaz@example.com','USER');
INSERT INTO torre (nombre,num_torre, direccion) VALUES('Torre A',1,'Calle Falsa 123');