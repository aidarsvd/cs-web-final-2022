package pro.aidar.alatoonews.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerTest {

    @GetMapping
    String test(){
        return "index";
    }

}
