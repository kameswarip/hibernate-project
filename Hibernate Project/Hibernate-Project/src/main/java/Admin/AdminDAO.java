package Admin;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.anp.Registration.Registration;
import com.anp.books.books;

public class AdminDAO {
	 private SessionFactory sessionFactory;

	    public AdminDAO(SessionFactory sessionFactory) {
	        this.sessionFactory = sessionFactory;
	    }

	    public void insertAdmin(Admin admin) {
	        Session session = null;
	        Transaction transaction = null;
	        try {
	            session = sessionFactory.openSession();
	            transaction = session.beginTransaction();
	            session.save(admin);
	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	        } finally {
	            if (session != null) {
	                session.close();
	            }
	        }
	    }
	    
	    public void saveAdmin(Admin admin) {
	        Session session = sessionFactory.getCurrentSession();
	       
	        session.beginTransaction();
	        session.persist(admin);
	        session.getTransaction().commit();
	    }
	    
}