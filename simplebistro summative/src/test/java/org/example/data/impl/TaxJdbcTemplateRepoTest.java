package org.example.data.impl;

import org.example.data.exceptions.RecordNotFoundException;
import org.example.mappers.TaxMapper;
import org.example.model.Tax;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaxJdbcTemplateRepoTest {

    private JdbcTemplate jdbcTemplate;
    private TaxMapper mapper;
    private TaxJdbcTemplateRepo repo;

    @BeforeEach
    void setUp() {
        jdbcTemplate = mock(JdbcTemplate.class);
        mapper = mock(TaxMapper.class);
        repo = new TaxJdbcTemplateRepo(jdbcTemplate, mapper);
    }

    @Test
    void testGetCurrentTax_success() throws RecordNotFoundException {
        LocalDate date = LocalDate.now();
        Tax expected = new Tax();
        expected.setTaxId(1);
        expected.setTaxName("State Tax");
        expected.setRate(BigDecimal.valueOf(8.25));

        when(jdbcTemplate.queryForObject(anyString(), eq(mapper), eq(date), eq(date)))
                .thenReturn(expected);

        Tax actual = repo.getCurrentTax(date);

        assertEquals(expected.getTaxId(), actual.getTaxId());
        assertEquals(expected.getTaxName(), actual.getTaxName());
        assertEquals(expected.getRate(), actual.getRate());
    }

    @Test
    void testGetCurrentTax_notFound() {
        LocalDate date = LocalDate.now();
        when(jdbcTemplate.queryForObject(anyString(), eq(mapper), eq(date), eq(date)))
                .thenThrow(new DataAccessException("Not Found") {});

        assertThrows(RecordNotFoundException.class, () -> repo.getCurrentTax(date));
    }
}
