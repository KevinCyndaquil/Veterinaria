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
    vId  INTEGER := null;
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

    IF vid == NULL THEN
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

--- agrega un alimento a la tabla alimentos;
DROP FUNCTION IF EXISTS agrAlimento;
CREATE OR REPLACE FUNCTION agrAlimento (reg ANYARRAY) RETURNS INTEGER AS $$
DECLARE
    vNmb VARCHAR        := null;
    vMnt DECIMAL(10, 2) := null;
    vGrm DECIMAL(10, 2) := null;
    vDsc VARCHAR        := null;
    vStc INTEGER        := 0;
    vId  INTEGER        := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 5 THEN
        RETURN 0;
    END IF;

    vNmb := upper(reg[1]);
    vMnt := reg[2]::DECIMAL;
    vGrm := reg[3]::DECIMAL;
    vDsc := upper(reg[4]);
    vStc := reg[5]::INTEGER;

    INSERT INTO
        articulos
    VALUES
    (
        DEFAULT,
        vNmb,
        vMnt,
        vDsc,
        vStc,
        DEFAULT)
    RETURNING id_articulo INTO vId;

    IF vId = 0 THEN
        RAISE EXCEPTION 'articulo no añadido, función cancelada';
    END IF;

    -- consulta
    INSERT INTO
        alimentos
    VALUES (
           vId,
           vGrm)
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

DROP FUNCTION IF EXISTS actAlimento;
CREATE OR REPLACE FUNCTION actAlimento (reg ANYARRAY) RETURNS INTEGER AS $$
DECLARE
    vNmb VARCHAR        := null;
    vMnt DECIMAL(10, 2) := null;
    vGrm DECIMAL(10, 2) := null;
    vDsc VARCHAR        := null;
    vStc INTEGER        := null;
    vId  INTEGER        := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 6 THEN
        RETURN 0;
    END IF;

    vNmb := upper(reg[1]);
    vMnt := reg[2]::DECIMAL;
    vGrm := reg[3]::DECIMAL;
    vDsc := upper(reg[4]);
    vStc := reg[5]::INTEGER;
    vId  := reg[6]::INTEGER;

    -- consulta
    UPDATE
        articulos
    SET
        nombre = vNmb,
        monto = vMnt,
        descripcion = vDsc,
        stock = vStc
    WHERE
            id_articulo = vId;
    IF NOT found THEN
        RAISE EXCEPTION 'articulo no actualizado, función cancelada';
    END IF;


    UPDATE
        alimentos
    SET
        gramaje = vgrm
    WHERE id_alimento = vId;
    -- fin consulta

    IF vId > 0 THEN
        RAISE NOTICE 'alimento % de id %, actualizado correctamente', vNmb, vId;
        RETURN vId;
    END IF;

    RAISE NOTICE 'ERROR: alimento no actualizado';
    RETURN 0;
END;
$$ LANGUAGE plpgsql;

DROP FUNCTION IF EXISTS agrProducto;
CREATE OR REPLACE FUNCTION agrProducto (reg ANYARRAY) RETURNS INTEGER AS $$
DECLARE
    vNmb VARCHAR        := null;
    vMnt DECIMAL(10, 2) := null;
    vDsc VARCHAR        := null;
    vTpo VARCHAR        := null;
    vStc INTEGER        := null;
    vId  INTEGER        := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 5 THEN
        RETURN 0;
    END IF;

    vNmb := upper(reg[1]);
    vMnt := reg[2]::DECIMAL;
    vDsc := upper(reg[3]);
    vTpo := lower(reg[4]);
    vStc := reg[5]::INTEGER;

    -- consulta
    INSERT INTO
        articulos
    VALUES
        (
            DEFAULT,
            vNmb,
            vMnt,
            vDsc,
            vStc,
            DEFAULT)
    RETURNING id_articulo INTO vId;

    IF vId = 0 THEN
        RAISE EXCEPTION 'articulo no añadido, función cancelada';
    END IF;

    INSERT INTO
        productos
    VALUES (
            vid,
            vTpo)
    RETURNING id_producto INTO vId;
    -- fin consulta

    IF vId > 0 THEN
        RAISE NOTICE 'producto % de id %, insertado correctamente', vNmb, vId;
        RETURN vId;
    END IF;

    RAISE NOTICE 'ERROR: producto no insertado a la base de datos';
    RETURN 0;
