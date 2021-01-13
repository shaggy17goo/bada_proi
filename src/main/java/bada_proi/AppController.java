package bada_proi;

import bada_proi.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AppController {
    @Autowired
    private PostOfficesDAO postOfficesDAO;

    @RequestMapping("/")
    public String viewHomePage(Model model){
        List<PostOffice> postOfficeList = postOfficesDAO.list();
        model.addAttribute("postOfficeList",postOfficeList);
        return "index";
    }
    @RequestMapping("/newPostOffice")
    public String showNewPostOfficeForm(Model model){
        PostOffice postOffice = new PostOffice();
        model.addAttribute("postOffice",postOffice);

        return "newPostOfficeForm";
    }
    @RequestMapping(value = "/savePostOffice", method = RequestMethod.POST)
    public String savePostOffice(@ModelAttribute("postOffice") PostOffice postOffice){
        postOfficesDAO.save(postOffice);

        return "redirect:/";
    }
    @RequestMapping("/editPostOfficeForm/{postOfficeId}")
    public ModelAndView showPostOfficeEditForm(@PathVariable(name = "postOfficeId") int id){
        ModelAndView mav = new ModelAndView("editPostOfficeForm");
        PostOffice postOffice = postOfficesDAO.get(id);
        mav.addObject("postOffice",postOffice);

        return mav;
    }
    @RequestMapping(value = "/updatePostOffice", method = RequestMethod.POST)
    public String updatePostOffice(@ModelAttribute(name = "postOffice") PostOffice postOffice){
        postOfficesDAO.update(postOffice);

        return "redirect:/";
    }
}
