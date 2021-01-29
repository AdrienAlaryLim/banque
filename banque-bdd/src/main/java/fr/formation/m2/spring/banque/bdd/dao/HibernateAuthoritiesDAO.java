package fr.formation.m2.spring.banque.bdd.dao;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import fr.formation.m2.spring.banque.bdd.entities.Authorities;
import fr.formation.m2.spring.banque.bdd.util.BanqueException;

public class HibernateAuthoritiesDAO implements AuthoritiesDAO {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void ajouterAuthority(Authorities authority) throws BanqueException {
		try {
			sessionFactory.getCurrentSession().persist(authority);
		}catch(HibernateException e) {
			throw new BanqueException("Erreur d'enregistrement de l'authority");
		}
	}
}
