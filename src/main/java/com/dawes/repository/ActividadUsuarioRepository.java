package com.dawes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.dawes.model.Actividad;
import com.dawes.model.ActividadUsuario;

public interface ActividadUsuarioRepository extends CrudRepository<ActividadUsuario, Integer>{
	 List<ActividadUsuario> findByActividad(Actividad a);
}
