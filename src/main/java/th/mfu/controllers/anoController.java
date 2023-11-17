package th.mfu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ano")
@CrossOrigin("*")
public class anoController {
    @GetMapping("/")
    public String buyingPage(Model model)
    {
        return"index";
    }

    @GetMapping("/Courses")
    public String coursesPage(Model model)
    {
        return"anoCoursesPage";
    }

    @GetMapping("/FAQ")
     public String FAQPage(Model model)
    {
        return"anoFAQPage";
    }

    @GetMapping("/contacts")
     public String contactPage(Model model)
    {
        return"anocontactPage";
    }
}
