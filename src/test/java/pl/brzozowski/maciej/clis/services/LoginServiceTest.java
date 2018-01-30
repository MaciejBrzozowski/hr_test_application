package pl.brzozowski.maciej.clis.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import pl.brzozowski.maciej.clis.entity.User;
import pl.brzozowski.maciej.clis.entity.UserIn;
import pl.brzozowski.maciej.clis.entity.UserOut;
import pl.brzozowski.maciej.clis.repository.UserRepository;
import pl.brzozowski.maciej.clis.utilities.TokenGenerator;

import java.util.logging.Logger;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
public class LoginServiceTest {

    private UserRepository userRepository;
    private User user;
    private String userEmail;
    private UserIn userIn;
    private LoginService loginService;
    private TokenGenerator tokenGenerator;
    private Logger logger;


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
    }

    @Test
    public void shouldLoginUserWithCorrectEmailAndPassword() throws Exception {
        userIn.setEmail("correct@good.ok");
        userIn.setPassword("correct");
    }

    @Test
    public void shouldNotLoginUserWithCorrectEmailAndIncorrectPassword() throws Exception {
        userIn.setEmail("correct@good.ok");
        userIn.setPassword("incorrect");
        when(userRepository.read(eq(userIn.getEmail()))).thenReturn(user);
        when(user.getPassword().contentEquals(anyString())).thenReturn(false);
        when(user.getEmail()).thenReturn(userIn.getEmail());
        UserOut result = loginService.loginUser(userIn);
        verify(userRepository).read(eq(userEmail));
    }

}