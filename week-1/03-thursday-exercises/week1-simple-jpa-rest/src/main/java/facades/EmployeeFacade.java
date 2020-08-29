package facades;

import com.google.gson.Gson;
import dto.EmployeeDTO;
import entities.Employee;
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
public class EmployeeFacade {

    private static EmployeeFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private EmployeeFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static EmployeeFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public EmployeeDTO getEmployeeById(int id) {
        EntityManager em = getEntityManager();
        try{
            Employee e = em.find(Employee.class, id);
            EmployeeDTO result = null;
            if(e != null) {
                result = new EmployeeDTO(e);
            }
            return result;
        } finally {
            em.close();
        }
    }
    
    public List<EmployeeDTO> getEmployeesByName(String name) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e WHERE e.name = :NAME", Employee.class);
            query.setParameter("NAME", name);
            List<Employee> es = query.getResultList();
            List<EmployeeDTO> result = new ArrayList();
            for(Employee e : es) {
                result.add(new EmployeeDTO(e));
            }
            return result;
        } finally {
            em.close();
        }
    }
    
    public List<EmployeeDTO> getAllEmployees() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);
            List<Employee> es = query.getResultList();
            List<EmployeeDTO> result = new ArrayList();
            for(Employee e : es) {
                result.add(new EmployeeDTO(e));
            }
            return result;
        } finally {
            em.close();
        }
    }
    
    public List<EmployeeDTO> getEmployeesWithHighestSalary() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Employee> query = 
                    em.createQuery("SELECT e FROM Employee e WHERE e.salary = "
                            + "(SELECT max(e.salary) FROM Employee e)", Employee.class);
            List<Employee> es = query.getResultList();
            List<EmployeeDTO> result = new ArrayList();
            for(Employee e : es) {
                result.add(new EmployeeDTO(e));
            }
            return result;
        } finally {
            em.close();
        }
    }
    
    public void createEmployee(Employee e) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.createNativeQuery("INSERT INTO EMPLOYEE (address, name, salary) "
                            + "VALUES (?,?,?)")
                    .setParameter(1, e.getAddress())
                    .setParameter(2, e.getName())
                    .setParameter(3, e.getSalary())
                    .executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
