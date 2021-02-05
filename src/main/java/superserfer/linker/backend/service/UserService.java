package superserfer.linker.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import superserfer.linker.backend.exception.DuplicateIndexException;
import superserfer.linker.backend.exception.MissingAttributeException;
import superserfer.linker.backend.exception.NoSuchElementFoundException;
import superserfer.linker.backend.model.User;
import superserfer.linker.backend.repository.UserRepository;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User create(User newUser) {
        if (userRepository.findByUsername(newUser.getUsername()) != null)
            throw new DuplicateIndexException(String.format("A user with the username:'%s' already exist.", newUser.getUsername()));
        if (userRepository.findByEmail(newUser.getEmail()) != null)
            throw new DuplicateIndexException(String.format("'%s' is already in use.",newUser.getEmail()));
        if (newUser.getUsername() == null || newUser.getEmail() == null || newUser.getPassword() == null)
            throw new MissingAttributeException("Your user has missing attributes.");
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return userRepository.save(newUser);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updatePassword(User user, String newPassword) {
        return null;
    }

    @Override
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null)
            throw new NoSuchElementFoundException(String.format("'%s doesn't exist.'",username));
         return user;
    }
}
