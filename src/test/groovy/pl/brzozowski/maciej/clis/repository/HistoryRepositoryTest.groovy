package pl.brzozowski.maciej.clis.repository

import pl.brzozowski.maciej.clis.entity.UserHistory
import spock.lang.Specification

class HistoryRepositoryTest extends Specification {

    HistoryRepository historyRepository
    HashMap<String, LinkedList<String>> mockedHistory
    String element = "element"
    String userEmail = "email@email.pl"
    UserHistory userHistory

    void setup() {
        mockedHistory = new HashMap<>()
        historyRepository = new HistoryRepository(mockedHistory)
        userHistory = new UserHistory()
        userHistory.setEmail(userEmail)
    }

    def "shoud read history form database"() {
        given:
        mockedHistory.clear()
        LinkedList<String> linkedList = new LinkedList<>()
        linkedList.add(element)
        mockedHistory.put(userEmail, linkedList)
        when:
        LinkedList<String> result = historyRepository.read(userHistory)
        then:
        result.peek() == element
        linkedList.size() == result.size()
    }

    def "should save single request to database"() {
        setup:
        userHistory.setEmail(userEmail)
        userHistory.setQuery(element)
        when:
        LinkedList<String> result = historyRepository.save(userHistory)
        then:
        result.peek() == userHistory.getQuery()

    }

    def "should not update history of requests"() {
        given:
        mockedHistory.clear()
        LinkedList<String> linkedList = new LinkedList<>()
        linkedList.add(element + element)
        linkedList.add(element)
        userHistory.setQuery(element + element)
        mockedHistory.put(userEmail, linkedList)
        when:
        LinkedList<String> result = historyRepository.update(userHistory)
        then:
        result.peek() == element + element
        result.get(1) == element
        linkedList.size() == result.size()
    }

    def "should clear user history"() {
        given:
        mockedHistory.clear()
        LinkedList<String> linkedList = new LinkedList<>()
        linkedList.add(element)
        linkedList.add(element + element)
        linkedList.add(element + element + element)
        mockedHistory.put(userEmail, linkedList)
        when:
        historyRepository.delete(userHistory)
        then:
        linkedList.size() == 0

    }
}
