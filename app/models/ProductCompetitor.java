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
    private String technicalData;

    @Constraints.Required
    @ManyToOne
    @JoinColumn(name = "related_product_id")
    @JsonIgnore
    private Product relatedHiltiProduct;

    public static Finder<Integer, ProductCompetitor> find = new Finder<>(ProductCompetitor.class);

    public ProductCompetitor() {
    }

    public ProductCompetitor(Integer id, @Constraints.Required String name, @Constraints.Required String description, @Constraints.Required String technicalData, @Constraints.Required Product relatedHiltiProduct) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.technicalData = technicalData;
        this.relatedHiltiProduct = relatedHiltiProduct;
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

    public Product getRelatedHiltiProduct() {
        return relatedHiltiProduct;
    }

    public void setRelatedHiltiProduct(Product relatedHiltiProduct) {
        this.relatedHiltiProduct = relatedHiltiProduct;
    }

    public static Finder<Integer, ProductCompetitor> getFind() {
        return find;
    }

    public static void setFind(Finder<Integer, ProductCompetitor> find) {
        ProductCompetitor.find = find;
    }
}