package th.mfu.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

import th.mfu.models.Orders;
import th.mfu.models.users;
import th.mfu.repositories.CategoryRepository;
import th.mfu.repositories.CourseRepository;
import th.mfu.repositories.FAQRepository;
import th.mfu.repositories.OrderRepository;
import th.mfu.repositories.UsersRepository;
import th.mfu.repositories.VideoRepository;

//Student login page stuff
@Controller
@RequestMapping("/student")
@CrossOrigin("*")
public class studentController {
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
    @Autowired
    UsersRepository userRepo;


    @GetMapping("/buying/{cId}")
    public String buyingPage(@PathVariable Long cId ,Model model)
    {
        model.addAttribute("course", courseRepo.findById(cId).get());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        users user = userRepo.findByusername(username);

        if(orderRepo.findOrdersByUserAndCourse(user,courseRepo.findById(cId).get()) != null)
        {
            model.addAttribute("greet", "Hi "+user.getUsername()+"You Already Send the buying request" );
            return "studentBuyingPage";
        }

        model.addAttribute("greet", "Hi "+user.getUsername()+" Click send buying request to send the order to the tutor" );


        return"studentBuyingPage";
    }

    @PostMapping("/buying/{cId}")
    public String SaveOrder(@PathVariable Long cId ,Model model)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        users user = userRepo.findByusername(username);

         if(orderRepo.findOrdersByUserAndCourse(user,courseRepo.findById(cId).get()) != null)
        {
            model.addAttribute("greet", "Hi "+user.getUsername()+"You Already Send the buying request" );
            return "redirect:/student/buying/{cId}";
        }

        Orders newOrder = new Orders();
        newOrder.setCourse(courseRepo.findById(cId).get());
        newOrder.setUser(user);
        orderRepo.save(newOrder);

        return"redirect:/student/buying/{cId}";
    }


    @GetMapping("/profile")
    public String profilePage(Model model)
    {    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        users user = userRepo.findByusername(username);
        model.addAttribute("username", user.getUsername());

        return"studentProfile";
    }

    @GetMapping("/myCou")
    public String myCoursePage(Model model)
    { 
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        users user = userRepo.findByusername(username);
        model.addAttribute("orders", orderRepo.findByResponeAndUserId(true,user.getId()));
      
      return"studentMyCoursePage";
    }

     @GetMapping("/watchCou/{cId}")
     public String watchCoursePage(@PathVariable Long cId, Model model)
     {  
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Long uId = userRepo.findByusername(username).getId();
        try {
            if(orderRepo.findOrdersByUserIdAndCourseId(uId, cId).getRespone()==true)
            {
            model.addAttribute("course", courseRepo.findById(cId).get());
            model.addAttribute("units", videoRepo.findByCourseId(cId));
            return"studentWatchTheCourse";
             }
        else
        {   // Order Respone is false (Tutor is not accept the request)
            model.addAttribute("resp", "You Order is yet accepted");
            return"anoResp";
        }
        } catch (Exception e) {
            // Order Respone never intiated before
           model.addAttribute("resp", "You haven't yet made the order for this course");
            return"anoResp";
        }
        
        
     }

    @GetMapping("/watchCou/{cId}/{vId}")
    public String watchVideoPage(@PathVariable Long cId,@PathVariable Long vId, Model model)
     {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Long uId = userRepo.findByusername(username).getId();
        try {
             if(orderRepo.findOrdersByUserIdAndCourseId(uId, cId).getRespone()==true)
            {
                model.addAttribute("units", videoRepo.findByCourseId(cId));
                model.addAttribute("theUnit", videoRepo.findById(vId).get());
                model.addAttribute("course", courseRepo.findById(cId).get());
                return"studentWatchVideoPage";
            }
             else
             {  // Order Respone is false (Tutor is not accept the request)
                model.addAttribute("resp", "You Order is yet accepted");
                return"anoResp";
             }
        } catch (Exception e) {
             // Order Respone never intiated before
             model.addAttribute("resp", "You haven't yet made the order for this course");
            return"anoResp";
        }

        

        
     }

     

   

}
