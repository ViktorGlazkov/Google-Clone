package core;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SearchControllerController {
    @RequestMapping(value = {"/index"}, method = RequestMethod.GET)
    public String getIndexControllers() {
        return "index_page";
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String getSearchControllers() {
        return "root";
    }
}
