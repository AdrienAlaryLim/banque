package fr.formation.m2.spring.banque.bdd.dao.exec;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.formation.m2.spring.banque.bdd.dao.CompteDAO;
import fr.formation.m2.spring.banque.bdd.dao.UserDAO;
import fr.formation.m2.spring.banque.bdd.entities.Compte;
import fr.formation.m2.spring.banque.bdd.entities.User;
import fr.formation.m2.spring.banque.bdd.util.BanqueException;

public class Create {

	private static ApplicationContext contexte;
	
	/**
	 * Créé le user demandé dans la base de données
	 * @param paramUser
	 */
	public static void createUser(User paramUser) {
		
		contexte = new ClassPathXmlApplicationContext("bdd-context.xml");
		
		UserDAO userDAO = (UserDAO) contexte.getBean("userDAO");
		try {
			userDAO.ajouterUser(paramUser);
		}catch(BanqueException e)
		{
			System.out.println("Erreur création du user");
			e.printStackTrace();
		}
	}
	
	/**
	 * Créé le compte demandé dans la base de données
	 * @param paramCompte
	 */
	public static void createCompte(Compte paramCompte) {
		
		contexte = new ClassPathXmlApplicationContext("bdd-context.xml");
		
		CompteDAO compteDAO = (CompteDAO) contexte.getBean("compteDAO");
		try {
			compteDAO.ajouterCompte(paramCompte);
		}catch(BanqueException e)
		{
			System.out.println("Erreur création du compte");
			e.printStackTrace();
		}
	}
	
}
