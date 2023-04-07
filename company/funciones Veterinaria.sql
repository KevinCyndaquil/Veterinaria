/*
 .______________________________________________________________________________.
 |                                                                              |
 |                      PROCEDIMIENTOS ALMACENADOS                              |
 |______________________________________________________________________________|

 */

DROP FUNCTION IF EXISTS agrAnimal;
CREATE OR REPLACE FUNCTION agrAnimal (reg VARCHAR[]) RETURNS INTEGER AS $$
DECLARE
    vNmb VARCHAR := null;
    vId  INTEGER := 0;
BEGIN
    vNmb := upper(reg[1]);

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
CREATE OR REPLACE FUNCTION actAnimal (reg ANYARRAY) RETURNS INTEGER AS $$
DECLARE
    vNmb VARCHAR        := null;
    vId  INTEGER        := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 2 THEN
        RETURN 0;
    END IF;

    vNmb := upper(reg[1]);
    vId  := reg[2]::INTEGER;

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
CREATE OR REPLACE FUNCTION agrRaza (reg ANYARRAY) RETURNS INTEGER AS $$
DECLARE
    vNmb VARCHAR := null;
    vTtl INTEGER := null;
    vIdA INTEGER := 0;
    vId  INTEGER := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 3 THEN
        RETURN 0;
    END IF;

    vNmb := upper(reg[1]);
    vTtl := reg[2]::INTEGER;
    vIdA := reg[3]::INTEGER;

    -- consulta
    INSERT INTO razas
    VALUES (DEFAULT, vNmb, vTtl, vIdA, DEFAULT)
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
CREATE OR REPLACE FUNCTION actRaza (reg ANYARRAY) RETURNS INTEGER AS $$
DECLARE
    vNmb VARCHAR := null;
    vTtl INTEGER := null;
    vIdA INTEGER := 0;
    vId  INTEGER := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 4 THEN
        RETURN 0;
    END IF;

    vNmb := upper(reg[1]);
    vTtl := reg[2]::INTEGER;
    vIdA := reg[3]::INTEGER;
    vId  := reg[4]::INTEGER;

    -- consulta
    UPDATE
        razas
    SET
        nombre = vNmb,
        total_adopción = vTtl,
        id_animal = vIdA
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
CREATE OR REPLACE FUNCTION agrPropietario (reg ANYARRAY) RETURNS INTEGER AS $$
DECLARE
    vRfc VARCHAR := null;
    vNmb VARCHAR := null;
    vApP VARCHAR := null;
    vApM VARCHAR := null;
    vNoC VARCHAR := null;
    vId  INTEGER := 0;
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
CREATE OR REPLACE FUNCTION actPropietario (reg ANYARRAY) RETURNS INTEGER AS $$
DECLARE
    vRfc VARCHAR := null;
    vNmb VARCHAR := null;
    vApP VARCHAR := null;
    vApM VARCHAR := null;
    vNoC VARCHAR := null;
    vId  INTEGER := 0;
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
    vId  := reg[6]::INTEGER;

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
CREATE OR REPLACE FUNCTION agrMascota (reg ANYARRAY) RETURNS INTEGER AS $$
DECLARE
    vNmb VARCHAR := null;
    vFch DATE    := null;
    vSxo VARCHAR := null;
    vIdP INTEGER := null;
    vIdR INTEGER := null;
    vId  INTEGER := null;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 5 THEN
        RETURN 0;
    END IF;

    vNmb := upper(reg[1]);
    vFch := reg[2]::DATE;
    vSxo := lower(reg[3]);
    vIdP := reg[4]::INTEGER;
    vIdR := reg[5]::INTEGER;

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
CREATE OR REPLACE FUNCTION actMascota (reg ANYARRAY) RETURNS INTEGER AS $$
DECLARE
    vNmb VARCHAR := null;
    vFch DATE    := null;
    vSxo VARCHAR := null;
    vIdP INTEGER := null;
    vIdR INTEGER := null;
    vId  INTEGER := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 6 THEN
        RETURN 0;
    END IF;

    vNmb := upper(reg[1]);
    vFch := reg[2]::DATE;
    vSxo := lower(reg[3]);
    vIdP := reg[4]::INTEGER;
    vIdR := reg[5]::INTEGER;
    vId  := reg[7]::INTEGER;

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
CREATE OR REPLACE FUNCTION agrArticulo_proveedor (reg ANYARRAY, tipo INTEGER) RETURNS INTEGER LANGUAGE plpgsql AS $$
DECLARE
    vNmb VARCHAR        := null;
    vMnt DECIMAL(10, 2) := null;
    vIdP INTEGER        := null;
    vId  INTEGER        := 0;
    -- alimentos
    vGrm DECIMAL(10, 2) := null;
    -- productos
    vTpo VARCHAR        := null;
    -- medicamentos *incluye vGrm*
    vLbt VARCHAR        := null;
    vVia VARCHAR        := null;

    vTbl VARCHAR[]      := '{"alimento", "producto", "medicamento"}';
