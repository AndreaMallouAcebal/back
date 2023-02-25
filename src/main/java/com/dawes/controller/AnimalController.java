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

import com.dawes.model.Animal;

import com.dawes.service.AnimalService;


@RestController
//@RequestMapping("/animales")
@CrossOrigin(origins="*")
public class AnimalController {

	@Autowired
	private AnimalService animalService;
	

	// método para listar todas los animales
	@GetMapping("/animales")
//	private ResponseEntity<List<Animal>> getAllAnimales() {
//		return ResponseEntity.ok(animalService.findAll());
//	}
	public List<Animal> listarAnimales() {
		return animalService.findAll();
	}

	// método para guardar un animal
	// @requestBody es para enviar el objeto en formato Json
	@PostMapping("/animales")
	public Animal guardarAnimal(@RequestBody Animal animal) {
		return animalService.save(animal);
	}

	
	// método para listar todas los animales
	@GetMapping("/animales/{id}")
	public Optional<Animal> recuperarAnimal(@PathVariable Integer id) {
		return animalService.findById(id);
	}

	//modificar
	@PutMapping("/animales/{id}")
	public ResponseEntity<Animal>  modificarAnimal(@RequestBody Animal detallesAnimal, @PathVariable Integer id ) {
		 
		Optional<Animal> animal= animalService.findById(id);
		animal.get().setId(detallesAnimal.getId());
		animal.get().setNombre(detallesAnimal.getNombre());
		animal.get().setEdad(detallesAnimal.getEdad());
		animal.get().setRaza(detallesAnimal.getRaza());
		animal.get().setDescripcion(detallesAnimal.getDescripcion());
		animal.get().setImagen(detallesAnimal.getImagen());
		animal.get().setTipo(detallesAnimal.getTipo());
		
		
		Animal animalActualizado= animalService.save(animal.get());
		
		return ResponseEntity.ok(animalActualizado);
	}
	

	@DeleteMapping("/animales/{id}")
	// retorna verdadero si el elemento fue eliminado, si no lo encuentra
	public void deleteAnimal(@PathVariable Integer id) {

		animalService.deleteById(id);
	}
	
}
