
package tester;

import entity.Country;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Tester {
    
    
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        try{
            Country c1 = new Country("Denmark", "Germany");
            Country c2 = new Country("Iceland", "");
            Country c3 = new Country("Malta", "");
            
            em.getTransaction().begin();
            em.persist(c1);
            em.persist(c2);
            em.persist(c3);
            em.getTransaction().commit();
            
        } finally {
            em.close();
        }
    }
    
}
