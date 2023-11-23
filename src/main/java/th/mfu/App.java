package th.mfu;

import java.util.HashSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import th.mfu.models.users;
import th.mfu.repositories.UsersRepository;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
    @Bean
	CommandLineRunner run( UsersRepository userRepo){
		return args ->{
			if(userRepo.findByRole("TUTOR")!=null) return;
			users tutor = new users();
            tutor.setPassword("12345");
            tutor.setRole("TUTOR");
            tutor.setUsername("tutor");

			userRepo.save(tutor);
		};



}
}