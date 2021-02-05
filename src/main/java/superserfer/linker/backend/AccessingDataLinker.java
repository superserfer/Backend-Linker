package superserfer.linker.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import superserfer.linker.backend.model.User;
import superserfer.linker.backend.repository.UserRepository;

@SpringBootApplication
public class AccessingDataLinker implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(AccessingDataLinker.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//        userRepository.deleteAll();
//
//        userRepository.save(new User("superserfer","linus.ackermann@hispeed.ch"));
//        userRepository.save(new User("nikus","nik.nik@nik.nik"));



        userRepository.findAll().forEach(user -> {
            System.out.println(user.toString());
        });
    }
}
