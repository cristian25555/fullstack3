package com.amigoscode.customer;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("list")
public class CustomerListDataAccessService implements CustomerDao{

    private static List<Customer> customers;

    static {
        customers = new ArrayList<>();
        Customer alex= new Customer(
                "Alex",
                "a@gmail.com",
                21

        );
        Customer yamila= new Customer(
                "Yamila",
                "y@gmail.com",
                19

        );
        customers.add(alex);
        customers.add(yamila);
    }


    @Override
    public List<Customer> selectAllCustomers() {
        return customers;
    }

    @Override
    public Optional<Customer> selectCustomerById(Integer id) {
        return customers.stream()
                .filter(c-> c.getId().equals(id))
                .findFirst();
    }

    @Override
    public void insertCustomer(Customer customer) {
        customers.add(customer);
    }

    @Override
    public boolean existsPersonWithEmail(String email) {
        return customers.stream()
                .anyMatch(c -> c.getEmail().equals(email) );
    }

    @Override
    public boolean existsCustomerById(Integer id) {
        return customers.stream()
                .anyMatch(c -> c.getId().equals(id) );
    }

    @Override
    public void deleteCustomerById(Integer customerId) {
        customers.stream().filter(c -> c.getId().equals(customerId))
                .findFirst()
                .ifPresent(customers::remove);
    }

    @Override
    public void updateCustomer(Customer customer){
        customers.add(customer);
    }
}
