package ait.cohort49.shop.controller;

import ait.cohort49.shop.exceprionHandling.Response;
import ait.cohort49.shop.model.dto.UserRegisterDto;
import ait.cohort49.shop.service.interfaces.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sergey Bugaenko
 * {@code @date} 16.01.2025
 */

@RestController
@RequestMapping("/register")
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public Response register(@RequestBody UserRegisterDto userRegisterDto) {
        userService.register(userRegisterDto);
        return new Response("Registration complete. Please check your email.");
    }
}
