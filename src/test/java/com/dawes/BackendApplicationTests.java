package com.dawes;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;

import org.hibernate.mapping.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dawes.model.Animal;
import com.dawes.model.Cita;
import com.dawes.model.Usuario;
import com.dawes.service.AnimalService;
import com.dawes.service.CitaService;
import com.dawes.service.UsuarioService;


@SpringBootTest
class BackendApplicationTests {

	
	@Autowired
	AnimalService as;
	
	@Autowired 
	UsuarioService us;
	
	@Autowired
	CitaService cs;

	// Test para insertar un alumno nuevo
//	@Test
//	public void test01() {
//		// Si le damos id 0 sabe que tiene que generar la sigueinte posible
//		// no tenemos constructor sin id
//		assertNotNull(as.save(new Animal("Toby",3,"pastor alemán","Muy bueno","http...","perro",new ArrayList<Cita>())));
//	}
//	// Test para insertar un curso
//		@Test
//		public void test02() {
//			assertNotNull(us.save(new Usuario("Andrea", "Mallou", "andrea@email.com","1","contraseña", new ArrayList<Cita>()())));
//			//añado otro usuario
//			us.save(new Usuario("Andrea", "Mallou", "andrea@email.com","1","contraseña"))
//
//		}
//
//		// Test para matricular un alumno en un curso
//		@Test
//		public void test03() {
//			assertNotNull(sac.save(new AlumnoCursoVO(0,sa.findById(1).get(),sc.findById(1).get(),LocalDate.now())));
//			//matriculo a pepe en lengua tb
//			sac.save(new AlumnoCursoVO(0,sa.findById(1).get(),sc.findById(2).get(),LocalDate.now()));
//		}
}
