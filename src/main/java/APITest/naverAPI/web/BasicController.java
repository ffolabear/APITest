package APITest.naverAPI.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class BasicController {

    @GetMapping("hello")
    public String hello(Model model) {
        log.info("main controller called!");
        model.addAttribute("data", "hello!");
        return "hello";
    }
}
