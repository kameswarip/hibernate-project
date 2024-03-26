package com.anp.Registration;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.anp.books.booksDAO;

import Admin.Admin;
import Admin.AdminDAO;
import Login.Login;
import Login.LoginDAO;

public class Main {

		public static void main(String[] args) {

	        // Creating SessionFactory
			Configuration con = new Configuration().configure();
	      
			SessionFactory sf = con.buildSessionFactory();

//			LoginDAO logindao = new LoginDAO(sf);
//			RegistrationDAO regdao = new RegistrationDAO(sf);
//			booksDAO booksdao = new booksDAO(sf);
//			
	        Scanner sc = new Scanner(System.in);

	        try {
	        	LoginDAO logindao = new LoginDAO(sf);
				RegistrationDAO regdao = new RegistrationDAO(sf);
				booksDAO booksdao = new booksDAO(sf);
	            int choice;
	            do {
	                System.out.println("Library Management :");

	                System.out.println("1.Registration :");
	                System.out.println("2.Login Registration :");
	                System.out.println("3.Admin Registration");
	                System.out.println("4.Member");
	                System.out.println("5.Books");
	                System.out.println("6.Logout");
	              
	                System.out.println("Enter your choice :");
	                choice = sc.nextInt();
	                sc.nextLine();

	                switch (choice) {
	                    case 1:
	                    	register(sc, regdao);
	                        break;
	                        
	                    case 2:
	                    	login(sc, logindao);
	                        break;
	                      
	                    case 3:
	                    	book(sc, booksdao);
	                        break;
//	                    case 2:
//	                    	admin(sc,admindao);
//	                    	break;

	                    case 4:
	                        // Call the method to exit
	                        System.out.println("Exiting..");
	                        break;

	                    default:
	                        System.out.println("Invalid choice please try again..");
	                        break;
	                }

	            } while (choice != 5);

	        } finally {
	            // Closing resources
	            sf.close();
	            sc.close();
	        }
	    }
	    
	    private static void book(Scanner sc, booksDAO booksdao) {
			
			
		}
//
//		private static Configuration addAnnotatedClass(Class<Registration> class1) {
//			// TODO Auto-generated method stub
//			return null;
//		}

		// Method to perform registration
	    private static void register(Scanner sc, RegistrationDAO regDao) {
	        System.out.println("Enter username:");
	        String username = sc.nextLine();
	        System.out.println("Enter email:");
	        String email = sc.nextLine();
	        System.out.println("Enter password:");
	        String password = sc.nextLine();
	        sc.nextLine(); // Consume newline character

	        // Creating a new Registration object
	        Registration registration = new Registration(0, username, email,password);

	        // Inserting the registration into the database
	        regDao.saveregistration(registration);

	        System.out.println("Registration successful!");
	        
	        System.out.println(" -------------------------");
	        
	    }
	    	

	    private static void admin(Scanner sc, AdminDAO adminDao) {
	        System.out.println("Enter username:");
	        String username = sc.nextLine();
	        System.out.println("Enter email:");
	        String email = sc.nextLine();
	        System.out.println("Enter password:");
	        String password = sc.nextLine();

	        // Creating a new Admin object
	        Admin admin = new Admin(0, username, email, password);
	        // Inserting the admin into the database
	        adminDao.insertAdmin(admin);

	        System.out.println("Admin Registration successful!");
	        
	        System.out.println(" -------------------------");
	    }
	    
	    public static void login(Scanner sc, LoginDAO logindao) {
	        System.out.println("Login to enter ");
	        System.out.println("Enter email:");
	        String email = sc.nextLine();
	        System.out.println("Enter password :");
	        String password = sc.nextLine();

	     
	        Login login = logindao.findByEmail(email); // Corrected method call

	        if (login != null && login.Password().equals(password)) {
	            System.out.println("Login successful!");
	        } else {
	            System.out.println("Invalid username or password. Please try again.");
	        }
	    }

}
