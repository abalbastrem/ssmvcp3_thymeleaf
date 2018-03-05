package es.smartcoding.ssmvcp3.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * Los controladores multi-action son especialmente utiles cuando tenemos muchas
 * funcionalidades comunes en un controlador pero queremos tener múltiples
 * puntos de entrada en el controlador.
 * 
 * Los controladores multi-action son capaces de emparejar peticiones con
 * nombres de métodos.
 * 
 * @author pep
 *
 */
public class SimpleDemoController extends MultiActionController {

	// public (ModelAndView | Map | String | void) actionName(HttpServletRequest
	// request, HttpServletResponse response, [,HttpSession] [,AnyObject])
	// [throws Exception];

	/**
	 * Los objetos ModelAndView contiene el nombre lógico de la vista y el
	 * modelo, un conjunto de parejas (clave=valor)
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView action1(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("action1", "msg", "action1");
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return Un nombre de vista lógico.
	 * @throws Exception
	 */
	public String action2(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return "action2";
	}

}
