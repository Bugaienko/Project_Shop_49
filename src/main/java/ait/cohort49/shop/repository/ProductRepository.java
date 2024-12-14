package ait.cohort49.shop.repository;

import ait.cohort49.shop.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Sergey Bugaenko
 * {@code @date} 11.12.2024
 */

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Автоматически сгенерирован запрос для получения всех продуктов, у которых
    // поле active имеет значение true
    List<Product> findByActiveTrue();

}
