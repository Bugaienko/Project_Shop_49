package ait.cohort49.shop.service;

import ait.cohort49.shop.model.dto.UserRegisterDto;
import ait.cohort49.shop.model.entity.ConfirmationCode;
import ait.cohort49.shop.model.entity.User;
import ait.cohort49.shop.repository.ConfirmationCodeRepository;
import ait.cohort49.shop.repository.UserRepository;
import ait.cohort49.shop.service.interfaces.EmailService;
import ait.cohort49.shop.service.interfaces.RoleService;
import ait.cohort49.shop.service.interfaces.UserService;
import ait.cohort49.shop.service.mapping.UserMappingService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

/**
 * @author Sergey Bugaenko
 * {@code @date} 16.01.2025
 */

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final EmailService emailService;
    private final UserMappingService userMappingService;
    private final ConfirmationCodeRepository confirmationCodeRepository;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, RoleService roleService, EmailService emailService, UserMappingService userMappingService, ConfirmationCodeRepository confirmationCodeRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.emailService = emailService;
        this.userMappingService = userMappingService;
        this.confirmationCodeRepository = confirmationCodeRepository;
    }

    @Override
    @Transactional
    public void register(UserRegisterDto userRegisterDto) {

        User user = userMappingService.mapDtoToEntity(userRegisterDto);

        if (userRepository.existsByEmail(user.getEmail())) {
            // Пользователь с таким email уже сохранен в БД.
            // проверить его статус и т.д.
            updateUserCredentials(user);
            return;
        }


        // Устанавливаем роль "USER"
        user.setRoles(Set.of(roleService.getRoleUser()));

        // Шифруем пароль
        user.setPassword(passwordEncoder.encode(userRegisterDto.password()));

        // Устанавливаем статус false
        user.setActive(false);

        // сохраняем пользователя
        userRepository.save(user);

        // После сохранения, отправить письмо с кодом подтверждения (код сохраняется в БД)
        emailService.sendConfirmationEmail(user);

    }


    private void updateUserCredentials(User user) {

        // Уже проверили в методе register, что пользователь с таким email есть.
        User existUser = userRepository.findByEmail(user.getEmail()).get();


        // Убедиться, что указанный email не используется (проверяем active пользователя)
        if (existUser.isActive()) {
            throw new RuntimeException("User with email " + user.getEmail() + " already exists");
        }
        // Обновляем пароль
        existUser.setPassword(passwordEncoder.encode(user.getPassword()));
        // Обновить username

        if (!existUser.getUsername().equals(user.getUsername())) {
            existUser.setUsername(user.getUsername());
        }
//
//        existUser.setUsername(user.getUsername());
//
        userRepository.save(existUser);

        emailService.sendConfirmationEmail(existUser);
    }


    @Override
    public String confirmEmail(String code) {
        Optional<ConfirmationCode> optionalConfirmationCode = confirmationCodeRepository.findByCode(code);

        if (optionalConfirmationCode.isEmpty()) {
            throw new RuntimeException("Confirmation code not found");
        }

        ConfirmationCode confirmationCode = optionalConfirmationCode.get();

        if (confirmationCode.getExpired().isAfter(LocalDateTime.now())) {

            User user = confirmationCode.getUser();
            user.setActive(true);
            userRepository.save(user);

            return user.getEmail() + " confirmed!";
        }

        throw new RuntimeException("Confirmation code expired!");
    }
}
