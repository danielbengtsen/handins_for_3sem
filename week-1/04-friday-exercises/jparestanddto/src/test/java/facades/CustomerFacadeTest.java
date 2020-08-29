package facades;

import dto.CustomerDTO;
import entities.BankCustomer;
import static entities.MakeTestData.createBankCustomer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomerFacadeTest {
    private static final EntityManagerFactory ENF = Persistence.createEntityManagerFactory("pu");
    private static final CustomerFacade CF = CustomerFacade.getCustomerFacade(ENF);
    
    private static BankCustomer bc1 = new BankCustomer(1, "Kurt", "Kurbad", "123456789", 5000000.0, 8, "Lugter, hvis han ikke tager bad.");
    private static BankCustomer bc2 = new BankCustomer(2, "Asmus", "Musselund", "757575857", 12000000.0, 9, "Har mus i lommen.");
    private static BankCustomer bc3 = new BankCustomer(3, "Nille", "Nikspille", "172847584", 30240543.34, 7, "Piller ved alt!");
    
    private static CustomerDTO bc1DTO = new CustomerDTO(bc1);
    private static CustomerDTO bc2DTO = new CustomerDTO(bc2);
    private static CustomerDTO bc3DTO = new CustomerDTO(bc3);
    
    
        public CustomerFacadeTest() {
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
    public void getCustomerByIdTest() {
        int id = 1;
        CustomerDTO expected = bc1DTO;
        CustomerDTO result = CF.getCustomerByID(id);
        
        assertEquals(expected.toString(), result.toString());
    }
    
    @Test
    public void getCustomerByNameTest() {
        String name = "Kurt";
        List<CustomerDTO> expected = new ArrayList();
        expected.add(bc1DTO);
        List<CustomerDTO> result = CF.getCustomerByName(name);
        
        assertEquals(expected.get(0).toString(), result.get(0).toString());
    }
    
    /*
    * TEST AF addCustomer() ER DROPPET, DA JEG IKKE VED HVORDAN BEFORE/AFTER TEST
    * VIRKER MED DENNE MÅDE AT FORBINDE TIL DATABASE PÅ :(
    */
    
    @Test
    public void getAllBankCustomersTest() {
        List<BankCustomer> expected = new ArrayList();
        expected.add(bc1);
        expected.add(bc2);
        expected.add(bc3);
        List<BankCustomer> result = CF.getAllBankCustomers();
        
        assertEquals(expected.get(0).toString(), result.get(0).toString());
    }
    
}
