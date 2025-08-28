package org.example.data.impl;

import org.example.data.ItemRepo;
import org.example.data.exceptions.InternalErrorException;
import org.example.data.exceptions.RecordNotFoundException;
import org.example.mappers.ItemMapper;
import org.example.model.Item;
import org.example.model.ItemCategory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class ItemJdbcTemplateRepo implements ItemRepo {

    private final JdbcTemplate jdbcTemplate;
    private final ItemMapper itemMapper;

    public ItemJdbcTemplateRepo(JdbcTemplate jdbcTemplate, ItemMapper itemMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.itemMapper = itemMapper;
    }

    @Override
    public List<Item> getAllAvailableItems(LocalDate date) throws InternalErrorException {
        try {
            String sql = "SELECT * FROM item WHERE StartDate <= ? AND (EndDate IS NULL OR EndDate >= ?)";
            return jdbcTemplate.query(sql, itemMapper, date, date);
        } catch (DataAccessException e) {
            throw new InternalErrorException("Error retrieving available items", e);
        }
    }

    @Override
    public List<Item> getItemsByCategory(LocalDate date, int categoryId) throws InternalErrorException {
        try {
            String sql = "SELECT * FROM item WHERE categoryid = ? AND StartDate <= ? AND (EndDate IS NULL OR EndDate >= ?)";
            return jdbcTemplate.query(sql, itemMapper, categoryId, date, date);
        } catch (DataAccessException e) {
            throw new InternalErrorException("Error retrieving items by category", e);
        }
    }

    @Override
    public List<ItemCategory> getAllItemCategories() throws InternalErrorException {
        try {
            String sql = "SELECT * FROM itemcategory";
            return jdbcTemplate.query(sql, (rs, rowNum) -> {
                ItemCategory category = new ItemCategory();
                category.setItemCategoryID(rs.getInt("ItemCategoryId"));
                category.setItemCategoryName(rs.getString("ItemCategoryName"));
                return category;
            });
        } catch (DataAccessException e) {
            throw new InternalErrorException("Error retrieving item categories", e);
        }
    }

    @Override
    public Item getItemById(int id) throws RecordNotFoundException, InternalErrorException {
        try {
            String sql = "SELECT * FROM item WHERE ItemId = ?";
            return jdbcTemplate.queryForObject(sql, itemMapper, id);
        } catch (DataAccessException e) {
            throw new RecordNotFoundException("Item not found with ID: " + id);
        }
    }
}
