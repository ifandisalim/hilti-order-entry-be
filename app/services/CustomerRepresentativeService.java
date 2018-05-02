package services;

import configurations.Constants;
import exceptions.DatabaseException;
import exceptions.NotFoundException;
import models.CustomerRepresentative;
import models.Employee;

public class CustomerRepresentativeService {

    public Integer create(CustomerRepresentative newCustomerRepresentative) {
        try{
            newCustomerRepresentative.save();
        } catch (RuntimeException e) {
            throw new DatabaseException(e.getCause().getMessage());
        }

        return newCustomerRepresentative.getId();
    }

    public CustomerRepresentative get(Integer id) {
        CustomerRepresentative representative = CustomerRepresentative.find.byId(id);

        if(representative == null) {
            throw new NotFoundException(Constants.USER_NOT_FOUND + id);
        }
        return representative;
    }

    public Double reduceCreditUsed(CustomerRepresentative customerRepresentative, Double amountToReduce) {
        customerRepresentative.setCreditUsed(customerRepresentative.getCreditUsed() - amountToReduce);

        customerRepresentative.save();
        return customerRepresentative.getCreditUsed();
    }

    public Double addCreditUsed(CustomerRepresentative customerRepresentative, Double amountToAdd) {
        customerRepresentative.setCreditUsed(customerRepresentative.getCreditUsed() + amountToAdd);

        customerRepresentative.save();
        return customerRepresentative.getCreditUsed();
    }
    
}
