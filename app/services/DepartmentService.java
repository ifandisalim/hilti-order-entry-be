package services;

import configurations.Constants;
import exceptions.DatabaseException;
import exceptions.NotFoundException;
import models.Department;


public class DepartmentService {

    public Integer create(Department department) {
        try{
            department.save();
        } catch (RuntimeException e) {
            throw new DatabaseException(e.getCause().getMessage());
        }

        return department.getId();
    }

    public Department get(Integer id) {
        Department department = Department.find.byId(id);

        if(department == null) {
            throw new NotFoundException(Constants.DEPARTMENT_NOT_FOUND + id);
        }

        return department;
    }
}
