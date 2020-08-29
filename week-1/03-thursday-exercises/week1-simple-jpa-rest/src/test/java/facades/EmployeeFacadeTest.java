package facades;

import dto.EmployeeDTO;
import entities.Employee;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmployeeFacadeTest {
    
    private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("pu");
    private static final EmployeeFacade EF = EmployeeFacade.getFacadeExample(EMF);
    
    private static Employee e1 = new Employee(1, "Street 1", "John Doe", 40000);
    private static Employee e2 = new Employee(2, "Street 2", "Jane Doe", 50000);
    private static Employee e3 = new Employee(3, "Street 45", "John Doe", 47548);
    private static Employee e4 = new Employee(4, "Badevej 3", "Kurt Kurbad", 30000);
    
    private static EmployeeDTO e1DTO = new EmployeeDTO(e1);
    private static EmployeeDTO e2DTO = new EmployeeDTO(e2);
    private static EmployeeDTO e3DTO = new EmployeeDTO(e3);
    private static EmployeeDTO e4DTO = new EmployeeDTO(e4);
    
    
    public EmployeeFacadeTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
//        Add code to setup entities for test before running any test methods
    }
    
    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }
    
    @BeforeEach
    public void setUp() {
//        Put the test database in a proper state before each test is run
        
    }
    
    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    @Test
    public void getEmployeeByIdTest() {
        int id = 1;
        EmployeeDTO expected = e1DTO;
        EmployeeDTO result = EF.getEmployeeById(id);
        
        assertEquals(expected.toString(), result.toString());
    }
    
    @Test
    public void getEmployeesByNameTest() {
        String name = "John Doe";
        List<EmployeeDTO> expected = new ArrayList();
        expected.add(e1DTO);
        expected.add(e3DTO);
        List<EmployeeDTO> result = EF.getEmployeesByName(name);
        
        assertEquals(expected.get(1).toString(), result.get(1).toString());
    }
    
    @Test
    public void getAllEmployeesTest() {
        List<EmployeeDTO> expected = new ArrayList();
        expected.add(e1DTO);
        expected.add(e2DTO);
        expected.add(e3DTO);
        expected.add(e4DTO);
        List<EmployeeDTO> result = EF.getAllEmployees();
        
        assertEquals(expected.get(2).toString(), result.get(2).toString());
        
    }
    
    @Test
    public void getEmployeesWithHighestSalaryTest() {
        List<EmployeeDTO> expected = new ArrayList();
        expected.add(e2DTO);
        List<EmployeeDTO> result = EF.getEmployeesWithHighestSalary();
        
        assertEquals(expected.get(0).toString(), result.get(0).toString());
    }
    /*
    @Test
    public void createEmployeeTest() {
        Employee e5 = new Employee("Svend Sved", "Slagterby 2", 20000);
        Employee expected = e5;
        EF.createEmployee(e5);
        Employee result = EF.getEmployeeById(5);
        
        assertEquals(expected.getName(), result.getName());
        
    }*/
    
}
