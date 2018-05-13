package net.ddns.shodario.bolsa.controladores;

import net.ddns.shodario.bolsa.Procesador;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="*")
public class EstrategiaController {

    @RequestMapping("/infoEstrategia")
    public Object getInfoEstrategia(@RequestParam(value="nombreAccion", defaultValue="") String nombreAccion) {
        return Procesador.getInfoEstrategia(nombreAccion);
    }
}