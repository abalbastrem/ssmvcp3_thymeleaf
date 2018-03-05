package es.smartcoding.ssmvcp3.model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@NamedQueries(value = { 
		@NamedQuery(name = "capitalizeAll", query = "UPDATE UserEntity user SET user.name = upper(user.name)"), 
		})
/*
 * Una clase @Entity se corresponde con una tabla del modelo relacional
 */
@Entity
@Table(name = "users")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "name", length = 32)
	private String name;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateOfCreation;
	@OneToOne(mappedBy = "user")
	private ComputerEntity computer;
	@ManyToOne
	private DepartmentEntity department;
	@ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
	private Collection<RoleEntity> roles = new ArrayList<RoleEntity>();

	public UserEntity() {
	}

	public UserEntity(long id, String name) {
		this.id = id;
		this.name = name;
		dateOfCreation = new Date();
	}

	public void add(RoleEntity role) {
		roles.add(role);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateOfCreation() {
		return dateOfCreation;
	}

	public void setDateOfCreation(Date dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}

	@Override
	public String toString() {
		return String.format(
				"UserEntity [id=%s, name=%s, department=%s, roles=%s]", id,
				name, department, roles);
	}

}
