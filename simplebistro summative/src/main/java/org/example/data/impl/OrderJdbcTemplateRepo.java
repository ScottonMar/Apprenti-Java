package org.example.data.impl;

import org.example.data.OrderRepo;
import org.example.data.exceptions.InternalErrorException;
import org.example.data.exceptions.RecordNotFoundException;
import org.example.mappers.OrderMapper;
import org.example.model.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderJdbcTemplateRepo implements OrderRepo {

    private final JdbcTemplate jdbcTemplate;
    private final OrderMapper orderMapper;

    public OrderJdbcTemplateRepo(JdbcTemplate jdbcTemplate, OrderMapper orderMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.orderMapper = orderMapper;
    }

    @Override
    public List<Order> getAllOrders() throws InternalErrorException {
        try {
            String sql = """
	SELECT o.OrderID, o.ServerID, OrderDate, SubTotal, Tax, Tip, Total, FirstName, LastName, HireDate, TermDate FROM `order` o
	inner join `server` s on o.ServerID = s.ServerID
	""";
            return jdbcTemplate.query(sql, orderMapper);
        } catch (DataAccessException e) {
            throw new InternalErrorException("Unable to retrieve orders", e);
        }
    }

    @Override
    public Order getOrderById(int id) throws RecordNotFoundException {
        try {
            String sql = "SELECT * FROM `order` WHERE order_id = ?";
            return jdbcTemplate.queryForObject(sql, orderMapper, id);
        } catch (DataAccessException e) {
            throw new RecordNotFoundException("Order not found with ID: " + id);
        }
    }

    @Override
    public Order addOrder(Order order) throws InternalErrorException {
        // Placeholder for actual insert logic.
        // Assuming order ID is auto-generated and returned.
        throw new InternalErrorException("Add order not implemented yet");
    }

    @Override
    public Order deleteOrder(int id) throws InternalErrorException {
        // Placeholder for delete logic
        throw new InternalErrorException("Delete order not implemented yet");
    }

    @Override
    public void updateOrder(Order order) throws RecordNotFoundException, InternalErrorException {
        // Placeholder for update logic
        throw new InternalErrorException("Update order not implemented yet");
    }
}
