package superserfer.linker.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import superserfer.linker.backend.model.JsonWebToken;
import superserfer.linker.backend.model.Login;
import superserfer.linker.backend.service.AuthenticationService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping
    public JsonWebToken login(@Validated @RequestBody Login login) {
        return authenticationService.authenticate(login);
    }

}
