package pl.brzozowski.maciej.clis.controller.unathorized;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSimpleController {

    public static final String BASE_TEXT = "<h1>It's alive!</h1>";

    @GetMapping("/")
    public String baseController() {
        ;
        return BASE_TEXT;
    }

}
