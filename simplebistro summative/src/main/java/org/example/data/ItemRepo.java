package org.example.data;

import org.example.data.exceptions.InternalErrorException;
import org.example.data.exceptions.RecordNotFoundException;
import org.example.model.Item;
import org.example.model.ItemCategory;

import java.time.LocalDate;
import java.util.List;

public interface ItemRepo {
    List<Item> getAllAvailableItems(LocalDate date) throws InternalErrorException;
    List<Item> getItemsByCategory(LocalDate date, int categoryId) throws InternalErrorException;
    List<ItemCategory> getAllItemCategories() throws InternalErrorException;
    Item getItemById(int id) throws RecordNotFoundException, InternalErrorException;
}
