package pl.brzozowski.maciej.clis.utilities

import spock.lang.Specification

import java.util.logging.Logger

class TokenDetailsTest extends Specification {

    Logger logger = Mock()

    def "should return #expected then #expecedTokenStatus given "() {
        given:
        def tokenDetails = new TokenDetails()
        when:
        def result = tokenDetails.isTokenValid(token, time)
        then:
        result == expected
        where:
        expected | expecedTokenStatus | time | token
        true     | valid              | 1    | ""
    }

//    def "test isTokenValid1"() {
//        given:
//
//        when:
//        // TODO implement stimulus
//        then:
//        // TODO implement assertions
//    }


    def "should extract token details when token is valid"() {
        given:
        def tokenDetails = new TokenDetails(logger)
        when:
        TokenDetails result = tokenDetails.extractTokenDetails(token);
        then:
        result.getTimestamp() == 1518743778364
        result.getUserEmail() == "test@email.pl"
        where:
        _ | token
        _ | "eyJ1c2VyRW1haWwiOiJ0ZXN0QGVtYWlsLnBsIiwidGltZXN0YW1wIjoxNTE4NzQzNzc4MzY0fQ=="
    }
}
