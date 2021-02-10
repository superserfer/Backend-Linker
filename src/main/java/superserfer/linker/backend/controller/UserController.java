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

    @GetMapping("/{id}")
    public User findUserById(@PathVariable String id){
        return userService.findById(id);
    }

    @PostMapping
    public User createUser(@Validated @RequestBody User newUser) {
        return userService.create(newUser);
    }

    @PutMapping
    public User updateUser(@Validated @RequestBody User user) {
        return userService.update(user);
    }

    @DeleteMapping
    public void deleteUser(@Validated @RequestBody User user){
        userService.delete(user);
    }
}
