package dev.jes.authms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private User user;

    @Mock
    private UserRepository repositoryMock;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    @InjectMocks
    private AuthService authService;

    @Nested
    class authenticate {

        @Test
        @DisplayName("Should return false when user exist and password is invalid")
        void shouldReturnFalseWhenUserExistAndPasswordIsInvalid() {

            //Arrange
            String username = "admin";
            String password = "123";

            doReturn(user).when(repositoryMock).findByUsername(username);
            doReturn(false).when(user).isValidPassword(eq(password));

            //Act
            boolean isAuth = authService.authenticate(username, password);

            //Assert
            assertFalse(isAuth);
            verify(repositoryMock, times(1)).findByUsername(eq(username));
            verify(user, times(1)).isValidPassword(eq(password));
        }

        @Test
        @DisplayName("Should return true when user exist and password is valid")
        void shouldReturnTrueWhenUserExistAndPasswordIsValid() {

            //Arrange
            String username = "admin";
            String password = "123";

            doReturn(user).when(repositoryMock).findByUsername(username);
            doReturn(true).when(user).isValidPassword(eq(password));

            //Act
            boolean isAuth = authService.authenticate(username, password);

            //Assert
            assertTrue(isAuth);
            verify(repositoryMock, times(1)).findByUsername(eq(username));
            verify(user, times(1)).isValidPassword(eq(password));
        }

        @Test
        @DisplayName("Should return false when user does not exist")
        void shouldReturnFalseWhenUserDoesNotExist() {


            //Arrange
            String username = "admin";
            String password = "123";
            doReturn(null).when(repositoryMock).findByUsername(eq(username));

            //Act
            boolean isAuth = authService.authenticate(username, password);

            //Assert
            assertFalse(isAuth);
            verify(repositoryMock, times(1)).findByUsername(eq(username));
        }

    }

    @Nested
    class register {

        @Test
        @DisplayName("Should register user with success when does not exist")
        void shouldRegisterUserWithSuccessWhenDoesNotExist() {

            //Arrange
            var username = "admin";
            var password = "123";
            doReturn(null).when(repositoryMock).findByUsername(eq(username));

            //Act
            authService.register(username, password);

            verify(repositoryMock, times(1)).findByUsername(eq(username));
            verify(repositoryMock, times(1)).save(userArgumentCaptor.capture());

            var userCaptured = userArgumentCaptor.getValue();
            assertEquals(username, userCaptured.getUsername());
            assertEquals(password, userCaptured.getPassword());
        }

        @Test
        @DisplayName("Should not register when user already exists")
        void shouldNotRegisterWhenUserAlreadyExists() {

            //Arrange
            var username = "admin";
            var password = "123";
            doReturn(user).when(repositoryMock).findByUsername(eq(username));

            //Act & Assert
            var ex = assertThrows(IllegalArgumentException.class, () -> {
                authService.register(username, password);
            });

            verify(repositoryMock, times(1)).findByUsername(eq(username));
            verify(repositoryMock, times(0)).save(userArgumentCaptor.capture());

            assertEquals("Usuário já existe", ex.getMessage());
        }

    }

}