package com.dawes.controller;

import java.util.List;
import java.util.Optional;

import com.dawes.model.Actividad;
import com.dawes.model.Usuario;
import com.dawes.service.ActividadService;
import com.dawes.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dawes.model.ActividadUsuario;
import com.dawes.service.ActividadUsuarioService;

@RestController
//@RequestMapping("/actividadesusuarios")
@CrossOrigin(origins="*")
public class ActividadUsuarioController {


	@Autowired
	private ActividadUsuarioService actividadusuarioService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ActividadService actividadService;
	

	// método para listar todas los animales
	@GetMapping("/actividadesusuarios")
//	private ResponseEntity<List<Animal>> getAllAnimales() {
//		return ResponseEntity.ok(animalService.findAll());
//	}
	public List<ActividadUsuario> listarActividadesUsuarios() {
		return actividadusuarioService.findAll();
	}
	
	@GetMapping("/ver-usuarios/{id}")
	public List<ActividadUsuario> listarActividadesUsuariosByActividad(@PathVariable Integer id) {
		return actividadusuarioService.findByActividad(actividadService.findById(id).get());
	}

	// método para guardar un animal
	// @requestBody es para enviar el objeto en formato Json
	@PostMapping("/actividadesusuarios")
	public ActividadUsuario guardarActividadUsuario(@RequestParam("idActividad") int idActividad, @RequestParam("userEmail") String userEmail) {
		ActividadUsuario actividadusuario = new ActividadUsuario();
		actividadusuario.setActividad(actividadService.findById(idActividad).get());
		actividadusuario.setUsuario(usuarioService.findByEmail(userEmail).get());
		return actividadusuarioService.save(actividadusuario);
	}

	
	// método para listar todas los animales
	@GetMapping("/actividadesusuarios/{id}")
	public Optional<ActividadUsuario> recuperarActividadUsuario(@PathVariable Integer id) {
		return actividadusuarioService.findById(id);
	}

	//modificar
	@PutMapping("/actividadesusuarios/{id}")
	public ResponseEntity<ActividadUsuario>  modificarActividadUsuario(@RequestBody ActividadUsuario detallesActividadUsuario, @PathVariable Integer id ) {
		 
		Optional<ActividadUsuario> actividadusuario= actividadusuarioService.findById(id);
		actividadusuario.get().setId(detallesActividadUsuario.getId());
		actividadusuario.get().setActividad(detallesActividadUsuario.getActividad());
		actividadusuario.get().setUsuario(detallesActividadUsuario.getUsuario());
		
		ActividadUsuario actividadActualizado= actividadusuarioService.save(actividadusuario.get());
		
		return ResponseEntity.ok(actividadActualizado);
	}
	

	@DeleteMapping("/actividadesusuarios/{id}")
	// retorna verdadero si el elemento fue eliminado, si no lo encuentra
	public void deleteActividadUsuario(@PathVariable Integer id) {

		actividadusuarioService.deleteById(id);
	}
	
	
}
