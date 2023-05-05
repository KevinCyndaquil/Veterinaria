/*
 .______________________________________________________________________________.
 |                                                                              |
 |                      PROCEDIMIENTOS ALMACENADOS                              |
 |______________________________________________________________________________|

 */
/*
DROP FUNCTION IF EXISTS agrAnimal;
CREATE OR REPLACE FUNCTION agrAnimal (reg varchar[]) RETURNS integer AS $$
DECLARE
    vNmb varchar := null;
    vIdRaza integer := 0;
    vId  integer := 0;
BEGIN
    IF array_length(reg, 1) < 2 THEN
        RETURN 0;
    END IF;

    vNmb := upper(reg[1]);
    vIdRaza := reg[2]::integer;

    -- consulta
    INSERT INTO animales
    VALUES (DEFAULT, vNmb, DEFAULT)
    RETURNING id_animal INTO vId;
    -- fin consulta

    IF vId > 0 THEN
        RAISE NOTICE 'animal % de id %, insertado correctamente', vNmb, vId;
        RETURN vId;
    END IF;

    RAISE NOTICE 'ERROR: animal no insertado a la base de datos';
    RETURN 0;
END;
$$ LANGUAGE plpgsql;

DROP FUNCTION IF EXISTS actAnimal;
CREATE OR REPLACE FUNCTION actAnimal (reg anyarray) RETURNS integer AS $$
DECLARE
    vNmb varchar        := null;
    vId  integer        := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 2 THEN
        RETURN 0;
    END IF;

    vNmb := upper(reg[1]);
    vId  := reg[2]::integer;

    -- consulta
    UPDATE animales
    SET nombre = vNmb
    WHERE id_animal = vId;
    -- fin consulta

    IF vId > 0 THEN
        RAISE NOTICE 'animal % de id %, actualizado correctamente', vNmb, vId;
        RETURN vId;
    END IF;

    RAISE NOTICE 'ERROR: animal no actualizado';
    RETURN 0;
END;
$$ LANGUAGE plpgsql;

DROP FUNCTION IF EXISTS agrRaza;
CREATE OR REPLACE FUNCTION agrRaza (reg anyarray) RETURNS integer AS $$
DECLARE
    vNmb varchar := null;
    vTotalA integer := 0;
    vIdA integer := 0;
    vId  integer := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 3 THEN
        RETURN 0;
    END IF;

    vNmb := upper(reg[1]);
    vTotalA := reg[2]::integer;
    vIdA := reg[3]::integer;

    -- consulta
    INSERT INTO razas
    VALUES (DEFAULT, vNmb, vTotalA, vIdA)
    RETURNING id_raza INTO vId;
    -- fin consulta

    IF vId > 0 THEN
        RAISE NOTICE 'raza % de id %, insertado correctamente', vNmb, vId;
        RETURN vId;
    END IF;

    RAISE NOTICE 'ERROR: raza no insertado a la base de datos';
    RETURN 0;
END;
$$ LANGUAGE plpgsql;

DROP FUNCTION IF EXISTS actRaza;
CREATE OR REPLACE FUNCTION actRaza (reg anyarray) RETURNS integer AS $$
DECLARE
    vNmb varchar := null;
    vId  integer := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 1 THEN
        RETURN 0;
    END IF;

    vId  := reg[1]::integer;
    vNmb := upper(reg[2]);
    

    -- consulta
    UPDATE
        razas
    SET
        nombre = vNmb
    WHERE
            id_raza = vId;
    -- fin consulta

    IF vId > 0 THEN
        RAISE NOTICE 'raza % de id %, actualizado correctamente', vNmb, vId;
        RETURN vId;
    END IF;

    RAISE NOTICE 'ERROR: raza no actualizado';
    RETURN 0;
END;
$$ LANGUAGE plpgsql;

DROP FUNCTION IF EXISTS agrPropietario;
CREATE OR REPLACE FUNCTION agrPropietario (reg anyarray) RETURNS integer AS $$
DECLARE
    vRfc varchar := null;
    vNmb varchar := null;
    vApP varchar := null;
    vApM varchar := null;
    vNoC varchar := null;
    vId  integer := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 5 THEN
        RETURN 0;
    END IF;

    vRfc := upper(reg[1]);
    vNmb := upper(reg[2]);
    vApP := upper(reg[3]);
    vApM := upper(reg[4]);
    vNoC := reg[5];

    -- añadimos primero a personas
    INSERT INTO
        personas
    VALUES
    (
        DEFAULT,
        vRfc,
        vNmb,
        vApP,
        vApM,
        vNoC,
        DEFAULT
    ) RETURNING id_persona INTO vId;

    IF vid = 0 THEN
        RAISE EXCEPTION 'persona no creada, función cancelada';
    END IF;

    -- consulta
    INSERT INTO
        propietarios
    VALUES (
            vid
    ) RETURNING id_propietario INTO vId;
    -- fin consulta

    IF vId > 0 THEN
        RAISE NOTICE 'propietario % de id %, insertado correctamente', vNmb, vId;
        RETURN vId;
    END IF;

    RAISE NOTICE 'ERROR: propietario no insertado a la base de datos';
    RETURN 0;
END;
$$ LANGUAGE plpgsql;

DROP FUNCTION IF EXISTS actPropietario;
CREATE OR REPLACE FUNCTION actPropietario (reg anyarray) RETURNS integer AS $$
DECLARE
    vRfc varchar := null;
    vNmb varchar := null;
    vApP varchar := null;
    vApM varchar := null;
    vNoC varchar := null;
    vId  integer := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 6 THEN
        RETURN 0;
    END IF;

    vRfc := upper(reg[1]);
    vNmb := upper(reg[2]);
    vApP := upper(reg[3]);
    vApM := upper(reg[4]);
    vNoC := reg[5];
    vId  := reg[6]::integer;

    -- consulta
    UPDATE
        personas
    SET
        rfc = vRfc,
        nombre = vNmb,
        apellido_p = vApP,
        apellido_m = vApM,
        no_cuenta = vNoC
    WHERE id_persona = vId;
    -- fin consulta

    IF vId > 0 THEN
        RAISE NOTICE 'propietario % de id %, actualizado correctamente', vNmb, vId;
        RETURN vId;
    END IF;

    RAISE NOTICE 'ERROR: propietario no actualizado';
    RETURN 0;
END;
$$ LANGUAGE plpgsql;

DROP FUNCTION IF EXISTS agrMascota;
CREATE OR REPLACE FUNCTION agrMascota (reg anyarray) RETURNS integer AS $$
DECLARE
    vNmb varchar := null;
    vFch date    := null;
    vSxo varchar := null;
    vIdP integer := null;
    vIdR integer := null;
    vId  integer := null;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 5 THEN
        RETURN 0;
    END IF;

    vNmb := upper(reg[1]);
    vFch := reg[2]::date;
    vSxo := lower(reg[3]);
    vIdP := reg[4]::integer;
    vIdR := reg[5]::integer;

    -- consulta
    INSERT INTO
        mascotas
    VALUES (
           DEFAULT,
           vNmb,
           vFch,
           vSxo,
           vIdP,
           vIdR,
           DEFAULT)
    RETURNING id_mascota INTO vId;
    -- fin consulta

    IF vId > 0 THEN
        RAISE NOTICE 'mascota % de id %, insertado correctamente', vNmb, vId;
        RETURN vId;
    END IF;

    RAISE NOTICE 'ERROR: mascota no insertada a la base de datos';
    RETURN 0;
END;
$$ LANGUAGE plpgsql;

DROP FUNCTION IF EXISTS actMascota;
CREATE OR REPLACE FUNCTION actMascota (reg anyarray) RETURNS integer AS $$
DECLARE
    vNmb varchar := null;
    vFch date    := null;
    vSxo varchar := null;
    vIdP integer := null;
    vIdR integer := null;
    vId  integer := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 6 THEN
        RETURN 0;
    END IF;

    vNmb := upper(reg[1]);
    vFch := reg[2]::date;
    vSxo := lower(reg[3]);
    vIdP := reg[4]::integer;
    vIdR := reg[5]::integer;
    vId  := reg[7]::integer;

    -- consulta
    UPDATE
        mascotas
    SET
        nombre = vNmb,
        fecha_nacimiento = vFch,
        sexo = vSxo,
        id_propietario = vIdP,
        id_raza = vIdR
    WHERE
            id_mascota = vId;
    -- fin consulta

    IF vId > 0 THEN
        RAISE NOTICE 'mascota % de id %, actualizado correctamente', vNmb, vId;
        RETURN vId;
    END IF;

    RAISE NOTICE 'ERROR: mascota no actualizado';
    RETURN 0;
END;
$$ LANGUAGE plpgsql;

-- agrega un articuloVenta de proveedor a la base de datos, especificando el tipo
-- del articuloVenta en sus parámetros

-- 1 = alimentos
-- 2 = productos
-- 3 = medicamentos
DROP FUNCTION IF EXISTS agrArticulo_proveedor;
CREATE OR REPLACE FUNCTION agrArticulo_proveedor (reg anyarray, tipo integer) RETURNS integer LANGUAGE plpgsql AS $$
DECLARE
    vNmb varchar        := null;
    vMnt decimal(10, 2) := null;
    vIdP integer        := null;
    vId  integer        := 0;
    -- alimentos
    vGrm decimal(10, 2) := null;
    -- productos
    vTpo varchar        := null;
    -- medicamentos *incluye vGrm*
    vLbt varchar        := null;
    vVia varchar        := null;

    vTbl varchar[]      := '{"alimento", "producto", "medicamento"}';
BEGIN
    IF array_length(reg, 1) < 4 AND (tipo < 1 OR tipo > 3) THEN
        RETURN 0;
    END IF;

    vNmb := upper(reg[1]);
    vMnt := reg[2]::decimal;
    vIdP := reg[3]::integer;

    INSERT INTO
        articulos_proveedor
    VALUES
        (
            DEFAULT,
            vNmb,
            vMnt,
            vIdP,
            DEFAULT)
    RETURNING id_articulo_proveedor INTO vId;
    IF vId = 0 THEN
        RAISE EXCEPTION 'articuloVenta de proveedor no añadido, función cancelada';
    END IF;

    CASE tipo
        WHEN 1 THEN
            vGrm := reg[4]::decimal;

            INSERT INTO alimentos
            VALUES (vId, vGrm)
            RETURNING id_articulo_alimento INTO vId;
        WHEN 2 THEN
            vTpo := lower(reg[5]::varchar);

            INSERT INTO productos
            VALUES (vId, vTpo)
            RETURNING id_articulo_producto INTO vId;
        WHEN 3 THEN
            vGrm := reg[4]::decimal;
            vLbt := upper(reg[5]::varchar);
            vVia := lower(reg[6]::varchar);

            INSERT INTO medicamentos
            VALUES (vId, vGrm, vLbt, vVia)
            RETURNING id_articulo_medicamento INTO vId;
        ELSE
            RAISE EXCEPTION 'ERROR: tipo de articulo invalido';
        END CASE;

    IF vId = 0 THEN
        RAISE EXCEPTION '% no insertado a la base de datos', vTbl[tipo];
    END IF;

    RAISE NOTICE '% de id % insertado correctamente', vTbl[tipo], vId;

    RETURN vId;
END;
$$;

-- 1 = alimentos
-- 2 = productos
-- 3 = medicamentos
DROP FUNCTION IF EXISTS actArticulo_proveedor;
CREATE OR REPLACE FUNCTION actArticulo_proveedor (reg anyarray, tipo integer) RETURNS integer LANGUAGE plpgsql AS $$
DECLARE
    vNmb varchar        := null;
    vMnt decimal(10, 2) := null;
    vIdP integer        := null;
    vId  integer        := 0;
    -- alimentos
    vGrm decimal(10, 2) := null;
    -- productos
    vTpo varchar        := null;
    -- medicamentos *incluye vGrm*
    vLbt varchar        := null;
    vVia varchar        := null;

    vTbl varchar[]      := '{"alimento", "producto", "medicamento"}';
BEGIN
    IF array_length(reg, 1) < 5 AND (tipo < 1 OR tipo > 3) THEN
        RETURN 0;
    END IF;

    vNmb := upper(reg[1]);
    vMnt := reg[2]::decimal;
    vIdP := reg[3]::integer;
    vId  := reg[4]::integer;

    PERFORM *
    FROM articulos_proveedor
    WHERE id_articulo_proveedor = vId FOR UPDATE;

    UPDATE articulos_proveedor
    SET nombre = vnmb,
        monto = vmnt,
        id_proveedor = vidp
    WHERE id_articulo_proveedor = vid;
    IF NOT found THEN
        ROLLBACK;
        RAISE EXCEPTION 'ERROR: articulo_proveedor no actualizado';
    END IF;

    CASE tipo
        WHEN 1 THEN
            vGrm := reg[5]::decimal;

            UPDATE alimentos
            SET gramaje = vGrm
            WHERE id_articulo_alimento = vId;
        WHEN 2 THEN
            vTpo := reg[5]::varchar;

            UPDATE productos
            SET tipo = vtpo
            WHERE id_articulo_producto = vid;
        WHEN 3 THEN
            vGrm := reg[4]::decimal;
            vLbt := reg[5]::varchar;
            vVia := reg[6]::varchar;

            UPDATE medicamentos
            SET gramaje = vgrm,
                laboratorio = vlbt,
                via = vvia
            WHERE id_articulo_medicamento = vid;
        END CASE;

    IF NOT found THEN
        ROLLBACK;
        RAISE EXCEPTION '% no actualizado a la base de datos', vTbl[tipo];
    END IF;

    RAISE NOTICE '% de id % insertado correctamente', vTbl[tipo], vId;
    COMMIT;

    RETURN vId;
END;
$$;

DROP FUNCTION IF EXISTS agrArticulo_venta;
CREATE OR REPLACE FUNCTION agrArticulo_venta (reg anyarray) RETURNS integer AS $BODY$
DECLARE
    vMonto decimal(10, 2) := 0;
    vDesc varchar := NULL;
    vStock int := 0;
    vId_articulo int := 0;
    vTipo varchar := NULL;
    vId int := 0;
BEGIN
    IF array_length(reg, 1) < 5 THEN
        RETURN 0;
    END IF;

    vMonto := reg[1]::decimal;
    vDesc := reg[2];
    vstock := reg[3]::int;
    vid_articulo := reg[4]::int;
    vtipo := lower(reg[5]);

    INSERT INTO articulos_venta
    VALUES (DEFAULT, vmonto, vdesc, vstock, vid_articulo, vtipo)
    RETURNING id_articulo_venta INTO vid;

    IF vid = 0 THEN
        RAISE EXCEPTION 'articulo de venta no insertado correctamente';
    END IF;

    RAISE NOTICE 'articulo % insertado correctamente', vid;

    RETURN vid;
END;
$BODY$ LANGUAGE plpgsql;

DROP FUNCTION IF EXISTS actArticulo_venta;
CREATE OR REPLACE FUNCTION actArticulo_venta (reg anyarray) RETURNS integer AS $BODY$
DECLARE
    vMonto decimal(10, 2) := 0;
    vDesc varchar := NULL;
    vStock int := 0;
    vId_articulo int := 0;
    vTipo varchar := NULL;
    vId int := 0;
BEGIN
    IF array_length(reg, 1) < 6 THEN
        RETURN 0;
    END IF;

    vMonto := reg[1]::decimal;
    vDesc := reg[2];
    vstock := reg[3]::int;
    vid_articulo := reg[4]::int;
    vtipo := lower(reg[5]);
    vId := reg[6]::int;

    UPDATE articulos_venta
    SET monto = vmonto,
        descripcion = vdesc,
        stock = vstock,
        id_articulo = vid_articulo,
        tipo = vtipo
    WHERE id_articulo_venta = id_articulo;

    IF NOT FOUND THEN
        RAISE EXCEPTION 'articulo de venta no actualizado';
    END IF;

    RAISE NOTICE 'articulo % actualizado correctamente', vid;

    RETURN vid;
END;
$BODY$ LANGUAGE plpgsql;

DROP FUNCTION IF EXISTS agrEmpleado;
CREATE OR REPLACE FUNCTION agrEmpleado (reg anyarray) RETURNS integer AS $$
DECLARE
    vRfc varchar        := null;
    vNmb varchar        := null;
    vApP varchar        := null;
    vApM varchar        := null;
    vNoC varchar        := null;
    vFcI date           := null;
    vJrI TIME           := null;
    vJrF TIME           := null;
    vSal decimal(10, 2) := null;
    vPst varchar        := null;
    vId  integer        := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 10 THEN
        RETURN 0;
    END IF;

    vRfc := upper(reg[1]);
    vNmb := upper(reg[2]);
    vApP := upper(reg[3]);
    vApM := upper(reg[4]);
    vNoC := reg[5];
    vFcI := reg[6]::date;
    vJrI := reg[7]::TIME;
    vJrF := reg[8]::TIME;
    vSal := reg[9]::decimal;
    vPst := reg[10];

    -- consulta
    -- añadimos primero a personas
    INSERT INTO
        personas
    VALUES
        (
            DEFAULT,
            vRfc,
            vNmb,
            vApP,
            vApM,
            vNoC,
            DEFAULT
        ) RETURNING id_persona INTO vId;

    IF vid == NULL THEN
        RAISE EXCEPTION 'persona no creada, función cancelada';
    END IF;

    INSERT INTO
        empleados
    VALUES (
           vId,
           vFcI,
           vJrI,
           vJrF,
           vSal,
           vPst)
    RETURNING id_empleado INTO vId;
    -- fin consulta

    IF vId > 0 THEN
        RAISE NOTICE 'empleado % de id %, insertado correctamente', vNmb, vId;
        RETURN vId;
    END IF;

    RAISE NOTICE 'ERROR: empleado no insertado a la base de datos';
    RETURN 0;
END;
$$ LANGUAGE plpgsql;

DROP FUNCTION IF EXISTS actEmpleado;
CREATE OR REPLACE FUNCTION actEmpleado (reg anyarray) RETURNS integer AS $$
DECLARE
    vRfc varchar        := null;
    vNmb varchar        := null;
    vApP varchar        := null;
    vApM varchar        := null;
    vNoC varchar        := null;
    vFcI date           := null;
    vJrI TIME           := null;
    vJrF TIME           := null;
    vSal decimal(10, 2) := null;
    vPst varchar        := null;
    vId  integer := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 11 THEN
        RETURN 0;
    END IF;

    vRfc := upper(reg[1]);
    vNmb := upper(reg[2]);
    vApP := upper(reg[3]);
    vApM := upper(reg[4]);
    vNoC := reg[5];
    vFcI := reg[6]::date;
    vJrI := reg[7]::TIME;
    vJrF := reg[8]::TIME;
    vSal := reg[9]::decimal;
    vPst := reg[10];
    vId  := reg[11]::integer;

    -- consulta
    UPDATE
        personas
    SET
        rfc = vRfc,
        nombre = vNmb,
        apellido_p = vApP,
        apellido_m = vApM,
        no_cuenta = vNoC
    WHERE id_persona = vId;

    IF NOT found THEN
        RAISE EXCEPTION 'empleado no actualizado';
    END IF;

    UPDATE
        empleados
    SET
        fecha_ini  = vFcI,
        jor_ini    = vJrI,
        jor_fin    = vJrF,
        salario    = vSal,
        puesto     = vPst
    WHERE id_empleado = vId;
    -- fin consulta

    IF vId > 0 THEN
        RAISE NOTICE 'empleado % de id %, actualizado correctamente', vNmb, vId;
        RETURN vId;
    END IF;

    RAISE NOTICE 'ERROR: empleado no actualizado';
    RETURN 0;
END;
$$ LANGUAGE plpgsql;

DROP FUNCTION IF EXISTS agrNomina;
CREATE OR REPLACE FUNCTION agrNomina (reg anyarray) RETURNS integer AS $$
DECLARE
    vFcI date           := null;
    vFcF date           := null;
    vTlH integer        := null;
    vTlB decimal(10, 2) := null;
    vIdE integer        := 0;
    vCns integer        := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 5 THEN
        RETURN 0;
    END IF;

    vFcI := reg[1]::date;
    vFcF := reg[2]::date;
    vTlH := reg[3]::integer;
    vTlB := reg[4]::decimal;
    vIdE := reg[5]::integer;

    -- consulta
    INSERT INTO
        nominas
    VALUES (
           (SELECT
                (count(*) + 1)
            FROM
                nominas
            WHERE
                    id_empleado = vIdE),
           vFcI,
           vFcF,
           vTlH,
           vTlB,
           vIdE,
           DEFAULT)
    RETURNING cns_nomina INTO vCns;
    -- fin consulta

    IF vCns > 0 THEN
        RAISE NOTICE 'Nomina % de empleado %, insertado correctamente', vCns, vIdE;
        RETURN vCns;
    END IF;

    RAISE NOTICE 'ERROR: Nomina de empleado % no insertado a la base de datos', vIdE;
    RETURN 0;
END;
$$ LANGUAGE plpgsql;

DROP FUNCTION IF EXISTS actNomina;
CREATE OR REPLACE FUNCTION actNomina (reg anyarray) RETURNS integer AS $$
DECLARE
    vFcI date           := null;
    vFcF date           := null;
    vTlH integer        := null;
    vTlB decimal(10, 2) := null;
    vIdE integer        := 0;
    vCns integer        := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 6 THEN
        RETURN 0;
    END IF;

    vFcI := reg[1]::date;
    vFcF := reg[2]::date;
    vTlH := reg[3]::integer;
    vTlB := reg[4]::decimal;
    vIdE := reg[5]::integer;
    vCns := reg[6]::integer;

    -- consulta
    UPDATE
        nominas
    SET
        fecha_inicio = vFcI,
        fecha_fin    = vFcF,
        total_horas  = vTlH,
        total_bono   = vTlB
    WHERE id_empleado = vIdE AND cns_nomina = vCns;
    -- fin consulta

    IF vCns > 0 THEN
        RAISE NOTICE 'Nomina % de empleado %, actualizado correctamente', vCns, vIdE;
        RETURN vCns;
    END IF;

    RAISE NOTICE 'ERROR: Nomina de empleado % no actualizado', vIdE;
    RETURN 0;
END;
$$ LANGUAGE plpgsql;

DROP FUNCTION IF EXISTS agrProveedor;
CREATE OR REPLACE FUNCTION agrProveedor (reg anyarray) RETURNS integer AS $$
DECLARE
    vNmb varchar := null;
    vDrc varchar := null;
    vTlf varchar := null;
    vDsc varchar := null;
    vId integer  := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 4 THEN
        RETURN -1;
    END IF;

    vNmb := upper(reg[1]);
    vDrc := upper(reg[2]);
    vTlf := upper(reg[3]);
    vDsc := upper(reg[4]);

    -- consulta
    INSERT INTO
        proveedores
    VALUES (
           DEFAULT,
           vNmb,
           vDrc,
           vTlf,
           vDsc,
           DEFAULT)
    RETURNING id_proveedor INTO vId;
    -- fin consulta

    IF vId > 0 THEN
        RAISE NOTICE 'Proveedor % de id %, insertado correctamente', vNmb, vId;
        RETURN vId;
    END IF;

    RAISE NOTICE 'ERROR: proveedor % no insertado a la base de datos', vNmb;
    RETURN -1;
END;
$$ LANGUAGE plpgsql;

DROP FUNCTION IF EXISTS actProveedor;
CREATE OR REPLACE FUNCTION actProveedor (reg anyarray) RETURNS integer AS $$
DECLARE
    vNmb varchar := null;
    vDrc varchar := null;
    vTlf varchar := null;
    vDsc varchar := null;
    vId integer  := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 5 THEN
        RETURN 0;
    END IF;

    vNmb := upper(reg[1]);
    vDrc := upper(reg[2]);
    vTlf := upper(reg[3]);
    vDsc := upper(reg[4]);
    vId  := reg[5]::integer;

    -- consulta
    UPDATE
        proveedores
    SET
        nombre      = vNmb,
        direccion   = vDrc,
        telefono    = vTlf,
        descripcion = vDsc
    WHERE id_proveedor = vId;
    -- fin consulta

    IF vId > 0 THEN
        RAISE NOTICE 'Proveedor % de id %, actualizado correctamente', vNmb, vId;
        RETURN vId;
    END IF;

    RAISE NOTICE 'ERROR: proveedor % no actualizado', vNmb;
    RETURN 0;
END;
$$ LANGUAGE plpgsql;

DROP FUNCTION IF EXISTS agrFactura_proveedor;
CREATE OR REPLACE FUNCTION agrFactura_proveedor (reg anyarray) RETURNS integer AS $$
DECLARE
    vFch date           := null;
    vMnt decimal(10, 2) := 0;
    vIdP integer        := null;
    vId  integer        := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 2 THEN
        RETURN 0;
    END IF;

    vFch := reg[1]::date;
    --vMnt := reg[2]::decimal;
    vIdP := reg[3]::integer;

    -- consulta
    INSERT INTO
        facturas_proveedor
    VALUES (
           DEFAULT,
           vFch,
           vMnt,
           vIdP,
           DEFAULT)
    RETURNING id_factura INTO vId;
    -- fin consulta

    IF vId > 0 THEN
        RAISE NOTICE 'Factura % de id %, insertado correctamente', vFch, vId;
        RETURN vId;
    END IF;

    RAISE NOTICE 'ERROR: factura % no insertado a la base de datos', vFch;
    RETURN 0;
END;
$$ LANGUAGE plpgsql;

DROP FUNCTION IF EXISTS actFactura_proveedor;
CREATE OR REPLACE FUNCTION actFactura_proveedor (reg anyarray) RETURNS integer AS $$
DECLARE
    vFch varchar        := null;
    --vMnt decimal(10, 2) := null;
    vIdP integer        := null;
    vId  integer        := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 3 THEN
        RETURN 0;
    END IF;

    vFch := reg[1]::date;
    --vMnt := reg[2]::decimal;
    vIdP := reg[3]::integer;
    vId  := reg[4]::integer;

    -- consulta
    UPDATE
        facturas_proveedor
    SET
        fecha_factura = vFch,
        id_proveedor  = vIdP
    WHERE id_factura = vId;
    -- fin consulta

    IF vId > 0 THEN
        RAISE NOTICE 'Factura % de id %, actualizado correctamente', vFch, vId;
        RETURN vId;
    END IF;

    RAISE EXCEPTION 'ERROR: factura % no actualizado', vFch;
END;
$$ LANGUAGE plpgsql;
*/

