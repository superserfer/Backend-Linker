package superserfer.linker.backend.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
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

import java.util.Date;

@Service
public class AuthenticationService implements IAuthenticationService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 100;

    @Value("${security.pepper}")
    private String pepper;

    @Value("${security.signature}")
    private String signature;

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

    private JsonWebToken createJsonWebToken(User user){
        String jwt = JWT.create()
                .withSubject(user.getUsername())
                .withClaim("id",user.getId())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(signature));
        return new JsonWebToken(jwt);
    }
}
