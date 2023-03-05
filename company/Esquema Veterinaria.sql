\c postgres

DROP DATABASE veterinaria;

CREATE DATABASE veterinaria;

\c veterinaria

-- CREAMOS LOS ESQUEMAS

CREATE SCHEMA receta;
CREATE SCHEMA ticket;
CREATE SCHEMA stock;
CREATE SCHEMA factura;

-- CREAMOS LAS TABLAS,


CREATE TABLE animales(
    id_animal SERIAL PRIMARY KEY,
    nombre VARCHAR(15) NOT NULL,
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE razas(
    id_raza SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    total_adopción INTEGER DEFAULT 0,
    id_animal INTEGER NOT NULL REFERENCES animales,
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE propietarios(
    id_propietario SERIAL PRIMARY KEY,
    rfc VARCHAR(13),
    UNIQUE(rfc),
    nombre VARCHAR(30) NOT NULL,
    apellido_p VARCHAR(30) NOT NULL,
    apellido_m VARCHAR(30),
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE mascotas(
    id_mascota SERIAL PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    sexo VARCHAR(6),
    CHECK ( sexo = 'macho' OR sexo = 'hembra' ),
    id_propietario INTEGER REFERENCES propietarios,
    id_raza INTEGER REFERENCES razas,
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE alimentos(
    id_alimento SERIAL PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL,
    monto DECIMAL(10, 2) NOT NULL,
    gramaje DECIMAL(10, 2) NOT NULL,
    descripcion VARCHAR(100) DEFAULT '',
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE productos(
    id_producto SERIAL NOT NULL PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL,
    monto DECIMAL(10, 2) NOT NULL,
    descripcion VARCHAR(100) DEFAULT '',
    tipo VARCHAR(10) NOT NULL,
    CHECK ( tipo = 'accesorio' OR tipo = 'ropa' OR tipo = 'juguete' OR tipo = 'seguridad' OR tipo = 'higiene' ),
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE medicamentos(
    id_medicamento SERIAL PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL,
    monto DECIMAL(10, 2) NOT NULL,
    gramaje DECIMAL(10, 2) NOT NULL,
    laboratorio VARCHAR(30) NOT NULL,
    descripcion VARCHAR(100) DEFAULT '',
    via VARCHAR(13) NOT NULL,
    CHECK ( via = 'oral' OR via = 'intravenosa' OR via = 'intramuscular' OR via = 'rectal' OR via = 'ocular' OR via = 'nasal' OR via = 'cutaneo'),
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE puestos(
    id_puesto SERIAL PRIMARY KEY,
    nombre VARCHAR(30),
    salario DECIMAL(10, 2) NOT NULL,
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE empleados(
    id_empleado SERIAL PRIMARY KEY,
    rfc VARCHAR(13) NOT NULL,
    UNIQUE(rfc),
    nombre VARCHAR(30) NOT NULL,
    apellido_p VARCHAR(30) NOT NULL,
    apellido_m VARCHAR(30) NOT NULL,
    fecha_ini DATE NOT NULL,
    jor_ini TIME NOT NULL,
    jor_fin TIME NOT NULL,
    id_puesto INTEGER REFERENCES puestos,
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE detalles_nomina(
    cns_nomina SERIAL NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    total_horas INTEGER NOT NULL,
    total_bono DECIMAL(10, 2) DEFAULT 0,
    id_empleado INTEGER NOT NULL REFERENCES empleados,
    PRIMARY KEY(id_empleado, cns_nomina),
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE proveedores(
    id_proveedor SERIAL PRIMARY KEY,
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

CREATE TABLE factura.alimentos(
    cns_factura_a INTEGER NOT NULL,
    cantidad INTEGER NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL,
    id_alimento INTEGER REFERENCES alimentos,
    id_factura INTEGER REFERENCES facturas,
    PRIMARY KEY(id_factura, cns_factura_a)
);

CREATE TABLE factura.productos(
    cns_factura_p INTEGER NOT NULL,
    cantidad INTEGER NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL,
    id_producto INTEGER REFERENCES productos,
    id_factura INTEGER REFERENCES facturas,
    PRIMARY KEY(id_factura, cns_factura_p)
);

CREATE TABLE factura.medicamentos(
    cns_factura_m INTEGER NOT NULL,
    cantidad INTEGER NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL,
    id_medicamento INTEGER REFERENCES medicamentos,
    id_factura INTEGER REFERENCES facturas,
    PRIMARY KEY(id_factura, cns_factura_m)
);

CREATE TABLE stock.alimentos(
    cns_stock_a INTEGER NOT NULL,
    caducidad DATE NOT NULL,
    cantidad INTEGER NOT NULL,
    id_alimento INTEGER REFERENCES alimentos,
    PRIMARY KEY(id_alimento,cns_stock_a)
);

CREATE TABLE stock.productos(
    cns_stock_p INTEGER NOT NULL,
    caducidad DATE NOT NULL,
    cantidad INTEGER NOT NULL,
    id_producto INTEGER REFERENCES productos,
    PRIMARY KEY(id_producto, cns_stock_p)
);

CREATE TABLE stock.medicamentos(
    cns_stock_m INTEGER NOT NULL,
    caducidad DATE NOT NULL,
    cantidad INTEGER NOT NULL,
    id_medicamento INTEGER REFERENCES medicamentos,
    PRIMARY KEY(id_medicamento, cns_stock_m)
);

CREATE TABLE formas_pago(
    id_forma_pago SERIAL PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL,
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
    cns_pago SERIAL,
    subtotal DECIMAL(10, 2) NOT NULL,
    id_forma_pago INTEGER REFERENCES formas_pago,
    id_ticket INTEGER REFERENCES tickets,
    activo BOOLEAN DEFAULT TRUE,
    PRIMARY KEY(id_ticket, cns_pago)
);

CREATE TABLE ticket.alimentos(
    cns_alimento_ve serial,
    cantidad INTEGER NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL,
    id_alimento INTEGER REFERENCES alimentos,
    id_ticket INTEGER REFERENCES tickets,
    activo BOOLEAN DEFAULT TRUE,
    PRIMARY KEY(id_ticket, cns_alimento_ve)

);

CREATE TABLE ticket.productos(
    cns_producto_ve serial,
    cantidad INTEGER NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL,
    id_producto INTEGER REFERENCES productos,
    id_ticket INTEGER REFERENCES tickets,
    activo BOOLEAN DEFAULT TRUE,
    PRIMARY KEY(id_ticket, cns_producto_ve)
);

CREATE TABLE vacunas_expediente(
    cns_vacuna serial,
    fecha_vacuna DATE NOT NULL,
    id_mascota INTEGER REFERENCES mascotas,
    id_medicamento INTEGER NOT NULL REFERENCES medicamentos,
    activo BOOLEAN DEFAULT TRUE,
    primary key(id_mascota, cns_vacuna)
);

CREATE TABLE citas(
    id_cita SERIAL PRIMARY KEY,
    fecha_cita DATE NOT NULL,
    hora TIME NOT NULL,
    detalle VARCHAR(100) DEFAULT '',
    id_mascota INTEGER REFERENCES mascotas,
    id_empleado INTEGER REFERENCES empleados, --validar que sea un veterinario
    id_ticket INTEGER REFERENCES tickets,
    estatus VARCHAR(12) NOT NULL,
    CHECK ( estatus = 'pendiente' OR estatus = 'realizada' OR estatus = 'cancelada' OR estatus = 'no realizada' OR estatus = 'pospuesta' ),
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE receta.alimentos(
    cns_alimentos_re SERIAL,
    tomar_dias INTEGER NOT NULL,
    fre_dias INTEGER NOT NULL,
    can_fre DECIMAL(10, 1) NOT NULL,
    id_alimento INTEGER NOT NULL REFERENCES alimentos,
    id_cita INTEGER NOT NULL REFERENCES citas,
    PRIMARY KEY (id_cita, cns_alimentos_re)
);

CREATE TABLE receta.medicamentos(
    cns_medicamentos_re SERIAL,
    tomar_dias INTEGER NOT NULL,
    fre_dias INTEGER NOT NULL,
    can_fre DECIMAL(10, 1) NOT NULL,
    id_medicamento INTEGER NOT NULL REFERENCES medicamentos,
    id_cita INTEGER NOT NULL REFERENCES citas,
    PRIMARY KEY (id_cita, cns_medicamentos_re)
);
