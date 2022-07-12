package my.first.project;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlController {

    @GetMapping()
    public String indexPage() {
        return "index";
    }

}
