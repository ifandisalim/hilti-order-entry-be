package services;

import configurations.Constants;
import exceptions.DatabaseException;
import exceptions.NotFoundException;
import models.Product;
import models.ProductCategory;

public class ProductService {
    
    public Integer create(Product newProduct) {
        try{
            newProduct.save();
        } catch (RuntimeException e) {
            throw new DatabaseException(e.getCause().getMessage());
        }

        return newProduct.getId();
    }

    public Integer createCategory(ProductCategory newCategory) {
        try{
            newCategory.save();
        } catch (RuntimeException e) {
            throw new DatabaseException(e.getCause().getMessage());
        }

        return newCategory.getId();
    }

    public Product get(Integer id) {
        Product queriedProduct =  Product.find.byId(id);

        if(queriedProduct == null) {
            throw new NotFoundException(Constants.PRODUCT_NOT_FOUND + id);
        }

        return queriedProduct;
    }

}
