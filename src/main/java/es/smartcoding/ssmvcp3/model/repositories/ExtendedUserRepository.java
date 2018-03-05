package es.smartcoding.ssmvcp3.model.repositories;

/**
 * Los respositorios de Spring Data pueden ser ampliados
 * 
 * En algún momento es posible que queramos algun funcionalidad que 
 * no proporciona Spring automaticamente o que no pueda ser descrita 
 * mediante la convención de nombres de métodos de Spring Data y ni
 * tan siquiera con métodos @Query
 * 
 * En estos casos siempre nos queda el recurso de trabajar con el objeto
 * EntityManage directamente, como en los viejos tiempos, pero dejando a
 * Spring hacer el trabajo repetitivo que sabe hacer muy bien.
 * 
 * Cuando Spring Data JPA genera la implementación de un repositoril,
 * también busca una clase con el mismo nombre pero acabada en 'Impl' 
 * por defecto, aunque es configurable mediante el atributo
 * 'repositoryImplementationPostfix' de @EnableJpaRepositories.
 * 
 * Si la clase existe, Spring Data combina sus métodos con los métodos
 * implemetnados por Spring Data JPA.
 * 
 * AQUI PONEMOS LOS METODOS ADICIONALES QUE QUEREMOS PROPORCIONAR
 * 
 * @author pep
 *
 */

public interface ExtendedUserRepository {

	/**
	 * Enable all users
	 */
	void capitalizeAll();

}