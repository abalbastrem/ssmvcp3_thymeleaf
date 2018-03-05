package es.smartcoding.ssmvcp3.model.repositories;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

/**
 * Fíjate que esta clase no implementa la interfaz UserRepository, Spring Data
 * JPA sigue siendo responable de implementarla.
 * 
 * La clase 'UserRepositoryImpl' implementa la intertaz 'ExtendedUserRepository'
 * con los métodos adiciones cuya implementación es responsabilidad nuestra.
 * 
 * DEBE ASEGURARTE QUE la interfaz UserRepository extienda ambas interfaces:
 * 'JpaRepository' y 'ExtendedUserRepository'.
 * 
 * @author pep
 *
 */
@Repository
public class UserRepositoryImpl implements ExtendedUserRepository {

	/*
	 * Expresa una dependencia de un objeto EntityManger gestionado por el
	 * contenedor y su contexto de persistencia asociado.
	 */
	@PersistenceContext
	private EntityManager entityManager;

	/*
	 * @Inject es similar a @Autowire
	 * 
	 * @Autowired por si hace falta...
	 */
	@Inject
	private UserRepository userRepository;

	@Modifying
	@Override
	public void capitalizeAll() {
		Query query = entityManager.createNamedQuery("capitalizeAll");
		query.executeUpdate();
	}

}