package th.mfu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import th.mfu.models.CourseCategory;
import th.mfu.repositories.CategoryRepository;
import th.mfu.repositories.CourseRepository;
import th.mfu.repositories.FAQRepository;
import th.mfu.repositories.VideoRepository;

//Not login users
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
    @Autowired
    CategoryRepository cateRepo;

    @GetMapping("/")
    public String buyingPage(Model model)
    {   

        model.addAttribute("courses", courseRepo.findAll());
        return"index";
    }

    @GetMapping("/Courses")
    public String coursesPage(Model model)
    {
        model.addAttribute("catogories", cateRepo.findAll());
        return"anoCoursesPage";
    }

    @GetMapping("/support")
     public String supportPage(Model model)
    {
        model.addAttribute("faqs", faqRepo.findAll());
        return"anoSupportPage";
    }
    @GetMapping("/theCousre/{cId}")
    public String theCoursePage(@PathVariable Long cId, Model model)
    {
        model.addAttribute("course", courseRepo.findById(cId).get());
        model.addAttribute("units", videoRepo.findByCourseId(cId));
        return"anoTheCoursePage";
    }
     @GetMapping("/theCate/{cateId}")
    public String theCategoryPage(@PathVariable Long cateId, Model model)
    {
        model.addAttribute("courses", courseRepo.findByCategoryId(cateId));
        model.addAttribute("courseName", cateRepo.findById(cateId).get().getCategoryName());
        return"anoTheCategoryPage";
    }

  
}
