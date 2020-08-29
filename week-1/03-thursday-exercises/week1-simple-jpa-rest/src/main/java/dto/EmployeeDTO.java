/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import com.google.gson.Gson;
import entities.Employee;
import facades.EmployeeFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Danie
 */
public class EmployeeDTO {
    private int id;
    private String address;
    private String name;


    public EmployeeDTO(Employee e) {
        this.id = e.getId();
        this.address = e.getAddress();
        this.name = e.getName();
    }

    // toString() used for JUnit.
    @Override
    public String toString() {
        return "EmployeeDTO{" + "id=" + id + ", address=" + address + ", name=" + name + '}';
    }
    
    
    
}
