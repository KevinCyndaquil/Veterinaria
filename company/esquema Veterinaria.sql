\c postgres

DROP DATABASE veterinaria;

CREATE DATABASE veterinaria;

\c veterinaria

-- CREAMOS LAS TABLAS

CREATE TABLE razas
(
    id_raza SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    CONSTRAINT nombre_invalido
        CHECK ( nombre ~ '^[A-Z ]+$' ),
    total_adopción INTEGER NOT NULL DEFAULT 0,
    CONSTRAINT adopcion_invalido --No pueden haber razas en adopción negativos
        CHECK ( total_adopción >= 0 ),
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE animales
(
    id_animal SERIAL NOT NULL PRIMARY KEY,
    nombre VARCHAR(15) NOT NULL,
    CONSTRAINT nombre_invalido
        CHECK ( nombre ~ '^[A-Z]+$' ),
    activo BOOLEAN DEFAULT TRUE,
    id_raza INTEGER REFERENCES razas
);


-- Esta es una super tabla, hay generalización
CREATE TABLE personas
(
    id_persona SERIAL PRIMARY KEY,
    rfc VARCHAR(13),
    CONSTRAINT rfc_unique
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
    no_cuenta VARCHAR(19),
    CONSTRAINT no_cuenta_invalido
        CHECK ( no_cuenta ~ '^\d{4,4} \d{4,4} \d{4,4} \d{4,4}$' ),
    activo BOOLEAN DEFAULT TRUE
);

-- tabla débil
CREATE TABLE propietarios
(
    id_propietario INTEGER NOT NULL REFERENCES personas,
    PRIMARY KEY(id_propietario)
);

-- tabla débil
CREATE TABLE empleados
(
    id_empleado INTEGER NOT NULL REFERENCES personas,
    PRIMARY KEY (id_empleado),
    fecha_ini DATE NOT NULL,
    CONSTRAINT fecha_invalida
        CHECK ( fecha_ini <= now() ),
    jor_ini TIME NOT NULL,
    jor_fin TIME NOT NULL,
    CONSTRAINT jornada_invalida
        CHECK ( (jor_fin - jor_ini) <= '08:00:00' ),
    salario_bruto DECIMAL(10, 2) NOT NULL DEFAULT 0 CHECK ( salario_bruto > 0 ),
    salario_neto DECIMAL(10, 2) NOT NULL DEFAULT 0 CHECK ( salario_neto > 0 ),
    puesto VARCHAR NOT NULL,
    CONSTRAINT puesto_invalido
        CHECK ( puesto IN ('mostrador','veterinario', 'limpieza', 'gerente') )
);

-- tabla débil
CREATE TABLE nominas
(
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

CREATE TABLE tipos_deducciones
(
    id_tipo_deduccion SERIAL NOT NULL PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL,
    descripcion VARCHAR(100) NOT NULL,
    CONSTRAINT nombre_invalido
        CHECK ( nombre ~ '^[A-Z ]+$' ),
    porcentaje DECIMAL(10, 2) NOT NULL CHECK ( porcentaje > 0 ),
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE deducciones
(
    id_empleado INTEGER,
    cns_nomina INTEGER,
    id_tipo_deduccion INTEGER REFERENCES tipos_deducciones,
    foreign key(id_empleado, cns_nomina) references nominas,
    primary key(id_empleado, cns_nomina, id_tipo_deduccion)
);

CREATE TABLE mascotas
(
    id_mascota       SERIAL NOT NULL PRIMARY KEY,
    nombre           VARCHAR(30) NOT NULL,
    CONSTRAINT nombre_invalido
        CHECK ( nombre ~ '^[A-Z ]+$' ),
    fecha_nacimiento DATE NOT NULL,
    CONSTRAINT fecha_invalida
        CHECK ( fecha_nacimiento <= now() ),
    sexo             VARCHAR(6),
    CHECK ( sexo = 'macho' OR sexo = 'hembra' ),
    id_propietario   INTEGER NOT NULL REFERENCES propietarios,
    id_raza          INTEGER NOT NULL REFERENCES razas,
    activo           BOOLEAN DEFAULT TRUE
);r

CREATE TABLE proveedores
(
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

CREATE TABLE articulos(
    id_articulo SERIAL NOT NULL PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL,
    CONSTRAINT nombre_invalido
        CHECK ( nombre ~ '^[A-Z0-9.-/ ]+$' ),
    monto_compra DECIMAL(10, 2) NOT NULL,
    descripcion VARCHAR(100) DEFAULT '',
    CONSTRAINT monto_invalido
        CHECK ( monto > 0 ),
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE articulos_venta
(
    id_articulo INTEGER REFERENCES articulos primary key,
    monto DECIMAL(10, 2) NOT NULL,
    CONSTRAINT monto_invalido
        CHECK ( monto > 0 ),
    stock INTEGER NOT NULL DEFAULT 0,
    CONSTRAINT stock_invalido CHECK ( stock >= 0 ),
   
    CONSTRAINT articulo_repetido
        UNIQUE(id_articulo),
    tipo varchar NOT NULL,
    CONSTRAINT tipo_articulo_invalido
        CHECK ( tipo IN ('alimento', 'medicamento', 'producto') ),
);

-- esta función devuelve true si hay un id referenciado por las tablas alimentos, medicamentos, productos
-- la usamos para validar que un solo articulo sea referenciado por cualquiera de esas tablas
CREATE OR REPLACE FUNCTION check_articulos(id_articulo integer) RETURNS boolean LANGUAGE plpgsql AS $$
    DECLARE
        si_ali boolean;
        si_pro boolean;
        si_med boolean;
    BEGIN
        si_ali = exists(
                SELECT *
                FROM alimentos
                WHERE id_articulo_alimento = id_articulo);

        si_pro = exists(
                SELECT *
                FROM productos
                WHERE id_articulo_producto = id_articulo);

        si_med = exists(
                SELECT *
                FROM medicamentos
                WHERE id_articulo_medicamento = id_articulo);

        RETURN si_ali OR si_pro OR si_med;
    END;
$$;

CREATE TABLE alimentos
(
    id_articulo_alimento INT NOT NULL REFERENCES articulos_proveedor,
    CONSTRAINT referencia_invalida
        CHECK (check_articulos(id_articulo_alimento) = FALSE),
    PRIMARY KEY(id_articulo_alimento),
    gramaje DECIMAL(10, 2) NOT NULL,
    CONSTRAINT gramaje_invalido
        CHECK ( gramaje > 0 )
);

CREATE TABLE productos
(
    id_articulo_producto int NOT NULL REFERENCES articulos_proveedor,
    CONSTRAINT referencia_invalida
        CHECK (check_articulos(id_articulo_producto) = FALSE),
    PRIMARY KEY (id_articulo_producto),
    tipo VARCHAR(10),
    CHECK ( tipo IN ('accesorio','ropa','juguete','seguridad','higiene'))
);

CREATE TABLE medicamentos
(
    id_articulo_medicamento SERIAL REFERENCES articulos_proveedor,
    CONSTRAINT referencia_invalida
        CHECK (check_articulos(id_articulo_medicamento) = FALSE),
    gramaje        DECIMAL(10, 2) NOT NULL,
    laboratorio    VARCHAR(30)    NOT NULL,
    CONSTRAINT laboratorio_invalido
        CHECK ( laboratorio ~ '^[A-Z0-9.-/ ]+$' ),
    via            VARCHAR(13)    NOT NULL,
    CHECK ( via IN ('oral','intravenosa','intramuscular','rectal','ocular','nasal','cutaneo') )
);

CREATE TABLE facturas_proveedor
(
    id_factura SERIAL PRIMARY KEY,
    fecha_factura DATE NOT NULL,
    CONSTRAINT fecha_invalida
        CHECK ( fecha_factura <= now() ),
    monto_total DECIMAL(10, 2) NOT NULL,
    CONSTRAINT monto_invalido
        CHECK ( monto_total >= 0 ),
    id_proveedor INTEGER NOT NULL REFERENCES proveedores,
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE detalle_factura
(
    id_factura INTEGER NOT NULL REFERENCES facturas_proveedor,
    cns_detalle_factura INTEGER NOT NULL,
    cantidad INTEGER NOT NULL,
    CONSTRAINT cantidad_invalida
        CHECK ( cantidad > 0 ),
    subtotal DECIMAL(10, 2) NOT NULL,

    CONSTRAINT subtotal_invalido
        CHECK ( subtotal >= 0 ),
    id_articulo INTEGER NOT NULL REFERENCES articulos_proveedor,
    CONSTRAINT alimento_repetido
        UNIQUE (id_articulo, cns_detalle_factura),
    PRIMARY KEY (id_factura, cns_detalle_factura)
);

CREATE TABLE formas_pago
(
    id_forma_pago SERIAL PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL,
    CONSTRAINT nombre_invalido
        CHECK ( nombre ~ '^[A-Za-z\s]$' ),
    comision DECIMAL(10, 2) NOT NULL DEFAULT 0,
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE tickets
(
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
    estatus varchar CHECK 
);

CREATE TABLE pagos
(
    cns_pago SERIAL NOT NULL ,
    subtotal DECIMAL(10, 2) NOT NULL,
    CONSTRAINT subtotal_invalido
        CHECK ( subtotal > 0 ),
    id_forma_pago INTEGER NOT NULL REFERENCES formas_pago,
    id_ticket INTEGER NOT NULL REFERENCES tickets,
    activo BOOLEAN DEFAULT TRUE,
    PRIMARY KEY(id_ticket, cns_pago)
);

CREATE TABLE detalle_ticket
(
    id_ticket INTEGER NOT NULL REFERENCES tickets,
    cns_detalle_ticket INTEGER NOT NULL,
    cantidad INTEGER NOT NULL,
    CONSTRAINT cantidad_invalida
        CHECK ( cantidad > 0 ),
    subtotal DECIMAL(10, 2) NOT NULL,
    CONSTRAINT subtotal_invalido
        CHECK ( subtotal > 0 ),
    id_articulo INTEGER NOT NULL REFERENCES articulos_venta,
    PRIMARY KEY (id_ticket, cns_detalle_ticket)
);

-- tabla especializada
CREATE TABLE detalle_mascota
(
    id_mascota INTEGER NOT NULL REFERENCES mascotas,
    cns_detalle_mascota SERIAL,
    fecha DATE NOT NULL,
    CONSTRAINT fecha_invalida
        CHECK ( fecha <= now() ),
    cantidad INTEGER NOT NULL,
    CONSTRAINT cantidad_invalida
        CHECK ( cantidad > 0 ),
    id_articulo INTEGER NOT NULL REFERENCES articulos_venta,
    primary key(id_mascota, cns_detalle_mascota)
);

CREATE TABLE citas
(
    id_cita SERIAL PRIMARY KEY,
    fecha_cita DATE NOT NULL,
    CONSTRAINT fecha_invalida
        CHECK ( fecha_cita >= now() ), -- la cita no puede agendarse en el pasado
    hora TIME NOT NULL,
    CONSTRAINT hora_invalida
        CHECK ( hora >= '09:00:00' AND hora <= '21:00:00' ), -- la cita no puede realizarse fuera del horario de atención
    detalle VARCHAR(100) DEFAULT '',
    id_mascota INTEGER NOT NULL REFERENCES mascotas,
    id_veterinario INTEGER NOT NULL REFERENCES empleados, --validar que sea un veterinario
    id_ticket INTEGER NOT NULL REFERENCES tickets,
    estatus VARCHAR(12) NOT NULL,
    CHECK ( estatus IN ('pendiente','realizada','cancelada','no realizada','pospuesta') ),
    activo BOOLEAN DEFAULT TRUE
);