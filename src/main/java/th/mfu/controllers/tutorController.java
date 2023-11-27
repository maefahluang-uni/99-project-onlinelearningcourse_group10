package th.mfu.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.aop.IntroductionInfo;
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
import th.mfu.models.CourseCategory;
import th.mfu.models.Course;
import th.mfu.models.FAQ;
import th.mfu.models.Orders;
import th.mfu.models.Video;
import th.mfu.repositories.CategoryRepository;
import th.mfu.repositories.CourseRepository;
import th.mfu.repositories.FAQRepository;
import th.mfu.repositories.OrderRepository;
import th.mfu.repositories.VideoRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

//Tutor Controller 
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
    @Autowired
    CategoryRepository cateRepo;
    @Autowired
    OrderRepository orderRepo;
   
     @InitBinder
    public final void initBinderUsuariosFormValidator(final WebDataBinder binder){//, final Locale locale) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//, locale);
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
        model.addAttribute("cates", cateRepo.findAll());
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
        theCourse.setIntro_src(course.getIntro_src());
        theCourse.setCourse_description(course.getCourse_description());
        theCourse.setCategory(course.getCategory());
        courseRepo.save(theCourse);
        return"redirect:/tutor/manageCou";
    }
    
    @Transactional
    @PostMapping("deleCou/{id}")
    public String deleteCourse(@PathVariable long id)
    {   
        orderRepo.deleteByCourseId(id);
        videoRepo.deleteByCourseId(id);
        courseRepo.deleteById(id);
        return "redirect:/tutor/manageCou";
        
    }

    @GetMapping("editCou/{id}")
    public String editCoursePage(@PathVariable long id, Model model)
    {   model.addAttribute("cates", cateRepo.findAll());
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

        model.addAttribute("requests", orderRepo.findAll());

        return"tutorBuyingRequest";
    }
    @PostMapping("/buyingReq/{oId}/acp")
    public String buyingAccept(@PathVariable Long oId)
    {
        Orders theOrder =  orderRepo.findById(oId).get();
        theOrder.setRespone(true);
        orderRepo.save(theOrder);
        return"redirect:/tutor/buyingReq";
    }
    @PostMapping("/buyingReq/{oId}/de")
    public String buyingDeny(@PathVariable Long oId)
    {
    Orders theOrder =  orderRepo.findById(oId).get();
        theOrder.setRespone(false);
        orderRepo.save(theOrder);
        return"redirect:/tutor/buyingReq";
        
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
    @GetMapping("/uploadCate")
    public String UploadCategoryPage( Model model) {
        model.addAttribute("categories", cateRepo.findAll());
        model.addAttribute("cate", new CourseCategory());
        return"tutorUploadCategoryPage";
    }
    @PostMapping("/uploadCate")
    public String SaveCategory(@ModelAttribute CourseCategory cate) {
        cateRepo.save(cate);
        return"redirect:/tutor/uploadCate";
    }

    @GetMapping("/editCate/{id}")
    public String editCategoryPage(@PathVariable Long id, Model model){
    
        model.addAttribute("cate", cateRepo.findById(id).get());
        
        return"tutorEditCategory";
    }

    @PostMapping("/editCate/{id}")
    public String editCategory(@PathVariable Long id,@ModelAttribute CourseCategory cate){
        CourseCategory newCate = cateRepo.findById(id).get();
        newCate.setCategoryName(cate.getCategoryName());

        cateRepo.save(newCate);
        return "redirect:/tutor/uploadCate";
    }

    @Transactional
    @PostMapping("deleCate/{idCate}")
    public String deleteCategory(@PathVariable Long idCate)
    {   //set Fk to null so we can delete the data
       List<Course> courses = courseRepo.findByCategoryId(idCate);
       for (Course course : courses) {
        course.setCategory(null);
         
       }
        courseRepo.saveAll(courses);

        cateRepo.deleteById(idCate);
        return"redirect:/tutor/uploadCate";
    }


    
    


}
