package services;

import exceptions.DatabaseException;
import models.CustomerRepresentative;

public class CustomerRepresentativeService {

    public Integer create(CustomerRepresentative newCustomerRepresentative) {
        try{
            newCustomerRepresentative.save();
        } catch (RuntimeException e) {
            throw new DatabaseException(e.getCause().getMessage());
        }

        return newCustomerRepresentative.getId();
    }
    
}
