package bada_proi;

import bada_proi.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
