package com.pesguicom.tareas.repositorio;

import com.pesguicom.tareas.modelo.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TareaRepositorio extends JpaRepository<Tarea, Integer> {
}
