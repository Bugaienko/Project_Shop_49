package ait.cohort49.shop.service;

import ait.cohort49.shop.model.entity.User;
import ait.cohort49.shop.service.interfaces.ConfirmationService;
import ait.cohort49.shop.service.interfaces.EmailService;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

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



        try {
            // Представление email
            MimeMessage message = mailSender.createMimeMessage();

            //  от кого, кому, тема, текст письма

            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            String emailText = generateEmailText(user);

            // Получаем адрес отправителя из переменной среды
            String fromAddress = System.getenv("MAIL_USERNAME");

            // Указываем отправителя письма
            helper.setFrom(fromAddress);

            // Получатель
            helper.setTo(user.getEmail());

            // Тема письма
            helper.setSubject("Registration Confirmation");

            // Добавляем текст письма, с указанием, что текст HTML
            helper.setText(emailText, true);

            // Отправка письма (метод send объекта JavaMailSender)
            mailSender.send(message);



        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


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

            // Модель данных для подстановки в шаблон
            Map<String, Object> model = new HashMap<>();
            model.put("name", user.getUsername());
            model.put("confirmationLink", confirmationLink);

            // Мы передаем модель в шаблон, чтобы получить текст письма. FreeMarkerTemplateUtils

            return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);


        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
