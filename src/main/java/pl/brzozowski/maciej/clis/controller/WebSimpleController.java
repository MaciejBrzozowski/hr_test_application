package pl.brzozowski.maciej.clis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSimpleController {

    @GetMapping("/")
    public String baseController() {
        return "<h1>It's alive!</h1>";
    }

}
