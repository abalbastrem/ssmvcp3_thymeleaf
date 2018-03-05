package es.smartcoding.ssmvcp3.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/*
 * Esta clase sustituye al fichero de configurarión 'web.xml'
 */
public class WebAppInitializer extends
		AbstractAnnotationConfigDispatcherServletInitializer {

	/**
	 * Este método sustituye la configuración del fichero 'root-context.xml'
	 * Root Context: define recursos compartidos visibles a todos los componentes
	 * Web del Contexto Global
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { RootContextConfig.class };

	}

	/**
	 * Este método sustituye la configuración del fichero 'servlet-context.xml'
	 * Define la infraestructura del Contexto del DispatcherServlet: 
	 * configura este servlet para que responda a las peticiones Web
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { DispatcherServletConfig.class,
				CustomWebMvcConfigurationSupport.class };

	}

	/**
	 * Este método hace las funciones de la etiqueta '<serlvet-mapping>' del
	 * fichero 'web.xml' Mapea el servlet 'dispatcher' con URL's a las que
	 * responder
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}
