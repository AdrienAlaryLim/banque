package fr.formation.m2.spring.banque.bdd.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import fr.formation.m2.spring.banque.bdd.entities.User;
import fr.formation.m2.spring.banque.bdd.util.BanqueException;

public class HibernateUserDAO implements UserDAO {

	EntityManager em;
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

//	public void ajouterUser(User user) throws BanqueException {
//		try {
//			user.setEnabled(true);
//			sessionFactory.getCurrentSession().persist(user);
//		}catch(HibernateException e) {
//			throw new BanqueException("Erreur d'enregistrement du user");
//		}
//	}
	
	public void ajouterUser(User user) throws BanqueException {
		try {
			user.setEnabled(true);
			//sessionFactory.getCurrentSession().persist(user);
			sessionFactory.openSession().persist(user);
		}catch(HibernateException e) {
			throw new BanqueException("Erreur d'enregistrement du user");
		}
	}

	public User rechercherUserParUsername(String username) throws BanqueException {
		try {
			return (User) sessionFactory.getCurrentSession().createQuery("from User as u where u.id = 1");
			//return (User) sessionFactory.getCurrentSession().get(User.class, username);
			 
		}catch(HibernateException e) {
			throw new BanqueException("Erreur de chargement du user");
		}
	}

	@SuppressWarnings("unchecked")
	public List<User> rechercherTousLesUsers() throws BanqueException {
		try {
			String hql = "from User as u";
			return sessionFactory.getCurrentSession().createQuery(hql).list();
			//return sessionFactory.getCurrentSession().createCriteria(User.class).list();
		}catch(HibernateException e) {
			throw new BanqueException("Erreur de récupération des users");
		}
	}

	public User rechercherUserParId(String idUser) throws BanqueException {
		try {			
			User user = (User)
				    sessionFactory.openSession().createQuery("select u from User u where u.id=:id")
				           .setString("id", idUser).uniqueResult();
			
			return user;
			
			//Long id = new Long(idUser);
			//String hql = "from User as u where u.id=?";
			//return (User) sessionFactory.getCurrentSession().createQuery(hql).setEntity(0, id);
			
			//String hql = "from Compte as c where c.user=?";
			//Query query = sessionFactory.getCurrentSession().createSQLQuery(hql).setParameter(0, id);
			
			//return (User) sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, id);
			//return sessionFactory.getCurrentSession().createQuery(hql);
			//return (User) sessionFactory.getCurrentSession().get(User.class, id);
			
		}catch(HibernateException e) {
			throw new BanqueException("Erreur de chargement du user");
		}
	}

	public void modifierUser(User user) throws BanqueException {
		try {
			sessionFactory.getCurrentSession().merge(user);
		}catch(HibernateException e){
			throw new BanqueException("Erreur de la modification du user");
		}
	}
}
