package com.dawes.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dawes.model.Usuario;
import com.dawes.service.UsuarioService;
@CrossOrigin(origins="*")
@RestController
//@RequestMapping("/usuarios")
public class UsuarioController {
	

	@Autowired
	private UsuarioService usuarioService;

	// método para listar todos los usuarios
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/usuarios")
	public List<Usuario> listarUsuario() {
		return usuarioService.findAll();
	}

	// método para guardar una actividad
	// @requestBody es para enviar el objeto en formato Json
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/usuarios")
	public Usuario guardarUsuario(@RequestBody Usuario usuario) {
		return usuarioService.save(usuario);
	}

	
	// método para listar todas los animales
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/usuarios/{id}")
	public Optional<Usuario> recuperarUsuario(@PathVariable Integer id) {
		return usuarioService.findById(id);
	}
	

	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping("/usuarios/{id}")
	public ResponseEntity<Usuario>  modificarUsuario(@RequestBody Usuario detallesUsuario, @PathVariable Integer id ) {
		 
		Optional<Usuario> usuario= usuarioService.findById(id);
		usuario.get().setId(detallesUsuario.getId());
		usuario.get().setNombre(detallesUsuario.getNombre());
		usuario.get().setApellidos(detallesUsuario.getApellidos());
		usuario.get().setEmail(detallesUsuario.getEmail());
		usuario.get().setDni(detallesUsuario.getDni());
		usuario.get().setContrasenia(detallesUsuario.getContrasenia());
		usuario.get().setRol(detallesUsuario.getRol());
		
		Usuario usuarioActualizado= usuarioService.save(usuario.get());
		
		return ResponseEntity.ok(usuarioActualizado);
		
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/usuarios/{id}")
	// retorna verdadero si el elemento fue eliminado, si no lo encuentra
	public void eliminarUsuario(@PathVariable Integer id) {
		usuarioService.deleteById(id);
	}
	

}
