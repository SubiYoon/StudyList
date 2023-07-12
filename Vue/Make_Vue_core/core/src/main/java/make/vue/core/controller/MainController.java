package make.vue.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class MainController {
    @RequestMapping(value="")
    public String vue(){
        System.out.println("일단 타냐?");
        return "home.html";
    }
}
