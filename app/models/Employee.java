package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.List;

@Table(name = "employee")
@Entity(name = "employee")
public class Employee extends Model {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Constraints.Required
    private String firstName;

    @Constraints.Required
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "credential_id")
    @Constraints.Required
    @JsonIgnore
    private Credential credential;

    @ManyToOne
    @JoinColumn(name = "department_id")
    @Constraints.Required
    private Department department;

    @ManyToMany
    private List<CustomerRepresentative> favourites;

    public static Finder<Integer, Employee> find = new Finder<>(Employee.class);

    public Employee() {
        super();
    }

    public Employee(String firstName, String lastName, @Constraints.Required Credential credential, Department department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.credential = credential;
        this.department = department;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }


    public List<CustomerRepresentative> getFavourites() {
        return favourites;
    }

    public void setFavourites(List<CustomerRepresentative> favourites) {
        this.favourites = favourites;
    }


}
