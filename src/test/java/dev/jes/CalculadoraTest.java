package dev.jes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CalculadoraTest {

    private final Calculadora calculadora = new Calculadora();

    @Nested
    class somar {
        @Test
        @DisplayName("Should add two numbers")
        public void shouldAddToNumbers() {
            // Triple AAA (Arrange, Act, Asssert)
            //Arrange
            int a = 2;
            int b = 3;

            //Act
            var output = calculadora.somar(a, b);

            //Assert
            assertEquals(5, output);
        }

        @Test
        @DisplayName("Should add when two number is zero")
        void shouldAddWhenTwoNumberIsZero() {
            //Arrange
            int a = 0;
            int b = 0;

            //Act
            var output = calculadora.somar(a, b);

            //Assert
            assertEquals(0, output);
        }
    }

    @Nested
    class subtrair {
        @Test
        @DisplayName("Shoul Subtract Two Numbers")
        void shouldSubtractTwoNumbers() {
            //Arrange
            int a = 10;
            int b = 9;

            //Act
            var output = calculadora.subtrair(a, b);

            //Assert
            assertEquals(1, output);

        }
    }

    @Nested
    class multiplicar {
        @Test
        @DisplayName("Shloud multiply two numbers")
        void shouldMultiplyTwoNumbers() {
            //Arrange
            int a = 9;
            int b = 8;

            //Act
            var output = calculadora.multiplicar(a, b);

            assertEquals(72, output);
        }
    }

    @Nested
    class dividir {
        @Test
        @DisplayName("Shloud divide two numbers")
        void shouldDivideTwoNumbers() {
            //Arrange
            int a = 4;
            int b = 2;

            //Act
            var output = calculadora.dividir(a, b);

            assertEquals(2, output);
        }

        @Test
        @DisplayName("Shloud throw exception when divide to zero")
        void shouldThrowExceptionWhenDivideToZero() {
            //Arrange
            int a = 4;
            int b = 0;

            //Act
            var ex = assertThrows(ArithmeticException.class, () -> {
                calculadora.dividir(a, b);
            });

            assertEquals("Divisão por zero não permitida.", ex.getMessage());
        }
    }

    @Nested
    class potencia {
        @Test
        @DisplayName("Should calculate pow correctly two numbers")
        void shouldCalculatePowCorrectlyTwoNumbers() {
            //Arrange
            int base = 2;
            int expoente = 2;

            //Act
            var output = calculadora.potencia(base, expoente);

            assertEquals(4, output);
        }

    }

    @Nested
    class raizQuadrada {
        @Test
        @DisplayName("Should calculate sqrt correctly")
        void shouldCalculateSqrtCorrectly() {
            //Arrange
            int number = 9;

            //Act
            var output = calculadora.raizQuadrada(number);

            assertEquals(3, output);
        }

        @Test
        @DisplayName("Should throw exception when number is negative")
        void shouldThrowExceptionWhenNumberIsNegative() {
            //Arrange
            int number = -1;

            //Act & Assert
            var ex = assertThrows(ArithmeticException.class, () -> {
                calculadora.raizQuadrada(number);
            });

            assertEquals("Número negativo não tem raiz real.", ex.getMessage());
        }
    }

    @Nested
    class absoluto {
        @Test
        @DisplayName("Should calculate abs correctly")
        void shouldCalculateAbsCorrectly() {
            //Arrange
            int number = -50;

            //Act
            var output = calculadora.absoluto(number);

            assertEquals(50, output);
        }
    }

    @Nested
    class ehPar {
        @Test
        @DisplayName("Should be true")
        void shouldBeTrue() {
            //Arrange
            int number = 4;

            //Act
            var output = calculadora.ehPar(number);

            assertTrue(output);
        }

        @Test
        @DisplayName("Should be false")
        void shouldBeFalse() {
            //Arrange
            int number = 3;

            //Act
            var output = calculadora.ehPar(number);

            assertFalse(output);
        }
    }

    @Nested
    class ehPrimo {
        @Test
        @DisplayName("Should be true when 3")
        void shouldBeTrueWhen3() {
            //Arrange
            int number = 3;

            //Act
            var output = calculadora.ehPrimo(number);

            assertTrue(output);
        }

        @Test
        @DisplayName("Should be false when 4")
        void shouldBeFalseWhen4() {
            //Arrange
            int number = 4;

            //Act
            var output = calculadora.ehPrimo(number);

            assertFalse(output);
        }
    }

    @Nested
    class maximo {
        @Test
        @DisplayName("Should be the max number")
        void shouldBeTheMaxNumber() {
            //Arrange
            int a = 3;
            int max = 10;

            //Act
            var output = calculadora.maximo(max, a);

            assertEquals(max, output);
        }
    }

    @Nested
    class minimo {
        @Test
        @DisplayName("Should be the min number")
        void shouldBeTheMaxNumber() {
            //Arrange
            int min = 3;
            int a = 10;

            //Act
            var output = calculadora.minimo(min, a);

            assertEquals(min, output);
        }
    }


}