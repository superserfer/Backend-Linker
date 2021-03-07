package superserfer.linker.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import superserfer.linker.backend.model.JsonWebToken;
import superserfer.linker.backend.model.Login;
import superserfer.linker.backend.model.User;
import superserfer.linker.backend.service.*;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private IAuthenticationService authenticationService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IEmailService emailService;

    @PostMapping
    public JsonWebToken login(@Validated @RequestBody Login login) {
        return authenticationService.authenticate(login);
    }

    @PostMapping("/password")
    public User updatePassword(@Validated @RequestBody Login login) {
        JsonWebToken jsonWebToken = authenticationService.authenticate(login);
        User user = userService.findByUsername(login.getUsername());
        return userService.updatePassword(user, login.getNewPassword());
    }

    @PostMapping("/forgot/username")
    public void forgotUsername(@Validated @RequestBody Login login){
        emailService.sendUsername(userService.findByEmail(login.getEmail()));
    }

    @PostMapping("/forgot/password")
    public void forgotPassword(@Validated @RequestBody Login login){

    }

}