/*
DROP FUNCTION IF EXISTS agrForma_pago;
CREATE OR REPLACE FUNCTION agrForma_pago (reg anyarray) RETURNS integer AS $$
DECLARE
    vNmb varchar        := null;
    vCms decimal(10, 2) := 0;
    vId  integer        := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 2 THEN
        RETURN 0;
    END IF;

    vNmb := upper(reg[1]::varchar);
    vCms := reg[2]::decimal;

    -- consulta
    INSERT INTO
        formas_pago
    VALUES (
            DEFAULT,
            vNmb,
            vCms,
            DEFAULT)
    RETURNING id_forma_pago INTO vId;
    -- fin consulta

    IF vId > 0 THEN
        RAISE NOTICE 'Pago con % de id % , insertado correctamente', vNmb, vId;
        RETURN vId;
    END IF;

    RAISE NOTICE 'ERROR: Pago con %, no insertado', vNmb;
    RETURN 0;
END;
$$ LANGUAGE plpgsql;

DROP FUNCTION IF EXISTS actForma_pago;
CREATE OR REPLACE FUNCTION actForma_pago (reg anyarray) RETURNS integer AS $$
DECLARE
    vNmb varchar := null;
    vCms decimal(10, 2) := 0;
    vId  integer := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 3 THEN
        RETURN 0;
    END IF;

    vNmb := upper(reg[1]::varchar);
    vCms := reg[2]::decimal;
    vId  := reg[3]::integer;

    -- consulta
    UPDATE
        formas_pago
    SET
        nombre = vNmb,
        comision = vcms
    WHERE id_forma_pago = vId;
    -- fin consulta

    IF vId > 0 THEN
        RAISE NOTICE 'Pago con % de id % , actualizado correctamente', vNmb, vId;
        RETURN vId;
    END IF;

    RAISE NOTICE 'ERROR: Pago con %, no actualizado', vNmb;
    RETURN 0;
END;
$$ LANGUAGE plpgsql;

DROP FUNCTION IF EXISTS agrTicket;
CREATE OR REPLACE FUNCTION agrTicket (reg anyarray) RETURNS integer AS $$
DECLARE
    vMnT decimal(10, 2) := null;
    vFcC date           := null;
    vHrC TIME           := null;
    vId  integer        := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 3 THEN
        RETURN 0;
    END IF;

    vMnT := reg[1]::decimal;
    vFcC := reg[2]::date;
    vHrC := reg[3]::TIME;

    -- consulta
    INSERT INTO
        tickets
    VALUES (
           DEFAULT,
           vMnT,
           vFcC,
           vHrC,
           DEFAULT)
    RETURNING id_ticket INTO vId;
    -- fin consulta

    IF vId > 0 THEN
        RAISE NOTICE 'Ticket de %:% de id % , insertado correctamente', vFcC, vHrC, vId;
        RETURN vId;
    END IF;

    RAISE NOTICE 'ERROR: Ticket de %:%, no insertado', vFcC, vHrC;
    RETURN 0;
END;
$$ LANGUAGE plpgsql;
*/
-- este procedimiento debe ser una transacción

