\c postgres





DROP DATABASE veterinaria;





CREATE DATABASE veterinaria;





\c veterinaria


-- CREAMOS LAS TABLAS,


CREATE TABLE animales(
    id_animal SERIAL PRIMARY KEY,
    nombre CHAR(15) NOT NULL,
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE razas(
    id_raza SERIAL PRIMARY KEY,
    nombre CHAR(30) NOT NULL,
    total_adopción INTEGER DEFAULT 0,
    id_animal INTEGER NOT NULL REFERENCES animales,
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE propietarios(
    rfc_dueño CHAR(13) PRIMARY KEY,
    nombre CHAR(30) NOT NULL,
    apellido_p CHAR(30) NOT NULL,
    apellido_m CHAR(30),
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE mascotas(
    curp_mascota CHAR(13) PRIMARY KEY,
    nombre CHAR(30) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    edad INTEGER NOT NULL,
    sexo BOOLEAN,
    rfc_dueño CHAR(13) REFERENCES propietarios,
    id_raza INTEGER REFERENCES razas,
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE alimentos(
    id_alimento SERIAL PRIMARY KEY,
    nombre CHAR(30) NOT NULL,
    monto DECIMAL(10, 2) NOT NULL,
    gramaje DECIMAL(10, 2) NOT NULL,
    descripcion CHAR(100) DEFAULT '',
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE tipo_Producto(
    id_tipo_producto SERIAL PRIMARY KEY,
    nombre CHAR(30) NOT NULL,
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE productos(
    id_producto SERIAL NOT NULL PRIMARY KEY,
    nombre CHAR(30) NOT NULL,
    monto DECIMAL(10, 2) NOT NULL,
    descripcion CHAR(100) DEFAULT '',
    id_tipo_producto INTEGER NOT NULL REFERENCES tipo_producto,
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE medicamentos(
    id_medicamento SERIAL PRIMARY KEY,
    nombre CHAR(30) NOT NULL,
    monto DECIMAL(10, 2) NOT NULL,
    gramaje DECIMAL(10, 2) NOT NULL,
    laboratorio CHAR(30) NOT NULL,
    descripcion CHAR(100) DEFAULT '',
    tipo VARCHAR(25) NOT NULL,
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE empleados(
    rfc_empleado CHAR(13) NOT NULL PRIMARY KEY,
    nombre CHAR(30) NOT NULL,
    apellido_p CHAR(30) NOT NULL,
    apellido_m CHAR(30) NOT NULL,
    fecha_ini DATE NOT NULL,
    jor_ini TIME NOT NULL,
    jor_fin TIME NOT NULL,
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE veterinarios(
    rfc_veterinario CHAR(13) NOT NULL REFERENCES empleados,
    PRIMARY KEY(rfc_veterinario),
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE especialidades(
    id_especialidad SERIAL NOT NULL PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL,
    activo BOOLEAN DEFAULT TRUE
);

-- Muchos a muchos
CREATE TABLE veterinarios_especialidades(
    rfc_veterinario char(13) REFERENCES veterinarios,
    id_especialidad integer REFERENCES especialidades,
    PRIMARY KEY(rfc_veterinario, id_especialidad),
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE nominas(
    id_nomina SERIAL NOT NULL,
    fecha_factura DATE NOT NULL,
    total_horas smallint NOT NULL,
    salario DECIMAL(10, 2) NOT NULL,
    total_bono DECIMAL(10, 2) DEFAULT 0,
    rfc_empleado VARCHAR(13) NOT NULL REFERENCES empleados,
    PRIMARY KEY(id_nomina, rfc_empleado),
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE proveedores(
    id_proveedores SERIAL PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL,
    direccion VARCHAR(50),
    telefono VARCHAR(13) NOT NULL,
    descripcion VARCHAR(40) NOT NULL,
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE facturas(
    id_factura SERIAL PRIMARY KEY,
    fecha_factura DATE NOT NULL,
    monto_total DECIMAL(10, 2) NOT NULL,
    id_proveedor INTEGER NOT NULL REFERENCES proveedores,
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE alimentos_factura(
    cantidad INTEGER NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL,
    id_alimento INTEGER REFERENCES alimentos,
    id_factura INTEGER REFERENCES facturas,
    PRIMARY KEY(id_alimento, id_factura)
);

CREATE TABLE productos_factura(
    cantidad INTEGER NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL,
    id_producto INTEGER REFERENCES productos,
    id_factura INTEGER REFERENCES facturas,
    PRIMARY KEY(id_producto, id_factura)
);

CREATE TABLE medicamentos_factura(
    cantidad INTEGER NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL,
    id_medicamento INTEGER REFERENCES medicamentos,
    id_factura INTEGER REFERENCES facturas,
    PRIMARY KEY(id_medicamento, id_factura)
);

CREATE TABLE alimentos_stock(
    caducidad DATE NOT NULL,
    cantidad INTEGER NOT NULL,
    id_alimento INTEGER REFERENCES alimentos,
    PRIMARY KEY(id_alimento,caducidad)
);

CREATE TABLE productos_stock(
    caducidad DATE NOT NULL ,
    cantidad INTEGER NOT NULL,
    id_producto INTEGER REFERENCES productos,
    PRIMARY KEY(id_producto, caducidad)
);

CREATE TABLE medicamentos_stock(
    caducidad DATE NOT NULL ,
    cantidad INTEGER NOT NULL,
    id_medicamento INTEGER REFERENCES medicamentos,
    PRIMARY KEY(id_medicamento, caducidad)
);

CREATE TABLE formas_pago(
    id_forma_pago SERIAL PRIMARY KEY,
    nombre CHAR(30) NOT NULL,
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE tickets(
    id_ticket SERIAL PRIMARY KEY,
    monto_total DECIMAL(10, 2) NOT NULL,
    fecha_cobro DATE NOT NULL,
    hora_cobro TIME NOT NULL,
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE pagos(
    id_pago SERIAL,
    subtotal DECIMAL(10, 2) NOT NULL,
    id_forma_pago INTEGER REFERENCES formas_pago,
    id_ticket INTEGER REFERENCES tickets,
    PRIMARY KEY(id_ticket, id_pago),
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE alimentos_vendidos(
    id_registro serial,
    cantidad INTEGER NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL,
    id_alimento INTEGER REFERENCES alimentos,
    id_ticket INTEGER REFERENCES tickets,
    PRIMARY KEY(id_ticket, id_registro),
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE productos_vendidos(
    id_registro serial,
    cantidad INTEGER NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL,
    id_producto INTEGER REFERENCES productos,
    id_ticket INTEGER REFERENCES tickets,
    PRIMARY KEY(id_ticket, id_registro),
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE vacunas_expediente(
    id_registro serial,
    fecha_vacuna DATE NOT NULL,
    curp_mascota CHAR(13) REFERENCES mascotas,
    id_vacuna INTEGER NOT NULL REFERENCES medicamentos,
    primary key(id_registro, curp_mascota),
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE estatus(
    id_estatus SERIAL PRIMARY KEY,
    nombre CHAR(20) NOT NULL
);

CREATE TABLE citas(
    id_cita SERIAL,
    fecha_cita DATE NOT NULL,
    hora TIME NOT NULL,
    monto DECIMAL(10, 2) NOT NULL,
    detalle CHAR(100) DEFAULT '',
    curp_mascota CHAR(13) REFERENCES mascotas,
    rfc_veterinario CHAR(13) REFERENCES veterinarios,
    id_ticket INTEGER REFERENCES tickets,
    id_estatus INTEGER REFERENCES estatus,
    PRIMARY KEY(id_cita, curp_mascota),
    activo BOOLEAN DEFAULT TRUE
);
