package pl.brzozowski.maciej.clis.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.brzozowski.maciej.clis.entity.UserHistory;
import pl.brzozowski.maciej.clis.repository.HistoryRepository;
import pl.brzozowski.maciej.clis.utilities.TokenDetails;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.Optional;
import java.util.logging.Logger;

@Component
public class UserHistoryService {

    @Autowired
    private HistoryRepository historyRepository;
    @Autowired
    private TokenDetails tokenDetails;
    @Autowired
    private Logger logger;
    @Autowired
    private HttpServletRequest httpServletRequest;
    private UserHistory userHistory;


    protected void setUserHistory(UserHistory userHistory) {
        this.userHistory = userHistory;
    }


    public LinkedList<String> getUserHistory() {
        String token = httpServletRequest.getHeader("token");
        String email = tokenDetails.extractTokenDetails(token).getUserEmail();
        setUserHistory(new UserHistory());
        userHistory.setEmail(email);
        return historyRepository.read(userHistory);

    }

    public void save(String requestPath, String bodyIn, String bodyOut) {
        logger.info("Saving data to history repo");
        Optional.ofNullable(httpServletRequest.getHeader("token"))
                .ifPresent(val -> {
                    tokenDetails.extractTokenDetails(val);
                    String userEmail = tokenDetails.getUserEmail();
                    userHistory = new UserHistory();
                    userHistory.setEmail(userEmail);
                    userHistory.setQuery(requestPath + ":" + bodyIn + ":" + bodyOut);
                    logger.info("Created user history element: " + userHistory.toString());
                    LinkedList<String> historyCreated = historyRepository.save(userHistory);
                    historyCreated.forEach(history -> logger.info("Element from history: " + history));
                });


    }
}
