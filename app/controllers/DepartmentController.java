package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import configurations.Constants;
import errors.ErrorFactory;
import errors.JsonError;
import helpers.FormValidationHelper;
import models.Department;
import models.Employee;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import services.DepartmentService;

import javax.inject.Inject;

import static play.mvc.Results.created;


public class DepartmentController {

    private final DepartmentService departmentService;
    private final FormFactory formFactory;
    private final FormValidationHelper formValidationHelper;


    @Inject
    public DepartmentController(DepartmentService departmentService, FormFactory formFactory, FormValidationHelper formValidationHelper) {
        this.departmentService = departmentService;
        this.formFactory = formFactory;
        this.formValidationHelper = formValidationHelper;
    }



    public Result create() {
        Form<Department> departmentRegistrationForm = formFactory.form(Department.class).bindFromRequest();
        JsonNode errors = formValidationHelper.validateFormErrors(departmentRegistrationForm);

        if(errors != null) {
            return ErrorFactory.create(new JsonError(Http.Status.BAD_REQUEST, "Validation Error", Constants.VALIDATION_ERROR, errors));
        }

        Department newDepartment = departmentRegistrationForm.get();
        Integer newDepartmentId = departmentService.create(newDepartment);
        return created(Json.toJson(newDepartmentId));
    }
}
