/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbfacade;

import entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Danie
 */
public class CustomerFacade {
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");      
        CustomerFacade facade = CustomerFacade.getCustomerFacade(emf);
        // addCustomer
        Customer c1 = facade.addCustomer("Carl", "Nielsen");
        Customer c2 = facade.addCustomer("Ebbe", "Munk");
        Customer c3 = facade.addCustomer("John", "Munk");
        // findByID:
        System.out.println("findByID: " + facade.findByID(1).getFirstName());
        // findByLastName:
        System.out.println("findByLastName: " + 
                facade.findByLastName(c2.getLastName()).size());
        // getNumberOfCustomers:
        System.out.println("getNumberOfCustomers: " + facade.getNumberOfCustomers());
        // allCustomers:
        System.out.println("allCustomers:");
        for(Customer c : facade.allCustomers()) {
            System.out.println(c);
        }
        
        
    }
    
    private static EntityManagerFactory emf;
    private static CustomerFacade instance;

    private CustomerFacade() {}

    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }
    
    public Customer addCustomer(String firstName, String lastName) {
        Customer customer = new Customer(firstName, lastName);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
            return customer;
        } finally {
            em.close();
        }
    }
    
    public Customer findByID(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Customer customer = em.find(Customer.class, id);
            return customer;
        } finally {
            em.close();
        }
    }
    
    public List<Customer> findByLastName(String lastName) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query = 
                    em.createQuery("SELECT c FROM Customer c WHERE c.lastName = :lastName", Customer.class);
            query.setParameter("lastName", lastName);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public int getNumberOfCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = 
                    em.createQuery("SELECT count(c) FROM Customer c");
            long result = (long)query.getSingleResult();
            return (int)result;
        } finally {
            em.close();
        }
    }
    
    public List<Customer> allCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query =
                    em.createQuery("SELECT c FROM Customer c", Customer.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    

    
}
