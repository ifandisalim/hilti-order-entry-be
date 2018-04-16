package models;

import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.List;

@Table(name = "product_category")
@Entity(name = "product_category")
public class ProductCategory extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Constraints.Required
    private String name;

    @Constraints.Required
    private String description;

    @Constraints.Required
    private String imageUrl;

    private boolean isMaster = false;

    @OneToMany(mappedBy = "parentCategory")
    private List<ProductCategory> childCategories;

    @ManyToOne()
    @JoinColumn(name = "parent_category_id")
    private ProductCategory parentCategory;

    @OneToMany
    private List<Product> products;

    public static Finder<Integer, ProductCategory> find = new Finder<>(ProductCategory.class);

    public ProductCategory() {
    }

    public ProductCategory(@Constraints.Required String name,
                           @Constraints.Required String description,
                           @Constraints.Required String imageUrl,
                           boolean isMaster,
                           List<ProductCategory> childCategories,
                           ProductCategory parentCategory,
                           List<Product> products) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.isMaster = isMaster;
        this.childCategories = childCategories;
        this.parentCategory = parentCategory;
        this.products = products;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isMaster() {
        return isMaster;
    }

    public void setMaster(boolean master) {
        isMaster = master;
    }

    public List<ProductCategory> getChildCategories() {
        return childCategories;
    }

    public void setChildCategories(List<ProductCategory> childCategories) {
        this.childCategories = childCategories;
    }

    public ProductCategory getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(ProductCategory parentCategory) {
        this.parentCategory = parentCategory;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
