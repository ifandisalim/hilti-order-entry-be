package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import configurations.Constants;
import errors.ErrorFactory;
import errors.JsonError;
import helpers.FormValidationHelper;
import models.Product;
import models.ProductCategory;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import services.ProductService;
import services.ProductService;

import javax.inject.Inject;

import static play.mvc.Results.created;

public class ProductController {

    private final ProductService productService;
    private final FormFactory formFactory;
    private final FormValidationHelper formValidationHelper;

    @Inject
    public ProductController(ProductService productService, FormFactory formFactory, FormValidationHelper formValidationHelper) {
        this.productService = productService;
        this.formFactory = formFactory;
        this.formValidationHelper = formValidationHelper;
    }


    public Result create() {
        Form<Product> productRegistrationForm = formFactory.form(Product.class).bindFromRequest();
        JsonNode errors = formValidationHelper.validateFormErrors(productRegistrationForm);

        if(errors != null) {
            return ErrorFactory.create(new JsonError(Http.Status.BAD_REQUEST, "Validation Error", Constants.VALIDATION_ERROR, errors));
        }

        Product newProduct = productRegistrationForm.get();
        Integer newProductId = productService.create(newProduct);
        return created(Json.toJson(newProductId));
    }

    public Result createCategory() {
        Form<ProductCategory> productCategoryForm = formFactory.form(ProductCategory.class).bindFromRequest();
        JsonNode errors = formValidationHelper.validateFormErrors(productCategoryForm);

        if(errors != null) {
            return ErrorFactory.create(new JsonError(Http.Status.BAD_REQUEST, "Validation Error", Constants.VALIDATION_ERROR, errors));
        }

        ProductCategory newProductCategory = productCategoryForm.get();
        Integer newCategoryId = productService.createCategory(newProductCategory);
        return created(Json.toJson(newCategoryId));
    }
}
