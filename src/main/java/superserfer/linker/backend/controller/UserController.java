package superserfer.linker.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import superserfer.linker.backend.model.User;
import superserfer.linker.backend.service.IUserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/{username}")
    public User findUserByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @PostMapping
    public User createUser(@Validated @RequestBody User newUser) {
        return userService.create(newUser);
    }


}
