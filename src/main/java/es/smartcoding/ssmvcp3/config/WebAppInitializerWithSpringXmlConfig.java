package es.smartcoding.ssmvcp3.config;

/*
 * Esta clase sustituye el fichero de configuración 'web.xml'
 * pero configura Spring con XML.
 * 
 */

public class WebAppInitializerWithSpringXmlConfig {
//implements WebApplicationInitializer {
	
	/*
	@Override
	public void onStartup(ServletContext container) {
		XmlWebApplicationContext appContext = new XmlWebApplicationContext();
		appContext.setConfigLocations("/WEB-INF/spring/root-context.xml",
				"/WEB-INF/spring/appServlet/servlet-context.xml");

		ServletRegistration.Dynamic dispatcher = container.addServlet(
				"dispatcher", new DispatcherServlet(appContext));

		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
	}
	*/


	/*
	@Override
	public void onStartup(ServletContext container) {

		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();

		rootContext.register(RootContextConfig.class);

		container.addListener(new ContextLoaderListener(rootContext));

		AnnotationConfigWebApplicationContext dispatcherServletContext = new AnnotationConfigWebApplicationContext();

		dispatcherServletContext.register(DispatcherServletConfig.class);

		DispatcherServlet dispatcherServlet = new DispatcherServlet(
				dispatcherServletContext);

		ServletRegistration.Dynamic dispatcher = container.addServlet(
				"dispatcher", dispatcherServlet);

		dispatcher.addMapping("/");
	}
	*/

}

