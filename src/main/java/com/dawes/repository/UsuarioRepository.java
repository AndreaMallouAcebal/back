package com.dawes.repository;

import org.springframework.data.repository.CrudRepository;

import com.dawes.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

}
