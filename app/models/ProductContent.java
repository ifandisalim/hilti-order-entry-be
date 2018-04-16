package models;

import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;

@Table(name = "product_content")
@Entity(name = "product_content")
public class ProductContent extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Constraints.Required
    private String name;

    @Constraints.Required
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @Constraints.Required
    private Product product;

    public static Finder<Integer, ProductContent> find = new Finder<>(ProductContent.class);

    public ProductContent() {
    }

    public ProductContent(String name, Integer quantity) {
        this.name = name;
        this.quantity = quantity;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
