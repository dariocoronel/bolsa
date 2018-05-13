package net.ddns.shodario.bolsa;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Accion {
    private final static int MACD_FAST = 12, MACD_SLOW = 26, MACD_SIGNAL = 9;
    private final static int SMA_SHORT = 50, SMA_LONG = 200;

    private String nombre;
    private List<Operacion> precios = new ArrayList<>();

    private List<Operacion> operacionesCierre = new ArrayList<>();
    private LocalDate fechaUltimoCierre;

    Accion(String nombre) {
        this.nombre = nombre;
    }

    public List<Operacion> getPrecios() {
        return precios;
    }

    public List<Operacion> getOperacionesCierre() {
        return operacionesCierre;
    }

    String getNombre() {
        return nombre;
    }

    Double getPrecioUltimo() {
        return precios.get(precios.size() - 1).precio;
    }

    void procesar(Dato dato) {
        Double precioUltimo = dato.getPrecioUltimo();
        LocalDateTime fecha = dato.getFecha();

        // Actualiza el precio si no es 0
        if (!precioUltimo.equals((double) 0))
            precios.add(new Operacion(fecha, precioUltimo));

        // Calcula indicadores sólo si cambia el día y pasaron las 10:30
        if (!fecha.toLocalDate().equals(fechaUltimoCierre) &&
                fecha.toLocalTime().compareTo(LocalTime.of(10, 30)) >= 0) {
            agregarOperacion(new Operacion(fecha, precioUltimo));
            fechaUltimoCierre = fecha.toLocalDate();
        }
    }

    private void agregarOperacion(Operacion operacion) {
        operacionesCierre.add(operacion);

        if (operacionesCierre.size() >= SMA_SHORT) {
            double total = 0;
            for (int i = operacionesCierre.size() - SMA_SHORT; i < operacionesCierre.size(); i++) {
                total += operacionesCierre.get(i).precio;
            }
            operacion.smaShort = total / SMA_SHORT;
        }

        if (operacionesCierre.size() >= SMA_LONG) {
            double total = 0;
            for (int i = operacionesCierre.size() - SMA_LONG; i < operacionesCierre.size(); i++) {
                total += operacionesCierre.get(i).precio;
            }
            operacion.smaLong = total / SMA_LONG;
        }

        if (operacionesCierre.size() == MACD_FAST) {
            operacion.macdFast = operacion.precio;
        } else if (operacionesCierre.size() > MACD_FAST) {
            double emaAnterior = operacionesCierre.get(operacionesCierre.size() - 2).macdFast;
            double factor = 2.0 / (MACD_FAST + 1);
            operacion.macdFast = (operacion.precio - emaAnterior) * factor + emaAnterior;
        }

        if (operacionesCierre.size() == MACD_SLOW) {
            operacion.macdSlow = operacion.precio;
        } else if (operacionesCierre.size() > MACD_SLOW) {
            double emaAnterior = operacionesCierre.get(operacionesCierre.size() - 2).macdSlow;
            double factor = 2.0 / (MACD_SLOW + 1);
            operacion.macdSlow = (operacion.precio - emaAnterior) * factor + emaAnterior;
        }

        if (operacion.macdFast != null && operacion.macdSlow != null)
            operacion.macd = operacion.macdFast - operacion.macdSlow;

        if (operacionesCierre.size() == MACD_SLOW + MACD_SIGNAL) {
            operacion.macdSignal = operacion.macd;
        } else if (operacionesCierre.size() > MACD_SLOW + MACD_SIGNAL) {
            double emaAnterior = operacionesCierre.get(operacionesCierre.size() - 2).macdSignal;
            double factor = 2.0 / (MACD_SIGNAL + 1);
            operacion.macdSignal = (operacion.macd - emaAnterior) * factor + emaAnterior;
        }

        if (operacion.macd != null && operacion.macdSignal != null)
            operacion.macdHistogram = operacion.macd - operacion.macdSignal;

        if (operacionesCierre.size() == MACD_SLOW + MACD_SIGNAL + 1) {
            double histogramAnterior = operacionesCierre.get(operacionesCierre.size() - 2).macdHistogram;
            if (histogramAnterior < 0 && operacion.macdHistogram > 0)
                operacion.senalCompra = true;
            if (histogramAnterior > 0 && operacion.macdHistogram < 0)
                operacion.senalVenta = true;
        }
    }

    @Override
    public String toString() {
        return "Accion{" +
                "nombre='" + nombre + '\'' +
                ", precioUltimo=" + getPrecioUltimo() +
                '}';
    }
}