/*
DROP FUNCTION IF EXISTS agrDetalle_mascota;
CREATE OR REPLACE FUNCTION agrDetalle_mascota (reg anyarray) RETURNS integer AS $$
DECLARE
    vIdM  integer := 0;
    vIdA  integer := 0;
    vCnt  integer := 0;
    vFch  date    := null;
    vCns  integer := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 4 THEN
        RETURN 0;
    END IF;

    vFch  := reg[1]::date;
    vCnt  := reg[2]::integer;
    vIdA  := reg[3]::integer;
    vIdM  := reg[4]::integer;

    -- consulta
    INSERT INTO detalle_mascota
    VALUES
        (
            vIdM,
            (
                SELECT
                        count(*) + 1
                FROM
                    detalle_mascota
                WHERE id_mascota = vIdM),
            vFch,
            vCnt,
            vIdA)
    RETURNING cns_detalle_mascota INTO vCns;
    -- fin consulta

    IF vCns > 0 THEN
        RAISE NOTICE 'detalle % de mascota % de id % , insertado correctamente', vIdA, vIdM, vCns;
        RETURN vCns;
    END IF;

    RAISE NOTICE 'ERROR: detalle % de mascota %, no insertado', vIdA, vIdM;
    RETURN 0;

    EXCEPTION
        WHEN INVALID_TEXT_REPRESENTATION THEN
            RAISE NOTICE 'ERROR: parámetro invalido para la función';
            RETURN 0;
        WHEN FOREIGN_KEY_VIOLATION THEN
            RAISE NOTICE 'ERROR: id para mascota o articuloVenta invalido';
            RETURN 0;
    END;
$$ LANGUAGE plpgsql;

DROP FUNCTION IF EXISTS actDetalle_mascota;
CREATE OR REPLACE FUNCTION actDetalle_mascota (reg anyarray) RETURNS integer AS $$
DECLARE
    vFcV  date    := null;
    vIdM  integer := 0;
    vIdA integer := 0;
    vCns  integer := 0;
    vCnt integer := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 5 THEN
        RETURN 0;
    END IF;

    vFcV  := reg[1]::date;
    vCnt  := reg[2]::integer;
    vIdA  := reg[3]::integer;
    vIdM  := reg[4]::integer;
    vCns  := reg[5]::integer;

    -- consulta
    UPDATE
        detalle_mascota
    SET
        fecha   = vFcV,
        cantidad = vCnt,
        id_articulo = vIdA
    WHERE id_mascota = vIdM AND cns_detalle_mascota = vCns;
    -- fin consulta

    IF vCns > 0 THEN
        RAISE NOTICE 'detalle % de mascota % de id % , actualizado correctamente', vIdA, vIdM, vCns;
        RETURN vCns;
    END IF;

    RAISE NOTICE 'ERROR: detalle % de mascota %, no actualizado', vIdA, vIdM;
    RETURN 0;
END;
$$ LANGUAGE plpgsql;

DROP FUNCTION IF EXISTS agrCita;
CREATE OR REPLACE FUNCTION agrCita (reg anyarray) RETURNS integer AS $$
DECLARE
    vFch  date    := null;
    vHra  TIME    := null;
    vDtl  varchar := null;
    vIdM  integer := 0;
    vIdV  integer := 0;
    vIdT  integer := 0;
    vEst  varchar := 0;
    vId   integer := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 7 THEN
        RETURN 0;
    END IF;

    vFch  := reg[1]::date;
    vHra  := reg[2]::TIME;
    vDtl  := upper(reg[3]);
    vIdM  := reg[4]::integer;
    vIdV  := reg[5]::integer;
    vIdT  := reg[6]::integer;
    vEst  := lower(reg[7]);

    -- consulta
    INSERT INTO
        citas
    VALUES (
           DEFAULT,
           vFch,
           vHra,
           vDtl,
           vIdM,
           vIdV,
           vIdT,
           vEst,
           DEFAULT)
    RETURNING id_cita INTO vId;
    -- fin consulta

    IF vId > 0 THEN
        RAISE NOTICE 'Cita % de mascota %, insertado correctamente', vId, vIdM;
        RETURN vId;
    END IF;

    RAISE NOTICE 'ERROR: Cita de mascota %, no insertado', vIdM;
    RETURN 0;
END;
$$ LANGUAGE plpgsql;

DROP FUNCTION IF EXISTS actCita;
CREATE OR REPLACE FUNCTION actCita (reg anyarray) RETURNS integer AS $$
DECLARE
    vFch  date    := null;
    vHra  TIME    := null;
    vDtl  varchar := null;
    vIdM  integer := 0;
    vIdV  integer := 0;
    vIdT  integer := 0;
    vEst  varchar := 0;
    vId   integer := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 8 THEN
        RETURN 0;
    END IF;

    vFch  := reg[1]::date;
    vHra  := reg[2]::TIME;
    vDtl  := upper(reg[3]);
    vIdM  := reg[4]::integer;
    vIdV  := reg[5]::integer;
    vIdT  := reg[6]::integer;
    vEst  := lower(reg[7]);
    vId   := reg[8];

    -- consulta
    UPDATE
        citas
    SET
        fecha_cita  = vFch,
        hora        = vHra,
        detalle     = vDtl,
        id_mascota  = vIdM,
        id_veterinario = vIdV,
        id_ticket   = vIdT,
        estatus     = vEst
    WHERE id_cita = vId;
    -- fin consulta

    IF vId > 0 THEN
        RAISE NOTICE 'Cita % de mascota %, insertado correctamente', vId, vIdM;
        RETURN vId;
    END IF;

    RAISE NOTICE 'ERROR: Cita de mascota %, no insertado', vIdM;
    RETURN 0;
END;
$$ LANGUAGE plpgsql;
*/

