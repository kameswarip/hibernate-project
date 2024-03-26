package com.anp.Registration;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;



public class RegistrationDAO {

    private SessionFactory sessionFactory;

    public RegistrationDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveregistration(Registration registration) {
        Session session = sessionFactory.getCurrentSession();
       
        session.beginTransaction();
        session.persist(registration);
        session.getTransaction().commit();
    }
    
    public  boolean isMemRegisterExists(String email) {
        try (
            Session session = sessionFactory.openSession()){
            session.beginTransaction();
            int count = session.createQuery("Select count(r) from registration r where r.email = :email",int.class).setParameter("email", email).getSingleResult();
            session.getTransaction().commit();
            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
