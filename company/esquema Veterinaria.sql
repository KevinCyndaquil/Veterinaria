\c postgres

DROP DATABASE veterinaria;

CREATE DATABASE veterinaria;

\c veterinaria

-- CREAMOS LAS TABLAS,


CREATE TABLE animales(
    id_animal SERIAL NOT NULL PRIMARY KEY,
    nombre VARCHAR(15) NOT NULL,
    CONSTRAINT nombre_invalido
        CHECK ( nombre ~ '^[A-Z]+$' ),
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE razas(
    id_raza SERIAL NOT NULL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    CONSTRAINT nombre_invalido
        CHECK ( nombre ~ '^[A-Z ]+$' ),
    total_adopción INTEGER NOT NULL DEFAULT 0,
    CONSTRAINT adopcion_invalido --No pueden haber razas en adopción negativos
        CHECK ( total_adopción >= 0 ),
    id_animal INTEGER NOT NULL REFERENCES animales,
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE propietarios(
    id_propietario SERIAL NOT NULL PRIMARY KEY,
    rfc VARCHAR(13),
    CONSTRAINT unique_rfc
        UNIQUE(rfc),
    CONSTRAINT rfc_invalido
        CHECK ( character_length(rfc) <= 13 ),
    CONSTRAINT rfc_character_invalido
        CHECK ( rfc ~ '^[A-Z0-9]+$' ),
    nombre VARCHAR(30) NOT NULL,
    CONSTRAINT nombre_invalido
        CHECK ( nombre ~ '^[A-Z ]+$' ),
    apellido_p VARCHAR(30) NOT NULL,
    CONSTRAINT apellido_p_invalido
        CHECK ( apellido_p ~ '^[A-Z]+$' ),
    apellido_m VARCHAR(30),
    CONSTRAINT apellido_m_invalido
        CHECK ( apellido_m ~ '^[A-Z]+$' ),
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE mascotas(
    id_mascota SERIAL NOT NULL PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL,
    CONSTRAINT nombre_invalido
        CHECK ( nombre ~ '^[A-Z ]+$' ),
    fecha_nacimiento DATE NOT NULL,
    CONSTRAINT fecha_invalida
        CHECK ( fecha_nacimiento <= now() ),
    sexo VARCHAR(6),
    CHECK ( sexo = 'macho' OR sexo = 'hembra' ),
    id_propietario INTEGER NOT NULL REFERENCES propietarios,
    id_raza INTEGER NOT NULL REFERENCES razas,
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE alimentos(
    id_alimento SERIAL NOT NULL PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL,
    CONSTRAINT nombre_invalido
        CHECK ( nombre ~ '^[A-Z0-9 ]+$' ),
    monto DECIMAL(10, 2) NOT NULL,
    CONSTRAINT monto_invalido
        CHECK ( monto > 0 ),
    gramaje DECIMAL(10, 2) NOT NULL,
    CONSTRAINT gramaje_invalido
        CHECK ( gramaje > 0 ),
    descripcion VARCHAR(100) DEFAULT '',
    stock INTEGER NOT NULL DEFAULT 0,
    CONSTRAINT stock_invalido CHECK ( stock >= 0 ),
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE productos(
    id_producto SERIAL NOT NULL PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL,
    CONSTRAINT nombre_invalido
        CHECK ( nombre ~ '^[A-Z0-9 ]+$' ),
    monto DECIMAL(10, 2) NOT NULL,
    CONSTRAINT monto_invalido
        CHECK ( monto > 0 ),
    descripcion VARCHAR(100) DEFAULT '',
    tipo VARCHAR(10),
    CHECK ( tipo = 'accesorio' OR tipo = 'ropa' OR tipo = 'juguete' OR tipo = 'seguridad' OR tipo = 'higiene' ),
    stock INTEGER NOT NULL DEFAULT 0,
    CONSTRAINT stock_invalido CHECK ( stock >= 0 ),
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE medicamentos(
    id_medicamento SERIAL PRIMARY KEY,
    nombre         VARCHAR(30)    NOT NULL,
    CONSTRAINT nombre_invalido
        CHECK ( nombre ~ '^[A-Z0-9 ]+$' ),
    monto          DECIMAL(10, 2) NOT NULL,
    CONSTRAINT monto_invalido
        CHECK ( monto > 0 ),
    gramaje        DECIMAL(10, 2) NOT NULL,
    CONSTRAINT gramaje_invalido
        CHECK ( gramaje > 0 ),
    laboratorio    VARCHAR(30)    NOT NULL,
    descripcion    VARCHAR(100)            DEFAULT '',
    via            VARCHAR(13)    NOT NULL,
    CHECK ( via = 'oral' OR via = 'intravenosa' OR via = 'intramuscular' OR via = 'rectal' OR via = 'ocular' OR
            via = 'nasal' OR via = 'cutaneo'),
    stock          INTEGER        NOT NULL DEFAULT 0,
    CONSTRAINT stock_invalido CHECK ( stock >= 0 ),
    activo         BOOLEAN                 DEFAULT TRUE
);

CREATE TABLE puestos(
    id_puesto SERIAL NOT NULL PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL,
    CONSTRAINT nombre_invalido
        CHECK ( nombre ~ '^[A-Z ]+$' ),
    salario DECIMAL(10, 2) NOT NULL,
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE empleados(
    id_empleado SERIAL PRIMARY KEY,
    rfc VARCHAR(13) NOT NULL,
    CONSTRAINT rfc_unique_error
        UNIQUE(rfc),
    CONSTRAINT rfc_invalido
        CHECK ( character_length(rfc) <= 13 ),
    CONSTRAINT rfc_character_invalido
        CHECK ( rfc ~ '^[A-Z0-9]+$' ),
    nombre VARCHAR(30) NOT NULL,
    CONSTRAINT nombre_invalido
        CHECK ( nombre ~ '^[A-Z ]+$' ),
    apellido_p VARCHAR(30) NOT NULL,
    CONSTRAINT apellido_p_invalido
        CHECK ( apellido_p ~ '^[A-Z ]+$' ),
    apellido_m VARCHAR(30) NOT NULL,
    CONSTRAINT apellido_m_invalido
        CHECK ( apellido_m ~ '^[A-Z ]+$' ),
    fecha_ini DATE NOT NULL,
    CONSTRAINT fecha_invalida
        CHECK ( fecha_ini <= now() ),
    jor_ini TIME NOT NULL,
    jor_fin TIME NOT NULL,
    CONSTRAINT jornada_invalida
        CHECK ( (jor_fin - jor_ini) <= '08:00:00' ),
    id_puesto INTEGER NOT NULL REFERENCES puestos,
    activo BOOLEAN DEFAULT TRUE
);

-- tabla especializada
CREATE TABLE detalles_nomina(
    cns_nomina SERIAL NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    CONSTRAINT quincena_invalida
        CHECK ( fecha_fin > fecha_inicio ),
    total_horas INTEGER NOT NULL,
    CONSTRAINT jornada_invalida
        CHECK ( total_horas > 0 ),
    total_bono DECIMAL(10, 2) DEFAULT 0,
    id_empleado INTEGER NOT NULL REFERENCES empleados,
    PRIMARY KEY(id_empleado, cns_nomina),
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE proveedores(
    id_proveedor SERIAL PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL,
    CONSTRAINT nombre_invalido
        CHECK ( nombre ~ '^[A-Z ]+$' ),
    direccion VARCHAR(50),
    telefono VARCHAR(16) NOT NULL,
    CONSTRAINT telefono_invalido
        CHECK ( telefono ~ '^\+?[-.\s]?\(?\d{3,3}\)?[-.\s]?\d{3,3}[-.\s]?\d{4,4}$' ),
    descripcion VARCHAR(40) NOT NULL,
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE facturas(
    id_factura SERIAL NOT NULL PRIMARY KEY,
    fecha_factura DATE NOT NULL,
    CONSTRAINT fecha_invalida
        CHECK ( fecha_factura <= now() ),
    monto_total DECIMAL(10, 2) NOT NULL,
    CONSTRAINT monto_invalido
        CHECK ( monto_total > 0 ),
    id_proveedor INTEGER NOT NULL REFERENCES proveedores,
    activo BOOLEAN DEFAULT TRUE
);

-- tabla muchos a muchos
CREATE TABLE factura_alimentos(
    cantidad INTEGER NOT NULL,
    CONSTRAINT cantidad_invalida
        CHECK ( cantidad > 0 ),
    subtotal DECIMAL(10, 2) NOT NULL,
    CONSTRAINT subtotal_invalido
        CHECK ( subtotal > 0 ),
    id_alimento INTEGER NOT NULL REFERENCES alimentos,
    id_factura INTEGER NOT NULL REFERENCES facturas,
    PRIMARY KEY(id_factura, id_alimento)
);

-- tabla muchos a muchos
CREATE TABLE factura_productos(
    cantidad INTEGER NOT NULL,
    CONSTRAINT cantidad_invalida
        CHECK ( cantidad > 0 ),
    subtotal DECIMAL(10, 2) NOT NULL,
    CONSTRAINT subtotal_invalido
        CHECK ( subtotal > 0 ),
    id_producto INTEGER NOT NULL REFERENCES productos,
    id_factura INTEGER NOT NULL REFERENCES facturas,
    PRIMARY KEY(id_factura, id_producto)
);

-- tabla muchos a muchos
CREATE TABLE factura_medicamentos(
    cantidad INTEGER NOT NULL,
    CONSTRAINT cantidad_invalida
        CHECK ( cantidad > 0 ),
    subtotal DECIMAL(10, 2) NOT NULL,
    CONSTRAINT subtotal_invalido
        CHECK ( subtotal > 0 ),
    id_medicamento INTEGER REFERENCES medicamentos,
    id_factura INTEGER REFERENCES facturas,
    PRIMARY KEY(id_factura, id_medicamento)
);

CREATE TABLE formas_pago(
    id_forma_pago SERIAL NOT NULL PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL,
    CONSTRAINT nombre_invalido
        CHECK ( nombre ~ '^[A-Za-z\s]$' ),
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE tickets(
    id_ticket SERIAL PRIMARY KEY,
    monto_total DECIMAL(10, 2) NOT NULL,
    CONSTRAINT monto_invalido
        CHECK ( monto_total > 0 ),
    fecha_cobro DATE NOT NULL,
    CONSTRAINT fecha_invalida
        CHECK ( fecha_cobro = now() ),
    hora_cobro TIME NOT NULL,
    CONSTRAINT hora_invalida
        CHECK ( hora_cobro >= '09:00:00' AND hora_cobro <= '21:00:00' ),
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE pagos(
    cns_pago SERIAL NOT NULL ,
    subtotal DECIMAL(10, 2) NOT NULL,
    CONSTRAINT subtotal_invalido
        CHECK ( subtotal > 0 ),
    id_forma_pago INTEGER NOT NULL REFERENCES formas_pago,
    id_ticket INTEGER NOT NULL REFERENCES tickets,
    activo BOOLEAN DEFAULT TRUE,
    PRIMARY KEY(id_ticket, cns_pago)
);

-- tabla muchos a muchos
CREATE TABLE ticket_alimentos(
    cantidad INTEGER NOT NULL,
    CONSTRAINT cantidad_invalida
        CHECK ( cantidad > 0 ),
    subtotal DECIMAL(10, 2) NOT NULL,
    CONSTRAINT subtotal_invalido
        CHECK ( subtotal > 0 ),
    id_alimento INTEGER NOT NULL REFERENCES alimentos,
    id_ticket INTEGER NOT NULL REFERENCES tickets,
    PRIMARY KEY(id_ticket, id_alimento)

);

-- tabla muchos a muchos
CREATE TABLE ticket_productos(
    cantidad INTEGER NOT NULL,
    CONSTRAINT cantidad_invalida
        CHECK ( cantidad > 0 ),
    subtotal DECIMAL(10, 2) NOT NULL,
    CONSTRAINT subtotal_invalido
        CHECK ( subtotal > 0 ),
    id_producto INTEGER NOT NULL REFERENCES productos,
    id_ticket INTEGER NOT NULL REFERENCES tickets,
    PRIMARY KEY(id_ticket, id_producto)
);

-- tabla muchos a muchos
CREATE TABLE ticket_medicamentos(
    cantidad INTEGER NOT NULL,
    CONSTRAINT cantidad_invalida
        CHECK ( cantidad > 0 ),
    subtotal DECIMAL(10, 2) NOT NULL,
    CONSTRAINT subtotal_invalido
        CHECK ( subtotal > 0 ),
    id_medicamento INTEGER NOT NULL REFERENCES medicamentos,
    id_ticket INTEGER NOT NULL REFERENCES tickets,
    PRIMARY KEY(id_ticket, id_medicamento)
);

-- tabla especializada
CREATE TABLE vacunas_expediente(
    cns_vacuna SERIAL,
    fecha_vacuna DATE NOT NULL,
    CONSTRAINT fecha_invalida
        CHECK ( fecha_vacuna <= now() ),
    id_mascota INTEGER NOT NULL REFERENCES mascotas,
    id_medicamento INTEGER NOT NULL REFERENCES medicamentos,
    primary key(id_mascota, cns_vacuna)
);

CREATE TABLE citas(
    id_cita SERIAL PRIMARY KEY,
    fecha_cita DATE NOT NULL,
    CONSTRAINT fecha_invalida
        CHECK ( fecha_cita >= now() ), -- la cita no puede agendarse en el pasado
    hora TIME NOT NULL,
    CONSTRAINT hora_invalida
        CHECK ( hora >= '09:00:00' AND hora <= '21:00:00' ), -- la cita no puede realizarse fuera del horario de atención
    detalle VARCHAR(100) DEFAULT '',
    id_mascota INTEGER NOT NULL REFERENCES mascotas,
    id_empleado INTEGER NOT NULL REFERENCES empleados, --validar que sea un veterinario
    id_ticket INTEGER NOT NULL REFERENCES tickets,
    estatus VARCHAR(12) NOT NULL,
    CHECK ( estatus = 'pendiente' OR estatus = 'realizada' OR estatus = 'cancelada' OR estatus = 'no realizada' OR estatus = 'pospuesta' ),
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE receta_alimentos(
    tomar_dias INTEGER NOT NULL,
    fre_dias INTEGER NOT NULL,
    can_fre DECIMAL(10, 1) NOT NULL,
    CONSTRAINT frecuencia_invalida
        CHECK ( fre_dias > 0 AND can_fre > 0 AND tomar_dias > 0 ),
    id_alimento INTEGER NOT NULL REFERENCES alimentos,
    id_cita INTEGER NOT NULL REFERENCES citas,
    PRIMARY KEY (id_cita, id_alimento)
);

CREATE TABLE receta_medicamentos(
    tomar_dias INTEGER NOT NULL,
    fre_dias INTEGER NOT NULL,
    can_fre DECIMAL(10, 1) NOT NULL,
    CONSTRAINT frecuencia_invalida
        CHECK ( fre_dias > 0 AND can_fre > 0 AND tomar_dias > 0 ),
    id_medicamento INTEGER NOT NULL REFERENCES medicamentos,
    id_cita INTEGER NOT NULL REFERENCES citas,
    PRIMARY KEY (id_cita, id_medicamento)
);
