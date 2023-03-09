package com.dawes.controller;

import java.util.List;
import java.util.Optional;

import com.dawes.model.Usuario;
import com.dawes.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.dawes.model.Cita;
import com.dawes.service.CitaService;
@CrossOrigin(origins="*")
@RestController
//@RequestMapping("/citas")
public class CitaController {
	
	@Autowired
	private CitaService citaService;
	@Autowired
	private UsuarioService usuarioService;


	// método para listar todas las actividades
	@GetMapping("/citas")
	public List<Cita> listarCitas() {
		List<Cita>citas=citaService.findAll();
		return citas;
	}
	@PostMapping("/mis-citas")
	public List<Cita> listarMisCitas(@RequestParam("userEmail") String userEmail) {
		Usuario u = usuarioService.findByEmail(userEmail).get();
		List<Cita>citas=citaService.findByUsuario(u);
		return citas;
	}

	// método para guardar una actividad
	// @requestBody es para enviar el objeto en formato Json
	@PostMapping("/citas")
	public Cita guardarCitas(@RequestBody Cita cita) {
		return citaService.save(cita);
	}

	@PostMapping("/citas/{email}")
	public Cita guardarCitaSinUsuario(@RequestBody Cita cita, @PathVariable String email) {
		Usuario usuario = usuarioService.findByEmail(email).get();
		cita.setUsuario(usuario);
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
