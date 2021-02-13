package superserfer.linker.backend.service;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import superserfer.linker.backend.exception.*;
import superserfer.linker.backend.model.User;
import superserfer.linker.backend.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IEmailService emailService;

    @Value("${security.pepper}")
    private String pepper;

    @Override
    public User create(User newUser) {
        //Check if username already exist
        if (userRepository.findByUsername(newUser.getUsername()) != null)
            throw new DuplicateIndexException(String.format("Username: '%s' is already in use.", newUser.getUsername()));
        //Check if email already exist
        if (userRepository.findByEmail(newUser.getEmail()) != null)
            throw new DuplicateIndexException(String.format("Email: '%s' is already in use.",newUser.getEmail()));
        //Check if all attributes are here
        if (newUser.getUsername() == null || newUser.getEmail() == null || newUser.getPassword() == null)
            throw new MissingAttributeException("Your user has missing attributes.");
        //Check if Email is valid
        if (!emailService.validateEmail(newUser.getEmail()))
            throw new WrongEmailFormatException(String.format("'%s' is not a email.",newUser.getEmail()));
        //encodes password and add salt
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword() + pepper));
        return userRepository.save(newUser);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public User findById(String id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty())
            throw new NoSuchElementFoundException(String.format("User with ID: '%s' not found.",id));
        return optionalUser.get();
    }

    @Override
    public User update(User user) {
        Optional<User> oldUserOptional = userRepository.findById(user.getId());
        //Check if User exists
        if (oldUserOptional.isEmpty())
            throw new NoSuchElementFoundException("User not found.");
        User oldUser = oldUserOptional.get();
        //Check if new Username is already taken
        if (!oldUser.getUsername().equals(user.getUsername()) & userRepository.findByUsername(user.getUsername()) != null)
            throw new DuplicateIndexException(String.format("Username: '%s' is already in use.",user.getUsername()));
        //Check if new Email is already taken
        if (!oldUser.getEmail().equals(user.getEmail()) & userRepository.findByEmail(user.getEmail()) != null)
            throw new DuplicateIndexException(String.format("Email: '%s' is already in use.",user.getEmail()));
        //Check if Email is valid
        if (emailService.validateEmail(user.getEmail()))
            throw new WrongEmailFormatException(String.format("'%s' is not a email.",user.getEmail()));
        //assign password from oldUser to user
        user.setPassword(oldUser.getPassword());
        return userRepository.save(user);
    }


    //Methode not implemented
    @Override
    public User updatePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword + pepper));
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null)
            throw new NoSuchElementFoundException(String.format("User with username: '%s' not found.",username));
         return user;
    }

    @Override
    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null)
            throw new NoSuchElementFoundException(String.format("User with email: '%s' not found.",email));
        return user;
    }
}
