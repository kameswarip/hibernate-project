package com.anp.books;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.anp.Registration.Registration;

public class booksDAO {	
		
		private SessionFactory sessionFactory;

	    public booksDAO(SessionFactory sessionFactory) {
	        this.sessionFactory = sessionFactory;
	    }

	    public void save(books books) {
	        try (Session session = sessionFactory.openSession()) {
	            Transaction tx = session.beginTransaction();
	            session.saveOrUpdate(books);
	            tx.commit();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public void insertBook(int id, String name, LocalDate assuredDate, LocalDate returnDate,LocalDate renewalDate) {
	        books books = new books(id, name, assuredDate, returnDate,renewalDate);
	        save(books);
	    }

	    public Optional<books> findById(int bookId) {
	        try (Session session = sessionFactory.openSession()) {
	            books books = session.get(books.class, bookId);
	            if (books != null) {
	                // Initialize lazy associations if needed
	                // Hibernate.initialize(book.getSomeLazyAssociation());
	                return Optional.of(books);
	            } else {
	                return Optional.empty();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            return Optional.empty();
	        }
	    }
	    public List<books> findAll() {
	        try (Session session = sessionFactory.openSession()) {
	            Query<books> query = session.createQuery("from books", books.class);
	            return query.list();
	        } catch (Exception e) {
	            e.printStackTrace();
	            return List.of();
	        }
	    }

	    public void updatebook(int bookId, String bookName, LocalDate assuredDate, LocalDate returnDate) {
	        try (Session session = sessionFactory.openSession()) {
	            Transaction tx = session.beginTransaction();
	            books books = session.get(books.class, bookId);
	            if (books != null) {
	                books.setBook_Name(bookName);
	                books.setAssured_Date(assuredDate);
	                books.setReturn_Date(returnDate);
	                books.setRenewal_Date(returnDate);
	                session.update(books);
	            }
	            tx.commit();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    public void remove(int bookId) {
	        try (Session session = sessionFactory.openSession()) {
	            Transaction tx = session.beginTransaction();
	            books books = session.get(books.class, bookId);
	            if (books != null) {
	                session.delete(books);
	            }
	            tx.commit();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    public boolean isMemberRegistered(String username) {
	        try (Session session = sessionFactory.openSession()) {
	            Query<Registration> query = session.createQuery("SELECT r FROM Registration r WHERE r.username = :username", Registration.class);
	            query.setParameter("username", username);
	            List<Registration> registrations = query.list();
	            return !registrations.isEmpty();
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	    public void performActionIfRegistered(String username, Runnable action) {
	        if (isMemberRegistered(username)) {
	            action.run();
	        } else {
	            System.out.println("Access denied. Only registered members can access this functionality.");
	        }
	    }
	
}