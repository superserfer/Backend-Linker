package superserfer.linker.backend.service;

import superserfer.linker.backend.model.JsonWebToken;
import superserfer.linker.backend.model.Login;
import superserfer.linker.backend.model.User;

public interface IAuthenticationService {
    JsonWebToken authenticate(Login login);
    User getUserByJsonWebToken(JsonWebToken jsonWebToken);
}