BEGIN
    IF array_length(reg, 1) < 4 AND (tipo < 1 OR tipo > 3) THEN
        RETURN 0;
    END IF;

    vNmb := upper(reg[1]);
    vMnt := reg[2]::DECIMAL;
    vIdP := reg[3]::INTEGER;

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
            vGrm := reg[4]::DECIMAL;

            INSERT INTO alimentos
            VALUES (vId, vGrm)
            RETURNING id_articulo_alimento INTO vId;
        WHEN 2 THEN
            vTpo := lower(reg[5]::VARCHAR);

            INSERT INTO productos
            VALUES (vId, vTpo)
            RETURNING id_articulo_producto INTO vId;
        WHEN 3 THEN
            vGrm := reg[4]::DECIMAL;
            vLbt := upper(reg[5]::VARCHAR);
            vVia := lower(reg[6]::VARCHAR);

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
CREATE OR REPLACE FUNCTION actArticulo_proveedor (reg ANYARRAY, tipo INTEGER) RETURNS INTEGER LANGUAGE plpgsql AS $$
DECLARE
    vNmb VARCHAR        := null;
    vMnt DECIMAL(10, 2) := null;
    vIdP INTEGER        := null;
    vId  INTEGER        := 0;
    -- alimentos
    vGrm DECIMAL(10, 2) := null;
    -- productos
    vTpo VARCHAR        := null;
    -- medicamentos *incluye vGrm*
    vLbt VARCHAR        := null;
    vVia VARCHAR        := null;

    vTbl VARCHAR[]      := '{"alimento", "producto", "medicamento"}';
