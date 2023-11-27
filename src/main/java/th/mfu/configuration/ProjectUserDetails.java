
 package th.mfu.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import th.mfu.models.users;
import th.mfu.repositories.UsersRepository;

//for retrieving a username, a password, and other attributes for authenticating to Authentication Provider with a username and password.
//สำหรับดึงข้อมูล username และ password จาก database ไป authenticate ที่ Authentication Provider 
@Service
public class ProjectUserDetails implements UserDetailsService {
    @Autowired
    UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String userName, password;
        List<GrantedAuthority> authorities;
        List<users> customer = usersRepository.findByUsername(username);
        if (customer.size() == 0) {
            throw new UsernameNotFoundException("User details not found for the user : " + username);
        } else{
            userName = customer.get(0).getUsername();
            password = customer.get(0).getPassword();
            authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(customer.get(0).getRole()));
            System.out.println(customer.get(0).getRole());
        }
        return new User(userName,password,authorities);
    }

    
}