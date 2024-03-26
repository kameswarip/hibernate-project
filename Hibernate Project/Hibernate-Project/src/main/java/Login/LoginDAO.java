package Login;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class LoginDAO {

	 private final SessionFactory sessionFactory;

	    public LoginDAO(SessionFactory sessionFactory) {
	        this.sessionFactory = sessionFactory;
	    }

	    public void save(Login login) {
	        Session session = sessionFactory.getCurrentSession();
	        session.beginTransaction();
	        session.persist(login);
	        session.getTransaction().commit();
	    }
	    
	    public Login findByEmail(String email) {
	        Session session = sessionFactory.getCurrentSession();
	        session.beginTransaction();
	        try {
	            return session.createQuery("SELECT l FROM Login l WHERE l.email = :email", Login.class)
	                    .setParameter("email", email)
	                    .getSingleResult();
	        } catch (NoResultException e) {
	            return null;
	        } finally {
	            session.getTransaction().commit();
	        }
	    }

	    public SessionFactory getSessionFactory() {
	        return sessionFactory;
	    }
	    
		public boolean validateLogin(String email, String password) {
			try (Session session = sessionFactory.openSession()){
				session.beginTransaction();
			     Object login = session.createQuery("FROM Login WHERE username = :email AND password = :password").setParameter("email", email).setParameter("Password", password).uniqueResult();
				session.getTransaction().commit();
				return login != null;
			}catch(Exception e) {
				e.printStackTrace();
			}
			return false;
		}
		
		public boolean isMemberRegister(String email) {
			try(Session session = sessionFactory.openSession()){
				session.beginTransaction();
				int count = session.createQuery("Select count(r) From Registration r Where r.username = :email",int.class).setParameter("email", email).uniqueResult();
				session.getTransaction().commit();
				return count>0;
				}catch(Exception e) {
					e.printStackTrace();
					return false;
				}
			
		}
}