BEGIN
    IF array_length(reg, 1) < 5 AND (tipo < 1 OR tipo > 3) THEN
        RETURN 0;
    END IF;

    vNmb := upper(reg[1]);
    vMnt := reg[2]::DECIMAL;
    vIdP := reg[3]::INTEGER;
    vId  := reg[4]::INTEGER;

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
            vGrm := reg[5]::DECIMAL;

            UPDATE alimentos
            SET gramaje = vGrm
            WHERE id_articulo_alimento = vId;
        WHEN 2 THEN
            vTpo := reg[5]::VARCHAR;

            UPDATE productos
            SET tipo = vtpo
            WHERE id_articulo_producto = vid;
        WHEN 3 THEN
            vGrm := reg[4]::DECIMAL;
            vLbt := reg[5]::VARCHAR;
            vVia := reg[6]::VARCHAR;

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
CREATE OR REPLACE FUNCTION agrArticulo_venta (reg ANYARRAY) RETURNS INTEGER AS $BODY$
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
CREATE OR REPLACE FUNCTION actArticulo_venta (reg ANYARRAY) RETURNS INTEGER AS $BODY$
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
CREATE OR REPLACE FUNCTION agrEmpleado (reg ANYARRAY) RETURNS INTEGER AS $$
DECLARE
    vRfc VARCHAR        := null;
    vNmb VARCHAR        := null;
    vApP VARCHAR        := null;
    vApM VARCHAR        := null;
    vNoC VARCHAR        := null;
    vFcI DATE           := null;
    vJrI TIME           := null;
    vJrF TIME           := null;
    vSal DECIMAL(10, 2) := null;
    vPst VARCHAR        := null;
    vId  INTEGER        := 0;
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
    vFcI := reg[6]::DATE;
    vJrI := reg[7]::TIME;
    vJrF := reg[8]::TIME;
    vSal := reg[9]::DECIMAL;
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
CREATE OR REPLACE FUNCTION actEmpleado (reg ANYARRAY) RETURNS INTEGER AS $$
DECLARE
    vRfc VARCHAR        := null;
    vNmb VARCHAR        := null;
    vApP VARCHAR        := null;
    vApM VARCHAR        := null;
    vNoC VARCHAR        := null;
    vFcI DATE           := null;
    vJrI TIME           := null;
    vJrF TIME           := null;
    vSal DECIMAL(10, 2) := null;
    vPst VARCHAR        := null;
    vId  INTEGER := 0;
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
    vFcI := reg[6]::DATE;
    vJrI := reg[7]::TIME;
    vJrF := reg[8]::TIME;
    vSal := reg[9]::DECIMAL;
    vPst := reg[10];
    vId  := reg[11]::INTEGER;

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
CREATE OR REPLACE FUNCTION agrNomina (reg ANYARRAY) RETURNS INTEGER AS $$
DECLARE
    vFcI DATE           := null;
    vFcF DATE           := null;
    vTlH INTEGER        := null;
    vTlB DECIMAL(10, 2) := null;
    vIdE INTEGER        := 0;
    vCns INTEGER        := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 5 THEN
        RETURN 0;
    END IF;

    vFcI := reg[1]::DATE;
    vFcF := reg[2]::DATE;
    vTlH := reg[3]::INTEGER;
    vTlB := reg[4]::DECIMAL;
    vIdE := reg[5]::INTEGER;

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
CREATE OR REPLACE FUNCTION actNomina (reg ANYARRAY) RETURNS INTEGER AS $$
DECLARE
    vFcI DATE           := null;
    vFcF DATE           := null;
    vTlH INTEGER        := null;
    vTlB DECIMAL(10, 2) := null;
    vIdE INTEGER        := 0;
    vCns INTEGER        := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 6 THEN
        RETURN 0;
    END IF;

    vFcI := reg[1]::DATE;
    vFcF := reg[2]::DATE;
    vTlH := reg[3]::INTEGER;
    vTlB := reg[4]::DECIMAL;
    vIdE := reg[5]::INTEGER;
    vCns := reg[6]::INTEGER;

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
CREATE OR REPLACE FUNCTION agrProveedor (reg ANYARRAY) RETURNS INTEGER AS $$
DECLARE
    vNmb VARCHAR := null;
    vDrc VARCHAR := null;
    vTlf VARCHAR := null;
    vDsc varchar := null;
    vId INTEGER  := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 4 THEN
        RETURN 0;
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
           vDsc,
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
    RETURN 0;
END;
$$ LANGUAGE plpgsql;

