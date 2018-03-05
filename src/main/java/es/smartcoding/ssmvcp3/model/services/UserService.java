package es.smartcoding.ssmvcp3.model.services;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.smartcoding.ssmvcp3.model.entities.UserEntity;
import es.smartcoding.ssmvcp3.model.repositories.UserRepository;

/*
 * Business Service Facade. 
 * Especialización de @Component
 */
@Service
/*
 * Aplicable a clases y métodos.
 * Los atributos propagation e isolation toma los valores por defecto
 */
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = true)
public class UserService {
	
	private static final Logger logger = LoggerFactory
			.getLogger(UserService.class);


	@Autowired
	private UserRepository userRepository;

	public UserEntity findByName(String name) {
		return userRepository.findByName(name);
	}

	public Collection<UserEntity> findAll() {
		return userRepository.findAll();
	}

	@Transactional(readOnly = false)
	public UserEntity save(UserEntity user) {
		return userRepository.save(user);
	}

	public UserEntity findOne(long id) {
		return userRepository.findOne(id);
	}

	@Transactional(readOnly = false)
	public void delete(long id) {
		userRepository.delete(id);
	}

	@Transactional(readOnly = false)
	public void capitalizeAll() {
		userRepository.capitalizeAll();
	}
	
	public void longTimeService() {
		logger.info("entering longtimeservice");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("exiting longtimeservice");
	}
}

// @Autowired
// private JdbcTemplate jdbcTemplate; 