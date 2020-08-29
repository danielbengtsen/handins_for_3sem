/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbfacade;

import entity.Customer;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Danie
 */
public class CustomerFacadeTest {
    
    public CustomerFacadeTest() {
    }
    
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.createQuery("DELETE FROM Customer c");
            Date created = new Date();
            em.createNativeQuery("INSERT INTO Customer c (firstName, lastName,"
                    + " created) VALUES (?,?,?)", Customer.class)
                    .setParameter(1, "Carl")
                    .setParameter(2, "Nielsen")
                    .setParameter(3, created)
                    .executeUpdate();
            em.createNativeQuery("INSERT INTO Customer c (firstName, lastName,"
                    + " created) VALUES (?,?,?)", Customer.class)
                    .setParameter(1, "Ebbe")
                    .setParameter(2, "Munk")
                    .setParameter(3, created)
                    .executeUpdate();
            em.createNativeQuery("INSERT INTO Customer c (firstName, lastName,"
                    + " created) VALUES (?,?,?)", Customer.class)
                    .setParameter(1, "John")
                    .setParameter(2, "Munk")
                    .setParameter(3, created)
                    .executeUpdate();
        } finally {
            em.close();
        }
        
    }

    /**
     * Test of addCustomer method, of class CustomerFacade.
     */
    @org.junit.jupiter.api.Test
    public void testAddCustomer() {
        CustomerFacade facade = CustomerFacade.getCustomerFacade(emf);
        System.out.println("addCustomer");
        String firstName = "Johan";
        String lastName = "Madsen";
        Customer customer = facade.addCustomer(firstName, lastName);
        int expResult = 4;
        int result = customer.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of findByID method, of class CustomerFacade.
     */
    @org.junit.jupiter.api.Test
    public void testFindByID() {
        System.out.println("findByID");
        int id = 0;
        CustomerFacade instance = null;
        Customer expResult = null;
        Customer result = instance.findByID(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByLastName method, of class CustomerFacade.
     */
    @org.junit.jupiter.api.Test
    public void testFindByLastName() {
        System.out.println("findByLastName");
        String lastName = "";
        CustomerFacade instance = null;
        List<Customer> expResult = null;
        List<Customer> result = instance.findByLastName(lastName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumberOfCustomers method, of class CustomerFacade.
     */
    @org.junit.jupiter.api.Test
    public void testGetNumberOfCustomers() {
        System.out.println("getNumberOfCustomers");
        CustomerFacade instance = null;
        int expResult = 0;
        int result = instance.getNumberOfCustomers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of allCustomers method, of class CustomerFacade.
     */
    @org.junit.jupiter.api.Test
    public void testAllCustomers() {
        System.out.println("allCustomers");
        CustomerFacade instance = null;
        List<Customer> expResult = null;
        List<Customer> result = instance.allCustomers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
