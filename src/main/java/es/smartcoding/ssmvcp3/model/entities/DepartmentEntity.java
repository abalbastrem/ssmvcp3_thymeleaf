package es.smartcoding.ssmvcp3.model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/*
 * Una clase @Entity se corresponde con una tabla del modelo relacional
 */
@Entity
@Table(name = "departments")
public class DepartmentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "ref", length = 32)
	private String ref;
	@OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
	Collection<UserEntity> users = new ArrayList<UserEntity>();

	public DepartmentEntity() {
	}

	public DepartmentEntity(long id, String ref) {
		this.id = id;
		this.ref = ref;
	}

	public void add(UserEntity user) {
		users.add(user);
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

	public Collection<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(Collection<UserEntity> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return String.format("DepartmentEntity [id=%s, ref=%s]", id, ref);
	}
}
