
-- obtener un detalle de la mascota, considera la cantidad de articulos que ha comprado
SELECT ma.nombre as mascota,
       a.nombre as articulo,
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
GROUP BY ma.nombre, a.nombre, al.id_articulo, p.id_articulo, m.id_articulo
ORDER BY cantidad DESC;