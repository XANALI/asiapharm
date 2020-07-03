package kz.xan.asiapharm.services.impl;

import kz.xan.asiapharm.domain.Order;
import kz.xan.asiapharm.repositories.OrderRepository;
import kz.xan.asiapharm.services.OrderService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order findById(Long aLong) {
        return orderRepository.findById(aLong).orElse(null);
    }

    @Override
    public Order save(Order object) {
        return orderRepository.save(object);
    }

    @Override
    public Set<Order> findAll() {
        Set<Order> orders = new HashSet<>();
        orderRepository.findAll().forEach(orders::add);

        return orders;
    }

    @Override
    public void delete(Order object) {
        orderRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        orderRepository.deleteById(aLong);
    }
}
