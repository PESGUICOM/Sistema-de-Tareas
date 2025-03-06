# Conexión hibernate
* "ddl-auto", significa Database Definition Language Auto y le indica a Hibernate cómo manejar la creación y actualización de tablas.
# Java FX
## Dependencias para agregar
* <dependency>
			<groupId>org.openjfx</groupId>
			<artifactId>javafx-controls</artifactId>
			<version>23.0.1</version>
		</dependency>
		<dependency>
			<groupId>org.openjfx</groupId>
			<artifactId>javafx-fxml</artifactId>
			<version>22.0.1</version>
		</dependency>
# Como trabaja JFX
* La clase de TareasApplication se manda a llamar la clase SistemasTareasFx, con esto se carga la aplicación de JavaFX. Se carga la plantilla index.fxml y también a su vez se comunica con nuestro controlador IndexControlador, a través del atributo fx:controller="com.pesguicom.tareas.controlador.IndexControlador, en el paquete y la clase, con esta vista.
## El método configurarColumnas() es para poder relacionar la información que cargamos en cada uno de los registros de nuestra tabla.
### Con esta línea integramos todas las tecnologías de Java FX con Java  Spring. Ej:  loader.setControllerFactory(applicationContext::getBean);
# Error
* Agrega un Listener en initialize() para que cargarTareaFormulario() se ejecute cuando selecciones una fila.
Verifica que la clase Tarea tenga los métodos get adecuados.
Asegúrate de que los nombres en PropertyValueFactory coincidan con los atributos de Tarea.
Depura la carga de datos para confirmar que tareaServicio.listarTareas() devuelve información.
