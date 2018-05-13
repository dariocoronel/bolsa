package net.ddns.shodario.bolsa;

import java.time.LocalDateTime;

public class Dato {
    private LocalDateTime fecha;
    private Integer horaEmisionNum;
    private String simbolo;
    private TipoActivo tipoActivo;
    private Integer vencimientoId;
    private Integer cantidadCompra;
    private Integer cantidadVenta;
    private Double precioCompra;
    private Double precioVenta;
    private Double precioUltimo;
    private Integer volumenOperado;
    private Double montoOperado;
    private Integer cantidadOperaciones;

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Integer getHoraEmisionNum() {
        return horaEmisionNum;
    }

    public void setHoraEmisionNum(Integer horaEmisionNum) {
        this.horaEmisionNum = horaEmisionNum;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public TipoActivo getTipoActivo() {
        return tipoActivo;
    }

    public void setTipoActivo(TipoActivo tipoActivo) {
        this.tipoActivo = tipoActivo;
    }

    public Integer getVencimientoId() {
        return vencimientoId;
    }

    public void setVencimientoId(Integer vencimientoId) {
        this.vencimientoId = vencimientoId;
    }

    public Integer getCantidadCompra() {
        return cantidadCompra;
    }

    public void setCantidadCompra(Integer cantidadCompra) {
        this.cantidadCompra = cantidadCompra;
    }

    public Integer getCantidadVenta() {
        return cantidadVenta;
    }

    public void setCantidadVenta(Integer cantidadVenta) {
        this.cantidadVenta = cantidadVenta;
    }

    public Double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(Double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Double getPrecioUltimo() {
        return precioUltimo;
    }

    public void setPrecioUltimo(Double precioUltimo) {
        this.precioUltimo = precioUltimo;
    }

    public Integer getVolumenOperado() {
        return volumenOperado;
    }

    public void setVolumenOperado(Integer volumenOperado) {
        this.volumenOperado = volumenOperado;
    }

    public Double getMontoOperado() {
        return montoOperado;
    }

    public void setMontoOperado(Double montoOperado) {
        this.montoOperado = montoOperado;
    }

    public Integer getCantidadOperaciones() {
        return cantidadOperaciones;
    }

    public void setCantidadOperaciones(Integer cantidadOperaciones) {
        this.cantidadOperaciones = cantidadOperaciones;
    }
}
