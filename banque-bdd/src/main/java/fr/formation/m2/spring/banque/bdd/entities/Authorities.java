package fr.formation.m2.spring.banque.bdd.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="authorities")
public class Authorities {

	@Id
	@Column(name="username")
	private String username;
	
//	@Id
//	@Column(name="idUser")
//	private String idUser;
	
	@Column(name="authority")
	private String authority;
	
//	@ManyToOne(targetEntity=User.class)
//	@MapsId
//	@JoinColumn(name="username")
//	private User user;
	
	@ManyToOne(fetch=FetchType.LAZY)
	//@MapsId
	@JoinColumns({
		@JoinColumn(name="username", insertable = false, updatable = false),
		@JoinColumn(name="id", insertable = false, updatable = false)
	})
	private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