END;
$$ LANGUAGE plpgsql;

DROP FUNCTION IF EXISTS actProducto;
CREATE OR REPLACE FUNCTION actProducto (reg ANYARRAY) RETURNS INTEGER AS $$
DECLARE
    vNmb VARCHAR        := null;
    vMnt DECIMAL(10, 2) := null;
    vDsc VARCHAR        := null;
    vTpo VARCHAR        := null;
    vStc INTEGER        := null;
    vId  INTEGER        := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 6 THEN
        RETURN 0;
    END IF;

    vNmb := upper(reg[1]);
    vMnt := reg[2]::DECIMAL;
    vDsc := upper(reg[3]);
    vTpo := lower(reg[4]);
    vStc := reg[5]::INTEGER;
    vId  := reg[6]::INTEGER;

    -- consulta
    UPDATE
        articulos
    SET
        nombre = vNmb,
        monto = vMnt,
        descripcion = vDsc,
        stock = vStc
    WHERE
            id_articulo = vId;
    IF NOT found THEN
        RAISE EXCEPTION 'articulo no actualizado, función cancelada';
    END IF;

    UPDATE
        productos
    SET
        tipo        = vTpo
    WHERE
            id_producto = vId;
    -- fin consulta

    IF vId > 0 THEN
        RAISE NOTICE 'producto % de id %, actualizado correctamente', vNmb, vId;
        RETURN vId;
    END IF;

    RAISE NOTICE 'ERROR: producto no actualizado';
    RETURN 0;
END;
$$ LANGUAGE plpgsql;

DROP FUNCTION IF EXISTS agrMedicamento;
CREATE OR REPLACE FUNCTION agrMedicamento (reg ANYARRAY) RETURNS INTEGER AS $$
DECLARE
    vNmb VARCHAR        := null;
    vMnt DECIMAL(10, 2) := null;
    vGrm DECIMAL(10, 2) := null;
    vLbr VARCHAR        := null;
    vDsc VARCHAR        := null;
    vVia VARCHAR        := null;
    vStc INTEGER        := null;
    vId  INTEGER        := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 7 THEN
        RETURN 0;
    END IF;

    vNmb := upper(reg[1]);
    vMnt := reg[2]::DECIMAL;
    vGrm := reg[3]::DECIMAL;
    vLbr := upper(reg[4]);
    vDsc := upper(reg[5]);
    vVia := lower(reg[6]);
    vStc := reg[7]::INTEGER;

    -- consulta
    INSERT INTO
        articulos
    VALUES
        (
            DEFAULT,
            vNmb,
            vMnt,
            vDsc,
            vStc,
            DEFAULT)
    RETURNING id_articulo INTO vId;

    IF vId = 0 THEN
        RAISE EXCEPTION 'articulo no añadido, función cancelada';
    END IF;

    INSERT INTO
        medicamentos
    VALUES (
            vId,
            vGrm,
            vLbr,
            vVia)
    RETURNING id_medicamento INTO vId;
    -- fin consulta

    IF vId > 0 THEN
        RAISE NOTICE 'medicamento % de id %, insertado correctamente', vNmb, vId;
        RETURN vId;
    END IF;

    RAISE NOTICE 'ERROR: medicamento no insertado a la base de datos';
    RETURN 0;
END;
$$ LANGUAGE plpgsql;

