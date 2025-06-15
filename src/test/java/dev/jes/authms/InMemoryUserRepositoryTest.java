package dev.jes.authms;

import dev.jes.ecommerce.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class InMemoryUserRepositoryTest {

    @Spy
    private HashMap<String, User> usersSpy;

    @InjectMocks
    private InMemoryUserRepository inMemoryUserRepository;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor; //melhor pratica para capturar argumentos

    @Nested
    class save {

        @Test
        @DisplayName("Deve salvar o usuario")
        void shouldSaveUser() {
            //Arrange
            var dummyUser = new User("jsilva", "pass");

            // Act
            inMemoryUserRepository.save(dummyUser);

            // Assert
            verify(usersSpy, times(1)).put(eq(dummyUser.getUsername()), userArgumentCaptor.capture());
            var userCaptured = userArgumentCaptor.getValue();
            assertSame(dummyUser, userCaptured); //Garante que é o mesmo objeto
        }
    }

    @Nested
    class findById {

        @Test
        @DisplayName("Should find by user name when user exists")
        void shouldFindByIdWhenOrderExists() {
            // Arrange
            var userName = "jsilva";

            // stub
            var dummyUser = new User("jsilva", "pass"); //criando stub
            doReturn(dummyUser).when(usersSpy).get(eq(userName));

            // Act
            var user = inMemoryUserRepository.findByUsername(userName);

            // Assert
            assertNotNull(user);
            assertSame(dummyUser, user);
            verify(usersSpy, times(1)).get(eq(userName));
        }

        @Test
        @DisplayName("Should return null when user does not exist")
        void shloudReturnNullWhenOrderDoesNotExist() {
            // Arrange
            var userName = "jsilva";
            doReturn(null).when(usersSpy).get(eq(userName));

            // Act
            var order = inMemoryUserRepository.findByUsername(userName);

            // Assert
            assertNull(order);
            verify(usersSpy, times(1)).get(eq(userName));
        }
    }
}