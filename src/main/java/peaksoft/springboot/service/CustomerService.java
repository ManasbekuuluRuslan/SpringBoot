package peaksoft.springboot.service;

import peaksoft.springboot.entity.Customer;

import java.util.List;

public interface CustomerService {
    void saveCustomer(Customer customer);
    boolean existsByEmail(String email);
    Customer getCustomerById(Long id);
    List<Customer> getAllCustomers();
    void updateCustomer(Long id, Customer customer);
    void deleteCustomerById(Long id);
    void assignCustomerToAgency(Long customerId, Long agencyId);
}
