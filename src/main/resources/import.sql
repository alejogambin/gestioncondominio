-- Asignar rol ADMIN a ale@gmail.com (usuario id 1)
-- Verificar que el rol ADMIN existe con ID 1
INSERT IGNORE INTO roles (id, nombre, descripcion) VALUES(1, 'ADMIN', 'Administrador del sistema');
INSERT IGNORE INTO roles (id, nombre, descripcion) VALUES(2, 'USER', 'Usuario normal');

-- Asignar role ADMIN al usuario ale@gmail.com
INSERT IGNORE INTO usuario_roles (usuario_id, roles_id) VALUES(1, 1);