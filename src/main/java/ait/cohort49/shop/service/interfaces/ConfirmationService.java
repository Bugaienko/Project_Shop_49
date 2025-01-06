package ait.cohort49.shop.service.interfaces;

import ait.cohort49.shop.model.entity.User;

/**
 * @author Sergey Bugaenko
 * {@code @date} 06.01.2025
 */

public interface ConfirmationService {

    // Метод для генерации кода подтверждения
    String generateConfirmationCode(User user);
}
