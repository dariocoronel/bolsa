package net.ddns.shodario.bolsa;

import java.util.Objects;

public class Estrategia {
    private final static double COM = 0.01;

    private Accion accion;
    private Opcion opcionComprada;
    private Opcion opcionVendida;
    private Double precioAccion;
    private Integer cantidadVenta;
    private Double precioVenta;
    private Integer cantidadCompra;
    private Double precioCompra;
    private InfoEstrategia infoEstrategia;

    Estrategia(Accion accion, Opcion opcionComprada, Opcion opcionVendida) {
        this.accion = accion;
        this.opcionComprada = opcionComprada;
        this.opcionVendida = opcionVendida;
    }

    public Accion getAccion() {
        return accion;
    }

    public Opcion getOpcionComprada() {
        return opcionComprada;
    }

    public Opcion getOpcionVendida() {
        return opcionVendida;
    }

    InfoEstrategia getInfoEstrategia() {
        if (infoEstrategia == null ||
                !accion.getPrecioUltimo().equals(precioAccion) ||
                !opcionVendida.getUltimaOferta().cantidadCompra.equals(cantidadCompra) ||
                !opcionVendida.getUltimaOferta().precioCompra.equals(precioCompra) ||
                !opcionComprada.getUltimaOferta().cantidadVenta.equals(cantidadVenta) ||
                !opcionComprada.getUltimaOferta().precioVenta.equals(precioVenta)) {

            precioAccion = accion.getPrecioUltimo();
            cantidadCompra = opcionVendida.getUltimaOferta().cantidadCompra;
            precioCompra = opcionVendida.getUltimaOferta().precioCompra;
            cantidadVenta = opcionComprada.getUltimaOferta().cantidadVenta;
            precioVenta = opcionComprada.getUltimaOferta().precioVenta;
            actualizarInfoEstrategia();
        }

        return infoEstrategia;
    }

    private void actualizarInfoEstrategia() {
        Double baseCallComprada = opcionComprada.getBase();
        Double baseCallVendida = opcionVendida.getBase();
        Double valorExtrinsecoCallComprada = null;
        Double valorExtrinsecoCallVendida = null;
        Double gananciaVariacion = null;
        Double perdidaVariacion = null;
        Double compraCall;
        Double ventaCall;
        Double inversion;
        Double gananciaBruta;
        Double gananciaNeta;
        Double gananciaNetaPorcentual;

        if (precioAccion != null) {

            if (cantidadVenta > 0) {
                if (precioAccion <= baseCallComprada)
                    valorExtrinsecoCallComprada = precioVenta / precioAccion * 100;
                else
                    valorExtrinsecoCallComprada = (precioVenta - (precioAccion - baseCallComprada)) / precioAccion * 100;
            }

            if (cantidadCompra > 0) {
                if (precioAccion <= baseCallVendida)
                    valorExtrinsecoCallVendida = precioCompra / precioAccion * 100;
                else
                    valorExtrinsecoCallVendida = (precioCompra - (precioAccion - baseCallVendida)) / precioAccion * 100;
            }

            gananciaVariacion = (baseCallVendida / precioAccion - 1) * 100;
            perdidaVariacion = (baseCallComprada / precioAccion - 1) * 100;
        }


        if (cantidadVenta > 0 && cantidadCompra > 0) {
            compraCall = -precioVenta * 100 * (1 + COM);
            ventaCall = precioCompra * 100 * (1 - COM);
            inversion = compraCall + ventaCall;
            gananciaBruta = ((baseCallVendida - baseCallComprada) * 100 * (1 - COM));
            gananciaNeta = inversion + gananciaBruta;

            if (inversion < 0)
                gananciaNetaPorcentual = gananciaNeta / (-inversion) * 100;
            else
                gananciaNetaPorcentual = 999999d;

            this.infoEstrategia = new InfoEstrategia(
                    accion.getNombre(),
                    precioAccion,
                    opcionComprada.getNombre(),
                    opcionComprada.getMes(),
                    opcionComprada.getBase(),
                    valorExtrinsecoCallComprada,
                    cantidadVenta,
                    precioVenta,
                    opcionVendida.getNombre(),
                    opcionVendida.getMes(),
                    opcionVendida.getBase(),
                    valorExtrinsecoCallVendida,
                    cantidadCompra,
                    precioCompra,
                    gananciaVariacion,
                    perdidaVariacion,
                    compraCall,
                    ventaCall,
                    inversion,
                    gananciaBruta,
                    gananciaNeta,
                    gananciaNetaPorcentual);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estrategia that = (Estrategia) o;
        return Objects.equals(accion, that.accion) &&
                Objects.equals(opcionComprada, that.opcionComprada) &&
                Objects.equals(opcionVendida, that.opcionVendida);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accion, opcionComprada, opcionVendida);
    }

    @Override
    public String toString() {
        return "Estrategia{" +
                "accion=" + accion +
                ", opcionComprada=" + opcionComprada +
                ", opcionVendida=" + opcionVendida +
                '}';
    }
}