DROP FUNCTION IF EXISTS actMedicamento;
CREATE OR REPLACE FUNCTION actMedicamento (reg ANYARRAY) RETURNS INTEGER AS $$
DECLARE
    vNmb VARCHAR        := null;
    vMnt DECIMAL(10, 2) := null;
    vGrm DECIMAL(10, 2) := null;
    vLbr VARCHAR        := null;
    vDsc VARCHAR        := null;
    vVia VARCHAR        := null;
    vStc INTEGER        := null;
    vId  INTEGER        := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 8 THEN
        RETURN 0;
    END IF;

    vNmb := upper(reg[1]);
    vMnt := reg[2]::DECIMAL;
    vGrm := reg[3]::DECIMAL;
    vLbr := upper(reg[4]);
    vDsc := upper(reg[5]);
    vVia := lower(reg[6]);
    vStc := reg[7]::INTEGER;
    vId  := reg[8]::INTEGER;

    -- consulta
    UPDATE
        articulos
    SET
        nombre = vNmb,
        monto = vMnt,
        descripcion = vDsc,
        stock = vStc
    WHERE
            id_articulo = vId;
    IF NOT found THEN
        RAISE EXCEPTION 'articulo no actualizado, función cancelada';
    END IF;

    UPDATE
        medicamentos
    SET
        gramaje     = vGrm,
        laboratorio = vLbr,
        via         = vVia
    WHERE id_medicamento = vId;
    -- fin consulta

    IF vId > 0 THEN
        RAISE NOTICE 'medicamento % de id %, actualizado correctamente', vNmb, vId;
        RETURN vId;
    END IF;

    RAISE NOTICE 'ERROR: medicamento no actualizado';
    RETURN 0;
END;
$$ LANGUAGE plpgsql;

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
    vId  INTEGER        := null;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 9 THEN
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
           vSal)
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
    vId  INTEGER := 0;
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
    vId  := reg[10]::INTEGER;

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
        salario    = vSal
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

DROP FUNCTION IF EXISTS agrVeterinario;
CREATE OR REPLACE FUNCTION agrVeterinario (reg ANYARRAY) RETURNS INTEGER AS $$
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
    vFcS DATE           := null;
    vId  INTEGER        := null;
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
    vFcS := reg[10]::DATE;

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
               vSal)
    RETURNING id_empleado INTO vId;

    INSERT INTO
        veterinarios
    VALUES
    (
         vid,
         vFcS
    ) RETURNING id_veterinario INTO vid;
    -- fin consulta

    IF vId > 0 THEN
        RAISE NOTICE 'empleado % de id %, insertado correctamente', vNmb, vId;
        RETURN vId;
    END IF;

    RAISE NOTICE 'ERROR: empleado no insertado a la base de datos';
    RETURN 0;
END;
$$ LANGUAGE plpgsql;

DROP FUNCTION IF EXISTS actVeterinario;
CREATE OR REPLACE FUNCTION actVeterinario (reg ANYARRAY) RETURNS INTEGER AS $$
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
    vFcS date           := null;
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
    vFcS := reg[10]::DATE;
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
        RAISE EXCEPTION 'persona no actualizada';
    END IF;

    UPDATE
        empleados
    SET
        fecha_ini  = vFcI,
        jor_ini    = vJrI,
        jor_fin    = vJrF,
        salario    = vSal
    WHERE id_empleado = vId;

    IF NOT found THEN
        RAISE EXCEPTION 'empleado no actualizado';
    END IF;

    UPDATE
        veterinarios
    SET
        fecha_servicio = vFcS
    WHERE id_veterinario = vId;
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

DROP FUNCTION IF EXISTS agrFactura;
CREATE OR REPLACE FUNCTION agrFactura (reg ANYARRAY) RETURNS INTEGER AS $$
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
        facturas
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

