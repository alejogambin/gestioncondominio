CREATE TABLE `user`(
    `id_user` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `rut` VARCHAR(255) NOT NULL,
    `nombre` VARCHAR(255) NOT NULL,
    `apellido` VARCHAR(255) NOT NULL,
    `correo` VARCHAR(255) NOT NULL,
    `contraseña` VARCHAR(255) NOT NULL,
    `rol` VARCHAR(255) NOT NULL,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP NULL CURRENT_TIMESTAMP
);
ALTER TABLE
    `user` ADD UNIQUE `user_rut_unique`(`rut`);
ALTER TABLE
    `user` ADD UNIQUE `user_correo_unique`(`correo`);
CREATE TABLE `gastos_comunes`(
    `id_gastos_comunes` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `valor` BIGINT NOT NULL,
    `id_departamento` BIGINT NOT NULL,
    `fecha_pago` DATE NOT NULL,
    `mes` VARCHAR(255) NOT NULL,
    `num_departamento` BIGINT NOT NULL,
    `torre` BIGINT NOT NULL
);
CREATE TABLE `departamento`(
    `id_departamento` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `piso` BIGINT NOT NULL,
    `num_departamento` BIGINT NOT NULL,
    `id_arrendatario` BIGINT NOT NULL,
    `id_propietario` BIGINT NOT NULL,
    `id_torre` BIGINT NOT NULL,
    `num_habitantes` BIGINT NOT NULL
);
CREATE TABLE `torre`(
    `id_torre` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `nombre` VARCHAR(255) NOT NULL,
    `num_torre` BIGINT NOT NULL,
    `direccion` VARCHAR(255) NOT NULL
);
CREATE TABLE `estacionamiento`(
    `id_estacionamiento` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `num_estacionamiento` BIGINT NOT NULL,
    `tipo` VARCHAR(255) NOT NULL COMMENT 'cubierto/descubierto/visita',
    `id_torre` BIGINT NOT NULL,
    `id_propietario` BIGINT NOT NULL
);
CREATE TABLE `visitas`(
    `id_visitas` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `rut` VARCHAR(255) NOT NULL,
    `nombre` VARCHAR(255) NOT NULL,
    `fecha_ingreso` DATETIME NOT NULL,
    `fecha_salida` DATETIME NOT NULL
);
CREATE TABLE `historial_visitas`(
    `id_historial_visitas` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `id_visita` BIGINT NOT NULL,
    `id_departamento` BIGINT NOT NULL,
    `id_estacionamiento` BIGINT NOT NULL
);
CREATE TABLE `bodega`(
    `id_bodega` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `num_bodega` BIGINT NOT NULL,
    `id_torre` BIGINT NOT NULL,
    `id_propietario` BIGINT NOT NULL
);
ALTER TABLE
    `historial_visitas` ADD CONSTRAINT `historial_visitas_id_visita_foreign` FOREIGN KEY(`id_visita`) REFERENCES `visitas`(`id_visitas`);
ALTER TABLE
    `estacionamiento` ADD CONSTRAINT `estacionamiento_id_torre_foreign` FOREIGN KEY(`id_torre`) REFERENCES `torre`(`id_torre`);
ALTER TABLE
    `departamento` ADD CONSTRAINT `departamento_id_arrendatario_foreign` FOREIGN KEY(`id_arrendatario`) REFERENCES `user`(`id_user`);
ALTER TABLE
    `historial_visitas` ADD CONSTRAINT `historial_visitas_id_departamento_foreign` FOREIGN KEY(`id_departamento`) REFERENCES `departamento`(`id_departamento`);
ALTER TABLE
    `bodega` ADD CONSTRAINT `bodega_id_propietario_foreign` FOREIGN KEY(`id_propietario`) REFERENCES `user`(`id_user`);
ALTER TABLE
    `departamento` ADD CONSTRAINT `departamento_id_propietario_foreign` FOREIGN KEY(`id_propietario`) REFERENCES `user`(`id_user`);
ALTER TABLE
    `estacionamiento` ADD CONSTRAINT `estacionamiento_id_propietario_foreign` FOREIGN KEY(`id_propietario`) REFERENCES `user`(`id_user`);
ALTER TABLE
    `gastos_comunes` ADD CONSTRAINT `gastos_comunes_id_departamento_foreign` FOREIGN KEY(`id_departamento`) REFERENCES `departamento`(`id_departamento`);
ALTER TABLE
    `departamento` ADD CONSTRAINT `departamento_id_torre_foreign` FOREIGN KEY(`id_torre`) REFERENCES `torre`(`id_torre`);
ALTER TABLE
    `bodega` ADD CONSTRAINT `bodega_id_torre_foreign` FOREIGN KEY(`id_torre`) REFERENCES `torre`(`id_torre`);