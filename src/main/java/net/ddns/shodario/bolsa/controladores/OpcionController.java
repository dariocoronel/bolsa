package net.ddns.shodario.bolsa.controladores;

import net.ddns.shodario.bolsa.Oferta;
import net.ddns.shodario.bolsa.Opcion;
import net.ddns.shodario.bolsa.Procesador;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
public class OpcionController {

    @GetMapping(value = "/opcion/{nombre}")
    public Object getOpcion(@PathVariable("nombre") String nombre) {
        Opcion opcion = Procesador.getOpcion(nombre);
        if(opcion == null)
            return null;

         return opcion.getOfertas();
    }
}