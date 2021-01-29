package fr.formation.m2.spring.banque.bdd.dao;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.formation.m2.spring.banque.bdd.entities.Authorities;
import fr.formation.m2.spring.banque.bdd.util.BanqueException;

public interface AuthoritiesDAO {
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=BanqueException.class)
	public abstract void ajouterAuthority (Authorities authority) throws BanqueException;
}
