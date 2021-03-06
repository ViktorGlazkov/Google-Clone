package core.searcher;

import com.sun.istack.internal.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class SearchController {

    @Autowired
    SearcherService searcherService;

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String rootController() {
        return "root";
    }

    @RequestMapping(value = {"/search"}, method = RequestMethod.GET)
    public String searchController(Model model, @Nullable @RequestParam int p, @Nullable @RequestParam String q) throws IOException {
        if (q != null && !q.isEmpty()) {
            model.addAttribute("results", searcherService.search(p, q));
            model.addAttribute("count", searcherService.count(q));
            model.addAttribute("page", p);
            model.addAttribute("query", q);
        }

        return "result";
    }
}
