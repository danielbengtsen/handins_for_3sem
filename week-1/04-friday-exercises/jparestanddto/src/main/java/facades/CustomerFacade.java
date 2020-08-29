package facades;

import dto.CustomerDTO;
import entities.BankCustomer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class CustomerFacade {

    private static CustomerFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private CustomerFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public CustomerDTO getCustomerByID(int id) {
        EntityManager em = getEntityManager();
        try {
            BankCustomer bc = em.find(BankCustomer.class, id);
            CustomerDTO cDTO = null;
            if(bc != null) {
               cDTO = new CustomerDTO(bc); 
            }
            return cDTO;
        } finally {
            em.close();
        }
    }
    
    public List<CustomerDTO> getCustomerByName(String name) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<BankCustomer> query = 
                    em.createQuery("SELECT bc FROM BankCustomer bc "
                            + "WHERE bc.firstName = :NAME OR bc.lastName = :NAME", BankCustomer.class);
            query.setParameter("NAME", name);
            List<BankCustomer> bcs = query.getResultList();
            List<CustomerDTO> bcsDTO = new ArrayList();
            for(BankCustomer bc : bcs) {
                bcsDTO.add(new CustomerDTO(bc));
            }
            return bcsDTO;
        } finally {
            em.close();
        }
    }
    
    // Higher user privileges needed for this method.
    public BankCustomer addCustomer(BankCustomer cust) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.createNativeQuery("INSERT INTO BANKCUSTOMER "
                    + "(firstName, lastName, accountNumber, balance,"
                    + "customerRanking, internalInfo) VALUES (?,?,?,?,?,?)")
                    .setParameter(1, cust.getFirstName())
                    .setParameter(2, cust.getLastName())
                    .setParameter(3, cust.getAccountNumber())
                    .setParameter(4, cust.getBalance())
                    .setParameter(5, cust.getCustomerRanking())
                    .setParameter(6, cust.getInternalInfo())
                    .executeUpdate(); 
            em.getTransaction().commit();
            return cust;
        } finally {
            em.close();
        }
    }
    
    // Higher user privileges needed for this method.
    public List<BankCustomer> getAllBankCustomers() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<BankCustomer> query =
                    em.createQuery("SELECT bc FROM BankCustomer bc", BankCustomer.class);
            List<BankCustomer> allBCS = query.getResultList();
            return allBCS;
        } finally {
            em.close();
        }
    }

}
