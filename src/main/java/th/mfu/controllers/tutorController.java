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
import th.mfu.models.FAQ;
import th.mfu.models.Video;
import th.mfu.repositories.CourseRepository;
import th.mfu.repositories.FAQRepository;
import th.mfu.repositories.VideoRepository;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/tutor")
@CrossOrigin("*")
public class tutorController {

    @Autowired
    CourseRepository courseRepo;
    @Autowired
    VideoRepository videoRepo;
    @Autowired 
    FAQRepository faqRepo;

     @InitBinder
    public final void initBinderUsuariosFormValidator(final WebDataBinder binder, final Locale locale) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", locale);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }


     @GetMapping("/")
    public String tutorMenuPage(Model model)
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
    @PostMapping("deleCou/{id}")
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
    @Transactional
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
    @Transactional
    @PostMapping("deleVid/{idc}/{idv}")
    public String deleteVid(@PathVariable Long idc,@PathVariable Long idv)
    {
        videoRepo.deleteById(idv);
        return"redirect:/tutor/uplVid/"+idc+"/videos";
    }

    @GetMapping("editVid/{vId}")
    public String editVidPage(Model model,@PathVariable Long vId)
    {
        model.addAttribute("video", videoRepo.findById(vId).get());
    return "tutorEditVideo";
    }

    @PostMapping("editVid/{vId}/{cId}")
    public String editVid(@PathVariable Long vId, @PathVariable Long cId,@ModelAttribute Video video) {
        Video newVideo = videoRepo.findById(vId).get();
        Course course = courseRepo.findById(cId).get();
        newVideo.setCourse(course);
        newVideo.setDescription(video.getDescription());
        newVideo.setPath(video.getPath());
        newVideo.setTitle(video.getTitle());
        videoRepo.save(newVideo);
        return "redirect:/tutor/uplVid/"+cId+"/videos";
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

    @GetMapping("/FAQ")
    public String tutorFAQPage(Model model)
    {
        model.addAttribute("faqs",faqRepo.findAll());  
        model.addAttribute("faq", new FAQ());
        return"tutorFAQ";
    }

    @PostMapping("/FAQupl")
    public String saveFAQ(@ModelAttribute FAQ faq)
    {   faq.setFaqQuestion("Q: "+faq.getFaqQuestion());
    faq.setFaqAnswer("A: "+faq.getFaqAnswer());
        faqRepo.save(faq);
        return"redirect:/tutor/FAQ";
    }

    @PostMapping("/deleFAQ/{fId}")
    public String deleteFAQ(@PathVariable Long fId)
    {
        faqRepo.deleteById(fId);
        return"redirect:/tutor/FAQ";
    }

    @PostMapping("/editFAQ/{fId}")
    public String EditFAQ(@PathVariable Long fId, @ModelAttribute FAQ faq)
    {
        FAQ newFAQ = faqRepo.findById(fId).get();
        newFAQ.setFaqAnswer(faq.getFaqAnswer());
        newFAQ.setFaqQuestion(faq.getFaqQuestion());

        newFAQ.setFaqUploadDate(faq.getFaqUploadDate());
        
        faqRepo.save(newFAQ);
        return"redirect:/tutor/FAQ"; 
    }
    @GetMapping("/editFAQ/{fId}")
    public String EditFAQPage(@PathVariable Long fId,Model model)
    {   model.addAttribute("newFAQ", new FAQ());
        model.addAttribute("oldFAQ", faqRepo.findById(fId).get());
        return"tutorEditFAQ"; 
    }
    


}
