package fr.formation.m2.spring.banque.bdd.dao.exec;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.formation.m2.spring.banque.bdd.dao.CompteDAO;
import fr.formation.m2.spring.banque.bdd.dao.UserDAO;
import fr.formation.m2.spring.banque.bdd.entities.Compte;
import fr.formation.m2.spring.banque.bdd.entities.User;
import fr.formation.m2.spring.banque.bdd.util.BanqueException;

public class Update {

	private static ApplicationContext contexte;
	
	/**
	 * Modifie le user avec l'identifiant renseigné
	 * @param idUser
	 */
	public static void updateUser(User user) {
		contexte = new ClassPathXmlApplicationContext("bdd-context.xml");
		
		UserDAO userDAO = (UserDAO) contexte.getBean("userDAO");
		
		try {
			System.out.println("Try to find user by ID");
			userDAO.rechercherUserParId(Long.toString(user.getId()));
			
			try {
				System.out.println("Try to update user");
				userDAO.modifierUser(user);
			}catch(BanqueException e)
			{
				System.out.println("Erreur lors de la modification du user");
				e.printStackTrace();
			}
		}catch(BanqueException e)
		{
			System.out.println("Erreur lors de la recherche du user");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Modifie le compte passé en paramètre
	 * @param compte
	 */
	public static void modifyCompte(Compte compte) {
		
		contexte = new ClassPathXmlApplicationContext("bdd-context.xml");
		
		CompteDAO compteDAO = (CompteDAO) contexte.getBean("compteDAO");
		try {
			compteDAO.modifierCompter(compte);
		}catch(BanqueException e)
		{
			System.out.println("Erreur création du user");
			e.printStackTrace();
		}
	}	
}
