package org.example.data.impl;

import org.example.data.exceptions.InternalErrorException;
import org.example.data.exceptions.RecordNotFoundException;
import org.example.mappers.OrderMapper;
import org.example.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderJdbcTemplateRepoTest {

    private JdbcTemplate jdbcTemplate;
    private OrderMapper orderMapper;
    private OrderJdbcTemplateRepo repo;

    @BeforeEach
    void setUp() {
        jdbcTemplate = mock(JdbcTemplate.class);
        orderMapper = mock(OrderMapper.class);
        repo = new OrderJdbcTemplateRepo(jdbcTemplate, orderMapper);
    }

    @Test
    void testGetAllOrders_success() {
        Order order = new Order();
        order.setOrderId(1);
        order.setOrderTime(LocalDateTime.now());

        when(jdbcTemplate.query(anyString(), eq(orderMapper)))
                .thenReturn(List.of(order));

        List<Order> result = repo.getAllOrders();

        assertEquals(1, result.size());
        verify(jdbcTemplate).query(anyString(), eq(orderMapper));
    }

    @Test
    void testGetAllOrders_internalError() {
        when(jdbcTemplate.query(anyString(), any()))
                .thenThrow(new DataAccessException("DB Error") {});

        assertThrows(InternalErrorException.class, () -> repo.getAllOrders());
    }

    @Test
    void testGetOrderById_success() {
        Order expected = new Order();
        expected.setOrderId(1);

        when(jdbcTemplate.queryForObject(anyString(), eq(orderMapper), eq(1)))
                .thenReturn(expected);

        Order actual = repo.getOrderById(1);
        assertEquals(expected, actual);
    }

    @Test
    void testGetOrderById_notFound() {
        when(jdbcTemplate.queryForObject(anyString(), any(), any()))
                .thenThrow(new DataAccessException("Not Found") {});

        assertThrows(RecordNotFoundException.class, () -> repo.getOrderById(1));
    }

    @Test
    void testAddOrder_notImplemented() {
        assertThrows(InternalErrorException.class, () -> repo.addOrder(new Order()));
    }

    @Test
    void testDeleteOrder_notImplemented() {
        assertThrows(InternalErrorException.class, () -> repo.deleteOrder(1));
    }

    @Test
    void testUpdateOrder_notImplemented() {
        assertThrows(InternalErrorException.class, () -> repo.updateOrder(new Order()));
    }
}
