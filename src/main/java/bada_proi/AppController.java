package bada_proi;

import bada_proi.dao.*;
import bada_proi.entity.*;
import bada_proi.forms.CourseInfo;
import bada_proi.forms.EmployeeInfo;
import bada_proi.forms.ParticipantInfo;
import bada_proi.forms.ParticipantRegistration;
import bada_proi.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AppController implements ErrorController {
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
    @Autowired
    private SalaryDAO salaryDAO;
    @Autowired
    private DatesDAO datesDAO;
    @Autowired
    private EmployeeDAO employeeDAO;


    @RequestMapping(value = {"/", "/home", "/index"}, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        return "index";
    }

    @RequestMapping(value = "/fireEmployee/{employeeId}", method = RequestMethod.GET)
    public String fireEmployee(Model model, @PathVariable(name = "employeeId") int employeeId) {
        Employee employee = employeeDAO.get(employeeId);
        Address address = addressDAO.get(employee.getAddressId());
        List<EmployeeRealization> employeeRealizationList = employeeRealizationDAO.employeeCourses(employeeId);
        for(EmployeeRealization emRe : employeeRealizationList){
            employeeRealizationDAO.delete(employeeId,emRe.getRealizationId());
        }
        List<Salary> salaryList = salaryDAO.getSalariesByEmployeeId(employeeId);
        for(Salary salary : salaryList){
            salaryDAO.delete(salary.getSalaryId());
        }
        employeeDAO.delete(employeeId);
       // addressDAO.delete(employee.getAddressId());
        //postOfficeDAO.delete(address.getPostOfficeId());

        if(employee.getUserId() != null){
            userRoleDAO.delete(employee.getUserId());
            appUserDAO.delete(employee.getUserId());
        }

        return adminPage(model);
    }

    @RequestMapping(value = "/payEmployee", method = RequestMethod.POST)
    public String payEmployee(Model model, @ModelAttribute("salary") Salary salary) {
        salary.setDateOfPayment(new Date(System.currentTimeMillis()));
        int salaryId = salaryDAO.getNextSeqId() + 1;
        salary.setSalaryId(salaryId);
        salaryDAO.save(salary);
        return adminPage(model);
    }


    @RequestMapping(value = {"/admin", "adminPage"}, method = RequestMethod.GET)
    public String adminPage(Model model) {
        List<EmployeeInfo> employeeInfoList = employeeInfoDAO.list();
        model.addAttribute("employeeInfoList", employeeInfoList);
        Salary salary = new Salary();
        model.addAttribute("salary", salary);
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
                return "participant/participantInfoPage";
            case "ROLE_EMPLOYEE":

                EmployeeInfo employeeInfo = employeeInfoDAO.get(userLogin);
                List<Salary> salaries = salaryDAO.getSalariesByEmployeeId(employeeInfo.getEmployeeId());

                model.addAttribute("employeeInfo", employeeInfo);
                model.addAttribute("salaries", salaries);
                return "employee/employeeInfoPage";
            default:
                return "redirect:/";
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
        participantRealizationDAO.save(new ParticipantRealization(participantInfo.getParticipantId(), id));
        int courseId = courseDAO.getCourseInfoListFromRealizationId(id).get(0).getCourseId();
        return showCourseRealizationPage(model, courseId);
    }

    @RequestMapping("/signOutOfCourse/{page}/{courseRealizationId}")
    public String signOutOfCourse(Model model, @PathVariable(name = "page") String page, @PathVariable(name = "courseRealizationId") int id) {
        User loginUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = loginUser.getUsername();
        ParticipantInfo participantInfo = participantInfoDAO.get(username);
        participantRealizationDAO.delete(participantInfo.getParticipantId(), id);
        if (page.equals("yourCourses")) // yourCourses  allCourses
            return viewYourCourses(model);
        else {
            int courseId = courseDAO.getCourseInfoListFromRealizationId(id).get(0).getCourseId();
            return showCourseRealizationPage(model, courseId);
        }
    }

    @RequestMapping("/realizationInfo/{courseId}")
    public String showRealizationInfoPage(Model model, @PathVariable(name = "courseId") int id) {
        List<CourseInfo> realizationsList = courseDAO.getCourseInfoListFromRealizationId(id);
        List<ParticipantInfo> participantsInfo = courseRealizationDAO.getParticipantsInfoByCourseRealization(id);
        List<Dates> dates = datesDAO.getDatesByRealizationId(id);

        List<String> instructors = new ArrayList<>();
        for (CourseInfo realization : realizationsList) {
            instructors.add(realization.getEmployeeName() + ' ' + realization.getSurname());
        }


        model.addAttribute("realizationInfo", realizationsList.get(0));
        model.addAttribute("dates", dates);
        model.addAttribute("instructors", instructors);
        model.addAttribute("participantsInfo", participantsInfo);

        return "default/realizationInfoPage";
    }




    @RequestMapping("/courseRalization/{courseId}")
    public String showCourseRealizationPage(Model model, @PathVariable(name = "courseId") int id) {
        List<CourseInfo> realizationsList = courseRealizationDAO.getRealizationListByCourseId(id);
        if (!(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User)) {
            model.addAttribute("courseInfoList", realizationsList);
            return "default/courseRealizationTable";
        }


        User loginUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userUsername = loginUser.getUsername();
        String userRole = WebUtils.getRoleName(loginUser);
        int userId = -1;
        if (userRole.equals("ROLE_ADMIN")) {
            EmployeeRealization employeeRealization = new EmployeeRealization();
            model.addAttribute("employeeRealization",employeeRealization);
        }
        if (userRole.equals("ROLE_PARTICIPANT")) {
            userId = participantInfoDAO.get(userUsername).getParticipantId();
            List<ParticipantRealization> participantCourses = participantRealizationDAO.participantCourses(userId);

            List<Integer> participantCoursesId = new ArrayList<>();
            for (ParticipantRealization participantCourse : participantCourses) {
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
        } else {
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

    @RequestMapping(value = "/editUserInfo", method = RequestMethod.GET)
    public String editUserInfo(Model model) {
        User loginUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = loginUser.getUsername();
        AppUser appUser = appUserDAO.get(username);
        ParticipantInfo pI = participantInfoDAO.get(username);
        ParticipantRegistration pr = new ParticipantRegistration(username, appUser.getPassword(), pI.getName(), pI.getSurname(), pI.getBirthDate(), pI.getPesel(), pI.getGender(), pI.getPhoneNumber(), pI.getEmail(), pI.getCity(), pI.getStreet(), pI.getHouseNumber(), pI.getCode(), pI.getPostCity());
        model.addAttribute("pr", pr);
        return "forms/changeUserDataFormPage";
    }

    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
    public String updateUserInfo(Model model, @ModelAttribute("pr") ParticipantRegistration pr) {
        User loginUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = loginUser.getUsername();
        ParticipantInfo participantInfo = participantInfoDAO.get(username);
        AppUser appUser = appUserDAO.get(username);
        appUser.setPassword(pr.getPassword());
        appUser.setUsername(pr.getLogin());
        Participant participant = participantDAO.get(participantInfo.getParticipantId());
        Address address = addressDAO.get(participant.getAddressId());
        PostOffice postOffice = postOfficeDAO.get(address.getPostOfficeId());
        postOffice = new PostOffice(postOffice.getPostOfficeId(), pr.getPostCode(), pr.getPostCity());
        address = new Address(address.getAddressId(), pr.getCity(), pr.getStreet(), pr.getHouseNumber(), postOffice.getPostOfficeId());
        participant = new Participant(participant.getParticipantId(), pr.getName(), pr.getSurname(), pr.getBirthDate(), pr.getPesel(), pr.getGender(), pr.getPhoneNumber(), pr.getEmail(), address.getAddressId());
        participant.setUserId(appUser.getUserId());
        postOfficeDAO.update(postOffice);
        addressDAO.update(address);
        participantDAO.update(participant);
        appUserDAO.update(appUser);

        return userInfo(model);
    }






    @RequestMapping(value = "/addEmployee", method = RequestMethod.GET)
    public String addEmployee(Model model) {
        EmployeeInfo employeeInfo = new EmployeeInfo();
        model.addAttribute("employeeInfo", employeeInfo);
        return "forms/newEmployeeFormPage";
    }

    @RequestMapping(value = "/saveNewEmployee", method = RequestMethod.POST)
    public String saveNewUser(Model model, @ModelAttribute("newEmployee") EmployeeInfo employeeInfo) {

        int userId = appUserDAO.getNextSeqId() + 1;
        AppUser user = new AppUser(employeeInfo.getUsername(), employeeInfo.getPassword());
        appUserDAO.save(user);
        userRoleDAO.save(new UserRole(userId, appRoleDAO.getRoleId("ROLE_EMPLOYEE").getRoleId()));

        int postOfficeId = postOfficeDAO.getNextSeqId() + 1;
        PostOffice postOffice = new PostOffice(0, employeeInfo.getCode(), employeeInfo.getPostCity());
        postOfficeDAO.save(postOffice);

        int addressId = addressDAO.getNextSeqId() + 1;
        Address address = new Address(0, employeeInfo.getCity(), employeeInfo.getStreet(), employeeInfo.getHouseNumber(), postOfficeId);
        addressDAO.save(address);

        Employee employee = new Employee(0, employeeInfo.getType(), employeeInfo.getName(), employeeInfo.getSurname(), employeeInfo.getBirthDate(),
                employeeInfo.getPesel(), employeeInfo.getGender(), employeeInfo.getPhoneNumber(), employeeInfo.getEmail(), employeeInfo.getEmploymentDate(),
                employeeInfo.getAccountNumber(), 1, addressId);
        employee.setUserId(userId);
        employeeDAO.save(employee);

        return adminPage(model);
    }

    @RequestMapping(value ="/403", method = RequestMethod.GET)
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

    @ExceptionHandler({java.lang.IllegalArgumentException.class})
    public String genericError() {
        return "errors/404error";
    }
    @ExceptionHandler({DataAccessException.class, SQLException.class, org.springframework.core.convert.ConversionFailedException.class})
    public String databaseError() {

        return "errors/SQLerror";
    }

    @RequestMapping("/error")
    public String handleError() {
        //do something like logging
        return "errors/404error";
    }

    @Override
    public String getErrorPath() {
        return "errors/404error";
    }


}
