package models;


import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;

@Table(name = "customer")
@Entity(name = "customer")
public class Customer extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Constraints.Required
    @Column(unique = true)
    private String name;

    public static Finder<Integer, Customer> find = new Finder<>(Customer.class);

    public Customer() {
    }

    public Customer(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
