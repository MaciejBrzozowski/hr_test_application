package pl.brzozowski.maciej.clis.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import pl.brzozowski.maciej.clis.entity.User;
import pl.brzozowski.maciej.clis.entity.UserIn;
import pl.brzozowski.maciej.clis.entity.UserOut;
import pl.brzozowski.maciej.clis.exceptions.UserNotExistsException;
import pl.brzozowski.maciej.clis.repository.UserRepository;
import pl.brzozowski.maciej.clis.utilities.TokenGenerator;

import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
public class LoginServiceTest {

    private UserRepository userRepository;
    private User user;
    private String correctUserEmail = "correct@good.ok";
    private UserIn userIn;
    private LoginService loginService;
    private TokenGenerator tokenGenerator;
    private Logger logger;
    private String password = "password";


    @Before
    public void setUp() throws Exception {
        userIn = new UserIn();
        userRepository = mock(UserRepository.class);
        tokenGenerator = mock(TokenGenerator.class);
        logger = mock(Logger.class);
        user = mock(User.class);
        doNothing().when(logger).info(anyString());
        when(tokenGenerator.updateTokenForUser(any(User.class))).thenReturn(user);
        loginService = new LoginService(userRepository, null, tokenGenerator, logger);
        when(userRepository.read(any())).thenReturn(user);
        when(user.getPassword()).thenReturn(password);
        when(user.getEmail()).thenReturn(correctUserEmail);

    }

    @Test
    public void shouldLoginUserWithCorrectEmailAndPassword() throws Exception {
        userIn.setEmail(correctUserEmail);
        userIn.setPassword(password);
        UserOut result = loginService.loginUser(userIn);
        assertEquals(result.getEmail(), correctUserEmail);
        assertEquals(result.getToken(), null);
    }

    @Test(expected = UserNotExistsException.class)
    public void shouldNotLoginUserWithCorrectEmailAndIncorrectPassword() throws Exception {
        userIn.setEmail("correct@good.ok");
        userIn.setPassword("incorrect");
        UserOut result = loginService.loginUser(userIn);

    }

}