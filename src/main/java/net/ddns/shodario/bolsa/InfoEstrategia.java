package net.ddns.shodario.bolsa;

import java.text.DecimalFormat;

public class InfoEstrategia {
    private DecimalFormat decimal = new DecimalFormat("#.##");
    private DecimalFormat porcentaje = new DecimalFormat("#.##'%'");

    private String nombreAccion;
    private Double precioAccion;

    private String nombreCallComprada;
    private Integer mesCallComprada;
    private Double baseCallComprada;
    private Double valorExtrinsecoCallComprada;
    private Integer cantidadVenta;
    private Double precioVenta;

    private String nombreCallVendida;
    private Integer mesCallVendida;
    private Double baseCallVendida;
    private Double valorExtrinsecoCallVendida;
    private Integer cantidadCompra;
    private Double precioCompra;

    private Double gananciaVariacion;
    private Double perdidaVariacion;
    private Double compraCall;
    private Double ventaCall;
    private Double inversion;
    private Double gananciaBruta;
    private Double gananciaNeta;
    private Double gananciaNetaPorcentual;

    public InfoEstrategia(String nombreAccion, Double precioAccion, String nombreCallComprada, Integer mesCallComprada, Double baseCallComprada, Double valorExtrinsecoCallComprada, Integer cantidadVenta, Double precioVenta, String nombreCallVendida, Integer mesCallVendida, Double baseCallVendida, Double valorExtrinsecoCallVendida, Integer cantidadCompra, Double precioCompra, Double gananciaVariacion, Double perdidaVariacion, Double compraCall, Double ventaCall, Double inversion, Double gananciaBruta, Double gananciaNeta, Double gananciaNetaPorcentual) {
        this.nombreAccion = nombreAccion;
        this.precioAccion = precioAccion;
        this.nombreCallComprada = nombreCallComprada;
        this.mesCallComprada = mesCallComprada;
        this.baseCallComprada = baseCallComprada;
        this.valorExtrinsecoCallComprada = valorExtrinsecoCallComprada;
        this.cantidadVenta = cantidadVenta;
        this.precioVenta = precioVenta;
        this.nombreCallVendida = nombreCallVendida;
        this.mesCallVendida = mesCallVendida;
        this.baseCallVendida = baseCallVendida;
        this.valorExtrinsecoCallVendida = valorExtrinsecoCallVendida;
        this.cantidadCompra = cantidadCompra;
        this.precioCompra = precioCompra;
        this.gananciaVariacion = gananciaVariacion;
        this.perdidaVariacion = perdidaVariacion;
        this.compraCall = compraCall;
        this.ventaCall = ventaCall;
        this.inversion = inversion;
        this.gananciaBruta = gananciaBruta;
        this.gananciaNeta = gananciaNeta;
        this.gananciaNetaPorcentual = gananciaNetaPorcentual;
    }

    public String getNombreAccion() {
        return nombreAccion;
    }

    public Double getPrecioAccion() {
        return precioAccion;
    }

    public String getNombreCallComprada() {
        return nombreCallComprada;
    }

    public Integer getMesCallComprada() {
        return mesCallComprada;
    }

    public Double getBaseCallComprada() {
        return baseCallComprada;
    }

    public Double getValorExtrinsecoCallComprada() {
        return valorExtrinsecoCallComprada;
    }

    public Integer getCantidadVenta() {
        return cantidadVenta;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public String getNombreCallVendida() {
        return nombreCallVendida;
    }

    public Integer getMesCallVendida() {
        return mesCallVendida;
    }

    public Double getBaseCallVendida() {
        return baseCallVendida;
    }

    public Double getValorExtrinsecoCallVendida() {
        return valorExtrinsecoCallVendida;
    }

    public Integer getCantidadCompra() {
        return cantidadCompra;
    }

    public Double getPrecioCompra() {
        return precioCompra;
    }

    public Double getGananciaVariacion() {
        return gananciaVariacion;
    }

    public Double getPerdidaVariacion() {
        return perdidaVariacion;
    }

    public Double getCompraCall() {
        return compraCall;
    }

    public Double getVentaCall() {
        return ventaCall;
    }

    public Double getInversion() {
        return inversion;
    }

    public Double getGananciaBruta() {
        return gananciaBruta;
    }

    public Double getGananciaNeta() {
        return gananciaNeta;
    }

    public Double getGananciaNetaPorcentual() {
        return gananciaNetaPorcentual;
    }

    @Override
    public String toString() {
        return "InfoEstrategia{" +
                "nombreAccion='" + nombreAccion + '\'' +
                ", precioAccion=" + precioAccion +
                ", nombreCallComprada='" + nombreCallComprada + '\'' +
                ", mesCallComprada=" + mesCallComprada +
                ", baseCallComprada=" + baseCallComprada +
                ", cantidadVenta=" + cantidadVenta +
                ", precioVenta=" + precioVenta +
                ", valorExtrinsecoCallComprada=" + porcentaje.format(valorExtrinsecoCallComprada) +
                ", nombreCallVendida='" + nombreCallVendida + '\'' +
                ", mesCallVendida=" + mesCallVendida +
                ", baseCallVendida=" + baseCallVendida +
                ", cantidadCompra=" + cantidadCompra +
                ", precioCompra=" + precioCompra +
                ", valorExtrinsecoCallVendida=" + porcentaje.format(valorExtrinsecoCallVendida) +
                ", gananciaVariacion=" + porcentaje.format(gananciaVariacion) +
                ", perdidaVariacion=" + porcentaje.format(perdidaVariacion) +
                ", compraCall=" + decimal.format(compraCall) +
                ", ventaCall=" + decimal.format(ventaCall) +
                ", inversion=" + decimal.format(inversion) +
                ", gananciaBruta=" + decimal.format(gananciaBruta) +
                ", gananciaNeta=" + decimal.format(gananciaNeta) +
                ", gananciaNetaPorcentual=" + porcentaje.format(gananciaNetaPorcentual) +
                '}';
    }
}
