package es.smartcoding.ssmvcp3.controllers;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.smartcoding.ssmvcp3.model.entities.UserEntity;
import es.smartcoding.ssmvcp3.model.services.UserService;

/**
 * Gestiona las peticiones de la página 'home'.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	@Autowired
	private ApplicationContext appContext;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		// getMessage(key, params, defaultMsg, locale)
		String msg = appContext.getMessage("homeController.msg",
				new String[] {}, "Bienvenido! El idioma actual es '{}'.",
				locale);

		logger.info(msg, locale);

		logger.info(appContext.getApplicationName());

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);
		
//		model.addAttribute("userService", findAll());
		
		/*** EJERCICIO ***/
		// Creo tres usuarios por separado y los añado al model
		UserService ue = new UserService();
		ue.save(new UserEntity(1, "aaaa"));
		ue.save(new UserEntity(2, "bbbb"));
		ue.save(new UserEntity(3, "cccc"));
		
		model.addAttribute("user1", ue.findOne(1));
		model.addAttribute("user2", ue.findOne(2));
		model.addAttribute("user3", ue.findOne(3));
		
		// Creo una lista de los tres usuarios
		List<UserEntity> userlist = new ArrayList<>();
		userlist.addAll(ue.findAll());
		
		// Añado lista al model
		model.addAttribute("userlist", userlist);

		return "home";
	}

}



/*
 * La gestión de las excepciones se puede configurar en cada Controller o de
 * forma centralizada en una clase anotada con @ControllerAdvice
 */
// @ExceptionHandler(Exception.class)
// public ModelAndView exceptionHandler(Exception e) {
//
// ModelAndView mav = new ModelAndView("error");
// mav.addObject("msg", e.getMessage());
// return mav;
// }
