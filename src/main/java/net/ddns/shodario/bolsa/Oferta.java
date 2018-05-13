package net.ddns.shodario.bolsa;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

public class Oferta {
    LocalDateTime fecha;
    Integer cantidadCompra;
    Double precioCompra;
    Integer cantidadVenta;
    Double precioVenta;

    Oferta(LocalDateTime fecha, Integer cantidadCompra, Integer cantidadVenta, Double precioCompra, Double precioVenta) {
        this.fecha = fecha;
        this.cantidadCompra = cantidadCompra;
        this.precioCompra = precioCompra;
        this.cantidadVenta = cantidadVenta;
        this.precioVenta = precioVenta;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public Double getPrecioCompra() {
        return precioCompra;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }
}
