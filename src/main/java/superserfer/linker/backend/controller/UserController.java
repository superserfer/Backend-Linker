package superserfer.linker.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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


}
