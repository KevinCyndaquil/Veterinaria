\c postgres;

DROP DATABASE veterinaria;

CREATE DATABASE veterinaria;

\c veterinaria;

-- CREAMOS LAS TABLAS

CREATE TABLE animales (
    id_animal serial NOT NULL PRIMARY KEY,
    nombre varchar(15) NOT NULL,
    CONSTRAINT nombre_invalido
        CHECK ( nombre ~ '^[A-Z]+$' ),
    active boolean DEFAULT TRUE
);

CREATE TABLE razas (
    id_raza integer PRIMARY KEY,
    nombre varchar(50) NOT NULL,
    CONSTRAINT nombre_invalido
        CHECK ( nombre ~ '^[A-Z ]+$' ),
    total_adopción integer NOT NULL DEFAULT 0,
    CONSTRAINT adopcion_invalido --No pueden haber razas en adopción negativos
        CHECK ( total_adopción >= 0 ),
    id_animal integer NOT NULL REFERENCES animales,
    active boolean DEFAULT TRUE
);


-- Esta es una super tabla, hay generalización
CREATE TABLE personas (
    id_persona serial PRIMARY KEY,
    rfc varchar(13),
    CONSTRAINT rfc_unique
        UNIQUE(rfc),
    CONSTRAINT rfc_invalido
        CHECK ( character_length(rfc) <= 13 ),
    CONSTRAINT rfc_character_invalido
        CHECK ( rfc ~ '^[A-Z0-9]+$' ),
    nombre varchar(30) NOT NULL,
    CONSTRAINT nombre_invalido
        CHECK ( nombre ~ '^[A-Z ]+$' ),
    apellido_p varchar(30) NOT NULL,
    CONSTRAINT apellido_p_invalido
        CHECK ( apellido_p ~ '^[A-Z]+$' ),
    apellido_m varchar(30),
    CONSTRAINT apellido_m_invalido
        CHECK ( apellido_m ~ '^[A-Z]+$' ),
    no_cuenta varchar(19),
    CONSTRAINT no_cuenta_invalido
        CHECK ( no_cuenta ~ '^\d{4,4} \d{4,4} \d{4,4} \d{4,4}$' ),
    active boolean DEFAULT TRUE
);

-- tabla débil
CREATE TABLE empleados (
    PRIMARY KEY (id_persona),
    fecha_ini date NOT NULL,
    CONSTRAINT fecha_invalida
        CHECK ( fecha_ini <= now() ),
    jor_ini time NOT NULL,
    jor_fin time NOT NULL,
    CONSTRAINT jornada_invalida
        CHECK ( (jor_fin - jor_ini) <= '08:00:00' ),
    salario_bruto decimal(10, 2) NOT NULL DEFAULT 0 CHECK ( salario_bruto > 0 ),
    salario_neto decimal(10, 2) NOT NULL DEFAULT 0 CHECK ( salario_neto > 0 ),
    puesto varchar NOT NULL,
    CONSTRAINT puesto_invalido
        CHECK ( puesto IN ('mostrador','veterinario', 'limpieza', 'gerente') )
) INHERITS (personas);

CREATE TABLE mascotas (
    id_mascota serial NOT NULL PRIMARY KEY,
    nombre varchar(30) NOT NULL,
    CONSTRAINT nombre_invalido
        CHECK ( nombre ~ '^[A-Z ]+$' ),
    fecha_nacimiento date NOT NULL,
    CONSTRAINT fecha_invalida
        CHECK ( fecha_nacimiento <= now() ),
    sexo varchar(6),
    CHECK ( sexo = 'macho' OR sexo = 'hembra' ),
    id_persona integer NOT NULL NOT NULL REFERENCES personas,
    id_raza integer NOT NULL NOT NULL REFERENCES razas,
    active boolean DEFAULT TRUE
);

CREATE TABLE proveedores (
    id_proveedor serial PRIMARY KEY,
    nombre varchar(30) NOT NULL UNIQUE,
    CONSTRAINT nombre_invalido
        CHECK ( nombre ~ '^[A-Z ]+$' ),
    direccion varchar(50),
    telefono varchar(16) NOT NULL,
    CONSTRAINT telefono_invalido
        CHECK ( telefono ~ '^\+?[-.\s]?\(?\d{3,3}\)?[-.\s]?\d{3,3}[-.\s]?\d{4,4}$' ),
    descripcion varchar(40) NOT NULL,
    active boolean DEFAULT TRUE
);

