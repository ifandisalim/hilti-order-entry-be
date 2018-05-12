package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.List;

@Table(name = "customer_representative")
@Entity(name = "customer_representative")
public class CustomerRepresentative extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Constraints.Required
    private String firstName;

    @Constraints.Required
    private String lastName;

    private String customerClass;

    @Constraints.Required
    private double creditLimit;
    private double creditUsed;

    @ManyToOne
    @JoinColumn(name = "company_id")
    @Constraints.Required
    private Customer company;

    @OneToMany(mappedBy = "buyer")
    private List<Order> orderHistory;

    @ManyToMany(mappedBy = "favourites")
    @JsonIgnore
    private List<Employee> favouritedBy;

    public static Finder<Integer, CustomerRepresentative> find = new Finder<>(CustomerRepresentative.class);

    public CustomerRepresentative() {
        super();
    }

    public CustomerRepresentative(@Constraints.Required String firstName, @Constraints.Required String lastName, String customerClass, @Constraints.Required double creditLimit, double creditUsed, @Constraints.Required Customer company, List<Order> orderHistory, List<Employee> favouritedBy) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.customerClass = customerClass;
        this.creditLimit = creditLimit;
        this.creditUsed = creditUsed;
        this.company = company;
        this.orderHistory = orderHistory;
        this.favouritedBy = favouritedBy;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Customer getCompany() {
        return company;
    }

    public void setCompany(Customer company) {
        this.company = company;
    }

    public String getCustomerClass() {
        return customerClass;
    }

    public void setCustomerClass(String customerClass) {
        this.customerClass = customerClass;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public double getCreditUsed() {
        return creditUsed;
    }

    public void setCreditUsed(double creditUsed) {
        this.creditUsed = creditUsed;
    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(List<Order> orderHistory) {
        this.orderHistory = orderHistory;
    }

    public List<Employee> getFavouritedBy() {
        return favouritedBy;
    }

    public void setFavouritedBy(List<Employee> favouritedBy) {
        this.favouritedBy = favouritedBy;
    }
}
