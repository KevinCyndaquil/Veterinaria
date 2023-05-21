/*
 .______________________________________________________________________________.
 |                                                                              |
 |                      PROCEDIMIENTOS ALMACENADOS                              |
 |______________________________________________________________________________|
 */

/**
  agrega un detalle a la factura, si el detalle ya existe, lo actualizará
 */

DROP PROCEDURE IF EXISTS agrDetalle_factura;
CREATE OR REPLACE PROCEDURE agrDetalle_factura (reg varchar[], OUT pCns integer) LANGUAGE plpgsql AS $$
DECLARE
    -- Parámetros
    vCantidad integer           := 0;
    vId_articulo integer        := 0;
    vId_factura integer         := 0;
    -- Auxiliares
    vMonto decimal(10, 2)       := 0;
    vCanAnterior integer        := 0;
    vId_articulo_venta integer  := 0;
    vTipo varchar               := NULL;
    vMessage varchar            := NULL;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 3 THEN
        RETURN;
    END IF;

    vCantidad := reg[1]::integer;
    vId_articulo := reg[2]::integer;
    vId_factura := reg[3]::integer;
    pCns := 0;

    RAISE NOTICE 'Parámetros: cantidad %, id_articulo %, id_factura %',
        vCantidad,
        vId_articulo,
        vId_factura;

    -- obtenemos el iPrecio del producto
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
    IF NOT FOUND THEN
        ROLLBACK;
        RAISE EXCEPTION 'ERROR: factura % no encontrada, operación invalida', vId_factura;
    END IF;

    -- verificamos si el a ya está dentro del detalle, también obtenemos la cantidad
    SELECT INTO pCns, vCanAnterior cns_detalle_factura, cantidad
    FROM detalle_factura
    WHERE id_factura = vId_factura
      AND id_articulo = vId_articulo;

    -- comprobamos si ya existe el a en el detalle
    IF pCns IS NOT NULL THEN
        -- la actualizamos si existe
        UPDATE detalle_factura
        SET cantidad = vCantidad,
            subtotal = vCantidad * vMonto
        WHERE id_factura = vId_factura
          AND cns_detalle_factura = pCns;

        -- aquí obtenemos la cantidad actualizada, positiva si aumento, negativa si disminuyo
        vCantidad = vCantidad - vCanAnterior;

        vMessage := 'UPDATE -> a ' || vId_articulo || ' en la factura ' || vId_factura || ' actualizada correctamente';
    ELSE
        -- de lo contrario lo insertamos
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

        vMessage := 'INSERT -> a ' || vId_articulo || ' en la factura ' || vId_factura || ' insertado correctamente';
    END IF;


    -- añadimos el iPrecio del articuloVenta a la factura
    UPDATE facturas_proveedor
    SET monto_total = monto_total + (vCantidad * vMonto)
    WHERE id_factura = vId_factura;
    IF NOT FOUND THEN
        ROLLBACK;
        RAISE EXCEPTION 'ERROR: subtotal de articuloVenta no añadido a la factura, procedimiento no realizada';
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
    ELSE
        -- actualizamos el stock
        UPDATE articulos_venta
        SET stock = stock + vCantidad
        WHERE id_articulo = vId_articulo;
        IF NOT FOUND THEN
            ROLLBACK;
            RAISE EXCEPTION 'ERROR: inventario no actualizado, operación invalida';
        END IF;
    END IF;

    RAISE NOTICE '%', vMessage;
    COMMIT;
END;
$$;


/**
  agrega o actualiza un pago de un ticket, se usa así ->
  3 parámetros: id del ticket, id de la forma de pago y el subtotal de compra
  4 parámetros: el id del ticket y el cns del pago, id de la forma de pago, subtotal de compra

  con 3 parámetros inserta un pago.
  con 4 parámetros oculta un pago.
 */

