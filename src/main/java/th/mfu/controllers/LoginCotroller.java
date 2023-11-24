package th.mfu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import th.mfu.models.users;
import th.mfu.repositories.UsersRepository;

@Controller
public class LoginCotroller {
    @Autowired
    UsersRepository userRepo;
    

    @GetMapping("/login")
    public String LoginPage(){
        return"login";
    }

    @GetMapping("/register")
    public String registerUserPage(Model model ) {
      
        model.addAttribute("user", new users());
        
        return "anoRegisterPage";
    }

     @PostMapping("/register")
    public String registerUser(@ModelAttribute users user,Model model ) {
    try {
        System.out.println("User naaaaaaaaaaam"+user.getUsername());
        if (userRepo.findByusername(user.getUsername())!= null) {
            model.addAttribute("resp", "An exception occured due to repeated usesrname");
            return"anoResp";
        }
        users savedUser = null;
        user.setRole("STUDENT");
        userRepo.save(user);
       
            savedUser = userRepo.findById(user.getId()).get();
            if (savedUser.getId() > 0) {
               model.addAttribute("resp", "Registeration Succesful");
            }
        } catch (Exception ex) {
           model.addAttribute("resp","An exception occured due to " + ex.getMessage());
        }
        return "anoResp";
    }

    //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    @GetMapping("/getDetail")
    public String detailPage(Model model ) {
    
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        users user = userRepo.findByusername(username);
        model.addAttribute("user", user);
        
        return "detailPage";
    }
}
