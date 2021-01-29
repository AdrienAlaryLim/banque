package fr.formation.m2.spring.banque.metier;

import java.util.List;

import fr.formation.m2.spring.banque.bdd.dao.CompteDAO;
import fr.formation.m2.spring.banque.bdd.dao.UserDAO;
import fr.formation.m2.spring.banque.bdd.dao.exec.Find;
import fr.formation.m2.spring.banque.bdd.dao.exec.Update;
import fr.formation.m2.spring.banque.bdd.entities.Compte;
import fr.formation.m2.spring.banque.bdd.entities.User;
import fr.formation.m2.spring.banque.bdd.util.BanqueException;

public class BanqueServiceImpl implements BanqueService {

	//private ClientDAO clientDAO;
	private UserDAO userDAO;
	private CompteDAO compteDAO;
	
	
//	@Override
//	public Client authentifierUser(String idClient, String password) throws BanqueException {
//		try {
//			Client client = Find.findClient(idClient);
//			if(client != null && client.getMotDePasse().equals(password)) {
//				return client;
//			}else {
//				throw new BanqueException();
//			}
//		}catch (Exception e) {
//			e.printStackTrace();
//			throw new BanqueException("Erreur d'authentification");
//		}
//	}
	
	@Override
	public User authentifierUser(String username, String password) throws BanqueException {
		try {
			User user = userDAO.rechercherUserParUsername(username);
			if(user != null && user.getPassword().equals(password)) {
				return user;
			}else {
				throw new BanqueException();
			}
		}catch (Exception e) {
			e.printStackTrace();
			throw new BanqueException("Erreur d'authentification");
		}
	}

	@Override
	public List<Compte> mesComptes(Long idUser) throws BanqueException {
		try {
			User user = Find.findUserById(idUser.toString());
			return compteDAO.rechercherComptesUsers(user);
		}catch (Exception e) {
			e.printStackTrace();
			throw new BanqueException("Erreur lors de la recupération de vos comptes");
		}
	}

	@Override
	public void virement(String numDebiteur, String numCrediteur, String montant) throws BanqueException {
		try {			
			
			Compte compteDebiteur = Find.findCompte(numDebiteur.toString());
			Compte compteCrediteur = Find.findCompte(numCrediteur.toString());
			
			if(compteDebiteur.getSolde() - new Long(montant) < new Double(0)) {
				throw new Exception("Le montant à débiter est trop élevé");
			}
			
			compteDebiteur.setSolde((Double) compteDebiteur.getSolde() - new Long(montant));
			compteCrediteur.setSolde((Double) compteCrediteur.getSolde() + new Long(montant));
			
			Update.modifyCompte(compteCrediteur);
			Update.modifyCompte(compteDebiteur);
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new BanqueException("Erreur lors de l'enregistrement de votre virement");
		}
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public void setCompteDAO(CompteDAO compteDAO) {
		this.compteDAO = compteDAO;
	}

//	public void setClientDAO(ClientDAO clientDAO) {
//		this.clientDAO = clientDAO;
//	}
	
	
}
