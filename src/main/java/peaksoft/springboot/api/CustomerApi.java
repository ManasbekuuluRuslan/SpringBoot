package peaksoft.springboot.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import peaksoft.springboot.entity.Agency;
import peaksoft.springboot.entity.Customer;
import peaksoft.springboot.exception.MyException;
import peaksoft.springboot.service.AgencyService;
import peaksoft.springboot.service.CustomerService;

import java.time.LocalDate;

@Controller
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerApi {

    private final CustomerService customerService;
    private final AgencyService agencyService;
    @GetMapping()
    public String getAllCustomers(Model model) {
        model.addAttribute("customers",customerService.getAllCustomers());
        return "CustomerPage";
    }

    @GetMapping("/{id}")
    public String getACustomerById(@PathVariable ("id")Long id,Model model) {
        model.addAttribute("customers", customerService.getCustomerById(id));
        return "customerInfo";
    }

    @GetMapping("/new")
    public String createCustomer(Model model) {
        model.addAttribute("errorMessage", "Ошибка: Неверный формат email");
        model.addAttribute("newCustomer", new Customer());
        return "newCustomer";
    }

    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute("newCustomer")Customer customer, BindingResult bindingResult) throws MyException {
        int phoneNumber = customer.getPhoneNumber();
        String phoneNumberString = String.valueOf(phoneNumber);
        if(phoneNumberString.length() !=13){
            throw new MyException("Phone number must not be less than 13 characters");
        }
        if(bindingResult.hasErrors()){
            return "newCustomer";
        }
        if (customerService.existsByEmail(customer.getEmail())) {
            bindingResult.rejectValue("email", "error.customer", "A client with this email already exists");
            return "newCustomer";
        }
        LocalDate today = LocalDate.now();
        LocalDate minAgeDate = today.minusYears(18);

        if (customer.getDateOfBirth().isAfter(minAgeDate)) {
            throw new MyException("Your minimum age must be 18 at least!");
        }
        if(customer.getName().isEmpty() && customer.getEmail().isEmpty()){
            throw new MyException("Fields should not be null when stored!");
        }
        if(customer.getSurname().isEmpty()){
            throw new MyException("Fields should not be null when stored!");
        }
        int num = customer.getPhoneNumber();
        if(num ==0){
            throw new MyException("Fields should not be null when stored!");
        }
//        LocalDate date = customer.getDateOfBirth();
//        if(date == null){
//            return "error4";
//        }
        customerService.saveCustomer(customer);
        return "redirect:/customers";
    }

    @PostMapping("/{id}/delete")
    public String deleteCustomer(@PathVariable("id") Long id){
        customerService.deleteCustomerById(id);
        return "redirect:/customers";
    }

    @GetMapping("/{id}/edit")
    public String update(@PathVariable("id") Long id,
                         Model model) {
        model.addAttribute("editCustomer", customerService.getCustomerById(id));
        return "updateCustomer";
    }

    @PostMapping("/updateCustomer/{id}")
    public String saveUpdate(@ModelAttribute("editCustomer")Customer customer,
                             @PathVariable("id") Long id) {
        customerService.updateCustomer(id, customer);
        return "redirect:/customers";
    }
    @GetMapping("/assign")
    public String assignCustomerToAgency(Model model) {
        model.addAttribute("assignCustomer", customerService.getAllCustomers());
        model.addAttribute("assignAgency", agencyService.getAllAgencies());
        return "assigned";
    }
    @GetMapping("/rusi")
    public String assign(@PathVariable("id") Long customerId,
                         @PathVariable("id") Long agencyId, Model model) {
        model.addAttribute("newAgency", new Agency());
        model.addAttribute("newCustomer", new Customer());
        customerService.assignCustomerToAgency(customerId, agencyId);
        return "assign";
    }
}