DROP FUNCTION IF EXISTS actFactura;
CREATE OR REPLACE FUNCTION actFactura (reg ANYARRAY) RETURNS INTEGER AS $$
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
        facturas
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
    BEGIN
        -- preguntamos si el arreglo está vacío
        IF array_length(reg, 1) < 3 THEN
            RETURN;
        END IF;

        vCnt := reg[1]::INTEGER;
        vIdA := reg[2]::INTEGER;
        vIdF := reg[3]::INTEGER;

        -- obtenemos el precio del producto
        SELECT INTO vMnT monto FROM articulos WHERE id_articulo = vIdA;
        IF NOT FOUND THEN
            RAISE EXCEPTION 'ERROR: articulo de id % no valido', vIdA;
        END IF;

        -- seleccionamos la factura a actualizar y la bloqueamos
        PERFORM
            *
        FROM
            facturas
        WHERE id_factura = vIdF FOR UPDATE; -- transacción
        -- si la factura no existe
        IF NOT FOUND THEN
            ROLLBACK;
            RAISE EXCEPTION 'ERROR: factura no encontrada, operación invalida';
        END IF;

        -- consulta
        INSERT INTO
            detalle_factura
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

        -- añadimos el precio del articulo a la factura
        UPDATE
            facturas
        SET
            monto_total = monto_total +  (vCnt * vMnT)
        WHERE id_factura = vIdF;

        -- comprobamos que se ha añadido el subtotal a la factura
        IF NOT FOUND THEN
            ROLLBACK;
            RAISE EXCEPTION 'ERROR: subtotal de alimento no añadido a la factura, inserción no realizada';
        END IF;

        -- añadimos los articulos a stock
        UPDATE articulos SET stock = stock + vCnt WHERE id_articulo = vIdA;

        IF NOT FOUND THEN
            ROLLBACK;
            RAISE EXCEPTION 'ERROR: articulo no añadido al inventario, inserción no realizada';
        END IF;

        RAISE NOTICE 'articulo % de id % de factura id %, insertado correctamente', vCns, vIdA, vIdF;
        COMMIT;
    END;
$$;

DROP PROCEDURE IF EXISTS actDetalleFactura;
CREATE OR REPLACE PROCEDURE actDetalleFactura (reg ANYARRAY) AS $$
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
    FROM
        detalle_factura
    WHERE id_factura = vIdF
      AND cns_detalle_factura = vCns;

    RAISE NOTICE 'Subtotal A %, cantidad A %', vASbT, vACnt;

    vMnt = vASbT / vACnt;

    IF NOT FOUND THEN
        RAISE EXCEPTION 'ERROR: articulo % no encontrado en factura %', vIdA, vIdF;
    END IF;

    PERFORM
        *
    FROM
        facturas
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
        RAISE EXCEPTION 'ERROR: alimento en factura % no actualizado, operación invalida', vIdF;
    END IF;

    -- actualizamos la factura
    UPDATE facturas SET monto_total = (monto_total - vASbT) + (vCnt * vMnt) WHERE id_factura = vIdF;

    -- actualizamos el stock
    UPDATE articulos SET stock = (stock - vACnt) + vCnt WHERE id_articulo = vIdA;
    IF NOT FOUND THEN
        ROLLBACK;
        RAISE EXCEPTION 'ERROR: inventario no actualizado, operación invalida';
    END IF;

    RAISE NOTICE 'articulo de id % de factura id %, actualizado correctamente', vIdA, vIdF;
    COMMIT;
END;
$$ LANGUAGE plpgsql;

DROP FUNCTION IF EXISTS agrFormaPago;
CREATE OR REPLACE FUNCTION agrFormaPago (reg ANYARRAY) RETURNS INTEGER AS $$
DECLARE
    vNmb VARCHAR := null;
    vId  INTEGER := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) = 0 THEN
        RETURN 0;
    END IF;

    vNmb := upper(reg[1]::VARCHAR);

    -- consulta
    INSERT INTO
        formas_pago
    VALUES (
           DEFAULT,
           vNmb,
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

DROP FUNCTION IF EXISTS actFormaPago;
CREATE OR REPLACE FUNCTION actFormaPago (reg ANYARRAY) RETURNS INTEGER AS $$
DECLARE
    vNmb VARCHAR := null;
    vId  INTEGER := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) = 0  OR array_length(reg, 1) < 4 THEN
        RETURN 0;
    END IF;

    vNmb := upper(reg[1]::VARCHAR);
    vId  := reg[2]::INTEGER;

    -- consulta
    UPDATE
        formas_pago
    SET
        nombre = vNmb
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

