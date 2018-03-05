package es.smartcoding.ssmvcp3.controllers;

import java.util.Collection;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import es.smartcoding.ssmvcp3.model.entities.UserEntity;
import es.smartcoding.ssmvcp3.model.services.UserService;

@Controller
@RequestMapping("/application")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private ApplicationContext appContext;

	@Autowired
	private UserService userService;

	/*
	 * Un objeto ModelAndView contiene ambos: el la vista y el modelo de datos:
	 * un conjunto de parejas (clave=valor)
	 */
	@RequestMapping("/allUsers")
	public ModelAndView getUsers() {
		ModelAndView mav = new ModelAndView("users");
		Collection<UserEntity> users = userService.findAll();
		mav.addObject("userEntityList", users);
		return mav;
	}

	/**
	 * Spring soporta procesamiento asíncrono de peticiones mediante objetos
	 * Callable. Un objeto Callable es similar a un objeto Runnable que retorna
	 * un resultado y que puede lanzar una excepcion checkeada.
	 * 
	 * Un controlador puede devolver un objeto java.util.concurrent.Callable y
	 * generar un valor de retorno desde un thread (hilo) gestionado por Spring
	 * MVC.
	 * 
	 * Mientras tanto, el thread (hilo) principal del contenedor de Servlets
	 * acaba y sus recursos liberados dispuestos para procesar otra petición.
	 * 
	 * Spring MVC invoca el Callable en un thread separado con la ayuda de un
	 * TaskExecutor y cuando el Callable retorna la petición se despacha de
	 * vuelta al contenedor de Servlets que continúa el procesamiento usando el
	 * valor retornado por el Callable.
	 * 
	 */
	@RequestMapping("/delete/{id}")
	public Callable<String> deleteUser(@PathVariable long id) {
		logger.info("Entrando en UserController -> delete/id");
		Callable<String> callable = () -> {
			userService.longTimeService();
			return "home";
		};
		logger.info("Saliendo de UserController -> delete/id");
		return callable;

	}
	
	/*
	 * ResponseEntity extiende HttpEntity
	 * 
	 * Un objeto ResponseEntity puede contener un código de estado, cabeceras y
	 * un cuerpo.
	 *  
	 *  /application/users/3;q=34
	 */
	@RequestMapping("/users/{id}")
	public ResponseEntity<UserEntity> showUser(@PathVariable(value = "id") long id,
			@MatrixVariable(name = "q", pathVar = "id", required = false, defaultValue = "-1") int q) {

		logger.info("Matrix Value " + q);

		UserEntity user = userService.findOne(id);
		return ResponseEntity.ok().body(user);
	}

}

/*
 * 
 * * Los controladores que retornan un objeto ResponseEntity pueden incluir
 * información de caché en las respuestas
 * 
 * Usado de esta manera los ETags son similares a fingerprints, que pueden ser
 * comparadas rápidamente para determinar si dos representaciones de un recurso
 * son identicas.
 * 
 * La próxima vez que un cliente envie una petición del mismo recurso. return
 * ResponseEntity.ok() .cacheControl(CacheControl.maxAge(3, TimeUnit.DAYS))
 * .eTag(user.getName()).body(user);
 */

/// *
// * Represents an immutable collection of URI components, mapping
// * component type to string values. Contains convenience getters for all
// * components. Effectively similar to URI, but with more powerful
// * encoding options and support for URI template variables.
// */
// UriComponents uriComponents = MvcUriComponentsBuilder.fromMethodName(
// UserController.class, "deleteUser", 1L).build();
// URI uri = uriComponents.encode().toUri();
//
// logger.info("URI " + uri.toString());