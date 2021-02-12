package superserfer.linker.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class WrongEmailFormatException extends ResponseStatusException {
    public WrongEmailFormatException(String message){
        super(HttpStatus.BAD_REQUEST,message);
    }
}
