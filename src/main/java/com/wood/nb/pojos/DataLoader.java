package com.wood.nb.pojos;

public class DataLoader {

    public Customer loadCustomer(){
        return new Customer("Nick","WoodMan", "667");
    }
    public Account loadCustomerAcccount(Customer customer){
        return new Account(customer.getFirstName()+" "+customer.getLastName(), Currency.Dollar);
    }
    public Deposit loadDeposit(){
        return new Deposit("1000");
    }
}
