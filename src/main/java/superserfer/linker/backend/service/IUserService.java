package superserfer.linker.backend.service;

import superserfer.linker.backend.model.User;

public interface IUserService {
    User create(User newUser);
    void delete(User user);
    User findById(String id);
    User update(User user);
    User updatePassword(User user,String newPassword);
    User findByUsername(String username);
}
