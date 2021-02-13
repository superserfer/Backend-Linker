package superserfer.linker.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import superserfer.linker.backend.exception.NoSuchElementFoundException;
import superserfer.linker.backend.exception.WrongCredentialsException;
import superserfer.linker.backend.model.User;
import superserfer.linker.backend.repository.UserRepository;
import superserfer.linker.backend.model.JsonWebToken;
import superserfer.linker.backend.model.Login;
import superserfer.linker.backend.security.JwtTokenUtil;

@Service
public class AuthenticationService implements IAuthenticationService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${security.pepper}")
    private String pepper;

    @Override
    public JsonWebToken authenticate(Login login) {
        User user = userRepository.findByUsername(login.getUsername());
        //Check if User with username exist
        if (user == null)
            throw new NoSuchElementFoundException(String.format("User with username: '%s' not found.",login.getUsername()));
        //Check if password equals
        if (!passwordEncoder.matches(login.getPassword() + pepper,user.getPassword()))
            throw new WrongCredentialsException("Wrong password.");

        return createJsonWebToken(user);
    }

    @Override
    public User getUserByJsonWebToken(JsonWebToken jsonWebToken) {
        return null;
    }

    @Override
    public boolean JWTHasAuthorityToGetUser(String jwt, User user) {
        if (!jwtTokenUtil.validateToken(jwt,user))
            throw new WrongCredentialsException("Invalid JWT.");
        return true;
    }

    private JsonWebToken createJsonWebToken(User user){
        return new JsonWebToken(jwtTokenUtil.generateToken(user.getId()));
    }

}
