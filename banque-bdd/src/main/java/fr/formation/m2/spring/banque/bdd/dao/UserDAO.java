package fr.formation.m2.spring.banque.bdd.dao;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.formation.m2.spring.banque.bdd.entities.User;
import fr.formation.m2.spring.banque.bdd.util.BanqueException;

public interface UserDAO {
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=BanqueException.class)
	public abstract void ajouterUser (User user) throws BanqueException;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public abstract User rechercherUserParUsername (String username) throws BanqueException;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public abstract List<User> rechercherTousLesUsers () throws BanqueException;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public abstract User rechercherUserParId (String id) throws BanqueException;
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=BanqueException.class)
	public abstract void modifierUser(User user) throws BanqueException;
}
