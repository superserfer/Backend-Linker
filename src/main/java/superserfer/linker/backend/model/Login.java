package superserfer.linker.backend.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Model for login, new password, forgot username, forgot password
 */
@Getter
@Setter
public class Login {
    private String username;
    private String password;
    private String newPassword;
    private String email;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public Login(String username, String password, String newPassword) {
        this.username = username;
        this.password = password;
        this.newPassword = newPassword;
    }

    public Login(String email) {
        this.email = email;
    }

    public Login(String username, String password, String newPassword, String email) {
        this.username = username;
        this.password = password;
        this.newPassword = newPassword;
        this.email = email;
    }

    public Login(){

    }
}
