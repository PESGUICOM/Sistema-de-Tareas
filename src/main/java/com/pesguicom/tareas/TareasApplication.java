package com.pesguicom.tareas;

import com.pesguicom.tareas.presentacion.SistemasTareasFx;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TareasApplication {

	public static void main(String[] args) {
		//SpringApplication.run(TareasApplication.class, args);
		Application.launch(SistemasTareasFx.class, args);
	}

}
