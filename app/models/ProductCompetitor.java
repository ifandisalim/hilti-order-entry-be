package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.ebean.Finder;
import play.data.validation.Constraints;

import javax.persistence.*;

@Table(name = "product_competitor")
@Entity(name = "product_competitor")
public class ProductCompetitor {

    @Id
    private Integer id;

    @Constraints.Required
    private String name;

    @Constraints.Required
    private String description;

    @Constraints.Required
    private String imageUrl;

    @Constraints.Required
    @Lob
    @Column( length = 100000 )
    private String technicalData;

    @Constraints.Required
    @Lob
    @Column( length = 100000 )
    private String features;


    @Constraints.Required
    @ManyToOne
    @JoinColumn(name = "related_category_id")
    @JsonIgnore
    private ProductCategory relatedHiltiCategory;


    @Constraints.Required
    private Double price;

    public static Finder<Integer, ProductCompetitor> find = new Finder<>(ProductCompetitor.class);

    public ProductCompetitor() {
    }

    public ProductCompetitor(Integer id, @Constraints.Required String name, @Constraints.Required String description, @Constraints.Required String imageUrl, @Constraints.Required String technicalData, @Constraints.Required String features, @Constraints.Required ProductCategory relatedHiltiCategory, @Constraints.Required Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.technicalData = technicalData;
        this.features = features;
        this.relatedHiltiCategory = relatedHiltiCategory;
        this.price = price;
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

    public String getTechnicalData() {
        return technicalData;
    }

    public void setTechnicalData(String technicalData) {
        this.technicalData = technicalData;
    }

    public ProductCategory getRelatedHiltiCategory() {
        return relatedHiltiCategory;
    }

    public void setRelatedHiltiCategory(ProductCategory relatedHiltiCategory) {
        this.relatedHiltiCategory = relatedHiltiCategory;
    }

    public static Finder<Integer, ProductCompetitor> getFind() {
        return find;
    }

    public static void setFind(Finder<Integer, ProductCompetitor> find) {
        ProductCompetitor.find = find;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
