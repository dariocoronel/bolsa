package net.ddns.shodario.bolsa;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Procesador extends Thread {
    private static ConcurrentLinkedQueue<Dato> datosPendientes = new ConcurrentLinkedQueue<>();
    private static int procesados;
    private final static Object lock = new Object();

    private static Map<String, Accion> acciones = new HashMap<>();
    private static Map<String, Opcion> opciones = new HashMap<>();
    private static Set<Estrategia> estrategias = new HashSet<>();

    public static Accion getAccion(String nombre){
        return acciones.get(nombre);
    }

    public static Opcion getOpcion(String nombre){
        return opciones.get(nombre);
    }

    public static InfoEstrategia[] getInfoEstrategia(String nombreAccion) {
        return estrategias.stream()
                .filter(e -> e.getAccion().getNombre().equals(nombreAccion))
                .map(Estrategia::getInfoEstrategia)
                .toArray(InfoEstrategia[]::new);
    }

    static void procesar(Dato dato) {
        datosPendientes.add(dato);
        synchronized (lock) {
            lock.notify();
        }
    }

    private static Accion getAccion(Opcion opcion) {
        String nombreAccion;
        switch (opcion.getNombre().substring(0, 3)) {
            case "GCC":
                nombreAccion = "GCLA";
                break;
            case "GFG":
                nombreAccion = "GGAL";
                break;
            case "PBR":
                nombreAccion = "APBR";
                break;
            case "TS.":
                nombreAccion = "TS";
                break;
            default:
                nombreAccion = acciones.entrySet().stream()
                        .filter(a -> a.getKey().startsWith(opcion.getNombre().substring(0, 3)))
                        .map(Map.Entry::getKey).findFirst().orElse(null);
        }
        return acciones.get(nombreAccion);
    }

    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                try {
                    lock.wait();
                    //Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            while (!datosPendientes.isEmpty()) {
                Dato dato = datosPendientes.poll();
                if (dato.getTipoActivo().equals(TipoActivo.Accion)) {
                    if (dato.getVencimientoId().equals(4) || dato.getVencimientoId().equals(3))
                        procesarAccion(dato);
                } else if (dato.getTipoActivo().equals(TipoActivo.Opcion)) {
                    procesarOpcion(dato);
                }
                procesados++;
            }
            if (procesados == 531897)
                System.out.println("Proceso terminado.");
        }
    }

    private void procesarAccion(Dato dato) {
        Accion accion;
        if (!acciones.containsKey(dato.getSimbolo())) {
            accion = new Accion(dato.getSimbolo());
            acciones.put(dato.getSimbolo(), accion);
            crearEstrategias(null);
        } else {
            accion = acciones.get(dato.getSimbolo());
        }
        accion.procesar(dato);
    }

    private void procesarOpcion(Dato dato) {
        Opcion opcion;
        if (!opciones.containsKey(dato.getSimbolo())) {
            opcion = new Opcion(dato.getSimbolo());
            opciones.put(dato.getSimbolo(), opcion);
            crearEstrategias(dato.getSimbolo().substring(0, 3));
        } else {
            opcion = opciones.get(dato.getSimbolo());
        }
        opcion.procesar(dato);
    }

    private void crearEstrategias(String filtroPrincipioOpcion) {
        Supplier<Stream<Map.Entry<String, Opcion>>> supplier;
        if (filtroPrincipioOpcion == null)
            supplier = () -> opciones.entrySet().stream();
        else
            supplier = () -> opciones.entrySet().stream().filter(o -> o.getKey().startsWith(filtroPrincipioOpcion));

        supplier.get().forEach(o1 -> {
            Accion accion = getAccion(o1.getValue());
            if (accion != null) {
                supplier.get().forEach(o2 -> {
                    if (accion.equals(getAccion(o2.getValue()))) {
                        if (estrategiaValida(o1.getValue(), o2.getValue())) {
                            Estrategia estrategia = new Estrategia(accion, o1.getValue(), o2.getValue());
                            estrategias.add(estrategia);
                        }
                    }
                });
            }
        });
    }

    private boolean estrategiaValida(Opcion opcion1, Opcion opcion2) {
        try {
            if (!(opcion1.getMes().equals(opcion2.getMes())))
                return false;
            if (!(opcion1.getBase() < opcion2.getBase()))
                return false;
            if (!(opcion1.getTipo().equals(TipoOpcion.Call) && opcion2.getTipo().equals(TipoOpcion.Call)))
                return false;
        } catch (Exception e) {
            System.out.println(opcion1);
            System.out.println(opcion2);
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
