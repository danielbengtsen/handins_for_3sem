package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String address;
    private String name;
    private int salary;

    public Employee(String address, String name, int salary) {
        this.address = address;
        this.name = name;
        this.salary = salary;
    }

    // Constructor used for JUnit.
    public Employee(int id, String address, String name, int salary) {
        this.id = id;
        this.address = address;
        this.name = name;
        this.salary = salary;
    }
    
    


    public Employee() {
    }
        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
    
    
    
}
