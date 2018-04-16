package models;


import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;

@Table(name = "order_item")
@Entity(name = "order_item")
public class OrderItem extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @Constraints.Required
    private Product product;

    @ManyToOne
    @JoinColumn(name = "product_order_id")
    @Constraints.Required
    private Order order;

    @Constraints.Required
    private Integer quantity;

    public static Finder<Integer, OrderItem> find = new Finder<>(OrderItem.class);

    public OrderItem() {
    }

    public OrderItem(Product product, Order order, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
