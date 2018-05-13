package net.ddns.shodario.bolsa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Pattern;

class Main {

    public static void main(String[] args) {


        //invertirArchivo("PAMP");

        /*Scanner in = null;

        try {
            in = new Scanner(new File("/home/dario/IdeaProjects/Bolsa/opc_panel.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        long startTime = System.currentTimeMillis();
        HashMap<String, Integer> campos = new HashMap<>();

        String[] cabecera = in.nextLine().split(Pattern.quote("|"));
        for (int i = 0; i < cabecera.length; i++) {
            campos.put(cabecera[i], i);
        }

        HashMap<String, Opcion> opciones = new HashMap<>();
        int i = 0;
        while(in.hasNextLine() && i++ < 50){
            String[] linea = in.nextLine().split(Pattern.quote("|"));

            String nombre = linea[campos.get("Simbolo")];

            Opcion opcion;
            if (!opciones.containsKey(nombre)) {
                opcion = new Opcion(nombre);
                opciones.put(nombre, opcion);
            } else {
                opcion = opciones.get(nombre);
            }

            SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date fecha = null;
            try {
                fecha = parser.parse(linea[campos.get("TimeStamp")]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String precioString = linea[campos.get("PrecioOperacion")].replace(',','.');
            Double precio = Double.valueOf(precioString);
            Integer volumen = Integer.valueOf(linea[campos.get("VolumenNominal")]);

            Operacion operacion = new Operacion(fecha, precio, volumen);
            opcion.agregarOperacion(operacion);
        }

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println(elapsedTime + "ms");

        opciones.get("PAMC45.1OC").imprimirResumen();*/

        /*
        Scanner in = null;

        try {
            in = new Scanner(new File("/home/dario/IdeaProjects/Bolsa/acc_panel.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        long startTime = System.currentTimeMillis();
        HashMap<String, Integer> campos = new HashMap<>();

        String[] cabecera = in.nextLine().split(Pattern.quote("|"));
        for (int i = 0; i < cabecera.length; i++) {
            campos.put(cabecera[i], i);
        }

        HashMap<String, Accion> acciones = new HashMap<>();
        while(in.hasNextLine()){
            String[] linea = in.nextLine().split(Pattern.quote("|"));

            String nombreAccion = linea[campos.get("Simbolo")];

            Accion accion;
            if (!acciones.containsKey(nombreAccion)) {
                accion = new Accion(nombreAccion);
                acciones.put(nombreAccion, accion);
            } else {
                accion = acciones.get(nombreAccion);
            }

            SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date fecha = null;
            try {
                fecha = parser.parse(linea[campos.get("TimeStamp")]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String precioString = linea[campos.get("PrecioOperacion")].replace(',','.');
            Double precio = Double.valueOf(precioString);
            Integer volumen = Integer.valueOf(linea[campos.get("VolumenNominal")]);

            Operacion operacion = new Operacion(fecha, precio, volumen);
            accion.agregarOperacion(operacion);
        }

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println(elapsedTime + "ms");

        acciones.get("PAMP").imprimirResumen();
        */


    }

    private static void invertirArchivo(String filtro) {
        Scanner in = null;
        try {
            in = new Scanner(new File("/home/dario/IdeaProjects/Bolsa/opc_panel.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        HashMap<String, Integer> campos = new HashMap<>();

        String[] cabecera = in.nextLine().split(Pattern.quote("|"));
        for (int i = 0; i < cabecera.length; i++) {
            campos.put(cabecera[i], i);
        }

        List<String> archivo = new ArrayList<String>();
        while (in.hasNextLine()) {
            String linea = in.nextLine();
            //if (linea.split(Pattern.quote("|"))[campos.get("Simbolo")].equals(filtro)) {
            archivo.add(linea);
            //}
        }
        archivo.add(String.join("|", cabecera));

        Collections.reverse(archivo);
        try {
            Files.write(Paths.get("/home/dario/IdeaProjects/Bolsa/opc_panel_asc.txt"), archivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
