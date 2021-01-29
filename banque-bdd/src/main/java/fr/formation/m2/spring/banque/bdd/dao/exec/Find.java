package fr.formation.m2.spring.banque.bdd.dao.exec;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.formation.m2.spring.banque.bdd.dao.CompteDAO;
import fr.formation.m2.spring.banque.bdd.dao.UserDAO;
import fr.formation.m2.spring.banque.bdd.entities.Compte;
import fr.formation.m2.spring.banque.bdd.entities.User;
import fr.formation.m2.spring.banque.bdd.util.BanqueException;

public class Find {
	private static ApplicationContext contexte;
	
	/**
	 * Cherche le user avec l'identifiant renseigné
	 * @param idUser
	 */
	public static User findUserById(String idUser) {
		contexte = new ClassPathXmlApplicationContext("bdd-context.xml");
		
		User user = new User();
		
		UserDAO userDAO = (UserDAO) contexte.getBean("userDAO");
		try {
			user = userDAO.rechercherUserParId(idUser);
		}catch(BanqueException e)
		{
			System.out.println("Erreur lors de la recherche du user");
			e.printStackTrace();
		}
		return user;
	}
	
	/**
	 * Cherche le user avec l'identifiant renseigné
	 * @param idUser
	 */
	public static User findUserByUsername(String username) {
		contexte = new ClassPathXmlApplicationContext("bdd-context.xml");
		
		User user = new User();
		
		UserDAO userDAO = (UserDAO) contexte.getBean("userDAO");
		try {
			user = userDAO.rechercherUserParUsername(username);
		}catch(BanqueException e)
		{
			System.out.println("Erreur lors de la recherche du user");
			e.printStackTrace();
		}
		return user;
	}
	
	
	/*public static User findUser(String idUser) {
		contexte = new ClassPathXmlApplicationContext("bdd-context.xml");
		
		User user = new User();
		
		UserDAO userDAO = (UserDAO) contexte.getBean("userDAO");
		try {
			Long id = new Long(idUser);
			user = userDAO.rechercherUserParId(id);
		}catch(BanqueException e)
		{
			System.out.println("Erreur lors de la recherche du user");
			e.printStackTrace();
		}
		return user;
	}*/
	
	/**
	 * Renvoie la liste de tous les users
	 */
	public static List<User> findAllUsers() {
		contexte = new ClassPathXmlApplicationContext("bdd-context.xml");
		
		List<User> listOfUsers = new ArrayList<User>();
		
		UserDAO userDAO = (UserDAO) contexte.getBean("userDAO");
		try {
			listOfUsers = userDAO.rechercherTousLesUsers();
		}catch(BanqueException e)
		{
			System.out.println("Erreur lors de la recherche de tous les users");
			e.printStackTrace();
		}
		return listOfUsers;
	}
	
	/**
	 * Cherche le compte avec l'identifiant renseigné
	 * @param numeroCompte
	 */
	public static Compte findCompte(String idCompte) {
		contexte = new ClassPathXmlApplicationContext("bdd-context.xml");
		
		Compte compte = new Compte();
		
		CompteDAO compteDAO = (CompteDAO) contexte.getBean("compteDAO");
		try {
			compte = compteDAO.rechercherCompteParNumero(new Long(idCompte));
		}catch(BanqueException e)
		{
			System.out.println("Erreur lors de la recherche du compte");
			e.printStackTrace();
		}
		return compte;
	}

	/**
	 * Renvoie la liste de tous les comptes liés au user renseigné
	 */
	public static List<Compte> findEmbaddedComptes(User user) {
		contexte = new ClassPathXmlApplicationContext("bdd-context.xml");
		
		List<Compte> listOfComptes = new ArrayList<Compte>();
		
		CompteDAO compteDAO = (CompteDAO) contexte.getBean("compteDAO");
		try {
			listOfComptes = compteDAO.rechercherComptesUsers(user);
		}catch(BanqueException e)
		{
			System.out.println("Erreur lors de la recherche des comptes user");
			e.printStackTrace();
		}
		return listOfComptes;
	}
	
	
}
