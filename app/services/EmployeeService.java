package services;

import configurations.Constants;
import exceptions.DatabaseException;
import exceptions.NotFoundException;
import models.Credential;
import models.Employee;

public class EmployeeService {

    public Integer create(Employee newEmployee) {
        try{
            newEmployee.save();
        } catch (RuntimeException e) {
            throw new DatabaseException(e.getCause().getMessage());
        }

        return newEmployee.getId();
    }

    public Employee get(Integer id) {
        Employee employee = Employee.find.byId(id);

        if(employee == null) {
            throw new NotFoundException(Constants.USER_NOT_FOUND + id);
        }

        return employee;
    }

    public Employee get(Credential credential) {
        Employee employee = Employee.find
                .query()
                .where()
                .eq("credential_id", credential.getId())
                .findOne();

        if(employee == null) {
            throw new NotFoundException(Constants.USER_NOT_FOUND + credential.getUsername());
        }

        return employee;
    }

    public Credential getCredential(String userName) {

        Credential credential = Credential.find
                .query()
                .where()
                .eq("username", userName)
                .findOne();

        if(credential == null) {
            throw new NotFoundException(Constants.CREDENTIAL_NOT_FOUND + userName);
        }

        return credential;
    }
}
