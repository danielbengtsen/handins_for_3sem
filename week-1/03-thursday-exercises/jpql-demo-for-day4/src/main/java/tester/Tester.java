package tester;

import entity.Employee;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Tester {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new Employee("xa12tt", "Kurt", "Wonnegut", new BigDecimal(335567)));
            em.persist(new Employee("hyu654", "Hanne", "Olsen", new BigDecimal(435867)));
            em.persist(new Employee("uio876", "Jan", "Olsen", new BigDecimal(411567)));
            em.persist(new Employee("klo999", "Irene", "Petersen", new BigDecimal(33567)));
            em.persist(new Employee("jik666", "Tian", "Wonnegut", new BigDecimal(56567)));
            em.getTransaction().commit();
            
            //Complete all these small tasks. Your will find the solution to all, but the last,
            //In this document: https://en.wikibooks.org/wiki/Java_Persistence/JPQL#JPQL_supported_functions
            Query query = null;
            
            //1) Create a query to fetch all employees with a salary > 100000 and print out all the salaries
            query = em.createQuery("Select e FROM Employee e WHERE e.salary > 100000");
            List<Employee> result = query.getResultList();
            System.out.println("\nExc 1:");
            for(Employee e : result) {
                System.out.println("*********************");
                System.out.println("ID: " + e.getId());
                System.out.println("Firstname: " + e.getFirstName());
                System.out.println("Lastname: " + e.getLastName());
                System.out.println("Salary: " + e.getSalary());
                System.out.println("*********************");
            }
            
            //2) Create a query to fetch the employee with the id "klo999" and print out the firstname
            String id = "klo999";
            query = em.createQuery("SELECT e FROM Employee e WHERE e.id = :id");
            query.setParameter("id", id);
            Employee result2 = (Employee)query.getSingleResult();
            System.out.println("\nExc 2:");
            System.out.println("*********************");
            System.out.println("Firstname: " + result2.getFirstName());
            System.out.println("*********************");
            
            //3) Create a query to fetch the highest salary and print the value
            query = em.createQuery("Select MAX(e.salary) FROM Employee e");
            BigDecimal result3 = (BigDecimal)query.getSingleResult();
            System.out.println("\nExc 3:");
            System.out.println("*********************");
            System.out.println("Salary: " + result3);
            System.out.println("*********************");

            //4) Create a query to fetch the firstName of all Employees and print the names
            query = em.createQuery("Select e.firstName FROM Employee e");
            List<String> result4 = query.getResultList();
            System.out.println("\nExc 4:");
            for(String s : result4) {
                System.out.println("*********************");
                System.out.println("Firstname: " + s);
                System.out.println("*********************");
            }
           
            //5 Create a query to calculate the number of employees and print the number
            query = em.createQuery("Select COUNT(e) FROM Employee e");
            long result5 = (long)query.getSingleResult();
            System.out.println("\nExc 5:");
            System.out.println("*********************");
            System.out.println("Number of employees: " + result5);
            System.out.println("*********************");
            
            //6 Create a query to fetch the Employee with the higest salary and print all his details
            query = em.createQuery("SELECT e FROM Employee e WHERE e.salary = "
                    + "(SELECT MAX(e.salary) FROM Employee e)");
            Employee result6 = (Employee)query.getSingleResult();
            System.out.println("\nExc 6:");
            System.out.println("*********************");
            System.out.println("ID: " + result6.getId());
            System.out.println("Firstname: " + result6.getFirstName());
            System.out.println("Lastname: " + result6.getLastName());
            System.out.println("Salary: " + result6.getSalary());
            System.out.println("*********************");
            
            
        } finally {
            em.close();
            emf.close();
        }
    }

}
