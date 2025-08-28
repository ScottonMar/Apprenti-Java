package org.example.data.impl;

import org.example.data.exceptions.InternalErrorException;
import org.example.mappers.PaymentTypeMapper;
import org.example.model.PaymentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentTypeJdbcTemplateRepoTest {

    private JdbcTemplate jdbcTemplate;
    private PaymentTypeMapper mapper;
    private PaymentTypeJdbcTemplateRepo repo;

    @BeforeEach
    void setUp() {
        jdbcTemplate = mock(JdbcTemplate.class);
        mapper = mock(PaymentTypeMapper.class);
        repo = new PaymentTypeJdbcTemplateRepo(jdbcTemplate, mapper);
    }

    @Test
    void testGetAll_success() {
        PaymentType type = new PaymentType();
        type.setPaymentTypeId(1);
        type.setPaymentTypeName("Cash");

        when(jdbcTemplate.query(anyString(), eq(mapper)))
                .thenReturn(List.of(type));

        List<PaymentType> result = repo.getAll();

        assertEquals(1, result.size());
        assertEquals("Cash", result.get(0).getPaymentTypeName());
        verify(jdbcTemplate).query(anyString(), eq(mapper));
    }

    @Test
    void testGetAll_internalError() {
        when(jdbcTemplate.query(anyString(), eq(mapper)))
                .thenThrow(new DataAccessException("DB failure") {});

        assertThrows(InternalErrorException.class, () -> repo.getAll());
    }
}
