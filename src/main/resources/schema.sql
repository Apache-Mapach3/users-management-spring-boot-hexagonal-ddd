-- =============================================
-- Script de creación de la base de datos
-- Gestión de Usuarios - Arquitectura Hexagonal
-- =============================================

CREATE DATABASE IF NOT EXISTS crud_usuarios
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE crud_usuarios;

CREATE TABLE IF NOT EXISTS users (
    id          VARCHAR(36)  NOT NULL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    email       VARCHAR(150) NOT NULL UNIQUE,
    password    VARCHAR(255) NOT NULL,
    role        ENUM('ADMIN', 'MEMBER', 'REVIEWER') NOT NULL,
    status      ENUM('ACTIVE', 'INACTIVE', 'PENDING', 'BLOCKED') NOT NULL DEFAULT 'PENDING',
    created_at  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Usuario administrador inicial (password: Admin1234!)
INSERT INTO users (id, name, email, password, role, status)
VALUES (
    '00000000-0000-0000-0000-000000000001',
    'Administrador',
    'admin@example.com',
    '$2a$12$placeholderHashReplaceWithRealBCryptHash',
    'ADMIN',
    'ACTIVE';

-- =============================================
-- Tabla para la Entidad Censo
-- =============================================
        CREATE TABLE IF NOT EXISTS censos (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    fecha DATE NOT NULL,
    pais VARCHAR(100) NOT NULL,
    departamento VARCHAR(100) NOT NULL,
    ciudad VARCHAR(100) NOT NULL,
    casa VARCHAR(100) NOT NULL,
    num_hombres INT NOT NULL,
    num_mujeres INT NOT NULL,
    num_ancianos_hombres INT NOT NULL,
    num_ancianas_mujeres INT NOT NULL,
    num_ninos INT NOT NULL,
    num_ninas INT NOT NULL,
    num_habitaciones INT NOT NULL,
    num_camas INT NOT NULL,
    tiene_agua BOOLEAN NOT NULL,
    tiene_luz BOOLEAN NOT NULL,
    tiene_alcantarillado BOOLEAN NOT NULL,
    tiene_gas BOOLEAN NOT NULL,
    tiene_otros_servicios BOOLEAN NOT NULL,
    nombre_sensador VARCHAR(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

