package org.example.data.impl;

import org.example.data.TaxRepo;
import org.example.data.exceptions.RecordNotFoundException;
import org.example.mappers.TaxMapper;
import org.example.model.Tax;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public class TaxJdbcTemplateRepo implements TaxRepo {

    private final JdbcTemplate jdbcTemplate;
    private final TaxMapper mapper;

    public TaxJdbcTemplateRepo(JdbcTemplate jdbcTemplate, TaxMapper mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
    }

    @Override
    public Tax getCurrentTax(LocalDate date) throws RecordNotFoundException {
        try {
            String sql = "SELECT * FROM tax WHERE StartDate <= ? AND (EndDate IS NULL OR EndDate >= ?)";
            return jdbcTemplate.queryForObject(sql, mapper, date, date);
        } catch (DataAccessException e) {
            throw new RecordNotFoundException("No tax found for date: " + date);
        }
    }
}
