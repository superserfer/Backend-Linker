package superserfer.linker.backend.service;

import superserfer.linker.backend.model.User;

public interface IEmailService {
    void sendUsername(User user);
    void sendNewPassword();
    void sendGreetings(User user);
    boolean validateEmail(String email);
}
