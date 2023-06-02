package peaksoft.springboot.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.springboot.entity.Agency;
import peaksoft.springboot.entity.Customer;
import peaksoft.springboot.repository.AgencyRepository;
import peaksoft.springboot.repository.CustomerRepository;
import peaksoft.springboot.service.CustomerService;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final AgencyRepository agencyRepository;
    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public boolean existsByEmail(String email) {
        return customerRepository.existsByEmail(email);
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).
                orElseThrow(()-> new NullPointerException
                        ("Customer with id: "+id+" is not found!"));
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public void updateCustomer(Long id, Customer customer) {
        Customer customer1 = customerRepository.findById(id).
                orElseThrow(()-> new NullPointerException
                        ("Customer with id: "+id+" is not found!"));
        customer1.setName(customer.getName());
        customer1.setEmail(customer.getEmail());
        customer1.setGender(customer.getGender());
        customer1.setSurname(customer.getSurname());
        customer1.setDateOfBirth(customer.getDateOfBirth());
        customer1.setPhoneNumber(customer.getPhoneNumber());
        customerRepository.save(customer1);
    }

    @Override
    public void deleteCustomerById(Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        }
        else throw new NullPointerException("Customer with id: " + id + " is not found");
    }

    @Override
    public void assignCustomerToAgency(Long customerId, Long agencyId) {
       Customer customer = customerRepository.findById(customerId).
               orElseThrow(()-> new NullPointerException
                 ("Customer with id: "+customerId+" is not found!"));

        Agency agency = agencyRepository.findById(agencyId).
                orElseThrow(()-> new NullPointerException
                        ("Agency with id: "+agencyId+" is not found!"));
        customer.getAgencyList().add(agency);
        agency.getCustomerList().add(customer);
    }
}
