package org.example.data;

import org.example.data.exceptions.InternalErrorException;
import org.example.data.exceptions.RecordNotFoundException;
import org.example.model.Order;

import java.util.List;

public interface OrderRepo {
    List<Order> getAllOrders() throws InternalErrorException, RecordNotFoundException;
    Order getOrderById(int id) throws RecordNotFoundException, InternalErrorException;
    Order addOrder(Order order) throws InternalErrorException;
    Order deleteOrder(int id) throws InternalErrorException;
    void updateOrder(Order order) throws RecordNotFoundException, InternalErrorException;
}