DROP FUNCTION IF EXISTS actProveedor;
CREATE OR REPLACE FUNCTION actProveedor (reg ANYARRAY) RETURNS INTEGER AS $$
DECLARE
    vNmb VARCHAR := null;
    vDrc VARCHAR := null;
    vTlf VARCHAR := null;
    vDsc varchar := null;
    vId INTEGER  := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 5 THEN
        RETURN 0;
    END IF;

    vNmb := upper(reg[1]);
    vDrc := upper(reg[2]);
    vTlf := upper(reg[3]);
    vDsc := upper(reg[4]);
    vId  := reg[5]::INTEGER;

    -- consulta
    UPDATE
        proveedores
    SET
        nombre      = vNmb,
        direccion   = vDsc,
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
CREATE OR REPLACE FUNCTION agrFactura_proveedor (reg ANYARRAY) RETURNS INTEGER AS $$
DECLARE
    vFch DATE           := null;
    vMnt DECIMAL(10, 2) := 0;
    vIdP INTEGER        := null;
    vId  INTEGER        := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 2 THEN
        RETURN 0;
    END IF;

    vFch := reg[1]::DATE;
    --vMnt := reg[2]::DECIMAL;
    vIdP := reg[3]::INTEGER;

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
CREATE OR REPLACE FUNCTION actFactura_proveedor (reg ANYARRAY) RETURNS INTEGER AS $$
DECLARE
    vFch VARCHAR        := null;
    --vMnt DECIMAL(10, 2) := null;
    vIdP INTEGER        := null;
    vId  INTEGER        := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 3 THEN
        RETURN 0;
    END IF;

    vFch := reg[1]::DATE;
    --vMnt := reg[2]::DECIMAL;
    vIdP := reg[3]::INTEGER;
    vId  := reg[4]::INTEGER;

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

DROP PROCEDURE IF EXISTS agrDetalle_factura;
CREATE OR REPLACE PROCEDURE agrDetalle_factura (reg ANYARRAY) LANGUAGE plpgsql AS $$
    DECLARE
        vCnt INTEGER        := 0;
        vMnT DECIMAL(10, 2) := 0;
        vIdA INTEGER        := 0;
        vIdF INTEGER        := 0;
        vCns INTEGER        := 0;

        vIdAV INTEGER       := 0;
    BEGIN
        -- preguntamos si el arreglo está vacío
        IF array_length(reg, 1) < 3 THEN
            RETURN;
        END IF;

        vCnt := reg[1]::INTEGER;
        vIdA := reg[2]::INTEGER;
        vIdF := reg[3]::INTEGER;

        -- obtenemos el precio del producto
        SELECT INTO vMnT monto
        FROM articulos_proveedor
        WHERE id_articulo_proveedor = vIdA;
        IF NOT FOUND THEN
            RAISE EXCEPTION 'ERROR: articuloVenta de id % no valido', vIdA;
        END IF;

        -- seleccionamos la factura a actualizar y la bloqueamos
        PERFORM *
        FROM facturas_proveedor
        WHERE id_factura = vIdF FOR UPDATE; -- transacción
        -- si la factura no existe
        IF NOT FOUND THEN
            ROLLBACK;
            RAISE EXCEPTION 'ERROR: factura no encontrada, operación invalida';
        END IF;

        -- consulta
        INSERT INTO detalle_factura
        VALUES
            (
                vIdF,
                (
                    SELECT
                        count(*) + 1
                    FROM
                        detalle_factura
                    WHERE id_factura = vIdF
                ),
                vCnt,
                vMnt * vCnt,
                vIdA)
        RETURNING cns_detalle_factura INTO vCns;
        -- fin consulta

        -- añadimos el precio del articuloVenta a la factura
        UPDATE
            facturas_proveedor
        SET
            monto_total = monto_total +  (vCnt * vMnT)
        WHERE id_factura = vIdF;

        -- comprobamos que se ha añadido el subtotal a la factura
        IF NOT FOUND THEN
            ROLLBACK;
            RAISE EXCEPTION 'ERROR: subtotal de articuloVenta no añadido a la factura, inserción no realizada';
        END IF;

        -- añadimos los articuloVendidos a la venta
        IF NOT exists( SELECT * FROM articulos_venta WHERE id_articulo = vIdA) THEN
            -- insertamos el nuevo articuloVenta a la venta
            INSERT INTO articulos_venta
            VALUES (
                    DEFAULT,
                    vmnt,
                    null,
                    vcnt,
                    vIdA,
                    DEFAULT
               ) RETURNING id_articulo_venta INTO vIdAV;
            IF vIdAV = 0 THEN
                ROLLBACK;
                RAISE EXCEPTION 'articuloVenta no añadido a la venta, operación invalida';
            END IF;

            RAISE NOTICE 'articuloVenta % de id % de factura id %, insertado correctamente en factura y a la venta %', vCns, vIdA, vIdF, vIdAV;
        ELSE
            -- actualizamos el stock
            UPDATE articulos_venta
            SET stock = stock + vCnt
            WHERE id_articulo = vIdA;
            IF NOT FOUND THEN
                ROLLBACK;
                RAISE EXCEPTION 'ERROR: inventario no actualizado, operación invalida';
            END IF;

            RAISE NOTICE 'articuloVenta % de id % de factura id %, insertado correctamente en factura y actualizado a la venta', vCns, vIdA, vIdF;
        END IF;

        COMMIT;
    END;
