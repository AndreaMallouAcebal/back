package com.dawes.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dawes.model.Cita;
import com.dawes.service.CitaService;
@CrossOrigin(origins="*")
@RestController
//@RequestMapping("/citas")
public class CitaController {
	
	@Autowired
	private CitaService citaService;


	// método para listar todas las actividades
	@GetMapping("/citas")
	public List<Cita> listarCitas() {
		List<Cita>citas=citaService.findAll();
		return citas;
	}

	// método para guardar una actividad
	// @requestBody es para enviar el objeto en formato Json
	@PostMapping("/citas")
	public Cita guardarCitas(@RequestBody Cita cita) {
		return citaService.save(cita);
	}
	
	
	// método para listar todas los animales
	@GetMapping("/citas/{id}")
	public Optional<Cita> recuperarCita(@PathVariable Integer id) {
		Optional<Cita> c=citaService.findById(id);
		return c;
	}

	//modificar
	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping("/citas/{id}")
	public ResponseEntity<Cita>  modificarRol(@RequestBody Cita detallesCita, @PathVariable Integer id ) {
		 
		Optional<Cita> cita= citaService.findById(id);
		cita.get().setId(detallesCita.getId());
		cita.get().setFecha(detallesCita.getFecha());
		cita.get().setAnimal(detallesCita.getAnimal());
		cita.get().setUsuario(detallesCita.getUsuario());
		
		Cita citaActualizada= citaService.save(cita.get());
		
		return ResponseEntity.ok(citaActualizada);
		
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/citas/{id}")
	// retorna verdadero si el elemento fue eliminado, si no lo encuentra
	public void eliminarCita(@PathVariable Integer id) {
		citaService.deleteById(id);
	}

}
