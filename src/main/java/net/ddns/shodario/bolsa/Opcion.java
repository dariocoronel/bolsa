package net.ddns.shodario.bolsa;

import java.util.ArrayList;
import java.util.List;

public class Opcion {
    private final int MACD_FAST = 12, MACD_SLOW = 26, MACD_SIGNAL = 9;
    private final int SMA_SHORT = 50, SMA_LONG = 200;

    private String nombre;
    private TipoOpcion tipo;
    private Double base;
    private Integer mes;
    private List<Oferta> ofertas = new ArrayList<>();

    Opcion(String nombre) {
        this.nombre = nombre;
        this.tipo = nombre.charAt(3) == 'C' ? TipoOpcion.Call : TipoOpcion.Put;

        if (nombre.substring(4).indexOf('.') != -1) {
            if (nombre.toUpperCase().charAt(8) >= 'A' && nombre.toUpperCase().charAt(8) <= 'Z') {
                base = Double.valueOf(nombre.substring(4, 8));
                mes = getMes(nombre.substring(8));
            } else {
                base = Double.valueOf(nombre.substring(4, 9));
                mes = getMes(nombre.substring(9));
            }
        } else {
            base = Double.valueOf(nombre.substring(4, 9));
            switch (nombre.substring(0, 3)) {
                case "ERA":
                case "GFG":
                case "SAM":
                    base /= 1000;
                    break;
                case "TS.":
                case "YPF":
                    base /= 100;
                    break;
                case "FRA":
                    if (base / 1000 > 80)
                        base /= 1000;
                    else
                        base /= 100;
                    break;
                default:
                    base = null;
            }
            mes = getMes(nombre.substring(9));
        }
    }

    private Integer getMes(String mes) {
        switch (mes) {
            case "E":
            case "EN":
                return 1;
            case "F":
            case "FE":
                return 2;
            case "M":
            case "MA":
                return 3;
            case "A":
            case "AB":
                return 4;
            case "Y":
            case "MY":
                return 5;
            case "J":
            case "JU":
                return 6;
            case "L":
            case "JL":
                return 7;
            case "G":
            case "AG":
                return 8;
            case "S":
            case "SE":
                return 9;
            case "O":
            case "OC":
                return 10;
            case "N":
            case "NO":
                return 11;
            case "D":
            case "DI":
                return 12;
            default:
                return null;
        }
    }

    void procesar(Dato dato) {
        ofertas.add(new Oferta(dato.getFecha(), dato.getCantidadCompra(), dato.getCantidadVenta(),
                dato.getPrecioCompra(), dato.getPrecioVenta()));
    }

    public List<Oferta> getOfertas() {
        return ofertas;
    }

    String getNombre() {
        return nombre;
    }

    TipoOpcion getTipo() {
        return tipo;
    }

    Double getBase() {
        return base;
    }

    Integer getMes() {
        return mes;
    }

    Oferta getUltimaOferta() {
        return ofertas.get(ofertas.size()-1);
    }

    @Override
    public String toString() {
        return "Opcion{" +
                "nombre='" + nombre + '\'' +
                ", base=" + base +
                ", mes=" + mes +
                '}';
    }
}
