package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import configurations.Constants;
import errors.ErrorFactory;
import errors.JsonError;
import helpers.FormValidationHelper;
import models.CustomerRepresentative;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import services.CustomerRepresentativeService;

import javax.inject.Inject;

import static play.mvc.Results.created;

public class CustomerRepresentativeController {
    private final CustomerRepresentativeService CustomerRepresentativeService;
    private final FormFactory formFactory;
    private final FormValidationHelper formValidationHelper;

    @Inject
    public CustomerRepresentativeController(CustomerRepresentativeService CustomerRepresentativeService,
                                            FormFactory formFactory,
                                            FormValidationHelper formValidationHelper) {
        this.CustomerRepresentativeService = CustomerRepresentativeService;
        this.formFactory = formFactory;
        this.formValidationHelper = formValidationHelper;
    }


    public Result create() {
        Form<CustomerRepresentative> CustomerRepresentativeRegistrationForm = formFactory.form(CustomerRepresentative.class).bindFromRequest();
        JsonNode errors = formValidationHelper.validateFormErrors(CustomerRepresentativeRegistrationForm);

        if(errors != null) {
            return ErrorFactory.create(new JsonError(Http.Status.BAD_REQUEST, "Validation Error", Constants.VALIDATION_ERROR, errors));
        }

        CustomerRepresentative newCustomerRepresentative = CustomerRepresentativeRegistrationForm.get();
        Integer newCustomerRepresentativeId = CustomerRepresentativeService.create(newCustomerRepresentative);
        return created(Json.toJson(newCustomerRepresentativeId));
    }
}
