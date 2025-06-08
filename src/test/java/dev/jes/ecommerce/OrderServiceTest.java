package dev.jes.ecommerce;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class) //utiliza-se quando temos construtores com parâmetros
class OrderServiceTest {

    @Mock
    OrderRepository repositoryMock;

    @Captor
    private ArgumentCaptor<Order> orderArgumentCaptor; //melhor prática para capturar argumentos

    @InjectMocks
    private OrderService orderService;

    @Nested
    class placeOrder{

        @Test
        @DisplayName("Should save order when total is positive")
        void shouldSaveOrderWhenTotalIsPositive() {
            // Arrange
            var dummyOrder = new Order(1, "Fulano", 200.0);

            // Act
            orderService.placeOrder(dummyOrder);

            // Assert
            verify(repositoryMock, times(1)).save(orderArgumentCaptor.capture());
            var orderCaptured = orderArgumentCaptor.getValue();
            assertEqualsOrder(dummyOrder, orderCaptured); //Garante que é o mesmo objeto

        }

        @ParameterizedTest
        @ValueSource(doubles = {0, -1.0, -2.0, -50.0})
        @DisplayName("Should throw exception when total is below or equal to zero")
        void shouldThrowExceptionWhenTotalIsBelowOrEqualToZero(double total) {
            // Arrange
            var dummyOrder = new Order(1, "Fulano", total);

            // Act & Assert
            assertThrows(IllegalArgumentException.class, () -> orderService.placeOrder(dummyOrder));
            verify(repositoryMock, times(0)).save(any());
        }

        @Test
        @DisplayName("Should throw exception when place order")
        void shouldThrowExceptionWhenPlaceOrder() {
            // Arrange
            var dummyOrder = new Order(1, "Bruno", 200.0);
            doThrow(new RuntimeException()).when(repositoryMock).save(any());
            // Act & Assert
            assertThrows(RuntimeException.class, () -> orderService.placeOrder(dummyOrder));
        }
    }

    @Nested
    class getOrder {

        @Test
        @DisplayName("Should return order when id exists")
        void shouldReturnOrderWhenIdExists() {
            // Arrange
            int orderId = 1;
            // stub
            var dummyOrder = new Order(1, "Fulano", 200.0);
            doReturn(dummyOrder).when(repositoryMock).findById(eq(orderId));

            // Act
            var order = orderService.getOrder(orderId);

            // Assert
            assertNotNull(order);
            assertEqualsOrder(dummyOrder, order);
            verify(repositoryMock, times(1)).findById(eq(orderId));
        }

        @Test
        @DisplayName("Should when order does not exist")
        void shouldReturnNullWhenOrderDoesNotExist() {
            // Arrange
            int orderId = 1;
            doReturn(null).when(repositoryMock).findById(eq(orderId));

            // Act
            var order = orderService.getOrder(orderId);

            // Assert
            assertNull(order);
            verify(repositoryMock, times(1)).findById(eq(orderId));
        }
    }

    private static void assertEqualsOrder(Order dummyOrder, Order order) {
        assertEquals(dummyOrder.getId(), order.getId());
        assertEquals(dummyOrder.getCustomer(), order.getCustomer());
        assertEquals(dummyOrder.getTotal(), order.getTotal());
    }
}