DROP PROCEDURE IF EXISTS agrDetalle_factura;
CREATE OR REPLACE PROCEDURE agrDetalle_factura (reg varchar[], OUT pCns integer) LANGUAGE plpgsql AS $$
DECLARE
    vCantidad integer           := 0;
    vMonto decimal(10, 2)       := 0;
    vId_articulo integer        := 0;
    -- Auxiliares
    vId_factura integer         := 0;
    vId_articulo_venta integer  := 0;
    vTipo varchar               := NULL;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 3 THEN
        RETURN;
    END IF;

    pCns := 0;
    vCantidad := reg[1]::integer;
    vId_articulo := reg[2]::integer;
    vId_factura := reg[3]::integer;

    -- obtenemos el precio del producto
    SELECT INTO vMonto monto_compra
    FROM articulos
    WHERE id_articulo = vId_articulo;
    IF NOT FOUND THEN
        RAISE EXCEPTION 'ERROR: articuloVenta de id % no valido', vId_articulo;
    END IF;

    -- seleccionamos la factura a actualizar y la bloqueamos
    PERFORM *
    FROM facturas_proveedor
    WHERE id_factura = vId_factura FOR UPDATE; -- transacción
    -- si la factura no existe
    IF NOT FOUND THEN
        ROLLBACK;
        RAISE EXCEPTION 'ERROR: factura % no encontrada, operación invalida', vId_factura;
    END IF;

    -- consulta
    INSERT INTO detalle_factura
    VALUES (
           vId_factura,
           (SELECT count(*) + 1
            FROM detalle_factura
            WHERE id_factura = vId_factura),
           vCantidad,
           vMonto * vCantidad,
           vId_articulo)
    RETURNING cns_detalle_factura INTO pCns;
    -- fin consulta

    -- añadimos el precio del articuloVenta a la factura
    UPDATE facturas_proveedor
    SET monto_total = monto_total + (vCantidad * vMonto)
    WHERE id_factura = vId_factura;
    -- comprobamos que se ha añadido el subtotal a la factura
    IF NOT FOUND THEN
        ROLLBACK;
        RAISE EXCEPTION 'ERROR: subtotal de articuloVenta no añadido a la factura, inserción no realizada';
    END IF;

    -- obtenemos el tipo del producto
    CASE
        WHEN exists(SELECT *
                    FROM alimentos
                    WHERE alimentos.id_articulo = vid_articulo) THEN
            vTipo := 'alimento';
        WHEN exists(SELECT *
                    FROM medicamentos
                    WHERE medicamentos.id_articulo = vid_articulo) THEN
            vTipo := 'medicamento';
        WHEN exists(SELECT *
                    FROM productos
                    WHERE productos.id_articulo = vid_articulo) THEN
            vTipo := 'producto';
        END CASE;
    -- añadimos los articuloVendidos a la venta
    IF NOT exists( SELECT *
                   FROM articulos_venta
                   WHERE id_articulo = vId_articulo ) THEN
        -- insertamos el nuevo articuloVenta a la venta
        INSERT INTO articulos_venta
        VALUES (
               vid_articulo,
               vMonto,
               vCantidad,
               vTipo)
        RETURNING id_articulo INTO vId_articulo_venta;
        IF vId_articulo_venta = 0 THEN
            ROLLBACK;
            RAISE EXCEPTION 'articuloVenta no añadido a la venta, operación invalida';
        END IF;

        RAISE NOTICE 'articuloVenta % de id % de factura id %, insertado correctamente en factura y a la venta %', pCns, vId_articulo, vId_factura, vId_articulo_venta;
    ELSE
        -- actualizamos el stock
        UPDATE articulos_venta
        SET stock = stock + vCantidad
        WHERE id_articulo = vId_articulo;
        IF NOT FOUND THEN
            ROLLBACK;
            RAISE EXCEPTION 'ERROR: inventario no actualizado, operación invalida';
        END IF;

        RAISE NOTICE 'articuloVenta % de id % de factura id %, insertado correctamente en factura y actualizado a la venta', pCns, vId_articulo, vId_factura;
    END IF;

    COMMIT;