/*
DROP FUNCTION IF EXISTS actTicket;
CREATE OR REPLACE FUNCTION actTicket (reg ANYARRAY) RETURNS INTEGER AS $$
DECLARE
    vMnT DECIMAL(10, 2) := null;
    --vFcC DATE           := null;
    --vHrC TIME           := null;
    vId  INTEGER        := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 1 THEN
        RETURN 0;
    END IF;

    vMnT := reg[1]::DECIMAL;
    --vFcC := reg[2]::DATE;
    --vHrC := reg[3]::TIME;
    vId  := reg[4]::INTEGER;

    -- consulta
    UPDATE
        tickets
    SET
        monto_total = vMnT
        --fecha_cobro = vFcC,
        --hora_cobro  = vHrC
    WHERE id_ticket = vId;
    -- fin consulta

    IF vId > 0 THEN
        RAISE NOTICE 'Ticket de id % , actualizado correctamente', vId;
        RETURN vId;
    END IF;

    RAISE NOTICE 'ERROR: Ticket, no actualizado';
    RETURN 0;
END;
$$ LANGUAGE plpgsql;
*/
-- este procedimiento debe ser una transacción
DROP PROCEDURE IF EXISTS agrPago;
CREATE OR REPLACE PROCEDURE agrPago (reg ANYARRAY) AS $$
DECLARE
    vSbT DECIMAL(10, 2) := 0;
    vIdF INTEGER        := 0;
    vIdT INTEGER        := 0;
    vCns INTEGER        := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 3 THEN
        RETURN;
    END IF;

    vSbT := reg[1]::DECIMAL;
    vIdF := reg[2]::INTEGER;
    vIdT := reg[3]::INTEGER;

    -- realizamos la transacción
    PERFORM
        *
    FROM
        tickets
    WHERE
            id_ticket = vIdT
        FOR UPDATE;

    -- consulta
    INSERT INTO
        pagos
    VALUES (
           (SELECT
                    count(*) + 1
            FROM
                pagos
            WHERE id_ticket = vIdT),
           vSbT,
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
    UPDATE
        tickets
    SET
        monto_total = monto_total + vSbT
    WHERE
            id_ticket = vIdT;

    IF NOT FOUND THEN
        ROLLBACK;
        RAISE EXCEPTION 'ERROR: ticket no actualizado, no se realizo la inserción';
    END IF;

    RAISE NOTICE 'Pago de ticket %:% de id % , insertado correctamente', vIdT, vIdF, vCns;
    COMMIT;
END;
$$ LANGUAGE plpgsql;

/*
DROP FUNCTION IF EXISTS actPago;
CREATE OR REPLACE FUNCTION actPago (reg ANYARRAY) RETURNS INTEGER AS $$
DECLARE
    vSbT DECIMAL(10, 2) := null;
    vIdF INTEGER        := 0;
    vIdT INTEGER        := 0;
    vCns INTEGER        := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 3 THEN
        RETURN 0;
    END IF;

    vSbT := reg[1]::DECIMAL;
    vIdF := reg[2]::INTEGER;
    vIdT := reg[3]::INTEGER;
    vCns := reg[4]::INTEGER;

    -- consulta
    UPDATE
        pagos
    SET
        subtotal      = vSbT,
        id_forma_pago = vIdF
    WHERE id_ticket = vIdT AND cns_pago = vCns;
    -- fin consulta

    IF vCns > 0 THEN
        RAISE NOTICE 'Pago de ticket %:% de id % , actualizado correctamente', vIdT, vIdF, vCns;
        RETURN vCns;
    END IF;

    RAISE NOTICE 'ERROR: Ticket de %:%, no actualizado', vIdT, vIdF;
    RETURN 0;
END;
$$ LANGUAGE plpgsql;
 */

-- esta función debe ser una transacción
DROP PROCEDURE IF EXISTS agrArticuloTicket;
CREATE OR REPLACE PROCEDURE agrArticuloTicket (reg ANYARRAY) AS $$
DECLARE
    vCnt INTEGER        := 0;
    vMnt DECIMAL(10, 2) := 0;
    --vSbT DECIMAL(10, 2) := 0;
    vIdA INTEGER        := 0;
    vIdT INTEGER        := 0;
    vCns INTEGER        := 0;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 3 THEN
        RETURN;
    END IF;

    vCnt := reg[1]::INTEGER;
    --vSbT := reg[2]::DECIMAL;
    vIdA := reg[3]::INTEGER;
    vIdT := reg[4]::INTEGER;

    -- transacción
    PERFORM
        *
    FROM
        tickets
    WHERE id_ticket = vIdT FOR UPDATE;

    -- obtenemos el precio del alimento
    SELECT INTO
        vMnt monto
    FROM
        articulos
    WHERE id_articulo = vIdA
      AND stock > 0; -- validamos que aún queden productos

    IF NOT FOUND THEN
        ROLLBACK;
        RAISE EXCEPTION 'ERROR: articulo de id % no encontrado o sin existencias', vIdA;
    END IF;

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
            (vCnt * vMnt),
            vIdA)
    RETURNING cns_detalle_ticket INTO vCns;
    -- fin consulta

    IF vIdT < 1 THEN
        ROLLBACK;
        RAISE EXCEPTION 'ERROR: Ticket de %:%, no insertado', vIdA, vIdT;
    END IF;

    -- restamos la venta al inventario
    UPDATE
        articulos
    SET
        stock = stock - vCnt
    WHERE id_articulo = vIdA;

    RAISE NOTICE 'SUCCESSFULLY: articulo % del ticket % , insertado correctamente', vIdA, vIdT;
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
    INSERT INTO
        detalle_mascota
    VALUES
        (
            vIdM,
            (
                SELECT
                        count(*) + 1
                FROM
                    detalle_mascota
                WHERE id_mascota = vIdM
            ),
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
END;
$$ LANGUAGE plpgsql;

DROP FUNCTION IF EXISTS actVacuna_expediente;
CREATE OR REPLACE FUNCTION actVacuna_expediente (reg ANYARRAY) RETURNS INTEGER AS $$
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

/*
DROP FUNCTION IF EXISTS agrAlimentoReceta;
CREATE OR REPLACE FUNCTION agrAlimentoReceta (reg ANYARRAY) RETURNS INTEGER AS $$
DECLARE
    vTmD INTEGER        := null;
    vFrD INTEGER        := null;
    vCnF DECIMAL(10, 2) := null;
    vIdA INTEGER        := 0;
    vIdC INTEGER        := 0;
    vVal BOOLEAN        := FALSE;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 5 THEN
        RETURN 0;
    END IF;

    vTmD := reg[1]::INTEGER;
    vFrD := reg[2]::INTEGER;
    vCnF := reg[3]::DECIMAL;
    vIdA := reg[3]::INTEGER;
    vIdC := reg[4]::INTEGER;

    -- consulta
    INSERT INTO
        receta_alimentos
    VALUES (
           vTmD,
           vFrD,
           vCnF,
           vIdA,
           vIdC)
    RETURNING TRUE INTO vVal;
    -- fin consulta

    IF vVal THEN
        RAISE EXCEPTION 'ERROR: Alimento % de receta %, no insertado', vIdA, vIdC;
    END IF;

    RAISE NOTICE 'Alimento % de receta %, insertado correctamente', vIdA, vIdC;
    RETURN 1;
END;
$$ LANGUAGE plpgsql;

DROP FUNCTION IF EXISTS agrMedicamentoReceta;
CREATE OR REPLACE FUNCTION agrMedicamentoReceta (reg ANYARRAY) RETURNS INTEGER AS $$
DECLARE
    vTmD INTEGER        := null;
    vFrD INTEGER        := null;
    vCnF DECIMAL(10, 2) := null;
    vIdM INTEGER        := 0;
    vIdC INTEGER        := 0;
    vVal BOOLEAN        := FALSE;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 5 THEN
        RETURN 0;
    END IF;

    vTmD := reg[1]::INTEGER;
    vFrD := reg[2]::INTEGER;
    vCnF := reg[3]::DECIMAL;
    vIdM := reg[3]::INTEGER;
    vIdC := reg[4]::INTEGER;

    -- consulta
    INSERT INTO
        receta_medicamentos
    VALUES (
           vTmD,
           vFrD,
           vCnF,
           vIdM,
           vIdC)
    RETURNING TRUE INTO vVal;
    -- fin consulta

    IF vVal THEN
        RAISE NOTICE 'Medicamento % de receta %, insertado correctamente', vIdM, vIdC;
        RETURN 1;
    END IF;

    RAISE NOTICE 'ERROR: Medicamento % de receta %, no insertado', vIdM, vIdC;
    RETURN 0;
END;
$$ LANGUAGE plpgsql;*/