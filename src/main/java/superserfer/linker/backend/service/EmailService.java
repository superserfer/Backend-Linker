package superserfer.linker.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import superserfer.linker.backend.model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmailService implements IEmailService {

    @Qualifier("getJavaMailSender")
    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendUsername(User user) {
        String message = String.format(
                        "%s \nHello %s \n\nYou forgot your username, here he is.\n Thank you for using linker."
        ,user.getUsername(),user.getUsername());
        sendMail(user.getEmail(), "Username",message);
    }

    @Override
    public void sendNewPassword() {

    }

    @Override
    public void sendGreetings(User user) {

    }

    @Override
    public boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(email);
        return mat.matches();
    }

    private void sendMail(String destinationEmail,String subject, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("linker.noreply@gmail.com");
        simpleMailMessage.setTo(destinationEmail);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        emailSender.send(simpleMailMessage);
    }
}
