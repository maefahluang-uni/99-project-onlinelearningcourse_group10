package th.mfu.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.transaction.Transactional;
import th.mfu.models.Course;
import th.mfu.models.Video;
import th.mfu.repositories.CourseRepository;
import th.mfu.repositories.VideoRepository;
@Controller
@RequestMapping("/tutor")
@CrossOrigin("*")
public class tutorController {

    @Autowired
    CourseRepository courseRepo;
    @Autowired
    VideoRepository videoRepo;

     @InitBinder
    public final void initBinderUsuariosFormValidator(final WebDataBinder binder, final Locale locale) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", locale);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }


     @GetMapping("/")
    public String buyingPage(Model model)
    {
        return"tutorMenu";
    }
      @GetMapping("/uplCou")
    public String uplCoursePage(Model model)
    {   
        model.addAttribute("course", new Course());

        return"tutorUploadCourse";
    }
    @PostMapping("/uplCou")
    public String saveCourse(@ModelAttribute Course course)
    {
        courseRepo.save(course);
        return"redirect:/tutor/manageCou";
    }

    @PostMapping("/editCou/{id}")
     public String editCourse(@ModelAttribute Course course,@PathVariable long id)
    {
        Course theCourse = courseRepo.findById(id).get();
        theCourse.setCourseName(course.getCourseName());
        theCourse.setCreateDate(course.getCreateDate());
        theCourse.setPrice(course.getPrice());
        theCourse.setThumbnail_src(course.getThumbnail_src());
        courseRepo.save(theCourse);
        return"redirect:/tutor/manageCou";
    }
    
    @Transactional
    @GetMapping("deleCou/{id}")
    public String deleteCourse(@PathVariable long id)
    {   
        videoRepo.deleteByCourseId(id);
        courseRepo.deleteById(id);
        return "redirect:/tutor/manageCou";
        
    }

    @GetMapping("editCou/{id}")
    public String editCoursePage(@PathVariable long id, Model model)
    {
        model.addAttribute("course", courseRepo.findById(id).get());
        return"tutorEditCourse";
    }

    @GetMapping("uplVid/{id}/videos")
    public String addVidPage(@PathVariable Long id, Model model)
    {   model.addAttribute("videos",videoRepo.findByCourseId(id));

    Course course = courseRepo.findById(id).get();
    Video video = new Video();
    video.setCourse(course);
    model.addAttribute("newVideo", video);
        return"tutorUploadVideo";
    }
    @PostMapping("uplVid/{id}/videos")
    public String saveVideo(@PathVariable Long id,@ModelAttribute Video newVideo)
    {   Course course = courseRepo.findById(id).get();
        newVideo.setCourse(course);
        Video newestvideo = new Video();
        newestvideo.setCourse(course);
        newestvideo.setDescription(newVideo.getDescription());
        newestvideo.setPath(newVideo.getPath());
        newestvideo.setTitle(newVideo.getTitle());
        videoRepo.save(newestvideo);
        return"redirect:/tutor/uplVid/"+id+"/videos";
    }

      @GetMapping("/manageCou")
    public String manageCoursePage(Model model)
    {

        model.addAttribute("courses", courseRepo.findAll());
        return"tutorManageCourse";
    }
    


    @GetMapping("/buyingReq")
    public String buyingRequestPage(Model model)
    {
        return"tutorBuyingRequest";
    }
    @GetMapping("/tutorFAQ")
    public String tutorFAQPage(Model model)
    {
        return"tutorFAQPage";
    }

}
