package superserfer.linker.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import superserfer.linker.backend.model.JsonWebToken;
import superserfer.linker.backend.model.Login;
import superserfer.linker.backend.model.User;
import superserfer.linker.backend.service.AuthenticationService;
import superserfer.linker.backend.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

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

}
