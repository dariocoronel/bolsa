package net.ddns.shodario.bolsa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BolsaApplication {

	public static void main(String[] args) {
		ProveedorDatos proveedorDatos = new ProveedorDatos();
		proveedorDatos.start();

		Procesador procesador = new Procesador();
		procesador.start();

        SpringApplication.run(BolsaApplication.class, args);
	}
}
