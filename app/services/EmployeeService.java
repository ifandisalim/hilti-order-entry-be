package services;

import configurations.Constants;
import exceptions.DatabaseException;
import exceptions.NotFoundException;
import models.Credential;
import models.CustomerRepresentative;
import models.Employee;

import javax.inject.Inject;
import java.util.List;

public class EmployeeService {

    private CustomerRepresentativeService customerRepresentativeService;

    @Inject
    public EmployeeService(CustomerRepresentativeService customerRepresentativeService) {
        this.customerRepresentativeService = customerRepresentativeService;
    }

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

    public void addFavourites(Integer employeeId, List<CustomerRepresentative> newFavs) {
        Employee employee = get(employeeId);
        List<CustomerRepresentative> favourites = employee.getFavourites();

        // Checking if the representative exist
        newFavs.forEach(favourite -> customerRepresentativeService.get(favourite.getId()));

        newFavs.forEach((favourite) -> {
            if(favourites.contains(favourite)) {
                return;
            }
            favourites.add(favourite);
        });

        employee.setFavourites(favourites);
        employee.update();

    }

    public void removeFavourites(Integer employeeId, List<CustomerRepresentative> favsToRemove) {
        Employee employee = get(employeeId);
        List<CustomerRepresentative> favourites = employee.getFavourites();

        favsToRemove.forEach((favourite) -> {
            if(favourites.contains(favourite)) {
                favourites.remove(favourite);
            }
        });

        employee.setFavourites(favourites);
        employee.update();

    }
}
