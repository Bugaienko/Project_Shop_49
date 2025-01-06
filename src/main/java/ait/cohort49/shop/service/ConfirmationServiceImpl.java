package ait.cohort49.shop.service;

import ait.cohort49.shop.model.entity.ConfirmationCode;
import ait.cohort49.shop.model.entity.User;
import ait.cohort49.shop.repository.ConfirmationCodeRepository;
import ait.cohort49.shop.service.interfaces.ConfirmationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

/**
 * @author Sergey Bugaenko
 * {@code @date} 06.01.2025
 */

@Service
public class ConfirmationServiceImpl implements ConfirmationService {

    private final ConfirmationCodeRepository repository;

    public ConfirmationServiceImpl(ConfirmationCodeRepository repository) {
        this.repository = repository;
    }

    @Override
    public String generateConfirmationCode(User user) {
        // Логика генерация кода и его сохранения в БД

        // Генерация уникального кода (используем UUID)
        String code = UUID.randomUUID().toString();

        //Создаем объект, наполняем его, сохраняем в БД
        ConfirmationCode confirmationCode = new ConfirmationCode(
                code,
                LocalDateTime.now().plusDays(1),
//                LocalDateTime.now().plus(5, ChronoUnit.MINUTES),
                user
        );

        repository.save(confirmationCode); // Сохраняем код в БД

        return code; // Возвращаем строку с генерированным кодом
    }
}
