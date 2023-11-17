package th.mfu.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/student")
@CrossOrigin("*")
public class studentController {
    @GetMapping("/buying")
    public String buyingPage(Model model)
    {
        return"studentBuyingPage";
    }
    @GetMapping("/profile")
    public String profilePage(Model model)
    {
        return"studentProfile";
    }
}
