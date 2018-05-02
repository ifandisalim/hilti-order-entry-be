package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import configurations.Constants;
import errors.ErrorFactory;
import errors.JsonError;
import exceptions.ExceedCreditLimitException;
import helpers.DateHelper;
import helpers.FormValidationHelper;
import helpers.OrderHelper;
import models.CustomerRepresentative;
import models.Order;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import services.CustomerRepresentativeService;
import services.OrderService;

import javax.inject.Inject;

import static play.mvc.Results.created;
import static play.mvc.Results.ok;

public class OrderController {

    private final OrderService OrderService;
    private final CustomerRepresentativeService customerRepresentativeService;
    private final OrderHelper orderHelper;
    private final DateHelper dateHelper;
    private final FormFactory formFactory;
    private final FormValidationHelper formValidationHelper;

    @Inject
    public OrderController(OrderService OrderService,
                           CustomerRepresentativeService customerRepresentativeService,
                           OrderHelper orderHelper,
                           DateHelper dateHelper,
                           FormFactory formFactory,
                           FormValidationHelper formValidationHelper) {
        this.OrderService = OrderService;
        this.customerRepresentativeService = customerRepresentativeService;
        this.orderHelper = orderHelper;
        this.dateHelper = dateHelper;
        this.formFactory = formFactory;
        this.formValidationHelper = formValidationHelper;
    }


    public Result create() {
        Form<Order> OrderRegistrationForm = formFactory.form(Order.class).bindFromRequest();
        JsonNode errors = formValidationHelper.validateFormErrors(OrderRegistrationForm);

        if(errors != null) {
            return ErrorFactory.create(new JsonError(Http.Status.BAD_REQUEST, "Validation Error", Constants.VALIDATION_ERROR, errors));
        }

        // Create order model
        Order newOrder = OrderRegistrationForm.get();
        newOrder.setDateOrdered(dateHelper.getCurrentDateString());
        newOrder.setItems(orderHelper.getOrderItemsWithProductInformation(newOrder));
        newOrder.setTotalPrice(orderHelper.getTotalOrderPrice(newOrder));

        // Check credit limit
        CustomerRepresentative customerRepresentative = customerRepresentativeService.get(newOrder.getBuyer().getId());
        if((customerRepresentative.getCreditUsed() + newOrder.getTotalPrice()) > customerRepresentative.getCreditLimit()) {
            throw new ExceedCreditLimitException("Credit limit has been exceeded. Order failed.");
        }

        customerRepresentativeService.addCreditUsed(customerRepresentative, newOrder.getTotalPrice());
        Integer newOrderId = OrderService.create(newOrder);
        
        return created(Json.toJson(newOrderId));
    }
}
