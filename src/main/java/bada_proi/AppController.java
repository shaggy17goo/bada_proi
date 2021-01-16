package bada_proi;

import bada_proi.dao.*;
import bada_proi.entity.*;
import bada_proi.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

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

    /*@RequestMapping("/")
    public String viewHomePage(Model model){
        List<PostOffice> postOfficeList = postOfficeDAO.list();
        model.addAttribute("postOfficeList",postOfficeList);
        return "index";
    }*/
    @RequestMapping("/newPostOffice")
    public String showNewPostOfficeForm(Model model){
        PostOffice postOffice = new PostOffice();
        model.addAttribute("postOffice",postOffice);

        return "newPostOfficeForm";
    }
    @RequestMapping(value = "/savePostOffice", method = RequestMethod.POST)
    public String savePostOffice(@ModelAttribute("postOffice") PostOffice postOffice){
        postOfficeDAO.save(postOffice);

        return "redirect:/";
    }
    @RequestMapping("/editPostOfficeForm/{postOfficeId}")
    public ModelAndView showPostOfficeEditForm(@PathVariable(name = "postOfficeId") int id){
        ModelAndView mav = new ModelAndView("editPostOfficeForm");
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

    @RequestMapping(value = { "/", "/welcome","/CulturalCenter-0.0.1-SNAPSHOT" }, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "welcomePage";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal) {

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
        return "adminPage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {

        return "loginPage";
    }
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerPage(Model model) {
        AppUser appUser = new AppUser();
        model.addAttribute("appUser",appUser);
        return "newUserFormPage";
    }

    @RequestMapping(value = "/saveNewUser", method = RequestMethod.POST)
    public String saveNewUser(@ModelAttribute("appUser") AppUser appUser){
        appUserDAO.save(appUser);
        AppUser tempUser = appUserDAO.get(appUser.getUsername());
        userRoleDAO.save(new UserRole(tempUser.getUserId(), 4));

        return "loginPage";
    }
    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public String menuBar(Model model) {

        return "menu";
    }
    @RequestMapping(value = "/addInfoPage", method = RequestMethod.GET)
    public String participantFormPage(Model model) {
        Participant participant = new Participant();
        model.addAttribute("participant",participant);
        return "newParticipantForm";
    }
    @RequestMapping(value = "/saveNewParticipant", method = RequestMethod.POST)
    public String saveNewParticipant(@ModelAttribute("participant") Participant participant){

        String username = WebUtils.getLoggedUsername();
        AppUser tempUser = appUserDAO.get(username);
        participant.setAddressId(1);//FIXME
        participant.setUserId(tempUser.getUserId());
        participantDAO.save(participant);
        
        return "redirect:/";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String errorPage(Model model) {

        return "welcomePage";
    }

    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {

        // After user login successfully.
        String userName = principal.getName();

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);

        return "userInfoPage";
    }

    @RequestMapping(value = "/userAccountInfo", method = RequestMethod.GET)
    public String userAccountInfo(Model model, Principal principal) {

        // After user login successfully.
        String userName = principal.getName();

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);

        return "userInfoPage";
    }

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

        return "403Page";
    }
}
