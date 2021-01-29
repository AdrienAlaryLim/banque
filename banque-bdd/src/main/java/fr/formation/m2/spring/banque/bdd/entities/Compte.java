package fr.formation.m2.spring.banque.bdd.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="compte")
public class Compte {

	@Id
	@Column(name="numero")
	private long numero;
	
	@Column(name="solde")
	private double solde;

//	@ManyToOne(targetEntity=User.class)
//	@JoinColumn(name="idUser")
//	private User user;

	@ManyToOne(targetEntity=User.class)
	@JoinColumns({
		@JoinColumn(name="username", insertable = false, updatable = false),
		@JoinColumn(name="idUser", insertable = false, updatable = false)
	})
	private User user;
	
	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
