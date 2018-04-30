package graphqlconfig;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import models.*;

import java.util.List;

public class RootQueryResolver implements GraphQLQueryResolver {

    public RootQueryResolver() {
    }


    public List<Employee> employees() {
        return Employee.find.all();
    }

    public Employee employee(Integer id) {
        return Employee.find.byId(id);
    }

    public List<Department> departments() {
        return Department.find.all();
    }

    public Department department(Integer id) {
        return Department.find.byId(id);
    }

    public List<Product> products() {
        return Product.find.all();
    }

    public Product product(Integer id) {
        return Product.find.byId(id);
    }

    public List<ProductCategory> categories() {
        return ProductCategory.find.all();
    }

    public List<ProductCategory> categoriesMaster() {
        return ProductCategory.find
                .query()
                .where()
                .eq("isMaster", true)
                .findList();
    }

    public ProductCategory category(Integer id) {
        return ProductCategory.find.byId(id);
    }

    public List<Order> orders() {
        return Order.find.all();
    }

    public Order order(Integer id) {
        return Order.find.byId(id);
    }

    public List<CustomerRepresentative> customerRepresentatives() { return CustomerRepresentative.find.all(); }

    public CustomerRepresentative customerRepresentative(Integer id) {
        return CustomerRepresentative.find.byId(id);
    }

    public List<Order> customerRepOrderHistory(Integer customerRepresentativeId, Integer offset) {
        return Order.find.query()
                .where()
                .eq("buyer_id", customerRepresentativeId)
                .setFirstRow(offset)
                .setMaxRows(10)
                .findList();
    }

    public List<Order> employeeOrderHistory(Integer employeeId, Integer offset) {
        return Order.find.query()
                .where()
                .eq("handler_id", employeeId)
                .setFirstRow(offset)
                .setMaxRows(10)
                .findList();
    }
}
