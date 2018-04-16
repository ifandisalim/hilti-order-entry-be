package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import configurations.Constants;
import errors.ErrorFactory;
import errors.JsonError;
import helpers.FormValidationHelper;
import models.Customer;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import services.CustomerService;

import javax.inject.Inject;

import static play.mvc.Results.created;

public class CustomerController {

    private final services.CustomerService CustomerService;
    private final FormFactory formFactory;
    private final FormValidationHelper formValidationHelper;

    @Inject
    public CustomerController(CustomerService CustomerService,
                                            FormFactory formFactory,
                                            FormValidationHelper formValidationHelper) {
        this.CustomerService = CustomerService;
        this.formFactory = formFactory;
        this.formValidationHelper = formValidationHelper;
    }


    public Result create() {
        Form<Customer> CustomerRegistrationForm = formFactory.form(Customer.class).bindFromRequest();
        JsonNode errors = formValidationHelper.validateFormErrors(CustomerRegistrationForm);

        if(errors != null) {
            return ErrorFactory.create(new JsonError(Http.Status.BAD_REQUEST, "Validation Error", Constants.VALIDATION_ERROR, errors));
        }

        Customer newCustomer = CustomerRegistrationForm.get();
        Integer newCustomerId = CustomerService.create(newCustomer);
        return created(Json.toJson(newCustomerId));
    }
}
