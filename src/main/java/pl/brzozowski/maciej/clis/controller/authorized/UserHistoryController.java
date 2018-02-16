package pl.brzozowski.maciej.clis.controller.authorized;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.brzozowski.maciej.clis.services.UserHistoryService;

import java.util.LinkedList;

import static pl.brzozowski.maciej.clis.configuration.UrlMaping.USER_HISTORY;

@RestController
@RequestMapping(path = USER_HISTORY)
public class UserHistoryController {

    @Autowired
    private UserHistoryService userHistoryService;

    @GetMapping
    public LinkedList<String> getUserHistory() {
        return userHistoryService.getUserHistory();
    }

}
