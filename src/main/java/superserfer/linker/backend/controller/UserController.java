package superserfer.linker.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import superserfer.linker.backend.model.User;
import superserfer.linker.backend.service.IAuthenticationService;
import superserfer.linker.backend.service.IUserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IAuthenticationService authenticationService;

    @GetMapping("/byUsername/{username}")
    public User findUserByUsername(@PathVariable String username,@RequestHeader("Bearer")String bearer) {
        User user = userService.findByUsername(username);
        authenticationService.JWTHasAuthorityToGetUser(bearer, user);
        return user;
    }

    @GetMapping("/byId/{id}")
    public User findUserById(@PathVariable String id, @RequestHeader("Bearer")String bearer){
        User user = userService.findById(id);
        authenticationService.JWTHasAuthorityToGetUser(bearer, user);
        return user;
    }

    @PostMapping
    public User createUser(@Validated @RequestBody User newUser) {
        return userService.create(newUser);
    }

    @PutMapping
    public User updateUser(@Validated @RequestBody User user, @RequestHeader("Bearer")String bearer) {
        User dbUser = userService.findById(user.getId());
        authenticationService.JWTHasAuthorityToGetUser(bearer, dbUser);
        return userService.update(user);
    }

    @DeleteMapping
    public void deleteUser(@Validated @RequestBody User user, @RequestHeader("Bearer")String bearer){
        User dbUser = userService.findById(user.getId());
        authenticationService.JWTHasAuthorityToGetUser(bearer, dbUser);
        userService.delete(user);
    }
}
