package ait.cohort49.shop.service;

import ait.cohort49.shop.model.entity.User;
import ait.cohort49.shop.service.interfaces.ConfirmationService;
import ait.cohort49.shop.service.interfaces.EmailService;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Sergey Bugaenko
 * {@code @date} 06.01.2025
 */

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    private final Configuration mailConfig;

    private final ConfirmationService confirmationService;

    private final static String HOST = "http://localhost:8080/api";

    public EmailServiceImpl(JavaMailSender mailSender, Configuration mailConfig, ConfirmationService confirmationService) {
        this.mailSender = mailSender;
        this.mailConfig = mailConfig;
        this.confirmationService = confirmationService;

        // Настройка кодировки и расположение шаблонов
        this.mailConfig.setDefaultEncoding("UTF-8");
        this.mailConfig.setTemplateLoader(new ClassTemplateLoader(this.getClass(), "/mail"));
    }


    @Override
    public void sendConfirmationEmail(User user) {


    }

    private String generateEmailText(User user) {
        try {
            // Загрузка шаблона письма
            Template template = mailConfig.getTemplate("confirm_reg_mail.ftlh");

            // Генерация кода
            String code = confirmationService.generateConfirmationCode(user);

            // Сформировать ссылку
            // http://localhost:8080/api/confirm?code=сгенерированный_код

            String confirmationLink = HOST + "/confirm?code=" + code;

            // Наполнение шаблона данными пользователя


            return "";

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
