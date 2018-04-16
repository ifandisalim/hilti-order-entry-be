package models;

import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;

@Table(name = "product_feature")
@Entity(name = "product_feature")
public class ProductFeature extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Constraints.Required
    private String description;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @Constraints.Required
    private Product product;

    public static Finder<Integer, ProductFeature> find = new Finder<>(ProductFeature.class);

    public ProductFeature() {}

    public ProductFeature(String description, Product product) {
        this.description = description;
        this.product = product;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