END;
$$;

DROP PROCEDURE IF EXISTS actDetalle_factura;
CREATE OR REPLACE PROCEDURE actDetalle_factura (IN reg anyarray, OUT pRes bool) AS $$
DECLARE
    -- Parámetros
    vCantidad  integer              := 0;
    vId_articulo  integer           := 0;
    vId_factura  integer            := 0;
    vCns  integer                   := 0;
    -- Auxiliares
    vMonto  decimal(10, 2)          := 0;
    vCantidadAnt integer            := 0;
    vSubtotalAnt decimal(10, 2)     := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 4 THEN
        RETURN;
    END IF;

    pRes := FALSE;
    vCantidad := reg[1]::integer;
    vId_articulo := reg[2]::integer;
    vId_factura := reg[3]::integer;
    vCns := reg[4]::integer;

    -- obtenemos los productos que se habían ingresado antes
    SELECT INTO
        vSubtotalAnt, vCantidadAnt
        subtotal, cantidad
    FROM detalle_factura
    WHERE id_factura = vId_factura
      AND cns_detalle_factura = vCns;

    RAISE NOTICE 'Subtotal A %, cantidad A %', vSubtotalAnt, vCantidadAnt;

    vMonto = vSubtotalAnt / vCantidadAnt;

    IF NOT FOUND THEN
        RAISE EXCEPTION 'ERROR: articuloVenta % no encontrado en factura %', vId_articulo, vId_factura;
    END IF;

    PERFORM  *
    FROM facturas_proveedor
    WHERE id_factura = vId_factura FOR UPDATE;
    -- si la factura no existe
    IF NOT FOUND THEN
        ROLLBACK;
        RAISE EXCEPTION 'ERROR: factura no encontrada, operación invalida';
    END IF;

    -- consulta
    UPDATE detalle_factura
    SET cantidad = vCantidad,
        subtotal = vCantidad * vMonto
    WHERE id_factura = vId_factura
      AND cns_detalle_factura = vCns;
    -- fin consulta
    IF NOT FOUND THEN
        ROLLBACK;
        RAISE EXCEPTION 'ERROR: articuloVenta en factura % no actualizado, operación invalida', vId_factura;
    END IF;

    -- actualizamos la factura
    UPDATE facturas_proveedor
    SET monto_total = (monto_total - vSubtotalAnt) + (vCantidad * vMonto)
    WHERE id_factura = vId_factura;
    -- actualizamos el stock
    UPDATE articulos_venta SET stock = (stock - vCantidadAnt) + vCantidad WHERE id_articulo = vId_articulo;
    IF NOT FOUND THEN
        ROLLBACK;
        RAISE EXCEPTION 'ERROR: inventario no actualizado, operación invalida';
    END IF;

    RAISE NOTICE 'articuloVenta de id % de factura id %, actualizado correctamente', vId_articulo, vId_factura;
    pRes := TRUE;
    COMMIT;