$$;

DROP PROCEDURE IF EXISTS actDetalle_factura;
CREATE OR REPLACE PROCEDURE actDetalle_factura (reg ANYARRAY) AS $$
DECLARE
    vACnt INTEGER        := 0;
    vASbT DECIMAL(10, 2) := 0;
    vCnt  INTEGER        := 0;
    vMnt  DECIMAL(10, 2) := 0;
    vIdA  INTEGER        := 0;
    vIdF  INTEGER        := 0;
    vCns  INTEGER        := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 4 THEN
        RETURN;
    END IF;

    vCnt := reg[1]::INTEGER;
    vIdA := reg[2]::INTEGER;
    vIdF := reg[3]::INTEGER;
    vCns := reg[4]::INTEGER;

    -- obtenemos los productos que se habían ingresado antes
    SELECT INTO
        vASbT, vACnt
        subtotal, cantidad
    FROM detalle_factura
    WHERE id_factura = vIdF
      AND cns_detalle_factura = vCns;

    RAISE NOTICE 'Subtotal A %, cantidad A %', vASbT, vACnt;

    vMnt = vASbT / vACnt;

    IF NOT FOUND THEN
        RAISE EXCEPTION 'ERROR: articuloVenta % no encontrado en factura %', vIdA, vIdF;
    END IF;

    PERFORM  *
    FROM facturas_proveedor
    WHERE id_factura = vIdF FOR UPDATE;
    -- si la factura no existe
    IF NOT FOUND THEN
        ROLLBACK;
        RAISE EXCEPTION 'ERROR: factura no encontrada, operación invalida';
    END IF;

    -- consulta
    UPDATE
        detalle_factura
    SET
        cantidad = vCnt,
        subtotal = vCnt * vMnt
    WHERE id_factura = vIdF AND cns_detalle_factura = vCns;
    -- fin consulta
    IF NOT FOUND THEN
        ROLLBACK;
        RAISE EXCEPTION 'ERROR: articuloVenta en factura % no actualizado, operación invalida', vIdF;
    END IF;

    -- actualizamos la factura
    UPDATE facturas_proveedor SET monto_total = (monto_total - vASbT) + (vCnt * vMnt) WHERE id_factura = vIdF;

    -- actualizamos el stock
    UPDATE articulos_venta SET stock = (stock - vACnt) + vCnt WHERE id_articulo = vIdA;
    IF NOT FOUND THEN
        ROLLBACK;
        RAISE EXCEPTION 'ERROR: inventario no actualizado, operación invalida';
    END IF;

    RAISE NOTICE 'articuloVenta de id % de factura id %, actualizado correctamente', vIdA, vIdF;
    COMMIT;
END;
$$ LANGUAGE plpgsql;

