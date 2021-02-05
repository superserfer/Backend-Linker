package superserfer.linker.backend.service;

import superserfer.linker.backend.model.User;

public interface IUserService {
    User create(User user);
    void delete(User user);
    User findById(String id);
    User update(User user);
    User findByUsername(String username);
}