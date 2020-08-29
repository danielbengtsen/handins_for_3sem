/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Danie
 */
public class MakeTestData {
    
    private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("pu");
    
    public static void main(String[] args) {
        createBankCustomer("Kurt", "Kurbad", "123456789", 5000000.0, 8, "Lugter, hvis han ikke tager bad.");
        createBankCustomer("Asmus", "Musselund", "757575857", 12000000.0, 9, "Har mus i lommen.");
        createBankCustomer("Nille", "Nikspille", "172847584", 30240543.34, 7, "Piller ved alt!");
        
    }
    
    public static void createBankCustomer(String firstName, String lastName, 
            String accountNumber, double balance, int customerRanking, String internalInfo) {
        
        EntityManager em = EMF.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNativeQuery("INSERT INTO BANKCUSTOMER "
                    + "(firstName, lastName, accountNumber, balance,"
                    + "customerRanking, internalInfo) VALUES (?,?,?,?,?,?)")
                    .setParameter(1, firstName)
                    .setParameter(2, lastName)
                    .setParameter(3, accountNumber)
                    .setParameter(4, balance)
                    .setParameter(5, customerRanking)
                    .setParameter(6, internalInfo)
                    .executeUpdate();  
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    
    
}
