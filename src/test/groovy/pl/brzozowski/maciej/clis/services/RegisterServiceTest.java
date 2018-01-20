package pl.brzozowski.maciej.clis.services;

import org.junit.Before;
import org.junit.Test;
import pl.brzozowski.maciej.clis.entity.User;
import pl.brzozowski.maciej.clis.entity.UserIn;
import pl.brzozowski.maciej.clis.entity.UserOut;
import pl.brzozowski.maciej.clis.exceptions.UserAlreadyExistsException;
import pl.brzozowski.maciej.clis.repository.UserRepository;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.testng.AssertJUnit.assertEquals;

public class RegisterServiceTest {

    private String password = "";
    private String email = "email@email.pl";
    private UserRepository userRepository;
    private RegisterService registerService;
    private UserIn userIn;
    private User user;

    @Before
    public void setUp() throws Exception {
        userRepository = mock(UserRepository.class);
        registerService = new RegisterService(userRepository, user);
        userIn = new UserIn();
        userIn.setEmail(email);
        userIn.setPassword(password);
    }

    @Test(expected = UserAlreadyExistsException.class)
    public void shouldThrowExceptionWhenUserExistsInDatabase() throws Exception {
        user = new User();
        user.setEmail(email);
        when(userRepository.read(anyString())).thenReturn(user);
        registerService.registerUserInDatabase(userIn);
    }

    @Test
    public void shouldSaveUserInDatabaseWhenUserNotExist() {
        when(userRepository.read(eq(userIn.getEmail()))).thenReturn(null);
        UserOut userOut = registerService.registerUserInDatabase(userIn);
        verify(userRepository).save(any(User.class));
        assertEquals(userOut.getEmail(), userIn.getEmail());
    }
}