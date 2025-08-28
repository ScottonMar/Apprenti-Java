package org.example.mappers;

import org.example.model.Item;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ItemMapper implements RowMapper<Item> {
    @Override
    public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
        Item item = new Item();
        item.setItemID(rs.getInt("item_id"));
        item.setItemName(rs.getString("item_name"));
        item.setPrice(rs.getBigDecimal("price"));
        item.setItemCategoryID(rs.getInt("category_id"));
        return item;
    }
}
