package superserfer.linker.backend.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Login {
    private String username;
    private String password;
    private String newPassword;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public Login(String username, String password, String newPassword) {
        this.username = username;
        this.password = password;
        this.newPassword = newPassword;
    }

    public Login(){

    }
}
