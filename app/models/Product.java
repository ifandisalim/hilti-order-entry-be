package models;

import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.List;

@Table(name = "product")
@Entity(name = "product")
public class Product extends Model{

    @Id
    private Integer id;

    @Constraints.Required
    private String name;

    @Constraints.Required
    private String description;

    @Constraints.Required
    private Double price;

    @Constraints.Required
    private String imageUrl;

    @ManyToOne
    @Constraints.Required
    @JoinColumn(name = "product_category_id")
    private ProductCategory category;

    @OneToMany(cascade = CascadeType.ALL)
    @Constraints.Required
    private List<ProductApplication> applications;

    @OneToMany(cascade = CascadeType.ALL)
    @Constraints.Required
    private List<ProductFeature> features;

    @OneToMany(cascade = CascadeType.ALL)
    @Constraints.Required
    private List<ProductContent> content;

    public static Finder<Integer, Product> find = new Finder<>(Product.class);

    public Product() {
    }

    public Product(Integer id,
                   @Constraints.Required String name,
                   @Constraints.Required String description,
                   @Constraints.Required Double price,
                   @Constraints.Required String imageUrl,
                   @Constraints.Required ProductCategory category,
                   @Constraints.Required List<ProductApplication> applications,
                   @Constraints.Required List<ProductFeature> features,
                   @Constraints.Required List<ProductContent> content) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.category = category;
        this.applications = applications;
        this.features = features;
        this.content = content;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public List<ProductApplication> getApplications() {
        return applications;
    }

    public void setApplications(List<ProductApplication> applications) {
        this.applications = applications;
    }

    public List<ProductFeature> getFeatures() {
        return features;
    }

    public void setFeatures(List<ProductFeature> features) {
        this.features = features;
    }

    public List<ProductContent> getContent() {
        return content;
    }

    public void setContent(List<ProductContent> content) {
        this.content = content;
    }


}
