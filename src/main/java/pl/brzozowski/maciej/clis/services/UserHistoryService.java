package pl.brzozowski.maciej.clis.services;


import org.springframework.beans.factory.annotation.Autowired;
import pl.brzozowski.maciej.clis.entity.UserHistory;
import pl.brzozowski.maciej.clis.repository.HistoryRepository;
import pl.brzozowski.maciej.clis.utilities.BodyExtractor;
import pl.brzozowski.maciej.clis.utilities.TokenDetails;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class UserHistoryService {

    @Autowired
    private HistoryRepository historyRepository;
    @Autowired
    private TokenDetails tokenDetails;
    @Autowired
    private Logger logger;
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private BodyExtractor bodyExtractor;
    private UserHistory userHistory;


    protected void setUserHistory(UserHistory userHistory) {
        this.userHistory = userHistory;
    }

    public void save(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        setUserHistory(new UserHistory());
        String token = httpServletRequest.getHeader("token");
        if (token != null) {
            userHistory.setEmail(tokenDetails.extractTokenDetails(token).getUserEmail());
            userHistory.setQuery(httpServletRequest.getServletPath() + ":" + bodyExtractor.getBody(httpServletRequest) + ":" + bodyExtractor.getBody(httpServletResponse));
            historyRepository.save(userHistory);
            logger.info("Saved user history for user:" + userHistory.getEmail() + " and query " + userHistory.getQuery());

        }
    }

    public LinkedList<String> getUserHistory() {
        String token = httpServletRequest.getHeader("token");
        String email = tokenDetails.extractTokenDetails(token).getUserEmail();
        setUserHistory(new UserHistory());
        userHistory.setEmail(email);
        return historyRepository.read(userHistory);

    }
}
