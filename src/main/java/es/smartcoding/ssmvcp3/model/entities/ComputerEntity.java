package es.smartcoding.ssmvcp3.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/*
 * Una clase @Entity se corresponde con una tabla del modelo relacional
 */
@Entity
@Table(name = "computers")
public class ComputerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "ref", length = 32)
	private String ref;
	@OneToOne
	private UserEntity user;

	public ComputerEntity() {
	}

	public ComputerEntity(long id, String ref) {
		this.id = id;
		this.ref = ref;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return String.format("ComputerEntity [id=%s, ref=%s, user=%s]", id,
				ref, user);
	}

}