END;
$$ LANGUAGE plpgsql;

DROP PROCEDURE IF EXISTS agrPago;
CREATE OR REPLACE PROCEDURE agrPago (IN reg anyarray, OUT pCns integer) AS $$
DECLARE
    vSubtotal decimal(10, 2) := 0;
    vId_forma_pago integer        := 0;
    vId_ticket integer        := 0;
    --Auxiliares
    vComision decimal(10, 2) := 0;
    vPago_total decimal(10, 2) := 0;
    vCns integer        := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 3 THEN
        RETURN;
    END IF;

    vSubtotal := reg[1]::decimal;
    vId_forma_pago := reg[2]::integer;
    vId_ticket := reg[3]::integer;

    SELECT INTO vPago_total sum(subtotal) FROM detalle_ticket WHERE id_ticket = vId_ticket;

    IF exists(SELECT * FROM tickets WHERE vPago_total >= tickets.monto_total) THEN
        RAISE EXCEPTION 'ticket % ya pagado, no se permiten más pagos', vId_ticket;
    END IF;

    -- realizamos la transacción
    PERFORM *
    FROM tickets
    WHERE id_ticket = vId_ticket FOR UPDATE;

    SELECT INTO vComision comision
    FROM formas_pago
    WHERE id_forma_pago = vId_forma_pago;

    -- consulta
    INSERT INTO pagos
    VALUES (
           (SELECT count(*) + 1
            FROM pagos
            WHERE id_ticket = vId_ticket),
           vSubtotal - vComision,
           vId_forma_pago,
           vId_ticket,
           DEFAULT)
    RETURNING cns_pago INTO pCns;
    -- fin consulta

    IF vCns < 1 THEN
        ROLLBACK;
        RAISE EXCEPTION 'ERROR: Ticket de %:%, no insertado', vId_ticket, vId_forma_pago;
    END IF;

    -- actualizamos el ticket
    UPDATE tickets
    SET monto_total = monto_total + vSubtotal
    WHERE id_ticket = vId_ticket;

    IF NOT FOUND THEN
        ROLLBACK;
        RAISE EXCEPTION 'ERROR: ticket no actualizado, no se realizo la inserción';
    END IF;

    RAISE NOTICE 'Pago de ticket %:% de id % , insertado correctamente', vId_ticket, vId_forma_pago, vCns;
    COMMIT;
