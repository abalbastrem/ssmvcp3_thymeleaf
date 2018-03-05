package es.smartcoding.ssmvcp3.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/*
 * Esta es una de las clases principales que proporcionan configurarión a Spring MVC. 
 * Esta clase habitualmente se importa al añadir @EnableWebMvc a una clase @Configuration. 
 * Una opción alternativa y más avanzada es extender directamente de esta clase y modificar
 * algunos de sus métodos como nos convenga.
 * 
 */
@Configuration
public class CustomWebMvcConfigurationSupport extends
		WebMvcConfigurationSupport {

	@Autowired
	private HandlerInterceptor localeChangeInterceptor;

	@Override
	@Bean
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
		RequestMappingHandlerMapping handlerMapping = super.requestMappingHandlerMapping();
		/*
		 * Controla si soporta correspondencia de patrones con sufijo (".*")
		 * dentro de las peticiones
		 * Si es true el patrón "/estudiantes" también filtrará "/estudiantes.*".
		 */
		handlerMapping.setUseSuffixPatternMatch(false);
		/*
		 * Controla si soporta URLs acabadas con '/'.
		 * Si es true el patrón "/users" también filtrará "/users/".
		 */
		handlerMapping.setUseTrailingSlashMatch(false);
		/*
		 * Controla si el contenido de la URL separado por ";" debe ser o no eliminado.
		 * Por defecto es true. Pero debe ser false si queremos utilizar Matrix Variables.
		 */
		handlerMapping.setRemoveSemicolonContent(false);

		/*
		 * Intercepta cambios de Locale
		 */
		handlerMapping
				.setInterceptors(new Object[] { localeChangeInterceptor });

		return handlerMapping;
	}

}
