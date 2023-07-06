package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        // Model -> html 에 렌더링
        model.addAttribute("data", "돼지");
        return "hello"; // resources/templates/hello.html

    }
}
