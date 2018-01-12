package pl.brzozowski.maciej.clis.repository

import com.thedeanda.lorem.Lorem
import org.apache.commons.lang3.RandomStringUtils
import pl.brzozowski.maciej.clis.entity.User
import pl.brzozowski.maciej.clis.entity.UserDetails
import spock.lang.Specification

class UserRepositoryTest extends Specification {

    HashMap<String, User> repositoryHasMap = new HashMap()
    UserRepository userRepository


    User testUser1 = new User("test@test.pl", "rvfsbVSJS", new UserDetails(Lorem.getFirstName(), Lorem.getLastName(), "efwefw", RandomStringUtils.randomAscii(64)))
    User testUser2 = new User("test@123.pl", "frewreg", new UserDetails(Lorem.getFirstName(), Lorem.getLastName(), "12341", RandomStringUtils.randomAscii(64)))
    User testUser3 = new User("test@321.pl", "vgcw233rfe", new UserDetails(Lorem.getFirstName(), Lorem.getLastName(), "3edefw", RandomStringUtils.randomAscii(64)))
    User testUser4 = new User("efwft@321.pl", "vgcwwefe", new UserDetails(Lorem.getFirstName(), Lorem.getLastName(), "3ed2121fw", RandomStringUtils.randomAscii(64)))

    def setup() {

        repositoryHasMap.put(testUser1.getEmail(), testUser1)
        repositoryHasMap.put(testUser2.getEmail(), testUser2)
        repositoryHasMap.put(testUser3.getEmail(), testUser3)
        userRepository = new UserRepository(repositoryHasMap)

    }

    def "should read user from memory Database"() {
        expect:
        def result = userRepository.read(testUser1.getEmail())
        result != null
        result.getEmail() == testUser1.getEmail()
    }

    def "should save user to database"() {
        given:
        def repositorySize = repositoryHasMap.size()
        when:
        userRepository.save(testUser4)
        then:
        repositorySize + 1 == repositoryHasMap.size()
        repositoryHasMap.containsKey(testUser3.getEmail())

    }

    def "test update"() {
        given:
        def repositorySize = repositoryHasMap.size()
        User updateUser = new User(testUser2.getEmail(), "123456789", new UserDetails(Lorem.getFirstName(), Lorem.getLastName(), "258369", RandomStringUtils.randomAscii(64)))
        when:
        userRepository.update(updateUser)
        then:
        repositorySize == repositoryHasMap.size()
        repositoryHasMap.get(testUser2.getEmail()).getPassword() == updateUser.getPassword()
        repositoryHasMap.get(testUser2.getEmail()).getUserDetails().getToken() == updateUser.getUserDetails().getToken()
        repositoryHasMap.get(testUser2.getEmail()).getUserDetails().getFirstName() == updateUser.getUserDetails().getFirstName()
        repositoryHasMap.get(testUser2.getEmail()).getUserDetails().getLastName() == updateUser.getUserDetails().getLastName()
        repositoryHasMap.get(testUser2.getEmail()).getUserDetails().getToken() != testUser2.getUserDetails().getToken()
        repositoryHasMap.get(testUser2.getEmail()).getUserDetails().getFirstName() != testUser2.getUserDetails().getFirstName()
        repositoryHasMap.get(testUser2.getEmail()).getUserDetails().getLastName() != testUser2.getUserDetails().getLastName()


    }

    def "should delete user from database"() {
        given:
        def repositorySize = repositoryHasMap.size()
        when:
        userRepository.delete(testUser3)
        then:
        repositorySize - 1 == repositoryHasMap.size()
        !repositoryHasMap.containsKey(testUser3.getEmail())
        !repositoryHasMap.containsValue(testUser3)
    }
}
