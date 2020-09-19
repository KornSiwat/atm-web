package th.ac.ku.atm.service;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import th.ac.ku.atm.model.Customer;

@Service
public class CustomerService {

    private List<Customer> customerList;

    @PostConstruct
    public void postConstruct() {
        this.customerList = new ArrayList<>();
    }

    public void createCustomer(Customer customer) {
        customerList.add(customer);
    }

    public List<Customer> getCustomers() {
        return new ArrayList<>(this.customerList);
    }
}
