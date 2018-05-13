package net.ddns.shodario.bolsa;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

class ProveedorDatos extends Thread {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss[.SSSSSSSSS]");
    private Map<String, Integer> indicesCamposAcciones = new HashMap<>();
    private Map<String, Integer> indicesCamposOpciones = new HashMap<>();

    @Override
    public void run() {
        Scanner inOpciones = null, inAcciones = null;

        try {
            inAcciones = new Scanner(new File("/home/dario/IdeaProjects/acc_panel_asc.txt"));
            inOpciones = new Scanner(new File("/home/dario/IdeaProjects/opc_panel_asc.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String[] cabeceraAcciones = inAcciones.nextLine().split(Pattern.quote("|"));
        for (int i = 0; i < cabeceraAcciones.length; i++) {
            indicesCamposAcciones.put(cabeceraAcciones[i], i);
        }

        String[] cabeceraOpciones = inOpciones.nextLine().split(Pattern.quote("|"));
        for (int i = 0; i < cabeceraOpciones.length; i++) {
            indicesCamposOpciones.put(cabeceraOpciones[i], i);
        }

        String lineaAccion = null, lineaOpcion = null;
        Dato datoAccion = null, datoOpcion = null;
        while (inAcciones.hasNextLine() || inOpciones.hasNextLine()) {
            if (lineaAccion == null && inAcciones.hasNextLine()) {
                lineaAccion = inAcciones.nextLine();
                datoAccion = crearDato(lineaAccion, TipoActivo.Accion);
            }
            if (lineaOpcion == null && inOpciones.hasNextLine()) {
                lineaOpcion = inOpciones.nextLine();
                datoOpcion = crearDato(lineaOpcion, TipoActivo.Opcion);
            }

            if (lineaAccion != null && lineaOpcion != null) {
                if (datoAccion.getFecha().compareTo(datoOpcion.getFecha()) <= 0) {
                    Procesador.procesar(datoAccion);
                    lineaAccion = null;
                } else {
                    Procesador.procesar(datoOpcion);
                    lineaOpcion = null;
                }
            } else if (lineaAccion != null) {
                Procesador.procesar(datoAccion);
                lineaAccion = null;
            } else {
                Procesador.procesar(datoOpcion);
                lineaOpcion = null;
            }
        }
        System.out.println("Archivo terminado.");
    }

    private Dato crearDato(String linea, TipoActivo tipoActivo) {
        Dato dato = new Dato();
        String[] valores = linea.split(Pattern.quote("|"));
        if (tipoActivo.equals(TipoActivo.Accion)) {
            try {
                dato.setFecha(LocalDateTime.parse(valores[indicesCamposAcciones.get("TimeStamp")], formatter));
                dato.setHoraEmisionNum(Integer.valueOf(valores[indicesCamposAcciones.get("HoraEmisionNum")]));
                dato.setSimbolo(valores[indicesCamposAcciones.get("Simbolo")]);
                dato.setVencimientoId(Integer.valueOf(valores[indicesCamposAcciones.get("VencimientoID")]));
                dato.setCantidadCompra(Integer.valueOf(valores[indicesCamposAcciones.get("CantidadNominalCompra")]));
                dato.setCantidadVenta(Integer.valueOf(valores[indicesCamposAcciones.get("CantidadNominalVenta")]));
                dato.setPrecioCompra(Double.valueOf(valores[indicesCamposAcciones.get("PrecioCompra")].replace(',', '.')));
                dato.setPrecioVenta(Double.valueOf(valores[indicesCamposAcciones.get("PrecioVenta")].replace(',', '.')));
                dato.setPrecioUltimo(Double.valueOf(valores[indicesCamposAcciones.get("PrecioOperacion")].replace(',', '.')));
                dato.setVolumenOperado(Integer.valueOf(valores[indicesCamposAcciones.get("VolumenNominal")]));
                dato.setMontoOperado(Double.valueOf(valores[indicesCamposAcciones.get("MontoOperado")].replace(',', '.')));
                dato.setCantidadOperaciones(Integer.valueOf(valores[indicesCamposAcciones.get("CantidadOperaciones")]));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (tipoActivo.equals(TipoActivo.Opcion)) {
            try {
                dato.setFecha(LocalDateTime.parse(valores[indicesCamposOpciones.get("TimeStamp")], formatter));
                dato.setHoraEmisionNum(Integer.valueOf(valores[indicesCamposOpciones.get("HoraEmisionNum")]));
                dato.setSimbolo(valores[indicesCamposOpciones.get("Simbolo")]);
                dato.setVencimientoId(Integer.valueOf(valores[indicesCamposOpciones.get("VencimientoID")]));
                dato.setCantidadCompra(Integer.valueOf(valores[indicesCamposOpciones.get("CantidadNominalCompra")]));
                dato.setCantidadVenta(Integer.valueOf(valores[indicesCamposOpciones.get("CantidadNominalVenta")]));
                dato.setPrecioCompra(Double.valueOf(valores[indicesCamposOpciones.get("PrecioCompra")].replace(',', '.')));
                dato.setPrecioVenta(Double.valueOf(valores[indicesCamposOpciones.get("PrecioVenta")].replace(',', '.')));
                dato.setPrecioUltimo(Double.valueOf(valores[indicesCamposOpciones.get("PrecioUltimo")].replace(',', '.')));
                dato.setVolumenOperado(Integer.valueOf(valores[indicesCamposOpciones.get("VolumenNominal")]));
                dato.setMontoOperado(Double.valueOf(valores[indicesCamposOpciones.get("MontoOperado")].replace(',', '.')));
                dato.setCantidadOperaciones(Integer.valueOf(valores[indicesCamposOpciones.get("CantidadOperaciones")]));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        dato.setTipoActivo(tipoActivo);
        return dato;
    }
}