DROP FUNCTION IF EXISTS agrForma_pago;
CREATE OR REPLACE FUNCTION agrForma_pago (reg ANYARRAY) RETURNS INTEGER AS $$
DECLARE
    vNmb VARCHAR        := null;
    vCms DECIMAL(10, 2) := 0;
    vId  INTEGER        := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 2 THEN
        RETURN 0;
    END IF;

    vNmb := upper(reg[1]::VARCHAR);
    vCms := reg[2]::DECIMAL;

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
CREATE OR REPLACE FUNCTION actForma_pago (reg ANYARRAY) RETURNS INTEGER AS $$
DECLARE
    vNmb VARCHAR := null;
    vCms DECIMAL(10, 2) := 0;
    vId  INTEGER := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 3 THEN
        RETURN 0;
    END IF;

    vNmb := upper(reg[1]::VARCHAR);
    vCms := reg[2]::DECIMAL;
    vId  := reg[3]::INTEGER;

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
CREATE OR REPLACE FUNCTION agrTicket (reg ANYARRAY) RETURNS INTEGER AS $$
DECLARE
    vMnT DECIMAL(10, 2) := null;
    vFcC DATE           := null;
    vHrC TIME           := null;
    vId  INTEGER        := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 3 THEN
        RETURN 0;
    END IF;

    vMnT := reg[1]::DECIMAL;
    vFcC := reg[2]::DATE;
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

-- este procedimiento debe ser una transacción
DROP PROCEDURE IF EXISTS agrPago;
CREATE OR REPLACE PROCEDURE agrPago (reg ANYARRAY) AS $$
DECLARE
    vSbT DECIMAL(10, 2) := 0;
    vIdF INTEGER        := 0;
    vIdT INTEGER        := 0;
    vCms DECIMAL(10, 2) := 0;
    vPgs DECIMAL(10, 2) := 0;
    vCns INTEGER        := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 3 THEN
        RETURN;
    END IF;

    vSbT := reg[1]::DECIMAL;
    vIdF := reg[2]::INTEGER;
    vIdT := reg[3]::INTEGER;

    SELECT INTO vPgs sum(subtotal) FROM detalle_ticket WHERE id_ticket = vIdT;

    IF exists(SELECT * FROM tickets WHERE vPgs >= tickets.monto_total) THEN
        RAISE EXCEPTION 'ticket % ya pagado, no se permiten más pagos', vIdT;
    END IF;

    -- realizamos la transacción
    PERFORM *
    FROM tickets
    WHERE id_ticket = vIdT FOR UPDATE;

    SELECT INTO vCms comision
    FROM formas_pago
    WHERE id_forma_pago = vIdF;

    -- consulta
    INSERT INTO
        pagos
    VALUES (
           (SELECT count(*) + 1
            FROM pagos
            WHERE id_ticket = vIdT),
           vSbT - vCms,
           vIdF,
           vIdT,
           DEFAULT)
    RETURNING cns_pago INTO vCns;
    -- fin consulta

    IF vCns < 1 THEN
        ROLLBACK;
        RAISE EXCEPTION 'ERROR: Ticket de %:%, no insertado', vIdT, vIdF;
    END IF;

    -- actualizamos el ticket
    UPDATE tickets
    SET monto_total = monto_total + vSbT
    WHERE id_ticket = vIdT;

    IF NOT FOUND THEN
        ROLLBACK;
        RAISE EXCEPTION 'ERROR: ticket no actualizado, no se realizo la inserción';
    END IF;

    RAISE NOTICE 'Pago de ticket %:% de id % , insertado correctamente', vIdT, vIdF, vCns;
    COMMIT;
END;
$$ LANGUAGE plpgsql;

