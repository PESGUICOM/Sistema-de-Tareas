package com.pesguicom.tareas.servicio;

import com.pesguicom.tareas.modelo.Tarea;
import com.pesguicom.tareas.repositorio.TareaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TareaServicio implements ITareaServicio{

    @Autowired
    private TareaRepositorio tareaRepositorio; //Agreganddo atributo

    @Override
    public List<Tarea> listarTareas() {
        return tareaRepositorio.findAll(); //regresamos todas las tareas
    }

    @Override
    public Tarea buscarTareaPorId(Integer idTarea) {
        Tarea tarea = tareaRepositorio.findById(idTarea).orElse(null);
        return tarea;
    }

    @Override
    public void guardarTarea(Tarea tarea) {
        tareaRepositorio.save(tarea);
    }

    @Override
    public void eliminarTarea(Tarea tarea) {
        tareaRepositorio.delete(tarea);
    }
}
