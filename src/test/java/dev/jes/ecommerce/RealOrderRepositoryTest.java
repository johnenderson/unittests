package dev.jes.ecommerce;

import dev.jes.ecommerce.Order;
import dev.jes.ecommerce.RealOrderRepository;
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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class) //utiliza-se quando temos construtores com parâmetros
class RealOrderRepositoryTest {

    @Spy
    private HashMap<Integer, Order> ordersSpy;

    @Captor
    private ArgumentCaptor<Order> orderArgumentCaptor; //melhor pratica para capturar argumentos

    @InjectMocks
    RealOrderRepository realOrderRepository;

    @Nested
    class save{

        @Test
        @DisplayName("Should save order")
        void shouldSaveOrder() {
            // Arrange
            var dummyOrder = new Order(1, "Fulano", 200.0);

            // Act
            realOrderRepository.save(dummyOrder);

            // Assert
            verify(ordersSpy, times(1)).put(eq(dummyOrder.getId()), orderArgumentCaptor.capture());
            var orderCaptured = orderArgumentCaptor.getValue();
            assertSame(dummyOrder, orderCaptured); //Garante que é o mesmo objeto
        }
    }

    @Nested
    class findById{

        @Test
        @DisplayName("Should find by id when order exists")
        void shouldFindByIdWhenOrderExists() {
            // Arrange
            var id = 1;

            // stub
            var dummyOrder = new Order(1, "Fulano", 200.0); //criando stub
            doReturn(dummyOrder).when(ordersSpy).get(eq(id));

            // Act
            var order = realOrderRepository.findById(id);

            // Assert
            assertNotNull(order);
            assertSame(dummyOrder, order);
            verify(ordersSpy, times(1)).get(eq(id));
        }

        @Test
        @DisplayName("Should return null when order does not exist")
        void shloudReturnNullWhenOrderDoesNotExist() {
            // Arrange
            var id = 1;
            doReturn(null).when(ordersSpy).get(eq(id));

            // Act
            var order = realOrderRepository.findById(id);

            // Assert
            assertNull(order);
            verify(ordersSpy, times(1)).get(eq(id));
        }
    }
}