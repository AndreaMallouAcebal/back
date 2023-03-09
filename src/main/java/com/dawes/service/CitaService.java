package com.dawes.service;

import java.util.List;
import java.util.Optional;

import com.dawes.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.dawes.model.Cita;
import com.dawes.repository.CitaRepository;

@Service
public class CitaService implements CitaRepository{

	
	@Autowired
	private CitaRepository citaRepository;
	
	
	@Override
	public List<Cita> findAll() {
		return (List<Cita>) citaRepository.findAll();
	}

	@Override
	public List<Cita> findByUsuario(Usuario usuario) {
		return (List<Cita>) citaRepository.findByUsuario(usuario);
	}
	
	@Override
	public <S extends Cita> S save(S entity) {
		return citaRepository.save(entity);
	}
	
	@Override
	public Optional<Cita> findById(Integer id) {
		// TODO Auto-generated method stub
		return citaRepository.findById(id);
	}
	
	@Override
	public void deleteById(Integer id) {
		citaRepository.deleteById(id);
		
	}
	

	@Override
	public <S extends Cita> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Cita> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void delete(Cita entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Cita> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

}
