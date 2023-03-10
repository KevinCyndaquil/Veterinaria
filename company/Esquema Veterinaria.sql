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
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE medicamentos(
    id_medicamento SERIAL PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL,
    CONSTRAINT nombre_invalido
        CHECK ( nombre ~ '^[A-Z0-9 ]+$' ),
    monto DECIMAL(10, 2) NOT NULL,
    CONSTRAINT monto_invalido
        CHECK ( monto > 0 ),
    gramaje DECIMAL(10, 2) NOT NULL,
    CONSTRAINT gramaje_invalido
        CHECK ( gramaje > 0 ),
    laboratorio VARCHAR(30) NOT NULL,
    descripcion VARCHAR(100) DEFAULT '',
    via VARCHAR(13) NOT NULL,
    CHECK ( via = 'oral' OR via = 'intravenosa' OR via = 'intramuscular' OR via = 'rectal' OR via = 'ocular' OR via = 'nasal' OR via = 'cutaneo'),
    activo BOOLEAN DEFAULT TRUE
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

CREATE TABLE factura.alimentos(
    cns_factura_a INTEGER NOT NULL,
    cantidad INTEGER NOT NULL,
    CONSTRAINT cantidad_invalida
        CHECK ( cantidad > 0 ),
    subtotal DECIMAL(10, 2) NOT NULL,
    CONSTRAINT subtotal_invalido
        CHECK ( subtotal > 0 ),
    id_alimento INTEGER NOT NULL REFERENCES alimentos,
    id_factura INTEGER NOT NULL REFERENCES facturas,
    PRIMARY KEY(id_factura, cns_factura_a)
);

CREATE TABLE factura.productos(
    cns_factura_p INTEGER NOT NULL,
    cantidad INTEGER NOT NULL,
    CONSTRAINT cantidad_invalida
        CHECK ( cantidad > 0 ),
    subtotal DECIMAL(10, 2) NOT NULL,
    CONSTRAINT subtotal_invalido
        CHECK ( subtotal > 0 ),
    id_producto INTEGER NOT NULL REFERENCES productos,
    id_factura INTEGER NOT NULL REFERENCES facturas,
    PRIMARY KEY(id_factura, cns_factura_p)
);

CREATE TABLE factura.medicamentos(
    cns_factura_m INTEGER NOT NULL,
    cantidad INTEGER NOT NULL,
    CONSTRAINT cantidad_invalida
        CHECK ( cantidad > 0 ),
    subtotal DECIMAL(10, 2) NOT NULL,
    CONSTRAINT subtotal_invalido
        CHECK ( subtotal > 0 ),
    id_medicamento INTEGER REFERENCES medicamentos,
    id_factura INTEGER REFERENCES facturas,
    PRIMARY KEY(id_factura, cns_factura_m)
);

CREATE TABLE stock.alimentos(
    cns_stock_a INTEGER NOT NULL,
    caducidad DATE NOT NULL,
    CONSTRAINT caducidad_invalidad
        CHECK ( caducidad > now() ),
    cantidad INTEGER NOT NULL,
    CONSTRAINT cantidad_invalida
        CHECK ( cantidad > 0 ),
    id_alimento INTEGER NOT NULL REFERENCES alimentos,
    PRIMARY KEY(id_alimento,cns_stock_a)
);

CREATE TABLE stock.productos(
    cns_stock_p INTEGER NOT NULL,
    caducidad DATE NOT NULL,
    CONSTRAINT caducidad_invalidad
        CHECK ( caducidad > now() ),
    cantidad INTEGER NOT NULL,
    CONSTRAINT cantidad_invalida
        CHECK ( cantidad > 0 ),
    id_producto INTEGER NOT NULL REFERENCES productos,
    PRIMARY KEY(id_producto, cns_stock_p)
);

CREATE TABLE stock.medicamentos(
    cns_stock_m INTEGER NOT NULL,
    caducidad DATE NOT NULL,
    CONSTRAINT caducidad_invalidad
        CHECK ( caducidad > now() ),
    cantidad INTEGER NOT NULL,
    CONSTRAINT cantidad_invalida
        CHECK ( cantidad > 0 ),
    id_medicamento INTEGER NOT NULL REFERENCES medicamentos,
    PRIMARY KEY(id_medicamento, cns_stock_m)
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

CREATE TABLE ticket.alimentos(
    cns_alimento_ve serial,
    cantidad INTEGER NOT NULL,
    CONSTRAINT cantidad_invalida
        CHECK ( cantidad > 0 ),
    subtotal DECIMAL(10, 2) NOT NULL,
    CONSTRAINT subtotal_invalido
        CHECK ( subtotal > 0 ),
    id_alimento INTEGER NOT NULL REFERENCES alimentos,
    id_ticket INTEGER NOT NULL REFERENCES tickets,
    activo BOOLEAN DEFAULT TRUE,
    PRIMARY KEY(id_ticket, cns_alimento_ve)

);

CREATE TABLE ticket.productos(
    cns_producto_ve serial,
    cantidad INTEGER NOT NULL,
    CONSTRAINT cantidad_invalida
        CHECK ( cantidad > 0 ),
    subtotal DECIMAL(10, 2) NOT NULL,
    CONSTRAINT subtotal_invalido
        CHECK ( subtotal > 0 ),
    id_producto INTEGER NOT NULL REFERENCES productos,
    id_ticket INTEGER NOT NULL REFERENCES tickets,
    activo BOOLEAN DEFAULT TRUE,
    PRIMARY KEY(id_ticket, cns_producto_ve)
);

CREATE TABLE vacunas_expediente(
    cns_vacuna serial,
    fecha_vacuna DATE NOT NULL,
    CONSTRAINT fecha_invalida
        CHECK ( fecha_vacuna <= now() ),
    id_mascota INTEGER NOT NULL REFERENCES mascotas,
    id_medicamento INTEGER NOT NULL REFERENCES medicamentos,
    activo BOOLEAN DEFAULT TRUE,
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

CREATE TABLE receta.alimentos(
    cns_alimentos_re SERIAL,
    tomar_dias INTEGER NOT NULL,
    fre_dias INTEGER NOT NULL,
    can_fre DECIMAL(10, 1) NOT NULL,
    CONSTRAINT frecuencia_invalida
        CHECK ( fre_dias > 0 AND can_fre > 0 AND tomar_dias > 0 ),
    id_alimento INTEGER NOT NULL REFERENCES alimentos,
    id_cita INTEGER NOT NULL REFERENCES citas,
    PRIMARY KEY (id_cita, cns_alimentos_re)
);

CREATE TABLE receta.medicamentos(
    cns_medicamentos_re SERIAL,
    tomar_dias INTEGER NOT NULL,
    fre_dias INTEGER NOT NULL,
    can_fre DECIMAL(10, 1) NOT NULL,
    CONSTRAINT frecuencia_invalida
        CHECK ( fre_dias > 0 AND can_fre > 0 AND tomar_dias > 0 ),
    id_medicamento INTEGER NOT NULL REFERENCES medicamentos,
    id_cita INTEGER NOT NULL REFERENCES citas,
    PRIMARY KEY (id_cita, cns_medicamentos_re)
);


-- PROCEDIMIENTOS ALMACENADOS --
--- Los procedimientos regresarán 0 en caso de haber ocurrido un error durante el proceso.

--- agrega un alimento a la tabla alimentos;
DROP FUNCTION IF EXISTS agrAlimento;
CREATE OR REPLACE FUNCTION  agrAlimento (reg ANYARRAY) RETURNS INTEGER AS $$
    DECLARE
        vNmb VARCHAR        := null;
        vMnt DECIMAL(10, 2) := null;
        vGrm DECIMAL(10, 2) := null;
        vDsc VARCHAR        := null;
        vId  INTEGER        := 0;
    BEGIN
        -- preguntamos si el arreglo está vacío
        IF array_length(reg, 1) = 0 OR array_length(reg, 1) < 4 THEN
            RETURN 0;
        END IF;

        vNmb := upper(reg[1]);
        vMnt := reg[2]::DECIMAL;
        vGrm := reg[3]::DECIMAL;
        vDsc := upper(reg[4]);

        -- consulta
        INSERT INTO
            alimentos
        VALUES (
                DEFAULT,
                vNmb,
                vMnt,
                vGrm,
                vDsc,
                DEFAULT)
        RETURNING id_alimento INTO vId;
        -- fin consulta

        IF vId > 0 THEN
            RAISE NOTICE 'alimento % de id %, insertado correctamente', vNmb, vId;
            RETURN vId;
        END IF;

        RAISE NOTICE 'ERROR: alimento no insertado a la base de datos';
        RETURN 0;
    END;
$$ LANGUAGE plpgsql;