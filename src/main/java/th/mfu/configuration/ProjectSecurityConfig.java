package th.mfu.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ProjectSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        
          http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> {
                auth.requestMatchers("/tutor/**").hasAuthority("TUTOR");
                auth.requestMatchers("/student/**").hasAuthority("STUDENT");
                auth.anyRequest().permitAll();
            }).formLogin(formLogin -> formLogin.loginPage("/login")).oauth2Login(oauth2Login -> oauth2Login.loginPage("/login")).httpBasic();
            //.formLogin(Customizer.withDefaults()).oauth2Login(Customizer.withDefaults()).httpBasic();//
            return http.build();

         
        /* 
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/tutor/**").hasAnyAuthority("TUTOR")
                .requestMatchers("/student/**").authenticated()
                .anyRequest().permitAll())
                .formLogin(Customizer.withDefaults()).oauth2Login(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();
        */
        }
        /* 
        @Bean
        public UserDetailsService userDetailsService(DataSource dataSource)
        {
            return new JdbcUserDetailsManager(dataSource);
        }
        */
        @Bean
        public PasswordEncoder passwordEncoder() {
            return NoOpPasswordEncoder.getInstance();
        }

       
        /* 
        @Bean
        public InMemoryUserDetailsManager userDetailsService() {
            UserDetails admin = User.withUsername("tutor")
                    .password("12345")
                    .authorities("TUTOR")
                    .build();
            return new InMemoryUserDetailsManager(admin);
        }
         */
        /*@Bean
        public UserDetailsService userDetailsService(DataSource dataSource) {
            return new JdbcUserDetailsManager(dataSource);
        }*/
    

    }

