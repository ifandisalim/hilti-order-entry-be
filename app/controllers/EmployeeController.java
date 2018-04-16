package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import configurations.Constants;
import errors.ErrorFactory;
import errors.JsonError;
import exceptions.AuthenticationException;
import helpers.AuthenticationHelper;
import helpers.FormValidationHelper;
import models.Credential;
import models.Employee;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import services.EmployeeService;

import javax.inject.Inject;

import static play.mvc.Controller.response;
import static play.mvc.Results.created;
import static play.mvc.Results.ok;

public class EmployeeController {

    private final EmployeeService employeeService;
    private final FormFactory formFactory;
    private final FormValidationHelper formValidationHelper;
    private final AuthenticationHelper authenticationHelper;


    @Inject
    public EmployeeController(EmployeeService employeeService, FormFactory formFactory, FormValidationHelper formValidationHelper, AuthenticationHelper authenticationHelper) {
        this.employeeService = employeeService;
        this.formFactory = formFactory;
        this.formValidationHelper = formValidationHelper;
        this.authenticationHelper = authenticationHelper;
    }


    public Result create() {
        Form<Employee> employeeRegistrationForm = formFactory.form(Employee.class).bindFromRequest();
        JsonNode errors = formValidationHelper.validateFormErrors(employeeRegistrationForm);

        if(errors != null) {
            return ErrorFactory.create(new JsonError(Http.Status.BAD_REQUEST, "Validation Error",Constants.VALIDATION_ERROR, errors));
        }

        Employee newEmployee = employeeRegistrationForm.get();
        Integer newEmployeeId = employeeService.create(newEmployee);
        return created(Json.toJson(newEmployeeId));
    }

    public Result login() {
        Form<Credential> credentialForm = formFactory.form(Credential.class).bindFromRequest();
        JsonNode errors = formValidationHelper.validateFormErrors(credentialForm);

        if(errors != null) {
            return ErrorFactory.create(new JsonError(Http.Status.BAD_REQUEST, "Validation Error",Constants.VALIDATION_ERROR, errors));
        }

        Credential inputCredential = credentialForm.get();
        Credential storedCredential = employeeService.getCredential(inputCredential.getUsername());

        Boolean truePassword = authenticationHelper.verifyPassword(inputCredential.getPassword(), storedCredential.getPassword());

        if(!truePassword) {
            throw new AuthenticationException("Wrong password");
        }

        Employee employee = employeeService.get(storedCredential);
        String accessToken = authenticationHelper.generateAccessToken(String.valueOf(employee.getId()));
        response().setHeader("Authorization", accessToken);

        return ok(Json.toJson(employee));
    }
}