CREATE TABLE articulos (
    id_articulo serial NOT NULL PRIMARY KEY,
    id_proveedor integer NOT NULL REFERENCES proveedores,
    nombre varchar(30) NOT NULL,
    CONSTRAINT nombre_invalido
        CHECK ( nombre ~ '^[A-Z0-9.-/ ]+$' ),
    monto_compra decimal(10, 2) NOT NULL,
    CONSTRAINT monto_invalido
        CHECK ( monto_compra > 0 ),
    descripcion varchar(100) DEFAULT '',
    active boolean DEFAULT TRUE
);

CREATE TABLE articulos_venta (
    id_articulo integer NOT NULL REFERENCES articulos,
    PRIMARY KEY (id_articulo),
    monto decimal(10, 2) NOT NULL,
    CONSTRAINT monto_invalido
        CHECK ( monto > 0 ),
    stock integer NOT NULL DEFAULT 0,
    CONSTRAINT stock_invalido CHECK ( stock >= 0 ),
    CONSTRAINT articulo_repetido
        UNIQUE(id_articulo),
    tipo varchar NOT NULL,
    CONSTRAINT tipo_articulo_invalido
        CHECK ( tipo IN ('alimento', 'medicamento', 'producto') )
);

-- esta función devuelve true si hay un id referenciado por las tablas alimentos, medicamentos, productos
-- la usamos para validar que un solo articulo sea referenciado por cualquiera de esas tablas
-- 1 = alimento
-- 2 = producto
-- 3 = medicamento
CREATE OR REPLACE FUNCTION check_articulos(pId_articulo integer, tipo integer) RETURNS boolean LANGUAGE plpgsql AS $$
DECLARE
    si_ali boolean;
    si_pro boolean;
    si_med boolean;
BEGIN
    si_ali = exists(
        SELECT *
        FROM alimentos
        WHERE id_articulo = pId_articulo);

    si_pro = exists(
        SELECT *
        FROM productos
        WHERE id_articulo = pId_articulo);

    si_med = exists(
        SELECT *
        FROM medicamentos
        WHERE id_articulo = pId_articulo);

    CASE
        WHEN tipo = 1 THEN
            RETURN si_pro OR si_med;
        WHEN tipo = 2 THEN
            RETURN si_ali OR si_med;
        WHEN tipo = 3 THEN
            RETURN si_ali OR si_pro;
    END CASE;

    RETURN TRUE;
END;
$$;

CREATE TABLE alimentos (
    id_articulo integer NOT NULL REFERENCES articulos PRIMARY KEY,
    CONSTRAINT referencia_invalida
        CHECK (check_articulos(id_articulo, 1) = FALSE),
    gramaje decimal(10, 2) NOT NULL,
    CONSTRAINT gramaje_invalido
        CHECK ( gramaje > 0 )
);

CREATE TABLE productos (
    id_articulo integer NOT NULL REFERENCES articulos PRIMARY KEY,
    CONSTRAINT referencia_invalida
        CHECK (check_articulos(id_articulo, 2) = FALSE),
    tipo varchar(10),
    CHECK ( tipo IN ('accesorio','ropa','juguete','seguridad','higiene'))
);

CREATE TABLE medicamentos (
    id_articulo integer NOT NULL REFERENCES articulos PRIMARY KEY,
    CONSTRAINT referencia_invalida
        CHECK (check_articulos(id_articulo, 3) = FALSE),
    gramaje        decimal(10, 2) NOT NULL,
    laboratorio    varchar(30)    NOT NULL,
    CONSTRAINT laboratorio_invalido
        CHECK ( laboratorio ~ '^[A-Z0-9.-/ ]+$' ),
    via            varchar(13)    NOT NULL,
    CHECK ( via IN ('oral','intravenosa','intramuscular','rectal','ocular','nasal','cutaneo') )
);

