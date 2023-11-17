package th.mfu.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ProjectSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        
          http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> {
                auth.requestMatchers("/tutor/**").hasAnyAuthority("TUTOR");
                auth.requestMatchers("/student/**").authenticated();
                auth.anyRequest().permitAll();
            }).formLogin(Customizer.withDefaults()).oauth2Login(Customizer.withDefaults()).httpBasic();//
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
        @Bean
        public PasswordEncoder passwordEncoder() {
            return NoOpPasswordEncoder.getInstance();
        }
    
        @Bean
        public InMemoryUserDetailsManager userDetailsService() {
            UserDetails admin = User.withUsername("tutor")
                    .password("12345")
                    .authorities("TUTOR")
                    .build();
            return new InMemoryUserDetailsManager(admin);
        }
    
        /*@Bean
        public UserDetailsService userDetailsService(DataSource dataSource) {
            return new JdbcUserDetailsManager(dataSource);
        }*/
    

    }

