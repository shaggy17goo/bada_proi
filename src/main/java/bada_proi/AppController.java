package bada_proi;

import bada_proi.dao.*;
import bada_proi.entity.*;
import bada_proi.forms.CourseInfo;
import bada_proi.forms.EmployeeInfo;
import bada_proi.forms.ParticipantInfo;
import bada_proi.forms.ParticipantRegistration;
import bada_proi.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class AppController {
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
    @Autowired
    private EmployeeInfoDAO employeeInfoDAO;
    @Autowired
    private ParticipantRealizationDAO participantRealizationDAO;
    @Autowired
    private EmployeeRealizationDAO employeeRealizationDAO;

    @RequestMapping("/newPostOffice")
    public String showNewPostOfficeForm(Model model) {
        PostOffice postOffice = new PostOffice();
        model.addAttribute("postOffice", postOffice);

        return "forms/newPostOfficeForm";
    }

    @RequestMapping(value = "/savePostOffice", method = RequestMethod.POST)
    public String savePostOffice(@ModelAttribute("postOffice") PostOffice postOffice) {
        postOfficeDAO.save(postOffice);

        return "redirect:/";
    }

    @RequestMapping("/editPostOfficeForm/{postOfficeId}")
    public ModelAndView showPostOfficeEditForm(@PathVariable(name = "postOfficeId") int id) {
        ModelAndView mav = new ModelAndView("form/editPostOfficeForm");
        PostOffice postOffice = postOfficeDAO.get(id);
        mav.addObject("postOffice", postOffice);

        return mav;
    }

    @RequestMapping(value = "/updatePostOffice", method = RequestMethod.POST)
    public String updatePostOffice(@ModelAttribute(name = "postOffice") PostOffice postOffice) {
        postOfficeDAO.update(postOffice);

        return "redirect:/";
    }

    @RequestMapping("/deletePostOffice/{postOfficeId}")
    public String deletePostOffice(@ModelAttribute(name = "postOfficeId") int id) {
        postOfficeDAO.delete(id);

        return "redirect:/";
    }


    /*
     * Copied from website
     */

    @RequestMapping(value = {"/", "/home", "/index"}, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        return "index";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model) {
        //List<EmployeeInfo> employeeInfoList = employeeInfoDAO.list();
        return "admin/adminPage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {

        return "user/loginPage";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerPage(Model model) {
        ParticipantRegistration participantRegistration = new ParticipantRegistration();
        model.addAttribute("pr", participantRegistration);
        return "forms/newUserFormPage";
    }

    @RequestMapping(value = "/saveNewUser", method = RequestMethod.POST)
    public String saveNewUser(@ModelAttribute("appUser") ParticipantRegistration pr) {


        int userId = appUserDAO.getNextSeqId() + 1;
        AppUser user = new AppUser(pr.getLogin(), pr.getPassword());
        appUserDAO.save(user);
        userRoleDAO.save(new UserRole(userId, appRoleDAO.getRoleId("ROLE_PARTICIPANT").getRoleId()));

        int postOfficeId = postOfficeDAO.getNextSeqId() + 1;
        PostOffice postOffice = new PostOffice(0, pr.getPostCode(), pr.getPostCity());
        postOfficeDAO.save(postOffice);

        int addressId = addressDAO.getNextSeqId() + 1;
        Address address = new Address(0, pr.getCity(), pr.getStreet(), pr.getHouseNumber(), postOfficeId);
        addressDAO.save(address);

        Participant participant = new Participant(0, pr.getName(), pr.getSurname(), pr.getBirthDate(), pr.getPesel(), pr.getGender(), pr.getPhoneNumber(), pr.getEmail(), addressId);
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
    public String userInfo(Model model) {
        // After user login successfully.
        User loginUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userLogin = loginUser.getUsername();
        String userRole = WebUtils.getRoleName(loginUser);

        switch (userRole) {
            case "ROLE_PARTICIPANT":
                ParticipantInfo participantInfo = participantInfoDAO.get(userLogin);
                model.addAttribute("participantInfo", participantInfo);
                return "user/participantInfoPage";
            case "ROLE_EMPLOYEE":
                EmployeeInfo employeeInfo = employeeInfoDAO.get(userLogin);
                model.addAttribute("employeeInfo", employeeInfo);
                return "user/employeeInfoPage";
            default:
                return "/";
        }

    }

    @RequestMapping(value = "/allCourses", method = RequestMethod.GET)
    public String viewAllCourses(Model model) {
        List<Course> courseList = courseDAO.list();
        model.addAttribute("courseList", courseList);
        return "default/allCoursesTable";
    }

    @RequestMapping("/signUpForCourse/{courseRealizationId}")
    public String signUpForCourse(Model model, @PathVariable(name = "courseRealizationId") int id) {
        User loginUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = loginUser.getUsername();
        ParticipantInfo participantInfo = participantInfoDAO.get(username);
        participantRealizationDAO.save(new ParticipantRealization(participantInfo.getParticipantId(),id));
        int courseId = courseDAO.getCourseInfoListFromRealizationId(id).get(0).getCourseId();
        return showCourseRealizationPage(model,courseId);
    }

    @RequestMapping("/signOutOfCourse/{page}/{courseRealizationId}")
    public String signOutOfCourse(Model model, @PathVariable(name = "page") String page, @PathVariable(name = "courseRealizationId") int id) {
        User loginUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = loginUser.getUsername();
        ParticipantInfo participantInfo = participantInfoDAO.get(username);
        participantRealizationDAO.delete(participantInfo.getParticipantId(),id);
        if(page.equals("yourCourses")) // yourCourses  allCourses
            return viewYourCourses(model);
        else{
            int courseId = courseDAO.getCourseInfoListFromRealizationId(id).get(0).getCourseId();
            return showCourseRealizationPage(model,courseId);
        }
    }

    @RequestMapping("/realizationInfo/{courseId}")
    public String showRealizationInfoPage(Model model, @PathVariable(name = "courseId") int id) {
        List<CourseInfo> realizationsList = courseDAO.getCourseInfoListFromRealizationId(id);
        List<String> instructors= new ArrayList<>();
        for (CourseInfo realization:realizationsList) {
            instructors.add(realization.getEmployeeName()+' '+realization.getSurname());
        }
        List<ParticipantInfo> participantsInfo = courseRealizationDAO.getParticipantsInfoByCourseRealization(id);

        model.addAttribute("realizationInfo", realizationsList.get(0));
        model.addAttribute("instructors", instructors);
        model.addAttribute("participantsInfo", participantsInfo);

        return "/default/realizationInfoPage";
    }


    @RequestMapping("/courseRalization/{courseId}")
    public String showCourseRealizationPage(Model model, @PathVariable(name = "courseId") int id) {
        List<CourseInfo> realizationsList = courseRealizationDAO.getRealizationListByCourseId(id);
        if(!(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User)){
            model.addAttribute("courseInfoList", realizationsList);
            return "default/courseRealizationTable";
        }


        User loginUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userUsername = loginUser.getUsername();
        String userRole = WebUtils.getRoleName(loginUser);
        int userId = -1;
        if (userRole.equals("ROLE_PARTICIPANT")) {
            userId = participantInfoDAO.get(userUsername).getParticipantId();
            List<ParticipantRealization> participantCourses = participantRealizationDAO.participantCourses(userId);

            List<Integer> participantCoursesId = new ArrayList<>();
            for (ParticipantRealization participantCourse:participantCourses) {
                participantCoursesId.add(participantCourse.getRealizationId());
            }

            List<CourseInfo> activeCoursesList = realizationsList
                    .stream()
                    .filter(real -> participantCoursesId.contains(real.getRealizationId()))
                    .collect(Collectors.toList());
            List<CourseInfo> anotherCoursesList = realizationsList
                    .stream()
                    .filter(real -> !participantCoursesId.contains(real.getRealizationId()))
                    .collect(Collectors.toList());

            model.addAttribute("activeCoursesList", activeCoursesList);
            model.addAttribute("courseInfoList", anotherCoursesList);
        }
        else{
            model.addAttribute("courseInfoList", realizationsList);
        }


        return "default/courseRealizationTable";
    }

    @RequestMapping(value = "/yourCourses", method = RequestMethod.GET)
    public String viewYourCourses(Model model) {
        User loginUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userLogin = loginUser.getUsername();
        String userRole = WebUtils.getRoleName(loginUser);
        List<CourseInfo> yourCourseInfoList = new ArrayList<>();
        int userId = -1;
        if (userRole.equals("ROLE_PARTICIPANT")) {
            userId = participantInfoDAO.get(userLogin).getParticipantId();
            List<ParticipantRealization> participantCourses = participantRealizationDAO.participantCourses(userId);
            for (ParticipantRealization temp : participantCourses) {
                yourCourseInfoList.add(courseDAO.getCourseInfoListFromRealizationId(temp.getRealizationId()).get(0));
            }
        } else if (userRole.equals("ROLE_EMPLOYEE")) {
            userId = employeeInfoDAO.get(userLogin).getEmployeeId();
            List<EmployeeRealization> employeeCourses = employeeRealizationDAO.employeeCourses(userId);

            for (EmployeeRealization temp : employeeCourses) {
                yourCourseInfoList.add(courseDAO.getCourseInfoListFromRealizationId(temp.getRealizationId()).get(0));
            }
        }
        model.addAttribute("yourCourseInfoList", yourCourseInfoList);
        return "default/yourCoursesPage";
    }
    @RequestMapping("/listOfParticipants/{courseId}")
    public String showListOfCourseParticipants(Model model, @PathVariable(name = "courseId") int id) {
        List<ParticipantInfo> participantInfoList = courseRealizationDAO.getParticipantsInfoByCourseRealization(id);
        model.addAttribute("participantInfoList", participantInfoList);
        return "";
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
