package org.example.data.impl;

import org.example.data.PaymentTypeRepo;
import org.example.data.exceptions.InternalErrorException;
import org.example.mappers.PaymentTypeMapper;
import org.example.model.PaymentType;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaymentTypeJdbcTemplateRepo implements PaymentTypeRepo {

    private final JdbcTemplate jdbcTemplate;
    private final PaymentTypeMapper mapper;

    public PaymentTypeJdbcTemplateRepo(JdbcTemplate jdbcTemplate, PaymentTypeMapper mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
    }

    @Override
    public List<PaymentType> getAll() throws InternalErrorException {
        try {
            String sql = "SELECT * FROM payment_type";
            return jdbcTemplate.query(sql, mapper);
        } catch (DataAccessException e) {
            throw new InternalErrorException("Error retrieving payment types", e);
        }
    }
}