DROP PROCEDURE IF EXISTS agrPago;
CREATE OR REPLACE PROCEDURE agrPago (IN reg anyarray, OUT pCns integer) AS $$
DECLARE
    -- Parámetros
    vSubtotal decimal(10, 2)    := 0;
    vId_forma_pago integer      := 0;
    vId_ticket integer          := 0;
    --Auxiliares
    vComision decimal(10, 2)    := 0;
    vPago_total decimal(10, 2)  := 0;
    vTicket record              := NULL;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) = 2 THEN
        vId_ticket := reg[1]::integer;
        pCns := reg[2]::integer;
    ELSE
        IF array_length(reg, 1) = 3 THEN
            vId_ticket := reg[1]::integer;
            vSubtotal := reg[2]::decimal;
            vId_forma_pago := reg[3]::integer;
        ELSE
            RETURN;
        END IF;
    END IF;


    -- realizamos la transacción
    SELECT INTO vTicket *
    FROM tickets
    WHERE id_ticket = vId_ticket FOR UPDATE;


    IF pCns IS NOT NULL THEN
        -- ponemos el detalle en oculto
        UPDATE pagos
        SET active = NOT active
        WHERE id_ticket = vId_ticket
          AND cns_pago = pCns;
        -- obtenemos el subtotal
        SELECT INTO vSubtotal subtotal
        FROM pagos
        WHERE id_ticket = vId_ticket
          AND cns_pago = pCns;
        -- lo consideramos en el ticket
        IF (SELECT active
            FROM pagos
            WHERE id_ticket = vId_ticket
              AND cns_pago = pCns) THEN
            vSubtotal = vSubtotal * -1;
        END IF;
        UPDATE tickets
        SET pago_total = pago_total - vSubtotal;

        RAISE NOTICE 'pago actualizado correctamente';

        COMMIT;
        RETURN;
    END IF;


    IF vTicket.estatus = 'PAGADO' THEN
        RAISE NOTICE 'Ticket % ya pagado', vTicket.id_ticket;
        RETURN;
    END IF;


    -- obtenemos la comision según la forma de pago
    SELECT INTO vComision comision
    FROM formas_pago
    WHERE id_forma_pago = vId_forma_pago;
    vSubtotal = vSubtotal - vComision;


    -- consulta
    INSERT INTO pagos
    VALUES (
           vId_ticket,
           (SELECT count(*) + 1
            FROM pagos
            WHERE id_ticket = vId_ticket),
           vSubtotal,
           vId_forma_pago,
           DEFAULT)
    RETURNING cns_pago INTO pCns;
    IF pCns < 1 THEN
        ROLLBACK;
        RAISE EXCEPTION 'ERROR: Ticket de %:%, no insertado',
            vId_ticket,
            vId_forma_pago;
    END IF;


    vPago_total = vTicket.pago_total + vSubtotal;


    -- actualizamos los pagos del ticket
    UPDATE tickets
    SET pago_total = vPago_total
    WHERE id_ticket = vId_ticket;
    IF NOT FOUND THEN
        ROLLBACK;
        RAISE EXCEPTION 'ERROR: ticket no actualizado, no se realizo la inserción';
    END IF;


    IF vPago_total >= vTicket.monto_total THEN
        -- ponemos el ticket como pagado
        UPDATE tickets
        SET estatus = 'PAGADO'
        WHERE id_ticket = vId_ticket;
    END IF;


    RAISE NOTICE 'Pago de ticket %:% de id %, insertado correctamente',
        vId_ticket,
        vId_forma_pago,
        pCns;
    COMMIT;
END;
$$ LANGUAGE plpgsql;

/**
    añade un a al ticket de la venta
 */

DROP PROCEDURE IF EXISTS agrArticulo_ticket;
CREATE OR REPLACE PROCEDURE agrArticulo_ticket (IN reg anyarray, OUT pCns integer) AS $$
DECLARE
    -- Parámetros
    vId_ticket integer          := 0;
    vId_articulo integer        := 0;
    vCantidad integer           := 0;
    -- Auxiliares
    vMonto decimal(10, 2)       := 0;
    vCanAnterior integer        := 0;
    vSubtotal decimal(10, 2)    := 0;
    vMessage varchar            := NULL;
BEGIN
    -- preguntamos si el arreglo está vacío
    IF array_length(reg, 1) < 3 THEN
        RAISE NOTICE 'el arreglo debe de contener lo siguiente: cantidad, id_articulo, id_ticket';
    END IF;

    vId_ticket := reg[1]::integer;
    vId_articulo := reg[2]::integer;
    vCantidad := reg[3]::integer;


    -- obtenemos el iPrecio del articuloVenta
    SELECT INTO vMonto monto
    FROM articulos_venta
    WHERE id_articulo = vId_articulo;
    IF NOT FOUND THEN
        ROLLBACK;
        RAISE EXCEPTION 'ERROR: articuloVenta de id % no encontrado', vId_articulo;
    END IF;


    -- transacción
    PERFORM *
    FROM tickets
    WHERE id_ticket = vId_ticket FOR UPDATE;
    -- bloqueamos el a (por el stock)
    PERFORM *
    FROM articulos_venta
    WHERE id_articulo = vId_articulo FOR UPDATE;


    SELECT INTO pCns, vCanAnterior cns_detalle_ticket, cantidad
    FROM detalle_ticket
    WHERE id_ticket = vId_ticket
      AND id_articulo = vId_articulo;


    -- comprobamos si el a ya está en la factura
    IF pCns IS NOT NULL THEN
        -- actualizamos el detalle
        UPDATE detalle_ticket
        SET cantidad = vCantidad,
            subtotal = vCantidad * vMonto
        WHERE id_ticket = vId_ticket
          AND cns_detalle_ticket = pCns;

        -- actualizamos la cantidad
        vCantidad := vCanAnterior - vCantidad;

        vMessage := 'UPDATE -> a ' || vId_articulo || ' actualizado en la compra % ' || vId_ticket || ' correctamente';
    ELSE
        -- insertamos
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

        vMessage := 'INSERT -> a ' || vId_articulo || ' añadido a la compra % ' || vId_ticket || ' correctamente';
    END IF;


    -- calculamos el subtotal
    vSubtotal := vCantidad * vMonto;


    -- actualizamos el subtotal al ticket
    UPDATE tickets
    SET monto_total = monto_total + vSubtotal
    WHERE id_ticket = vId_ticket;
    IF vId_ticket = 0 THEN
        ROLLBACK;
        RAISE EXCEPTION 'ERROR: Ticket de %:%, no insertado', vId_articulo, vId_ticket;
    END IF;

    RAISE NOTICE 'av: %, can %, pre %, sub %', vId_articulo, vCantidad, vMonto, vSubtotal;

    -- actualizamos la venta del a al inventario
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

