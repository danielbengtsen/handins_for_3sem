package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.CustomerDTO;
import entities.BankCustomer;
import facades.CustomerFacade;
import java.util.List;
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


@Path("bankcustomer")
public class BankCustomerResource {
    
    //NOTE: Change Persistence unit name according to your setup
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu"); 
    CustomerFacade facade =  CustomerFacade.getCustomerFacade(emf);
    private static Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"succes\"}";
    }
    
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String showCustomerByID(@PathParam("id") int id) {
        CustomerDTO custDTO = facade.getCustomerByID(id);
        String jsonString = GSON.toJson(custDTO);
        return jsonString;
    }
    
    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public String showAllCustomers() {
        List<BankCustomer> allCustsDTO = facade.getAllBankCustomers();
        String jsonString = GSON.toJson(allCustsDTO);
        return jsonString;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(BankCustomer entity) {
        throw new UnsupportedOperationException();
    }
    
    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void update(BankCustomer entity, @PathParam("id") int id) {
        throw new UnsupportedOperationException();
    }
}
