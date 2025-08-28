package org.example.mappers;

import org.example.model.Order;
import org.example.model.Server;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Component
public class OrderMapper implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();
        order.setOrderID(rs.getInt("OrderID"));
        order.setServerID(rs.getInt("ServerID"));
        order.setOrderDate(rs.getObject("OrderDate", LocalDateTime.class)); // ✅ match orderDate
        order.setSubTotal(rs.getBigDecimal("SubTotal"));
        order.setTax(rs.getBigDecimal("Tax"));
        order.setTip(rs.getBigDecimal("Tip"));
        order.setTotal(rs.getBigDecimal("Total"));
        order.setServer(new Server());
        order.getServer().setFirstName(rs.getString("FirstName"));
        order.getServer().setLastName(rs.getString("LastName"));

        // DO NOT try to set tableNumber (it doesn't exist)
        return order;
    }
}
