package org.example.mappers;

import org.example.model.PaymentType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PaymentTypeMapper implements RowMapper<PaymentType> {
    @Override
    public PaymentType mapRow(ResultSet rs, int rowNum) throws SQLException {
        PaymentType type = new PaymentType();
        type.setPaymentTypeID(rs.getInt("payment_type_id"));
        type.setPaymentTypeName(rs.getString("payment_type_name"));
        return type;
    }
}
