/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.BankCustomer;

/**
 *
 * @author Danie
 */
public class CustomerDTO {
    
    private int customerID;
    private String fullName;
    private String accountNumber;
    private double balance;

    public CustomerDTO(BankCustomer bc) {
        this.customerID = bc.getId();
        this.fullName = bc.getFirstName() + " " + bc.getLastName();
        this.accountNumber = bc.getAccountNumber();
        this.balance = bc.getBalance();
    }

    // toString() used for JUnit.
    @Override
    public String toString() {
        return "CustomerDTO{" + "customerID=" + customerID + ", fullName=" + fullName + ", accountNumber=" + accountNumber + ", balance=" + balance + '}';
    }
    
    
    
}
