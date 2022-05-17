package APITest.naverAPI.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("main")
@Controller
public class BasicController {

    @GetMapping
    public String main() {
        log.info("main controller called!");
        return "main";
    }

}