END;
$$ LANGUAGE plpgsql;

-- esta función debe ser una transacción
DROP PROCEDURE IF EXISTS agrArticulo_ticket;
CREATE OR REPLACE PROCEDURE agrArticulo_ticket (IN reg anyarray, OUT pCns integer) AS $$
DECLARE
    vCantidad integer           := 0;
    vId_articulo integer        := 0;
    vId_ticket integer          := 0;
    vMonto decimal(10, 2)       := 0;
    vSubtotal decimal(10, 2)    := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 3 THEN
        RAISE NOTICE 'el arreglo debe de contener lo siguiente: cantidad, id_articulo, id_ticket';
    END IF;

    vId_ticket := reg[1]::integer;
    vId_articulo := reg[2]::integer;
    vCantidad := reg[3]::integer;

    -- transacción
    PERFORM *
    FROM tickets
    WHERE id_ticket = vId_ticket FOR UPDATE;

    -- obtenemos el precio del articuloVenta
    SELECT INTO vMonto monto
    FROM articulos_venta
    WHERE id_articulo = vId_articulo
      AND stock > 0; -- validamos que aún queden productos

    IF NOT FOUND THEN
        ROLLBACK;
        RAISE EXCEPTION 'ERROR: articuloVenta de id % no encontrado o sin existencias', vId_articulo;
    END IF;

    vSubtotal := vCantidad * vMonto;

    -- consulta
    INSERT INTO detalle_ticket
    VALUES(
          vId_ticket,
          (SELECT count(*) + 1
           FROM detalle_ticket
           WHERE id_ticket = vId_ticket),
          vCantidad,
          vSubtotal,
          vId_articulo)
    RETURNING cns_detalle_ticket INTO pCns;
    -- fin consulta

    -- añadimos el subtotal al ticket
    UPDATE tickets
    SET monto_total = monto_total + vSubtotal
    WHERE id_ticket = vId_ticket;

    IF vId_ticket < 1 THEN
        ROLLBACK;
        RAISE EXCEPTION 'ERROR: Ticket de %:%, no insertado', vId_articulo, vId_ticket;
    END IF;

    RAISE NOTICE 'av: %, can %, pre %, sub %', vId_articulo, vCantidad, vMonto, vSubtotal;

    -- restamos la venta al inventario
    UPDATE articulos_venta
    SET stock = stock - vCantidad
    WHERE id_articulo = vId_articulo
      AND (stock - vCantidad) >= 0;

    IF NOT FOUND THEN
        ROLLBACK;
        RAISE EXCEPTION 'inventario no actualizado, transacción invalida';
    END IF;

    RAISE NOTICE 'SUCCESSFULLY: articuloVenta % del ticket % , insertado correctamente', vId_articulo, vId_ticket;
    COMMIT;
