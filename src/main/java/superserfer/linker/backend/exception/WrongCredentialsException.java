package superserfer.linker.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class WrongCredentialsException extends ResponseStatusException {
    public WrongCredentialsException(String message){
        super(HttpStatus.UNAUTHORIZED, message);
    }
}
