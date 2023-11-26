package th.mfu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//for redirect if there is no / / things and after login
@Controller
public class redirectController {
    

    @GetMapping("")
    public String redirectHome()
    {
        return"redirect:/ano/";
    }
}
