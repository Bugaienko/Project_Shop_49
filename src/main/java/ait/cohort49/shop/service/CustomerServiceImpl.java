package ait.cohort49.shop.service;

import ait.cohort49.shop.model.entity.Cart;
import ait.cohort49.shop.model.entity.Customer;
import ait.cohort49.shop.repository.CustomerRepository;
import ait.cohort49.shop.service.interfaces.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Sergey Bugaenko
 * {@code @date} 12.12.2024
 */

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public Customer save(Customer customer) {
       customer.setId(null);
       customer.setActive(true);

        Cart cart = new Cart();
        cart.setCustomer(customer);
        customer.setCart(cart);


        return customerRepository.save(customer);

    }

    @Override
    public List<Customer> getAllActiveCustomers() {
        return List.of();
    }
}
