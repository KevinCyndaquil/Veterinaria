-- PROCEDIMIENTOS ALMACENADOS VETERINARIA VIDA

-- realiza el proceso de insertar un a a la factura, considerando lo siguiente:
-- 1. se debe insertar o actualizar el subtotal al monto total de la factura.
-- 2. una vez ingresa el a a la factura, hay que ponerlo en venta-
-- 3. se debe insertar o actualizar el stock de los articulos en venta.

CREATE OR REPLACE FUNCTION procesoDetalle_factura() RETURNS trigger AS $body$
DECLARE
    vId_proveedor integer;
    vMonto decimal(10, 2);
    vCantidad integer;
    vTipo varchar;
BEGIN
    RAISE NOTICE 'valores: cantidad %, id_articulo %, id_factura %, cns %',
        new.cantidad,
        new.id_articulo,
        new.id_factura,
        new.cns_detalle_factura;

    -- obtenemos el precio del producto
    SELECT INTO vMonto, vId_proveedor monto_compra, id_proveedor
    FROM articulos
    WHERE id_articulo = new.id_articulo;
    IF NOT FOUND THEN
        RAISE EXCEPTION 'ERROR: articulo de id % no valido', new.id_articulo;
    END IF;

    IF (SELECT id_proveedor
        FROM facturas_proveedor
        WHERE id_factura = new.id_factura) != vId_proveedor THEN

        RAISE EXCEPTION 'ERROR; articulo % de proveedor % no valido para la factura %',
            new.id_articulo,
            vId_proveedor,
            new.id_factura;
    END IF;


    -- seleccionamos la factura a actualizar y la bloqueamos
    PERFORM *
    FROM facturas_proveedor
    WHERE id_factura = new.id_factura FOR UPDATE; -- transacción
    IF NOT FOUND THEN
        RAISE EXCEPTION 'ERROR: factura % no encontrada, operación invalida', new.id_factura;
    END IF;

    -- inserción
    IF old ISNULL THEN
        RAISE NOTICE '*inserción*';

        new.cns_detalle_factura := (SELECT count(*) + 1
                                    FROM detalle_factura
                                    WHERE id_factura = new.id_factura);

        vCantidad := new.cantidad;
        -- ingresamos el subtotal
        new.subtotal := new.cantidad * vMonto;
    END IF;

    -- actualización
    IF old IS NOT NULL THEN
        RAISE NOTICE '*actualización*';

        vCantidad = new.cantidad - old.cantidad;
    END IF;

    RAISE NOTICE 'can: %, mon %', vCantidad, vMonto;

    new.subtotal := new.cantidad * vMonto;

    RAISE NOTICE 'subtotal: %', new.subtotal;

    -- añadimos el precio del articuloVenta a la factura
    UPDATE facturas_proveedor
    SET monto_total = monto_total + (vCantidad * vMonto)
    WHERE id_factura = new.id_factura;
    IF NOT FOUND THEN
        RAISE EXCEPTION 'ERROR: subtotal de articuloVenta no añadido a la factura, procedimiento no realizada';
    END IF;


    -- obtenemos el tipo del producto
    CASE
        WHEN exists(SELECT *
                    FROM alimentos
                    WHERE alimentos.id_articulo = new.id_articulo) THEN
            vTipo := 'alimento';
        WHEN exists(SELECT *
                    FROM medicamentos
                    WHERE medicamentos.id_articulo = new.id_articulo) THEN
            vTipo := 'medicamento';
        WHEN exists(SELECT *
                    FROM productos
                    WHERE productos.id_articulo = new.id_articulo) THEN
            vTipo := 'producto';
    END CASE;


    -- añadimos los articuloVendidos a la venta
    IF NOT exists( SELECT *
                   FROM articulos_venta
                   WHERE id_articulo = new.id_articulo ) THEN
        -- insertamos el nuevo articuloVenta a la venta
        INSERT INTO articulos_venta
        VALUES (
               new.id_articulo,
               vMonto,
               vCantidad,
               vTipo);
    ELSE
        -- actualizamos el stock
        UPDATE articulos_venta
        SET stock = stock + vCantidad
        WHERE id_articulo = new.id_articulo;
        IF NOT FOUND THEN
            RAISE EXCEPTION 'ERROR: inventario no actualizado, operación invalida';
        END IF;
    END IF;

    RAISE NOTICE 'a %, añadidos % a la factura % exitosamente',
        new.id_articulo,
        new.cantidad,
        new.id_factura;

    RETURN new;
