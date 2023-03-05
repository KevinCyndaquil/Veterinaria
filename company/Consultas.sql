--- OBTENER TODOS LOS ARTÍCULOS QUE VINIERON EN UNA ENTREGA

SELECT
    a.id_factura,
    a.id_alimento,
    a.cantidad,
    a.subtotal,
    m.id_medicamento,
    m.cantidad,
    m.subtotal,
    p.id_producto,
    p.cantidad,
    p.subtotal
FROM
    alimentos_factura a,
    medicamentos_factura m,
    productos_factura p
WHERE
    a.id_factura = 1
AND
    p.id_factura = 1
AND
    m.id_factura = 1;