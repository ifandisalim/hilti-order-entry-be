package helpers;

import models.Order;
import models.OrderItem;
import models.Product;
import services.ProductService;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class OrderHelper {

    private final ProductService productService;

    @Inject
    public OrderHelper(ProductService productService) {
        this.productService = productService;
    }

    public  List<OrderItem>  getOrderItemsWithProductInformation(Order order) {
        List<OrderItem> orderItems = order.getItems();

        return orderItems
                .stream()
                .peek(item -> {
                    Product currentProduct = productService.get(item.getProduct().getId());
                    item.setProduct(currentProduct);
                })
                .collect(Collectors.toList());
    }

    public double getTotalOrderPrice(Order order) {
        List<OrderItem> orderItems = order.getItems();

        return orderItems
                .stream()
                .map(item -> item.getProduct().getPrice() * item.getQuantity())
                .reduce((double) 0, (totalAmount, orderItemTotalPrice) -> totalAmount += orderItemTotalPrice);
    }

}
