package es.smartcoding.ssmvcp3.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.smartcoding.ssmvcp3.model.entities.UserEntity;
import es.smartcoding.ssmvcp3.model.services.UserService;

/*
 * @RestController es una anotación que está anotada ella misma con @Controller and @ResponseBody.
 * Además, los métodos  @RequestMapping asumen @ResponseBody por defecto.
 * 
 * Transforma automáticamente un objeto en en objeto JSON
 */
@RestController
public class SimpleRestfulController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/users")
	/* ResponseEntity representa una respuesta HTTP */
	public ResponseEntity<Collection<UserEntity>> getUsers() {

		userService.capitalizeAll();

		Collection<UserEntity> users = userService.findAll();

		if (users.isEmpty()) {
			return new ResponseEntity<Collection<UserEntity>>(
					HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<Collection<UserEntity>>(users, HttpStatus.OK);
	}

}