-- esta función debe ser una transacción
DROP PROCEDURE IF EXISTS agrArticulo_ticket;
CREATE OR REPLACE PROCEDURE agrArticulo_ticket (reg ANYARRAY) AS $$
DECLARE
    vCnt INTEGER        := 0;
    vMnt DECIMAL(10, 2) := 0;
    vSbT DECIMAL(10, 2) := 0;
    vIdA INTEGER        := 0;
    vIdT INTEGER        := 0;
    vCns INTEGER        := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 3 THEN
        RAISE NOTICE 'el arreglo debe de contener lo siguiente: cantidad, id_articulo, id_ticket';
        RETURN 0;
    END IF;

    vCnt := reg[1]::INTEGER;
    vIdA := reg[2]::INTEGER;
    vIdT := reg[3]::INTEGER;

    -- transacción
    PERFORM *
    FROM tickets
    WHERE id_ticket = vIdT FOR UPDATE;

    -- obtenemos el precio del articuloVenta
    SELECT INTO vMnt monto
    FROM articulos_venta
    WHERE id_articulo_venta = vIdA
      AND stock > 0; -- validamos que aún queden productos

    IF NOT FOUND THEN
        ROLLBACK;
        RAISE EXCEPTION 'ERROR: articuloVenta de id % no encontrado o sin existencias', vIdA;
    END IF;

    vSbT := vCnt * vMnt;

    -- consulta
    INSERT INTO
        detalle_ticket
    VALUES
        (
            vIdT,
            (
                SELECT
                    count(*) + 1
                FROM
                    detalle_ticket
                WHERE id_ticket = vIdT
            ),
            vCnt,
            vSbT,
            vIdA)
    RETURNING cns_detalle_ticket INTO vCns;
    -- fin consulta

    -- añadimos el subtotal al ticket
    UPDATE tickets
    SET monto_total = monto_total + vSbt
    WHERE id_ticket = vIdT;

    IF vIdT < 1 THEN
        ROLLBACK;
        RAISE EXCEPTION 'ERROR: Ticket de %:%, no insertado', vIdA, vIdT;
    END IF;

    RAISE NOTICE 'av: %, can %, pre %, sub %', vIdA, vcnt, vmnt, vsbt;

    -- restamos la venta al inventario
    UPDATE articulos_venta
    SET stock = stock - vCnt
    WHERE id_articulo_venta = vIdA
      AND (stock - vCnt) >= 0;

    IF NOT FOUND THEN
        ROLLBACK;
        RAISE EXCEPTION 'inventario no actualizado, transacción invalida';
    END IF;

    RAISE NOTICE 'SUCCESSFULLY: articuloVenta % del ticket % , insertado correctamente', vIdA, vIdT;
    COMMIT;
END;
$$ LANGUAGE plpgsql;

DROP FUNCTION IF EXISTS agrDetalle_mascota;
CREATE OR REPLACE FUNCTION agrDetalle_mascota (reg ANYARRAY) RETURNS INTEGER AS $$
DECLARE
    vIdM  INTEGER := 0;
    vIdA  INTEGER := 0;
    vCnt  INTEGER := 0;
    vFch  DATE    := null;
    vCns  INTEGER := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 4 THEN
        RETURN 0;
    END IF;

    vFch  := reg[1]::DATE;
    vCnt  := reg[2]::INTEGER;
    vIdA  := reg[3]::INTEGER;
    vIdM  := reg[4]::INTEGER;

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
CREATE OR REPLACE FUNCTION actDetalle_mascota (reg ANYARRAY) RETURNS INTEGER AS $$
DECLARE
    vFcV  DATE    := null;
    vIdM  INTEGER := 0;
    vIdA INTEGER := 0;
    vCns  INTEGER := 0;
    vCnt integer := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 5 THEN
        RETURN 0;
    END IF;

    vFcV  := reg[1]::DATE;
    vCnt  := reg[2]::INTEGER;
    vIdA  := reg[3]::INTEGER;
    vIdM  := reg[4]::INTEGER;
    vCns  := reg[5]::INTEGER;

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
CREATE OR REPLACE FUNCTION agrCita (reg ANYARRAY) RETURNS INTEGER AS $$
DECLARE
    vFch  DATE    := null;
    vHra  TIME    := null;
    vDtl  VARCHAR := null;
    vIdM  INTEGER := 0;
    vIdV  INTEGER := 0;
    vIdT  INTEGER := 0;
    vEst  VARCHAR := 0;
    vId   INTEGER := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 7 THEN
        RETURN 0;
    END IF;

    vFch  := reg[1]::DATE;
    vHra  := reg[2]::TIME;
    vDtl  := upper(reg[3]);
    vIdM  := reg[4]::INTEGER;
    vIdV  := reg[5]::INTEGER;
    vIdT  := reg[6]::INTEGER;
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
CREATE OR REPLACE FUNCTION actCita (reg ANYARRAY) RETURNS INTEGER AS $$
DECLARE
    vFch  DATE    := null;
    vHra  TIME    := null;
    vDtl  VARCHAR := null;
    vIdM  INTEGER := 0;
    vIdV  INTEGER := 0;
    vIdT  INTEGER := 0;
    vEst  VARCHAR := 0;
    vId   INTEGER := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 8 THEN
        RETURN 0;
    END IF;

    vFch  := reg[1]::DATE;
    vHra  := reg[2]::TIME;
    vDtl  := upper(reg[3]);
    vIdM  := reg[4]::INTEGER;
    vIdV  := reg[5]::INTEGER;
    vIdT  := reg[6]::INTEGER;
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

