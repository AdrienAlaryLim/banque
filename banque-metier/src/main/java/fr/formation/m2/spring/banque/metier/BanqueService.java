package fr.formation.m2.spring.banque.metier;

import java.util.List;

import fr.formation.m2.spring.banque.bdd.entities.Compte;
import fr.formation.m2.spring.banque.bdd.entities.User;
import fr.formation.m2.spring.banque.bdd.util.BanqueException;

public interface BanqueService {
	//public abstract Client authentifierClient(String idClient, String password)throws BanqueException;
	
	public abstract User authentifierUser(String username, String password)throws BanqueException;
	
	public abstract List<Compte> mesComptes (Long idUser) throws BanqueException;
	
	public abstract void virement(String numDebiteur, String numCrediteur, String montant)throws BanqueException;
}
