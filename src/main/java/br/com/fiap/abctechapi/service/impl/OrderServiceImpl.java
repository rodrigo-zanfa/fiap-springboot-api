package br.com.fiap.abctechapi.service.impl;

import br.com.fiap.abctechapi.handler.exception.MaxAssistsException;
import br.com.fiap.abctechapi.handler.exception.MinimunAssistsRequiredException;
import br.com.fiap.abctechapi.model.Assistance;
import br.com.fiap.abctechapi.model.Order;
import br.com.fiap.abctechapi.repository.AssistanceRepository;
import br.com.fiap.abctechapi.repository.OrderRepository;
import br.com.fiap.abctechapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private AssistanceRepository assistanceRepository;

    public OrderServiceImpl(@Autowired OrderRepository orderRepository, @Autowired AssistanceRepository assistanceRepository) {
        this.orderRepository = orderRepository;
        this.assistanceRepository = assistanceRepository;
    }

    @Override
    public void saveOrder(Order order, List<Long> assists) throws Exception {
        ArrayList<Assistance> assistances = new ArrayList<>();

        assists.forEach(i -> {
            Assistance assistance = assistanceRepository.findById(i).orElseThrow();
            assistances.add(assistance);
        });

        order.setAssists(assistances);

        if (!order.hasMinAssists()) {
            throw new MinimunAssistsRequiredException("Invalid Assists", "Necessário no mínimo 1 assistência.");
        } else if (order.exceedsMaxAssists()) {
            throw new MaxAssistsException("Invalid Assists", "Máximo de 15 assistências permitidas.");
        }

        orderRepository.save(order);
    }

    @Override
    public List<Order> listOrdersByOperator(Long operatorId) {
        return null;
    }
}
