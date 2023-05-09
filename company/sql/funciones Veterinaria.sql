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
    IF NOT FOUND THEN
        ROLLBACK;
        RAISE EXCEPTION 'ERROR: factura % no encontrada, operación invalida', vId_factura;
    END IF;

    -- verificamos si el articulo ya está dentro del detalle, también obtenemos la cantidad
    SELECT INTO pCns, vCanAnterior cns_detalle_factura, cantidad
    FROM detalle_factura
    WHERE id_factura = vId_factura
      AND id_articulo = vId_articulo;

    -- comprobamos si ya existe el articulo en el detalle
    IF pCns IS NOT NULL THEN
        -- la actualizamos si existe
        UPDATE detalle_factura
        SET cantidad = vCantidad,
            subtotal = vCantidad * vMonto
        WHERE id_factura = vId_factura
          AND cns_detalle_factura = pCns;

        -- aquí obtenemos la cantidad actualizada, positiva si aumento, negativa si disminuyo
        vCantidad = vCantidad - vCanAnterior;

        vMessage := 'UPDATE -> articulo ' || vId_articulo || ' en la factura ' || vId_factura || ' actualizada correctamente';
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

        vMessage := 'INSERT -> articulo ' || vId_articulo || ' en la factura ' || vId_factura || ' insertado correctamente';
    END IF;


    -- añadimos el precio del articuloVenta a la factura
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
    añade un articulo al ticket de la venta
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


    -- obtenemos el precio del articuloVenta
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
    -- bloqueamos el articulo (por el stock)
    PERFORM *
    FROM articulos_venta
    WHERE id_articulo = vId_articulo FOR UPDATE;


    SELECT INTO pCns, vCanAnterior cns_detalle_ticket, cantidad
    FROM detalle_ticket
    WHERE id_ticket = vId_ticket
      AND id_articulo = vId_articulo;


    -- comprobamos si el articulo ya está en la factura
    IF pCns IS NOT NULL THEN
        -- actualizamos el detalle
        UPDATE detalle_ticket
        SET cantidad = vCantidad,
            subtotal = vCantidad * vMonto
        WHERE id_ticket = vId_ticket
          AND cns_detalle_ticket = pCns;

        -- actualizamos la cantidad
        vCantidad := vCanAnterior - vCantidad;

        vMessage := 'UPDATE -> articulo ' || vId_articulo || ' actualizado en la compra % ' || vId_ticket || ' correctamente';
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

        vMessage := 'INSERT -> articulo ' || vId_articulo || ' añadido a la compra % ' || vId_ticket || ' correctamente';
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

    -- actualizamos la venta del articulo al inventario
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
pId_factura integer)
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
                                        WHERE df.id_factura = pId_factura) df
                                       ON ap.id_articulo = df.id_articulo
                             LEFT JOIN alimentos a
                                       ON ap.id_articulo = a.id_articulo
                             LEFT JOIN productos p
                                       ON ap.id_articulo = p.id_articulo
                             LEFT JOIN medicamentos m
                                       ON ap.id_articulo = m.id_articulo
                    --WHERE id_factura = pId_factura
                    ORDER BY tipo;

    RAISE NOTICE 'cursor abierto correctamente';
    RETURN refCrs;
END;
$$;