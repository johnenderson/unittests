package dev.jes.mockito;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
//    @Mock
//    private Database databaseMock;

    @Spy
    private RealDatabase database; // uso do spy chama a implementação real

    @InjectMocks
    private UserService userService;

    @BeforeAll
    public static void beforeAll() {
        //database = spy(RealDatabase.class);
        //userService = new UserService(database);
    }
    @Nested
    class getUserStatus {
        @Test
        @DisplayName("Shloud return an active user")
        void shouldReturnActiveUser() {
            int userId = 1;
            String expectedStatus = "ACTIVE";
            //Arrange
            doReturn(expectedStatus).when(database).getStatus(eq(userId)); //test stub

            //Act
            var output = userService.getUserStatus(userId);

            //Assert
            assertEquals(expectedStatus, output);
            verify(database, times(1)).getStatus(eq(userId)); // call method
        }
    }

}