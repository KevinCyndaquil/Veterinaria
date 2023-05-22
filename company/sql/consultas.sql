
-- obtener un detalle de la mascota, considera la cantidad de articulos que ha comprado
SELECT ma.nombre as mascota,
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
GROUP BY mascota,
         articulo,
         costo,
         proveedor,
         tipo_articulo,
         r.nombre
ORDER BY cantidad DESC;


-- obtener un detalle de la mascota, este es más detallado
SELECT ma.nombre as mascota,
       a.nombre as articulo,
       dt.cantidad,
       t.fecha_cobro as fecha_pago,
       t.hora_cobro as hora_pago,
       c.fecha_cita,
       c.hora as hora_cita,
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
                    ON ma.id_mascota = c.id_mascota;


-- obtiene un detalle de factura con todos los articulos en ella
SELECT f.cns_detalle_factura as cns,
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
            WHERE df.id_factura = 120) f
    ON ap.id_articulo = f.id_articulo
           AND ap.id_proveedor = f.id_proveedor
LEFT JOIN alimentos a
          ON ap.id_articulo = a.id_articulo
LEFT JOIN productos p
          ON ap.id_articulo = p.id_articulo
LEFT JOIN medicamentos m
          ON ap.id_articulo = m.id_articulo
WHERE ap.id_proveedor = 7;

SELECT *
FROM articulos_venta;