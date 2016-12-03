package core;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {
    @RequestMapping(value = {"/index"}, method = RequestMethod.GET)
    public String getIndexControllers() {
        return "index_page";
    }

    @RequestMapping(value = {"/index"}, method = RequestMethod.POST)
    public String indexPage(Model model, @RequestParam String q) {
        System.out.println(q);
        model.addAttribute("q", q);
        return "index_page";
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String getSearchControllers() {
        return "root";
    }
}
