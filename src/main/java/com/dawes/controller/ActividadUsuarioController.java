package com.dawes.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dawes.model.ActividadUsuario;
import com.dawes.service.ActividadUsuarioService;

@RestController
//@RequestMapping("/actividadesusuarios")
@CrossOrigin(origins="*")
public class ActividadUsuarioController {


	@Autowired
	private ActividadUsuarioService actividadusuarioService;
	

	// método para listar todas los animales
	@GetMapping("/actividadesusuarios")
//	private ResponseEntity<List<Animal>> getAllAnimales() {
//		return ResponseEntity.ok(animalService.findAll());
//	}
	public List<ActividadUsuario> listarActividadesUsuarios() {
		return actividadusuarioService.findAll();
	}

	// método para guardar un animal
	// @requestBody es para enviar el objeto en formato Json
	@PostMapping("/actividadesusuarios")
	public ActividadUsuario guardarActividadUsuario(@RequestBody ActividadUsuario actividadusuario) {
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
