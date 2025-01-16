package ait.cohort49.shop.service;

import ait.cohort49.shop.model.dto.UserRegisterDto;
import ait.cohort49.shop.model.entity.User;
import ait.cohort49.shop.repository.UserRepository;
import ait.cohort49.shop.service.interfaces.EmailService;
import ait.cohort49.shop.service.interfaces.RoleService;
import ait.cohort49.shop.service.interfaces.UserService;
import ait.cohort49.shop.service.mapping.UserMappingService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, RoleService roleService, EmailService emailService, UserMappingService userMappingService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.emailService = emailService;
        this.userMappingService = userMappingService;
    }

    @Override
    @Transactional
    public void register(UserRegisterDto userRegisterDto) {

        User user = userMappingService.mapDtoToEntity(userRegisterDto);

        // Убедиться, что указанный email не используется
        if (userRepository.existsByEmail(user.getEmail()) &&
            userRepository.findByEmail(user.getEmail()).get().isActive()) {
            throw new RuntimeException("User with email " + user.getEmail() + " already exists");
        }

        // Вытаскиваем пользователя из БД. Если email есть + статус = false
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());

        if (optionalUser.isPresent()) {
            user = optionalUser.get();
            // Если пользователь есть - установить id остается прежним
//            user.setId(null);

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
}