CREATE OR REPLACE FUNCTION obtAlimentos_factura(pIdF INTEGER, pTpo INTEGER) RETURNS refcursor AS $$
DECLARE
    vCursor refcursor;
BEGIN
    CASE ptpo
        WHEN 0 THEN
            OPEN vcursor FOR SELECT ap.id_articulo_proveedor,
                                    ap.nombre,
                                    ap.monto,
                                    ap.id_proveedor,
                                    df.cantidad
                             FROM detalle_factura df
                                      INNER JOIN articulos_proveedor ap
                                                 ON ap.id_articulo_proveedor = df.id_articulo
                                      INNER JOIN articulos_proveedor a
                                                 ON ap.id_articulo_proveedor = a.id_articulo_proveedor
                             WHERE id_factura = pIdF;
        WHEN 1 THEN
            OPEN vcursor FOR SELECT ap.id_articulo_proveedor,
                                    ap.nombre,
                                    ap.monto,
                                    ap.id_proveedor,
                                    df.cantidad
                             FROM detalle_factura df
                                      INNER JOIN articulos_proveedor ap
                                                 ON ap.id_articulo_proveedor = df.id_articulo
                                      INNER JOIN alimentos a
                                                 ON ap.id_articulo_proveedor = a.id_articulo_alimento
                             WHERE id_factura = pIdF;
        WHEN 2 THEN
            OPEN vcursor FOR SELECT ap.id_articulo_proveedor,
                                    ap.nombre,
                                    ap.monto,
                                    ap.id_proveedor,
                                    df.cantidad
                             FROM detalle_factura df
                                      INNER JOIN articulos_proveedor ap
                                                 ON ap.id_articulo_proveedor = df.id_articulo
                                      INNER JOIN productos a
                                                 ON ap.id_articulo_proveedor = a.id_articulo_producto
                             WHERE id_factura = pIdF;
        WHEN 3 THEN
            OPEN vcursor FOR SELECT ap.id_articulo_proveedor,
                                    ap.nombre,
                                    ap.monto,
                                    ap.id_proveedor,
                                    df.cantidad
                             FROM detalle_factura df
                                      INNER JOIN articulos_proveedor ap
                                                 ON ap.id_articulo_proveedor = df.id_articulo
                                      INNER JOIN medicamentos a
                                                 ON ap.id_articulo_proveedor = a.id_articulo_medicamento
                             WHERE id_factura = pIdF;
    END CASE;

    RETURN vcursor;
END;
$$ LANGUAGE plpgsql;