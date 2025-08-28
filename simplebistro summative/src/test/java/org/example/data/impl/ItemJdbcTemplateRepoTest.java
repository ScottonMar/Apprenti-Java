package org.example.data.impl;

import org.example.data.exceptions.InternalErrorException;
import org.example.data.exceptions.RecordNotFoundException;
import org.example.mappers.ItemMapper;
import org.example.model.Item;
import org.example.model.ItemCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ItemJdbcTemplateRepoTest {

    private JdbcTemplate jdbcTemplate;
    private ItemMapper itemMapper;
    private ItemJdbcTemplateRepo repo;

    @BeforeEach
    void setUp() {
        jdbcTemplate = mock(JdbcTemplate.class);
        itemMapper = mock(ItemMapper.class);
        repo = new ItemJdbcTemplateRepo(jdbcTemplate, itemMapper);
    }

    @Test
    void testGetAllAvailableItems_success() {
        LocalDate date = LocalDate.now();
        when(jdbcTemplate.query(anyString(), eq(itemMapper), eq(date), eq(date)))
                .thenReturn(List.of(new Item(1, "Pizza", BigDecimal.TEN, 1)));

        List<Item> result = repo.getAllAvailableItems(date);

        assertEquals(1, result.size());
        verify(jdbcTemplate).query(anyString(), eq(itemMapper), eq(date), eq(date));
    }

    @Test
    void testGetAllAvailableItems_throwsInternalErrorException() {
        LocalDate date = LocalDate.now();
        when(jdbcTemplate.query(anyString(), any(), any(), any())).thenThrow(new DataAccessException("DB error") {});

        assertThrows(InternalErrorException.class, () -> repo.getAllAvailableItems(date));
    }

    @Test
    void testGetItemById_success() throws Exception {
        Item expected = new Item(1, "Pasta", BigDecimal.valueOf(12.50), 1);
        when(jdbcTemplate.queryForObject(anyString(), eq(itemMapper), eq(1))).thenReturn(expected);

        Item actual = repo.getItemById(1);

        assertEquals(expected, actual);
    }

    @Test
    void testGetItemById_notFound() {
        when(jdbcTemplate.queryForObject(anyString(), eq(itemMapper), eq(1)))
                .thenThrow(new DataAccessException("Not found") {});

        assertThrows(RecordNotFoundException.class, () -> repo.getItemById(1));
    }

    @Test
    void testGetAllItemCategories_success() {
        ItemCategory category = new ItemCategory();
        category.setItemCategoryId(1);
        category.setItemCategoryName("Entree");

        when(jdbcTemplate.query(anyString(), any())).thenReturn(List.of(category));

        List<ItemCategory> result = repo.getAllItemCategories();
        assertEquals(1, result.size());
    }

    @Test
    void testGetAllItemCategories_throwsInternalErrorException() {
        when(jdbcTemplate.query(anyString(), any())).thenThrow(new DataAccessException("Failure") {});
        assertThrows(InternalErrorException.class, () -> repo.getAllItemCategories());
    }
}
