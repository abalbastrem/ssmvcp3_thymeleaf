package es.smartcoding.ssmvcp3.model;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import es.smartcoding.ssmvcp3.RootPackage;

/*
 * Indica que una clase declara uno o más métodos @Bean
 * y que puede ser procesada por el contenedor de Spring 
 * para generar definiciones de esos beans en tiempo de ejecucción
 */
@Configuration
/*
 * Activa los repositorios JPA
 */
@EnableJpaRepositories(basePackageClasses = { RootPackage.class })
/*
 * Activa la gestion de transacciones de Spring con anotaciones
 */
@EnableTransactionManagement
@PropertySources({ @PropertySource(value = "classpath:application.properties"), })
public class PersistenceContext {

	/*
	 * Interfaz que representa el entorno en el cual se está ejecutando la
	 * aplicación
	 */
	@Autowired
	Environment env;

	/**
	 * Orígen de datos
	 * 
	 * @return a data source
	 */
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {

		/* Apache Commons DataSource */
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		dataSource.setUsername(env.getProperty("jdbc.username"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		dataSource.addConnectionProperty("ValidationQuery", "SELECT 1");

		return dataSource;
	}

	/**
	 * JPA Entity Manager Factory
	 * 
	 * @param dataSource
	 * @return an EntityManagerFactory
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {

		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		localContainerEntityManagerFactoryBean.setDataSource(dataSource);
		/*
		 * SPI (Service Provider Interface) interfaz que permite conectar
		 * EntityManagerFactories de terceras empresas
		 * 
		 * HibernateJpaVendorAdapter es la implementación de Hibernate EntityManager
		 */
		localContainerEntityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		/*
		 * Escanea el paquete donde se encuentra la interfaz PersistenceContext y subpaquetes
		 */
		localContainerEntityManagerFactoryBean.setPackagesToScan(PersistenceContext.class.getPackage().getName());
		Properties jpaProperties = new Properties();

		// El dialecto permite a Hibernate crear SQL optimizado para la base de datos actual
		jpaProperties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));

		// Especifica la acción que se invoca en la base de datos cuando se crea o se cierra el objeto SessionFactory: none, update, drop-create, create
		jpaProperties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));

		// Cuando es true, Hibernate escribe toda las órdenes SQL en la consola
		jpaProperties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));

		// Cuando es true, Hibernate formateara las ordenes SQL escritas en la consola
		jpaProperties.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));

		localContainerEntityManagerFactoryBean.setJpaProperties(jpaProperties);

		return localContainerEntityManagerFactoryBean;
	}

	/*
	 * Este gestor de transacciones es apropiado para aplicaciones que utilizan
	 * un único JPA EntityManagerFactory para acceso a los datos transaccional, 
	 * como es nuestro caso
	 * 
	 */
	@Bean
	JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}

	/*
	 * La clase JdbcTemplate es central en el núcleo del paquete JDBC.
	 * Gestiona la creación y liberación de recursos, lo que ayuda a los
	 * programadores a evitar errores comunes como olvidar cerrar una conexión.
	 * 
	 * La clase JdbcTemplate hace todo el trabajo 'sucio' como la creación
	 * y ejecución de statements. También captura las excepciones JDBC y las
	 * traduce a excepciones más descriptivas del paquete org.springframework.dao
	 * 
	 */
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

}

/* c3p0 DataSource */
// ComboPooledDataSource dataSource = new ComboPooledDataSource();
// try {
// dataSource.setDriverClass(env.getProperty("jdbc.driverClassName"));
// } catch (PropertyVetoException e) {
// e.printStackTrace();
// throw new RuntimeException(e);
// }
// dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
// dataSource.setUser(env.getProperty("jdbc.username"));
// dataSource.setPassword(env.getProperty("jdbc.password"));

/* Hikari DataSource */
// HikariConfig hikariConfig = new HikariConfig();
// hikariConfig
// .setDriverClassName(env.getProperty("jdbc.driverClassName"));
// hikariConfig.setJdbcUrl(env.getProperty("jdbc.url"));
// hikariConfig.setUsername(env.getProperty("jdbc.username"));
// hikariConfig.setPassword(env.getProperty("jdbc.password"));
//
// hikariConfig.setMaximumPoolSize(5);
// hikariConfig.setConnectionTestQuery("SELECT 1");
// hikariConfig.setPoolName("springHikariCP");
//
// hikariConfig.addDataSourceProperty("dataSource.cachePrepStmts", "true");
// hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSize",
// "250");
// hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit",
// "2048");
// hikariConfig.addDataSourceProperty("dataSource.useServerPrepStmts",
// "true");
//
// HikariDataSource dataSource = new HikariDataSource(hikariConfig);

// dataSource.addConnectionProperty("MaxActive", "20");
// dataSource.addConnectionProperty("MaxWait", "60000");
// dataSource.addConnectionProperty("TestWhileIdle", "true");
// dataSource.addConnectionProperty("TimeBetweenEvictionRunsMillis",
// "300000");
// dataSource
// .addConnectionProperty("MinEvictableIdleTimeMillis", "300000");
// dataSource.addConnectionProperty("TestOnBorrow", "true");
