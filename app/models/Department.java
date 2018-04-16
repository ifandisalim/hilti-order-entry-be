package models;

import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

import javax.annotation.Resource;
import javax.persistence.*;

@Table(name = "department")
@Entity(name = "department")
public class Department extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Constraints.Required
    @Column(unique = true)
    private String name;

    public static Finder<Integer, Department> find = new Finder<>(Department.class);

    private Department() {}

    public Department(String name) {
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
