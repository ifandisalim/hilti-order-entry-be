package services;

import exceptions.DatabaseException;
import models.Order;

public class OrderService {

    public Integer create(Order newOrder) {
        try{
            newOrder.save();
        } catch (RuntimeException e) {
            throw new DatabaseException(e.getCause().getMessage());
        }

        return newOrder.getId();
    }

}
