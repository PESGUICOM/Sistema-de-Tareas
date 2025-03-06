package com.pesguicom.tareas.controlador;

import com.pesguicom.tareas.modelo.Tarea;
import com.pesguicom.tareas.servicio.TareaServicio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.*;

@Component
public class IndexControlador implements Initializable {
    private static final Logger logger = LoggerFactory.getLogger(IndexControlador.class); //para mandar datos a consola

    @Autowired
    private TareaServicio tareaServicio; //para tener acceso a la tarea servicio

    @FXML
    private TableView<Tarea> tareaTabla;

    @FXML
    private TableColumn<Tarea, Integer> idTareaColumna;

    @FXML
    private TableColumn<Tarea, String> nombreTareaColumna;

    @FXML
    private TableColumn<Tarea, String> responsableColumna;

    @FXML
    private TableColumn<Tarea, String> estadoColumna;

    private final ObservableList<Tarea> tareaList = FXCollections.observableArrayList();

    @FXML
    private TextField nombreTareaTexto;

    @FXML
    private TextField responsableTexto;

    @FXML
    private TextField estadoTexto;

    private Integer idTareaInterno;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tareaTabla.getSelectionModel().setSelectionMode(SelectionMode.SINGLE); //para seleccionar un solo registro
        configurarColumnas();
        listarTareas();

        //Listener para cargar datos al seleccionar una fila
        tareaTabla.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null){
                cargarTareaFormulario();
            }
        });
    }

    private void configurarColumnas(){
        idTareaColumna.setCellValueFactory(new PropertyValueFactory<>("idTarea"));
        nombreTareaColumna.setCellValueFactory(new PropertyValueFactory<>("nombreTarea"));
        responsableColumna.setCellValueFactory(new PropertyValueFactory<>("responsable"));
        estadoColumna.setCellValueFactory(new PropertyValueFactory<>("estado"));
    }

    private void listarTareas(){
        logger.info("Ejecutando listado de tareas");
        tareaList.clear(); //limpiar tareas
        tareaList.addAll(tareaServicio.listarTareas());//obtenemos toda la información de la BD
        tareaTabla.setItems(tareaList); //agregamos información a la tabla
    }

    public void agregarTarea(){
        if (nombreTareaTexto.getText().isEmpty()){
            mostrarMensaje("Error Validación", "Debe proporcionar una tarea");
            nombreTareaTexto.requestFocus();
            return;
        } else {
            var tarea = new Tarea();
            recolectarDatosFormulario(tarea);
            tarea.setIdTarea(null);
            tareaServicio.guardarTarea(tarea);
            mostrarMensaje("Información", "Tarea agregada");
            limpiarFormulario();
            listarTareas();
        }
    }

    public void cargarTareaFormulario(){
        var tarea = tareaTabla.getSelectionModel().getSelectedItem();
        if (tarea != null){
            idTareaInterno = tarea.getIdTarea();
            nombreTareaTexto.setText(tarea.getNombreTarea());
            responsableTexto.setText(tarea.getResponsable());
            estadoTexto.setText(tarea.getEstado());
        }
    }

    private void recolectarDatosFormulario(Tarea tarea){
        if (idTareaInterno != null)
            tarea.setIdTarea(idTareaInterno);
        tarea.setNombreTarea(nombreTareaTexto.getText());
        tarea.setResponsable(responsableTexto.getText());
        tarea.setEstado(estadoTexto.getText());
    }

    public void modificarTarea(){
        if (idTareaInterno == null){
            mostrarMensaje("Información", "Debe seleccionar una tarea");
            return;
        }
        if (nombreTareaTexto.getText().isEmpty()){
            mostrarMensaje("Error Validación", "Debe proporcionar una tarea");
            nombreTareaTexto.requestFocus();
            return;
        }
        var tarea = new Tarea();
        recolectarDatosFormulario(tarea);
        tareaServicio.guardarTarea(tarea);
        mostrarMensaje("Información", "Tarea modificada");
        limpiarFormulario();
        listarTareas();
    }

    public void eliminarTarea(){
        var tarea = tareaTabla.getSelectionModel().getSelectedItem();
        if (tarea != null){
            logger.info("Registro a eliminar: " + tarea.toString());
            tareaServicio.eliminarTarea(tarea);
            mostrarMensaje("Información", "Tarea eliminada:" + tarea.getIdTarea());
            limpiarFormulario();
            listarTareas();
        } else {
            mostrarMensaje("Error", "No se ha seleccionado ninguna tarea");
        }
    }

    public void limpiarFormulario(){
        idTareaInterno = null;
       nombreTareaTexto.clear();
       responsableTexto.clear();
       estadoTexto.clear();
    }

    private void mostrarMensaje(String titulo, String mensaje){
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
