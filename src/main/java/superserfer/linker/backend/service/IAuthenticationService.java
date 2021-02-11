package superserfer.linker.backend.service;

import superserfer.linker.backend.model.JsonWebToken;
import superserfer.linker.backend.model.Login;

public interface IAuthenticationService {
    JsonWebToken authenticate(Login login);
}