END;
$body$ LANGUAGE plpgsql;



-- agrega un a a un ticket de una compra, hay que considerar lo siguiente:
-- 1. el subtotal del detalle debe verse reflejado en el monto del ticket.
-- 2. hay que considerar el stock para poder vender un a.
-- 3. hay que restar la venta al stock del a.

CREATE OR REPLACE FUNCTION procesoDetalle_ticket() RETURNS trigger AS $body$
DECLARE
    vMonto_articulo decimal(10, 2);
    vCantidad integer;
BEGIN
    RAISE NOTICE 'valores: subtotal %, cantidad %, id_articulo %, id_ticket %, cns %',
        new.subtotal,
        new.cantidad,
        new.id_articulo,
        new.id_ticket,
        new.cns_detalle_ticket;

    -- bloqueamos la fila de articulos
    PERFORM *
    FROM articulos_venta
    WHERE id_articulo = new.id_articulo;


    SELECT INTO vMonto_articulo monto
    FROM articulos_venta
    WHERE id_articulo = new.id_articulo
      AND stock > 0;
    -- verificamos el stock
    IF NOT FOUND THEN
        RAISE EXCEPTION 'a % sin existencias, no se puede realizar la venta', new.id_articulo;
    END IF;

    -- obtenemos el subtotal
    new.subtotal = new.cantidad * vMonto_articulo;

    -- inserción
    IF old ISNULL THEN
        -- obtenemos el consecutivo
        new.cns_detalle_ticket = (SELECT count(*) + 1
                                  FROM detalle_ticket
                                  WHERE id_ticket = new.id_ticket);

        -- obtenemos la cantidad
        vCantidad = new.cantidad;
    END IF;

    -- actualización
    IF  old IS NOT NULL THEN
        vCantidad = new.cantidad - old.cantidad;
    END IF;

    -- actualizamos el ticket
    UPDATE tickets
    SET monto_total = monto_total + (vCantidad * vMonto_articulo)
    WHERE id_ticket = new.id_ticket;

    -- actualizamos el stock de la venta
    UPDATE articulos_venta
    SET stock = stock - vCantidad
    WHERE id_articulo = new.id_articulo;

    RAISE NOTICE 'a %, añadidos % al ticket % correctamente',
        new.id_articulo,
        new.cantidad,
        new.id_ticket;

    RETURN new;
END;
$body$ LANGUAGE plpgsql;


-- realiza un pago a un ticket, considerando lo siguiente:
-- 1. el subtotal del pago debe verse reflejado en el ticket.
-- 2. un pago tiene una comision si se realiza de alguna manera en especifica.
-- 3. hay que considerar si el ticket ya está pagado para seguir metiendo pagos.
-- 4. una vez pagado, hay que ponerlo en un estatus de pagado.

CREATE OR REPLACE FUNCTION procesoPagos() RETURNS trigger AS $body$
DECLARE
    vComision decimal(10, 2);
    vMonto_total decimal(10, 2);
    vPago_total decimal(10, 2);
