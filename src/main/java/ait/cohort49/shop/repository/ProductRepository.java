package ait.cohort49.shop.repository;

import ait.cohort49.shop.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Sergey Bugaenko
 * {@code @date} 11.12.2024
 */

public interface ProductRepository extends JpaRepository<Product, Long> {


}
