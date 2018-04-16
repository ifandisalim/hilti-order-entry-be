package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import configurations.Constants;
import errors.ErrorFactory;
import errors.JsonError;
import helpers.DateHelper;
import helpers.FormValidationHelper;
import helpers.OrderHelper;
import models.Order;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import services.OrderService;

import javax.inject.Inject;

import static play.mvc.Results.created;

public class OrderController {

    private final OrderService OrderService;
    private final OrderHelper orderHelper;
    private final DateHelper dateHelper;
    private final FormFactory formFactory;
    private final FormValidationHelper formValidationHelper;

    @Inject
    public OrderController(OrderService OrderService,
                           OrderHelper orderHelper,
                           DateHelper dateHelper,
                           FormFactory formFactory,
                           FormValidationHelper formValidationHelper) {
        this.OrderService = OrderService;
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

        Order newOrder = OrderRegistrationForm.get();
        newOrder.setDateOrdered(dateHelper.getCurrentDateString());
        newOrder.setItems(orderHelper.getOrderItemsWithProductInformation(newOrder));
        newOrder.setTotalPrice(orderHelper.getTotalOrderPrice(newOrder));


        Integer newOrderId = OrderService.create(newOrder);
        return created(Json.toJson(newOrderId));
    }
}
