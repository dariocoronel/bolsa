package net.ddns.shodario.bolsa;

import java.time.LocalDateTime;

public class Operacion {
    LocalDateTime fecha;
    Double precio;

    Double smaShort, smaLong;
    Double macdFast, macdSlow, macd, macdSignal, macdHistogram;
    boolean senalCompra, senalVenta;

    Operacion(LocalDateTime fecha, Double precio) {
        this.fecha = fecha;
        this.precio = precio;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public Double getPrecio() {
        return precio;
    }

    public Boolean getSenalCompra() {
        return senalCompra;
    }
}