-- obtiene los articulos de una factura según la fecha de entrega y el proveedor que la trajo
CREATE OR REPLACE FUNCTION obtArticulos_factura(
pId_factura integer,
pId_proveedor integer)
    RETURNS refcursor LANGUAGE plpgsql AS $$
DECLARE
    refCrs refcursor;
BEGIN
    OPEN refcrs FOR SELECT f.cns_detalle_factura as cns,
                           ap.nombre,
                           coalesce(f.cantidad, 0) as cantidad,
                           ap.monto_compra,
                           coalesce(f.subtotal, 0) as subtotal,
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
                             LEFT JOIN (SELECT fp.id_factura,
                                               df.cns_detalle_factura,
                                               fp.id_proveedor,
                                               df.cantidad,
                                               df.subtotal,
                                               df.id_articulo
                                        FROM detalle_factura df
                                                 INNER JOIN facturas_proveedor fp ON
                                                fp.id_factura = df.id_factura
                                        WHERE df.id_factura = pId_factura) f
                                       ON ap.id_articulo = f.id_articulo
                                           AND ap.id_proveedor = f.id_proveedor
                             LEFT JOIN alimentos a
                                       ON ap.id_articulo = a.id_articulo
                             LEFT JOIN productos p
                                       ON ap.id_articulo = p.id_articulo
                             LEFT JOIN medicamentos m
                                       ON ap.id_articulo = m.id_articulo
                    WHERE ap.id_proveedor = pId_proveedor;

    RAISE NOTICE 'cursor abierto correctamente';
    RETURN refCrs;
END;
$$;

CREATE OR REPLACE FUNCTION obtDetalle_cita(pId_mascota int) RETURNS refcursor AS $body$
DECLARE
    refCrs refcursor;
BEGIN
    OPEN refCrs FOR SELECT ma.nombre as mascota,
                           a.nombre as articulo,
                           av.monto as costo,
                           p2.nombre as proveedor,
                           r.nombre as raza,
                           sum(dt.cantidad) as cantidad,
                           CASE
                               WHEN al.id_articulo IS NOT NULL THEN
                                   'ALIMENTO'
                               WHEN p.id_articulo IS NOT NULL THEN
                                   'PRODUCTO'
                               WHEN m.id_articulo IS NOT NULL THEN
                                   'MEDICAMENTO'
                               END AS tipo_articulo
                    FROM citas c
                             INNER JOIN tickets t
                                        ON t.id_ticket = c.id_ticket
                             RIGHT JOIN detalle_ticket dt
                                        ON t.id_ticket = dt.id_ticket
                             INNER JOIN articulos a
                                        ON dt.id_articulo = a.id_articulo
                             LEFT JOIN alimentos al
                                       ON a.id_articulo = al.id_articulo
                             LEFT JOIN productos p
                                       ON a.id_articulo = p.id_articulo
                             LEFT JOIN medicamentos m
                                       ON a.id_articulo = m.id_articulo
                             INNER JOIN mascotas ma
                                        ON ma.id_mascota = c.id_mascota
                             INNER JOIN proveedores p2
                                        ON p2.id_proveedor = a.id_proveedor
                             INNER JOIN articulos_venta av
                                        ON a.id_articulo = av.id_articulo
                             INNER JOIN razas r ON
                            ma.id_raza = r.id_raza
                    WHERE ma.id_mascota = pId_mascota
                    GROUP BY mascota,
                             articulo,
                             costo,
                             proveedor,
                             tipo_articulo,
                             r.nombre
                    ORDER BY cantidad DESC;

    RAISE NOTICE 'cursor abierto correctamente';
    RETURN refCrs;
END;
$body$ LANGUAGE plpgsql;
