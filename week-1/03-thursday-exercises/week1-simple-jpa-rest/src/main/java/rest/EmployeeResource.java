package rest;

import com.google.gson.Gson;
import dto.EmployeeDTO;
import entities.Employee;
import facades.EmployeeFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("employees")
public class EmployeeResource {
    
    //NOTE: Change Persistence unit name according to your setup
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu"); 
    EmployeeFacade facade =  EmployeeFacade.getFacadeExample(emf);
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"succes\"}";
    }
    
    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public String showAllEmployees() {
        List<EmployeeDTO> employees = facade.getAllEmployees();
        String jsonString = new Gson().toJson(employees);
        return jsonString;
    }
    
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String showEmployeeById(@PathParam("id") int id) {
        EmployeeDTO employee = facade.getEmployeeById(id);
        String jsonString = new Gson().toJson(employee);
        return jsonString;
    }
    
    @GET
    @Path("/highestpaid")
    @Produces({MediaType.APPLICATION_JSON})
    public String showHighestPaidEmployees() {
        List<EmployeeDTO> employees = facade.getEmployeesWithHighestSalary();
        String jsonString = new Gson().toJson(employees);
        return jsonString;
    }
    
    @GET
    @Path("/name/{name}")
    @Produces({MediaType.APPLICATION_JSON})
    public String showEmployeesByName(@PathParam("name") String name) {
        List<EmployeeDTO> employees = facade.getEmployeesByName(name);
        String jsonString = new Gson().toJson(employees);
        return jsonString;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Employee entity) {
        
    }
    
    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void update(Employee entity, @PathParam("id") int id) {
        throw new UnsupportedOperationException();
    }
}
