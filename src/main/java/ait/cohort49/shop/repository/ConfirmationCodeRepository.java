package ait.cohort49.shop.repository;

import ait.cohort49.shop.model.entity.ConfirmationCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Sergey Bugaenko
 * {@code @date} 06.01.2025
 */

public interface ConfirmationCodeRepository extends JpaRepository<ConfirmationCode, Long> {

    // Метод для поиска кода подтверждения по его значению
    Optional<ConfirmationCode> findByCode(String code);
}
