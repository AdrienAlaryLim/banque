package fr.formation.m2.spring.banque.bdd.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import fr.formation.m2.spring.banque.bdd.entities.Compte;
import fr.formation.m2.spring.banque.bdd.entities.User;
import fr.formation.m2.spring.banque.bdd.util.BanqueException;

public class HibernateCompteDAO implements CompteDAO {
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void ajouterCompte(Compte compte) throws BanqueException {
		try {
			sessionFactory.getCurrentSession().persist(compte);
		}catch(HibernateException e){
			throw new BanqueException("Erreur d'enregistrement du compte");
		}
	}

	public Compte rechercherCompteParNumero(Long numero) throws BanqueException {
		try {
			return (Compte) sessionFactory.getCurrentSession().get(Compte.class, new Long(numero));
		}catch(HibernateException e){
			throw new BanqueException("Erreur de chargement du compte");
		}
	}

	public List<Compte> rechercherComptesUsers(User user) throws BanqueException {
		try {
			String hql = "from Compte as c where c.user=?";
			//return sessionFactory.getCurrentSession().createQuery(hql).setEntity(0, user).list();
			
			return (List<Compte>) sessionFactory.openSession().createQuery("select c from Compte c where c.user.id=:idUser")
	           .setLong("idUser", user.getId()).list();
			
			//return (User) sessionFactory.getCurrentSession().createQuery("from Compte as c where c.user = ?");
			
		}catch(HibernateException e){
			throw new BanqueException("Erreur de récupération des comptes users");
		}
	}

	public void modifierCompter(Compte compte) throws BanqueException {
		try {
			sessionFactory.getCurrentSession().merge(compte);
		}catch(HibernateException e){
			throw new BanqueException("Erreur de la modification du compte");
		}
	}

}
