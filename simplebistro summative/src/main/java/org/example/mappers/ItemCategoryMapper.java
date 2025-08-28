package org.example.mappers;

import org.example.model.ItemCategory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemCategoryMapper implements RowMapper<ItemCategory> {
    @Override
    public ItemCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
        ItemCategory category = new ItemCategory();
        category.setItemCategoryID(rs.getInt("item_category_id"));
        category.setItemCategoryName(rs.getString("item_category_name"));
        return category;
    }
}
