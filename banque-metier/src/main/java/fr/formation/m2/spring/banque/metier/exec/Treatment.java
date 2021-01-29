package fr.formation.m2.spring.banque.metier.exec;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.formation.m2.spring.banque.bdd.entities.User;
import fr.formation.m2.spring.banque.bdd.util.BanqueException;
import fr.formation.m2.spring.banque.metier.BanqueService;

public class Treatment {
	private static ApplicationContext contexte;
	
	/**
	 * Authentifie l'utilisatur
	 * @param username
	 * @param password
	 */
	public static User connecterUser(String username, String password) {
		contexte = new ClassPathXmlApplicationContext("metier-context.xml");
		
		User user = new User();
		BanqueService banqueService = (BanqueService) contexte.getBean("BanqueService");
		try {
			user = banqueService.authentifierUser(username, password);
		}catch(BanqueException e)
		{
			System.out.println("Erreur lors de la recherche du client");
			e.printStackTrace();
		}
		return user;
	}
	
	public static void virer(String debiteur, String crediteur, String montant){
		contexte = new ClassPathXmlApplicationContext("metier-context.xml");
		
		BanqueService banqueService = (BanqueService) contexte.getBean("BanqueService");
		try {
			banqueService.virement(debiteur, crediteur, montant);
		}catch(BanqueException e)
		{
			System.out.println("Erreur lors de la recherche du client");
			e.printStackTrace();
		}
	}
	
}