BEGIN
    RAISE NOTICE 'valores: subtotal %, id_forma_pago %, id_ticket %, cns %',
        new.subtotal,
        new.id_forma_pago,
        new.id_ticket,
        new.cns_pago;

    -- realizamos la transacción
    PERFORM *
    FROM tickets
    WHERE id_ticket = new.id_ticket FOR UPDATE;

    IF (SELECT estatus
        FROM tickets
        WHERE id_ticket = new.id_ticket) LIKE 'pagado' THEN

        RAISE EXCEPTION 'ticket % ya pagado, no se permiten más pagos', new.id_ticket;
    END IF;

    -- obtenemos el monto_total
    SELECT INTO vMonto_total, vPago_total monto_total, pago_total
    FROM tickets
    WHERE id_ticket = new.id_ticket;

    -- obtenemos la comision según la forma de pago
    SELECT INTO vComision comision
    FROM formas_pago
    WHERE id_forma_pago = new.id_forma_pago;

    -- aqui consideramos la comision
    new.subtotal := new.subtotal - vComision;

    -- inserción
    IF old ISNULL THEN
        -- aquí obtenemos el consecutivo
        new.cns_pago := (SELECT count(*) + 1
                        FROM pagos
                        WHERE id_ticket = new.id_ticket);
        -- obtenemos el pago total
        vPago_total = vPago_total + new.subtotal;
    END IF;

    -- actualización
    IF  old IS NOT NULL THEN
        -- obtenemos el pago total
        vPago_total = vPago_total - new.subtotal;
    END IF;

    RAISE NOTICE 'subtotal -> %', new.subtotal;

    -- actualizamos los tickets
    UPDATE tickets
    SET pago_total = vPago_total
    WHERE id_ticket = new.id_ticket;
    IF NOT FOUND THEN
        RAISE EXCEPTION 'ERROR: ticket no actualizado, no se realizo la inserción';
    END IF;

    IF vPago_total >= vMonto_total THEN
        -- ponemos el ticket como pagado
        UPDATE tickets
        SET estatus = 'pagado'
        WHERE id_ticket = new.id_ticket;

        RAISE NOTICE 'ticket %s PAGADO', new.id_ticket;
    END IF;

    RAISE NOTICE 'pago de % reflejado exitosamente', new.subtotal;

    RETURN new;
END;
$body$ LANGUAGE plpgsql;


-- realiza una cita para la veterinaria vida, hay que considerar lo siguiente:
-- 1. el monto de la cita tiene que verse reflejado en el ticket
-- 2. si el ticket no está creado, hay que crearlo ahora.

CREATE OR REPLACE FUNCTION procesoCitas() RETURNS trigger AS $body$
BEGIN
    RAISE NOTICE 'valores: id_cita %, fecha %, hora %, detalle %, monto %, id_mascota %, id_veterinario %, id_ticket %, estatus %',
        new.id_cita,
        new.fecha_cita,
        new.hora,
        new.detalle,
        new.monto_cita,
        new.id_mascota,
        new.id_veterinario,
        new.id_ticket,
        new.estatus;

    -- confirmamos que el empleado sea un veterinario
    IF (SELECT puesto FROM empleados WHERE id_persona = new.id_veterinario) NOT LIKE 'veterinario'THEN
        RAISE EXCEPTION 'empleado % no es un veterinario, no está capacitado para dar consultas', new.id_veterinario;
    END IF;

    IF new.id_ticket IS NULL THEN
        -- creamos el ticket
        INSERT INTO tickets
        VALUES (DEFAULT,new.monto_cita,DEFAULT,now()::date,now()::time,DEFAULT)
        RETURNING id_ticket INTO new.id_ticket;
    END IF;

    RAISE NOTICE 'cita insertada correctamete y agendada para %/% ',
        new.fecha_cita,
        new.hora;

    RETURN new;
END;
$body$ LANGUAGE plpgsql;



INSERT INTO detalle_factura VALUES (1, 0, 10, 0, 1);
INSERT INTO detalle_factura VALUES (1, 0, 5, 0, 2);
INSERT INTO detalle_factura VALUES (1, 0, 2, 0, 3);
DELETE FROM detalle_ticket WHERE id_ticket = 1;
UPDATE tickets SET monto_total = 0, pago_total = 0, estatus = 'pendiente' WHERE id_ticket = 1;

INSERT INTO detalle_ticket VALUES (1, 0, 10, 0, 1);
INSERT INTO detalle_ticket VALUES (1, 0, 1, 0, 2);
INSERT INTO detalle_ticket VALUES (1, 0, 1, 0, 3);

INSERT INTO citas VALUES (DEFAULT,now()::date,'14:00:00'::time,'',15000,1,1,NULL,DEFAULT);