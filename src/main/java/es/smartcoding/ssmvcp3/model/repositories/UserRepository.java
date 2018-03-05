package es.smartcoding.ssmvcp3.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.smartcoding.ssmvcp3.model.entities.UserEntity;

/**
 * 
 * Spring Data Repository
 * 
 * El objetivo de la abstracción Spring Data repository es el de reducir
 * significativamente la cantidad de codigo rutinario necesario para implementar la
 * capa de persistencia.
 * 
 * Definir esta interfaz tiene dos objetivos:
 * 
 * Primero, por el hecho de extender JpaRepository obtenemos la implementación
 * automática de los métods CRUD que nos permite guardar, elimier un usuario etc.
 * 
 * Segundo, Spring escaneará el classpath y creará de forma automática un bean
 * de tipo 'UserRepository'.
 * 
 * Spring implementara todos los interfaces JpaRepository al inicio de la aplicación.
 * 
 * @author pep
 *
 */

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<UserEntity, Long>,
		ExtendedUserRepository {

	UserEntity findByName(String name);

}
