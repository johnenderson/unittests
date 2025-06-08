package dev.jes.authms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserTest {

    @Nested
    class isValidPassword {

        @Test
        @DisplayName("Should return true when password is valid")
        void shouldReturnTrueWhenPasswordIsValid() {

            //Arrange
            String password = "123456";
            var newUser = new User("admin", password);

            //Act
            boolean isPasswordValid = newUser.isValidPassword(password);

            //Assert
            assertTrue(isPasswordValid);
        }

        @Test
        @DisplayName("Should return false when password is Invalid")
        void shouldReturnFalseWhenPasswordIsInvalid() {

            //Arrange
            String password = "123456";
            String otherPassword = "1234567";
            String username = "admin";
            var newUser = new User(username, password);

            //Act
            boolean isPasswordValid = newUser.isValidPassword(otherPassword);

            //Assert
            assertFalse(isPasswordValid);
        }
    }

    @Nested
    class changePassword {

        @Test
        @DisplayName("Should change password with success")
        void shloudChangePasswordWithSuccess() {

            //Arrange
            String password = "123";
            String newPassword = "456";
            String username = "admin";
            var newUser = new User(username, password);

            //Act
            newUser.changePassword(newPassword);

            //Assert
            assertEquals(newPassword, newUser.getPassword());
        }

        @Test
        @DisplayName("Should throw exception when password is null")
        void shouldThrowExceptionWhenPasswordIsNull() {

            //Arrange
            String password = "123";
            String newPassword = null;
            String username = "admin";
            var newUser = new User(username, password);

            //Act
           var ex = assertThrows(IllegalArgumentException.class, () -> {
                newUser.changePassword(newPassword);
            });

           //Assert
           assertEquals("A senha não pode ser vazia", ex.getMessage());
        }

        @Test
        @DisplayName("Should throw exception when password is blank")
        void shouldThrowExceptionWhenPasswordIsBlank() {

            //Arrange
            String password = "123";
            String newPassword = "";
            String username = "admin";
            var newUser = new User(username, password);

            //Act
            var ex = assertThrows(IllegalArgumentException.class, () -> {
                newUser.changePassword(newPassword);
            });

            //Assert
            assertEquals("A senha não pode ser vazia", ex.getMessage());
        }
    }
}