END;
$$ LANGUAGE plpgsql;

-- obtiene todos los alimentos
CREATE OR REPLACE FUNCTION obtAlimentos_factura(
pId_factura integer,
pTipo integer)
    RETURNS refcursor AS $$
DECLARE
    vCursor refcursor;
BEGIN
    CASE pTipo
        WHEN 0 THEN
            OPEN vcursor FOR SELECT ap.id_articulo,
                                    ap.nombre,
                                    ap.monto_compra,
                                    ap.id_proveedor,
                                    df.cantidad
                             FROM detalle_factura df
                                      INNER JOIN articulos ap
                                                 ON ap.id_articulo = df.id_articulo
                                      INNER JOIN articulos a
                                                 ON ap.id_articulo = a.id_articulo
                             WHERE id_factura = pId_factura;
        WHEN 1 THEN
            OPEN vcursor FOR SELECT ap.id_articulo,
                                    ap.nombre,
                                    ap.monto_compra,
                                    ap.id_proveedor,
                                    df.cantidad
                             FROM detalle_factura df
                                      INNER JOIN articulos ap
                                                 ON ap.id_articulo = df.id_articulo
                                      INNER JOIN alimentos a
                                                 ON ap.id_articulo = a.id_articulo
                             WHERE id_factura = pId_factura;
        WHEN 2 THEN
            OPEN vcursor FOR SELECT ap.id_articulo,
                                    ap.nombre,
                                    ap.monto_compra,
                                    ap.id_proveedor,
                                    df.cantidad
                             FROM detalle_factura df
                                      INNER JOIN articulos ap
                                                 ON ap.id_articulo = df.id_articulo
                                      INNER JOIN productos p
                                                 ON ap.id_articulo = p.id_articulo
                             WHERE id_factura = pId_factura;
        WHEN 3 THEN
            OPEN vcursor FOR SELECT ap.id_articulo,
                                    ap.nombre,
                                    ap.monto_compra,
                                    ap.id_proveedor,
                                    df.cantidad
                             FROM detalle_factura df
                                      INNER JOIN articulos ap
                                                 ON ap.id_articulo = df.id_articulo
                                      INNER JOIN medicamentos m
                                                 ON ap.id_articulo = m.id_articulo
                             WHERE id_factura = pId_factura;
    END CASE;

    RETURN vcursor;
END;
$$ LANGUAGE plpgsql;

-- obtiene los articulos de una factura según la fecha de entrega y el proveedor que la trajo
CREATE OR REPLACE FUNCTION obtArticulos_factura(
pFecha date,
pId_proveedor integer)
    RETURNS refcursor LANGUAGE plpgsql AS $$
DECLARE
    refCrs refcursor;
BEGIN
    OPEN refcrs FOR SELECT  row_number() OVER (ORDER BY tipo) as cns,
                            ap.nombre,
                            coalesce(df.cantidad, 0) as cantidad,
                            ap.monto_compra,
                            coalesce(df.subtotal, 0) as subtotal,
                            ap.id_articulo,
                            ap.id_proveedor,
                            ap.descripcion,
                            CASE
                                WHEN a.id_articulo IS NOT NULL THEN
                                    'Alimento'
                                WHEN p.id_articulo IS NOT NULL THEN
                                    'Producto'
                                WHEN m.id_articulo IS NOT NULL THEN
                                    'Medicamento'
                                END AS tipo
                    FROM articulos ap
                             LEFT JOIN (SELECT *
                                        FROM detalle_factura df
                                                 LEFT JOIN facturas_proveedor fp
                                                           ON df.id_factura = fp.id_factura
                                        WHERE fp.fecha_factura = pfecha
                                          AND fp.id_proveedor = pid_proveedor) df
                                       ON ap.id_articulo = df.id_articulo
                             LEFT JOIN alimentos a
                                       ON ap.id_articulo = a.id_articulo
                             LEFT JOIN productos p
                                       ON ap.id_articulo = p.id_articulo
                             LEFT JOIN medicamentos m
                                       ON ap.id_articulo = m.id_articulo
                    WHERE ap.id_proveedor = pId_proveedor
                    ORDER BY tipo;

    RAISE NOTICE 'cursor abierto correctamente';
    RETURN refCrs;
END;
$$;