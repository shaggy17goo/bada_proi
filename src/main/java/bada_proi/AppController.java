package bada_proi;

import bada_proi.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    @RequestMapping("/new")
    public String showNewOfficeForm(Model model){
        PostOffice postOffice = new PostOffice();
        model.addAttribute("postOffice",postOffice);

        return "newOfficeForm";
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("postOffice") PostOffice postOffice){
        postOfficesDAO.save(postOffice);

        return "redirect:/";
    }
}
