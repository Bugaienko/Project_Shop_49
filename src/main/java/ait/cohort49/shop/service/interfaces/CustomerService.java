package ait.cohort49.shop.service.interfaces;

import ait.cohort49.shop.model.entity.Customer;

import java.util.List;

/**
 * @author Sergey Bugaenko
 * {@code @date} 12.12.2024
 */

public interface CustomerService {

    Customer save(Customer customer);
    List<Customer> getAllActiveCustomers();
}
