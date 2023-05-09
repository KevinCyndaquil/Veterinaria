-- TRIGGERS VETERINARIA VIDA

DROP TRIGGER IF EXISTS tgrProcesoDetalle_factura
    ON detalle_factura
    CASCADE;

CREATE TRIGGER tgrProcesoDetalle_factura
    BEFORE INSERT OR UPDATE ON detalle_factura
    FOR EACH ROW EXECUTE PROCEDURE procesodetalle_factura();

DROP TRIGGER IF EXISTS trgProcesoPagos
    ON pagos
    CASCADE;

CREATE TRIGGER trgProcesoPagos
    BEFORE INSERT OR UPDATE ON pagos
    FOR EACH ROW EXECUTE PROCEDURE procesoPagos();

DROP TRIGGER IF EXISTS trgProcesoDetalle_ticket
ON detalle_ticket
CASCADE;

CREATE TRIGGER trgProcesoDetalle_ticket
    BEFORE INSERT OR UPDATE ON detalle_ticket
    FOR EACH ROW EXECUTE PROCEDURE procesoDetalle_ticket();

DROP TRIGGER IF EXISTS trgProcesoCitas
    ON citas
    CASCADE;

CREATE TRIGGER trgProcesoCitas
    BEFORE INSERT ON citas
    FOR EACH ROW EXECUTE PROCEDURE procesoCitas();