package ait.cohort49.shop.controller;

import ait.cohort49.shop.exceprionHandling.Response;
import ait.cohort49.shop.model.dto.UserRegisterDto;
import ait.cohort49.shop.service.interfaces.UserService;
import org.springframework.web.bind.annotation.*;

/**
 * @author Sergey Bugaenko
 * {@code @date} 16.01.2025
 */

@RestController
@RequestMapping
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public Response register(@RequestBody UserRegisterDto userRegisterDto) {
        userService.register(userRegisterDto);
        return new Response("Registration complete. Please check your email.");
    }

    @GetMapping("/confirm")
    public Response confirm(@RequestParam String code) {
        return new Response(userService.confirmEmail(code));
    }
}
