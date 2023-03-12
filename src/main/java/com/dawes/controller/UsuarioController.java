package com.dawes.controller;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

	@PreAuthorize("hasAuthority('USER')")
	@PostMapping("/mi-perfil")
    public Usuario recuperarUsuarioByEmail(@RequestParam("userEmail") String email) {
        return usuarioService.findByEmail(email).get();
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

	@PreUpdate
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

	@PreRemove
	@DeleteMapping("/usuarios/{id}")
	// retorna verdadero si el elemento fue eliminado, si no lo encuentra
	public void eliminarUsuario(@PathVariable Integer id) {
		usuarioService.deleteById(id);
	}
	

}
