package th.mfu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import th.mfu.repositories.CourseRepository;
import th.mfu.repositories.FAQRepository;
import th.mfu.repositories.VideoRepository;

@Controller
@RequestMapping("/ano")
@CrossOrigin("*")
public class anoController {
    @Autowired
    CourseRepository courseRepo;
    @Autowired
    VideoRepository videoRepo;
    @Autowired 
    FAQRepository faqRepo;

    @GetMapping("/")
    public String buyingPage(Model model)
    {   

        model.addAttribute("courses", courseRepo.findAll());
        return"index";
    }

    @GetMapping("/Courses")
    public String coursesPage(Model model)
    {
        return"anoCoursesPage";
    }

    @GetMapping("/support")
     public String supportPage(Model model)
    {

        return"anoSupportPage";
    }
    @GetMapping("/theCousre/{cId}")
    public String theCoursePage(@PathVariable Long cId, Model model)
    {
        model.addAttribute("course", courseRepo.findById(cId).get());
        model.addAttribute("units", videoRepo.findByCourseId(cId));
        return"anoTheCoursePage";
    }

  
}
