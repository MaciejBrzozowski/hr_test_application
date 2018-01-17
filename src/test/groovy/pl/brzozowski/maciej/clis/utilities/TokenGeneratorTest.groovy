package pl.brzozowski.maciej.clis.utilities

import com.google.gson.Gson
import com.thedeanda.lorem.Lorem
import pl.brzozowski.maciej.clis.entity.User
import pl.brzozowski.maciej.clis.entity.UserDetails
import spock.lang.Specification

class TokenGeneratorTest extends Specification {

    User testUser1 = new User("123@123.pl", "rvfsbVSJS", new UserDetails(Lorem.getFirstName(), Lorem.getLastName(), "efwefw"))
    User testUser2 = new User("123@123.pl", "rvfsbVSJS", new UserDetails(Lorem.getFirstName(), Lorem.getLastName(), "efwefw"))
    TokenDetails tokenDetails = new TokenDetails(testUser1.getEmail(), 1234567890)
    TokenGenerator tokenGenerator
    Gson gson = new Gson()

    def setup() {
        tokenGenerator = new TokenGenerator(tokenDetails, gson, Base64.getEncoder())
    }

    def "should generate token and base64 token when user is method parameter"() {
        when:
        def token = tokenGenerator.generateNewToken()

        then:
        token == "eyJ1c2VyRW1haWwiOiIxMjNAMTIzLnBsIiwidGltZXN0YW1wIjoxMjM0NTY3ODkwfQ=="
    }

    def "should generate new token for user"() {
        when:
        testUser2.setToken(null)
        tokenGenerator.updateTokenForUser(testUser2)
        then:
        testUser2.getToken() != null

    }

    def "should generate new token"() {
        expect:
        tokenGenerator.generateNewToken(testUser2) != null
    }
}
