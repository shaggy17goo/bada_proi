package bada_proi;

import bada_proi.dao.*;
import bada_proi.entity.*;
import bada_proi.forms.CourseInfo;
import bada_proi.forms.ParticipantInfo;
import bada_proi.forms.ParticipantRegistration;
import bada_proi.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
public class AppController{
    @Autowired
    private PostOfficeDAO postOfficeDAO;
    @Autowired
    private AppUserDAO appUserDAO;
    @Autowired
    private ParticipantDAO participantDAO;
    @Autowired
    private AppRoleDAO appRoleDAO;
    @Autowired
    private UserRoleDAO userRoleDAO;
    @Autowired
    private AddressDAO addressDAO;
    @Autowired
    private CourseRealizationDAO courseRealizationDAO;
    @Autowired
    private CourseDAO courseDAO;
    @Autowired
    private ParticipantInfoDAO participantInfoDAO;

    @RequestMapping("/newPostOffice")
    public String showNewPostOfficeForm(Model model){
        PostOffice postOffice = new PostOffice();
        model.addAttribute("postOffice",postOffice);

        return "forms/newPostOfficeForm";
    }
    @RequestMapping(value = "/savePostOffice", method = RequestMethod.POST)
    public String savePostOffice(@ModelAttribute("postOffice") PostOffice postOffice) {
        postOfficeDAO.save(postOffice);

        return "redirect:/";
    }

    @RequestMapping("/editPostOfficeForm/{postOfficeId}")
    public ModelAndView showPostOfficeEditForm(@PathVariable(name = "postOfficeId") int id){
        ModelAndView mav = new ModelAndView("form/editPostOfficeForm");
        PostOffice postOffice = postOfficeDAO.get(id);
        mav.addObject("postOffice",postOffice);

        return mav;
    }
    @RequestMapping(value = "/updatePostOffice", method = RequestMethod.POST)
    public String updatePostOffice(@ModelAttribute(name = "postOffice") PostOffice postOffice){
        postOfficeDAO.update(postOffice);

        return "redirect:/";
    }
    @RequestMapping("/deletePostOffice/{postOfficeId}")
    public String deletePostOffice(@ModelAttribute(name = "postOfficeId") int id){
        postOfficeDAO.delete(id);

        return "redirect:/";
    }


    /*
     * Copied from website
     */

    @RequestMapping(value = { "/","/home","/index"}, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        return "index";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal) {

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
        return "admin/adminPage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {

        return "user/loginPage";
    }
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerPage(Model model) {
        ParticipantRegistration participantRegistration = new ParticipantRegistration();
        model.addAttribute("pr",participantRegistration);
        return "forms/newUserFormPage";
    }

    @RequestMapping(value = "/saveNewUser", method = RequestMethod.POST)
    public String saveNewUser(@ModelAttribute("appUser") ParticipantRegistration pr){


        int userId = appUserDAO.getNextSeqId()+1;
        AppUser user = new AppUser(pr.getLogin(),pr.getPassword());
        appUserDAO.save(user);
        userRoleDAO.save(new UserRole(userId, appRoleDAO.getRoleId("ROLE_PARTICIPANT").getRoleId()));

        int postOfficeId=postOfficeDAO.getNextSeqId()+1;
        PostOffice postOffice = new PostOffice(0, pr.getPostCode(), pr.getPostCity());
        postOfficeDAO.save(postOffice);

        int addressId = addressDAO.getNextSeqId()+1;
        Address address = new Address(0,pr.getCity(),pr.getStreet(),pr.getHouseNumber(),postOfficeId);
        addressDAO.save(address);

        Participant participant = new Participant(0, pr.getName(), pr.getSurname(), pr.getBirthDate(),pr.getPesel(),pr.getGender(),pr.getPhoneNumber(), pr.getEmail(),addressId);
        participant.setUserId(userId);
        participantDAO.save(participant);

        return "user/afterRegistration";
    }
    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public String menuBar(Model model) {

        return "menu";
    }

    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "user/logoutSuccessfulPage";
    }

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {
        // After user login successfully.
        User loginUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userLogin = loginUser.getUsername();
        String userRole = WebUtils.getRoleName(loginUser);

        switch(userRole) {
            case "ROLE_PARTICIPANT":
                ParticipantInfo participantInfo = participantInfoDAO.get(userLogin);
                model.addAttribute("participantInfo", participantInfo);
                return "user/userInfoPage";
            case "ROLE_EMPLOYEE":
                return "user/userInfoPage";
            default:
                return "user/userInfoPage";
        }

    }
    @RequestMapping(value = "/allCourses", method = RequestMethod.GET)
    public String viewAllCourses(Model model){
        List<Course> courseList =  courseDAO.list();
        model.addAttribute("courseList",courseList);
        return "default/allCoursesTable";
    }

    @RequestMapping("/courseRalization/{courseId}")
    public String showCourseRealizationPage(Model model, @PathVariable(name = "courseId") int id){
        List<CourseInfo> courseInfoList = courseDAO.getCourseInfoList(id);
        model.addAttribute("courseInfoList",courseInfoList);

        return "default/courseRealizationTable";
    }

    /*@RequestMapping("/courseRalization/{courseId}")
    public String showCourseRealizationPage(@PathVariable(name = "courseId") int id){
        ModelAndView mav = new ModelAndView("default/allCoursesTable");
        List<CourseInfo> courseInfoList = courseDAO.getCourseInfoList(id);
        mav.addObject("courseInfoList",courseInfoList);

        return mav;
    }*/


    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();

            String userInfo = WebUtils.toString(loginedUser);

            model.addAttribute("userInfo", userInfo);

            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);

        }

        return "errors/403Page";
    }
}
