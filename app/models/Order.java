package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.List;

@Table(name = "product_order")
@Entity(name = "product_order")
public class    Order extends Model{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Constraints.Required
    @ManyToOne
    @JoinColumn(name = "buyer_id")
    @JsonIgnore
    private CustomerRepresentative buyer;

    @ManyToOne
    @JoinColumn(name = "handler_id")
    @Constraints.Required
    @JsonIgnore
    private Employee handler;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    @Constraints.Required
    private List<OrderItem> items;

    private Double totalPrice;
    private String dateOrdered;
    private String datePaid;
    private Boolean isPaid = false;

    public static Finder<Integer, Order> find = new Finder<>(Order.class);


    public Order() {}

    public Order(@Constraints.Required CustomerRepresentative buyer, @Constraints.Required Employee handler, @Constraints.Required List<OrderItem> items, Double totalPrice, String dateOrdered, String datePaid, Boolean isPaid) {
        this.buyer = buyer;
        this.handler = handler;
        this.items = items;
        this.totalPrice = totalPrice;
        this.dateOrdered = dateOrdered;
        this.datePaid = datePaid;
        this.isPaid = isPaid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CustomerRepresentative getBuyer() {
        return buyer;
    }

    public void setBuyer(CustomerRepresentative buyer) {
        this.buyer = buyer;
    }

    public Employee getHandler() {
        return handler;
    }

    public void setHandler(Employee handler) {
        this.handler = handler;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDateOrdered() {
        return dateOrdered;
    }

    public void setDateOrdered(String dateOrdered) {
        this.dateOrdered = dateOrdered;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    public String getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(String datePaid) {
        this.datePaid = datePaid;
    }
}
