package es.smartcoding.ssmvcp3.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import es.smartcoding.ssmvcp3.RootPackage;


//importa WebMvcConfigurationSupport 
@EnableWebMvc
@Configuration
@ComponentScan(basePackageClasses = { RootPackage.class }, excludeFilters = { @Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class) })
public class RootContextConfig extends WebMvcConfigurerAdapter {

	/**
	 * ReloadableResourceBundleMessageSource detecta y carga ficheros de propiedades y XML 
	 * útiles para la internacionalizacion de mensajes.
	 */
	public @Bean MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		// Actualiza el fichero en cada lectura. No usar en producción, usar -1
		// valor por defecto !!!
		messageSource.setCacheSeconds(0);
		messageSource.setBasename("/resources/i18n/messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	/**
	 * Interceptor que permite cambiar el locale actual en función de un parámetro de la petición.
	 * Por defecto el parámtetro se llama 'locale'
	 */
	@Bean
	public HandlerInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("language");
		return localeChangeInterceptor;
	}

	/**
	 * Añade un interceptor a Spring MVC para el pre y post procesamiento 
	 * de invocaciones de métodos de controladores
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}

	/**
	 * Por defecto implementa AcceptHeaderLocaleResolver, 
	 * que simplemente usa el locale de la petición proporcionado por la 
	 * correspondiente cabecera HTTP.
	 * 
	 * Evita el mensaje error: 'Cannot change HTTP accept header - use a different locale
	 * resolution strategy'
	 * 
	 * CookieLocaleResolver is apropiado en aplicaciones sin estado que no utilizan una sesión.
	 * 
	 * @return
	 */
	// public @Bean LocaleResolver localeResolver() {
	// CookieLocaleResolver localeResolver = new CookieLocaleResolver();
	// localeResolver.setDefaultLocale(Locale.ENGLISH);
	// localeResolver.setCookieName("localeCookie");
	// localeResolver.setCookieMaxAge(4800);
	// return localeResolver;
	// }

	/**
	 * Por defecto implementa AcceptHeaderLocaleResolver, 
	 * que simplemente usa el locale de la petición proporcionado por la 
	 * correspondiente cabecera HTTP.
	 * 
	 * Evita el mensaje error: 'Cannot change HTTP accept header - use a different locale
	 * resolution strategy'
	 * 
	 * SessionLocaleResolver is apropiado si se necesita una sesión.
	 * 
	 * @return
	 */
	@Bean(name = "localeResolver")
	public LocaleResolver sessionLocaleResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(new Locale("en"));

		return localeResolver;
	}

}

// @Override
// public void addViewControllers(ViewControllerRegistry registry) {
// registry.addViewController("/").setViewName("home");
// }
