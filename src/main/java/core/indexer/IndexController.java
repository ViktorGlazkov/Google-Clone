package core.indexer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    @Autowired
    private IndexerService indexerService;

    @RequestMapping(value = {"/index"}, method = RequestMethod.GET)
    public String getIndexControllers() {
        return "index";
    }

    @RequestMapping(value = {"/index"}, method = RequestMethod.POST)
    public String indexPage(@RequestParam String q, @RequestParam int d) {
        indexerService.index(q, d);
        return "index";
    }
}
