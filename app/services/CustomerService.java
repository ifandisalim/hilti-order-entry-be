package services;

import exceptions.DatabaseException;
import models.Customer;

public class CustomerService {

    public Integer create(Customer newCustomer) {
        try{
            newCustomer.save();
        } catch (RuntimeException e) {
            throw new DatabaseException(e.getCause().getMessage());
        }

        return newCustomer.getId();
    }
}
