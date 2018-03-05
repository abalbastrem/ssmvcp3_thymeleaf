package es.smartcoding.ssmvcp3.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import es.smartcoding.ssmvcp3.RootPackage;

/*
 * Las clases @ControllerAdvice proporcionan una gestión centralizada de excepciones entre otras características.
 * mediante métodos @ExceptionHandler.
 * 
 * Sirven como una especialización de @Component.
 * 
 * Habitualmente definen métodos @ExceptionHandler, @InitBinder, y @ModelAttribute que se aplican a todos los métodos @RequestMapping.
 * 
 * El atributo basePackageClasses es preferible al atributo basePackages
 * porque especifica un array de clases/interfaces en vez de un array
 * de cadenas que en caso de refactorización el compilador puede detectar.
 * 
 */
@ControllerAdvice(basePackageClasses = { RootPackage.class })
public class AppControllerAdvice extends ResponseEntityExceptionHandler {

	/**
	 * Un único lugar para personalizar el cuerpo de la respuesta de todos los
	 * tipos de excepciones.
	 * 
	 * Este método retorna null por defecto.
	 */
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
			Object body, HttpHeaders headers, HttpStatus status,
			WebRequest request) {

		ResponseEntity<Object> response = new ResponseEntity<>(body, headers,
				status);
		return response;
	}

	/**
	 * Este método mapea la clase Throwable y subclases con la página
	 * 'error.jsp'
	 *
	 */
	@ExceptionHandler(Throwable.class)
	@ResponseStatus(value = HttpStatus.OK, reason = "Mensaje de error")
	public ModelAndView exceptionHandler(Exception e) {

		ModelAndView mav = new ModelAndView("error");
		mav.addObject("msg", e.getMessage());
		return mav;
	}

}
