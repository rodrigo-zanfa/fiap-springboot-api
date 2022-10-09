package br.com.fiap.abctechapi.service;

import br.com.fiap.abctechapi.handler.exception.MaxAssistsException;
import br.com.fiap.abctechapi.handler.exception.MinimunAssistsRequiredException;
import br.com.fiap.abctechapi.model.Assistance;
import br.com.fiap.abctechapi.model.Order;
import br.com.fiap.abctechapi.repository.AssistanceRepository;
import br.com.fiap.abctechapi.repository.OrderRepository;
import br.com.fiap.abctechapi.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;

    @Mock
    private AssistanceRepository assistanceRepository;

    private OrderService orderService;

    @BeforeEach
    public void init() {
        orderService = new OrderServiceImpl(orderRepository, assistanceRepository);

        when(assistanceRepository.findById(any())).thenReturn(
                Optional.of(new Assistance(1L, "Teste", "Teste Description"))
        );
    }

    @DisplayName("Verificando service não nulo")
    @Test
    public void order_service_not_null() {
        Assertions.assertNotNull(orderService);
    }

    @DisplayName("Verificando mínimo de assistências")
    @Test
    public void create_order_error_minimum() {
        Order order = new Order();
        order.setOperatorId(12345L);

        Assertions.assertThrows(MinimunAssistsRequiredException.class, () -> orderService.saveOrder(order, List.of()));
        verify(orderRepository, times(0)).save(order);
    }

    @DisplayName("Verificando máximo de assistências")
    @Test
    public void create_order_error_maximum() {
        Order order = new Order();
        order.setOperatorId(12345L);

        Assertions.assertThrows(MaxAssistsException.class, () -> orderService.saveOrder(order, generate_mocks_ids(20)));
        verify(orderRepository, times(0)).save(order);
    }

    @DisplayName("Verificando ordem criada com sucesso")
    @Test
    public void create_order_success() throws Exception {
        Order order = new Order();
        order.setOperatorId(12345L);

        orderService.saveOrder(order, generate_mocks_ids(5));
        verify(orderRepository, times(1)).save(order);
    }

    private List<Long> generate_mocks_ids(int count) {
        ArrayList<Long> list = new ArrayList<>();

        for (int x = 0; x < count; x++) {
            list.add(Integer.toUnsignedLong(x));
        }

        return list;
    }
}
