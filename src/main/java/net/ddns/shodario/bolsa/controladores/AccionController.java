package net.ddns.shodario.bolsa.controladores;

import net.ddns.shodario.bolsa.Accion;
import net.ddns.shodario.bolsa.Procesador;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins="*")
public class AccionController {

    @GetMapping(value = "/accion/{nombre}")
    public Object getAccion(@PathVariable("nombre") String nombre) {
        Accion accion = Procesador.getAccion(nombre);
        if(accion == null)
            return null;

        return accion.getPrecios();
    }
}