CREATE TABLE facturas_proveedor (
    id_factura serial PRIMARY KEY,
    fecha_factura date NOT NULL,
    CONSTRAINT fecha_invalida
        CHECK ( fecha_factura <= now() ),
    monto_total decimal(10, 2) DEFAULT 0 NOT NULL,
    CONSTRAINT monto_invalido
        CHECK ( monto_total >= 0 ),
    id_proveedor integer NOT NULL NOT NULL REFERENCES proveedores,
    CONSTRAINT factura_proveedor_repetida
        UNIQUE (fecha_factura, id_proveedor),
    active boolean DEFAULT TRUE
);

CREATE TABLE detalle_factura (
    id_factura integer NOT NULL NOT NULL REFERENCES facturas_proveedor,
    cns_detalle_factura integer NOT NULL,
    cantidad integer NOT NULL,
    CONSTRAINT cantidad_invalida
        CHECK ( cantidad > 0 ),
    subtotal decimal(10, 2) NOT NULL,
    CONSTRAINT subtotal_invalido
        CHECK ( subtotal >= 0 ),
    id_articulo integer NOT NULL REFERENCES articulos,
    CONSTRAINT articulo_en_factura_repetido
        UNIQUE (id_articulo, cns_detalle_factura),
    PRIMARY KEY (id_factura, cns_detalle_factura)
);

CREATE TABLE formas_pago (
    id_forma_pago serial PRIMARY KEY,
    nombre varchar(30) NOT NULL,
    CONSTRAINT nombre_invalido
        CHECK ( nombre ~ '^[A-Za-z\s]$' ),
    comision decimal(10, 2) NOT NULL DEFAULT 0,
    active boolean DEFAULT TRUE
);

CREATE TABLE tickets (
    id_ticket serial PRIMARY KEY,
    monto_total decimal(10, 2) NOT NULL,
    CONSTRAINT monto_invalido
        CHECK ( monto_total > 0 ),
    fecha_cobro date NOT NULL,
    CONSTRAINT fecha_invalida
        CHECK ( fecha_cobro = now() ),
    hora_cobro time NOT NULL,
    CONSTRAINT hora_invalida
        CHECK ( hora_cobro >= '09:00:00' AND hora_cobro <= '21:00:00' ),
    estatus varchar NOT NULL CHECK(estatus in('PAGADO','PENDIENTE','CANCELADO'))
);

CREATE TABLE pagos (
    cns_pago serial NOT NULL ,
    subtotal decimal(10, 2) NOT NULL,
    CONSTRAINT subtotal_invalido
        CHECK ( subtotal > 0 ),
    id_forma_pago integer NOT NULL NOT NULL REFERENCES formas_pago,
    id_ticket integer NOT NULL NOT NULL REFERENCES tickets,
    active boolean DEFAULT TRUE,
    PRIMARY KEY(id_ticket, cns_pago)
);

CREATE TABLE detalle_ticket (
    id_ticket integer NOT NULL NOT NULL REFERENCES tickets,
    cns_detalle_ticket integer NOT NULL,
    cantidad integer NOT NULL,
    CONSTRAINT cantidad_invalida
        CHECK ( cantidad > 0 ),
    subtotal decimal(10, 2) NOT NULL,
    CONSTRAINT subtotal_invalido
        CHECK ( subtotal > 0 ),
    id_articulo integer NOT NULL NOT NULL REFERENCES articulos_venta,
    PRIMARY KEY (id_ticket, cns_detalle_ticket)
);

CREATE TABLE citas (
    id_cita serial PRIMARY KEY,
    fecha_cita date NOT NULL,
    CONSTRAINT fecha_invalida
        CHECK ( fecha_cita >= now() ), -- la cita no puede agendarse en el pasado
    hora time NOT NULL,
    CONSTRAINT hora_invalida
        CHECK ( hora >= '09:00:00' AND hora <= '21:00:00' ), -- la cita no puede realizarse fuera del horario de atención
    detalle varchar(100) DEFAULT '',
    id_mascota integer NOT NULL NOT NULL REFERENCES mascotas,
    id_veterinario integer NOT NULL NOT NULL REFERENCES empleados, --validar que sea un veterinario
    id_ticket integer NOT NULL NOT NULL REFERENCES tickets,
    estatus varchar(12) NOT NULL,
    CHECK ( estatus IN ('pendiente','realizada','cancelada','no realizada','pospuesta') ),
    active boolean DEFAULT TRUE
);