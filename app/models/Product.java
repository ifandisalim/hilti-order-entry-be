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

    @Constraints.Required
    private String applications;

    @Constraints.Required
    private String features;

    @Constraints.Required
    private String technicalData;

    @OneToMany(mappedBy = "relatedHiltiProduct")
    private List<ProductCompetitor> competingProducts;

    public static Finder<Integer, Product> find = new Finder<>(Product.class);

    public Product() {
    }

    public Product(Integer id, @Constraints.Required String name, @Constraints.Required String description, @Constraints.Required Double price, @Constraints.Required String imageUrl, @Constraints.Required ProductCategory category, @Constraints.Required String applications, @Constraints.Required String features, @Constraints.Required String technicalData, List<ProductCompetitor> competingProducts) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.category = category;
        this.applications = applications;
        this.features = features;
        this.technicalData = technicalData;
        this.competingProducts = competingProducts;
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

    public String getApplications() {
        return applications;
    }

    public void setApplications(String applications) {
        this.applications = applications;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getTechnicalData() {
        return technicalData;
    }

    public void setTechnicalData(String technicalData) {
        this.technicalData = technicalData;
    }

    public List<ProductCompetitor> getCompetingProducts() {
        return competingProducts;
    }

    public void setCompetingProducts(List<ProductCompetitor> competingProducts) {
        this.competingProducts = competingProducts;
    }
}
