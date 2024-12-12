package ait.cohort49.shop.repository;

import ait.cohort49.shop.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Sergey Bugaenko
 * {@code @date} 12.12.2024
 */

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
