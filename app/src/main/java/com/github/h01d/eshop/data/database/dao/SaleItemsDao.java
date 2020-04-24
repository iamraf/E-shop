package com.github.h01d.eshop.data.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.github.h01d.eshop.data.database.entity.SaleItemEntity;
import com.github.h01d.eshop.data.models.SaleItem;

import java.util.List;

@Dao
public interface SaleItemsDao
{
    @Insert
    void insert(SaleItemEntity item);

    @Delete
    void delete(SaleItemEntity item);

    @Update
    void update(SaleItemEntity item);

    @Query("SELECT * FROM sale_items INNER JOIN products ON item_product_id = product_id WHERE item_sale_id = :id")
    LiveData<List<SaleItem>> getItems(int id);

    @Query("SELECT * FROM sale_items WHERE item_product_id = :id")
    LiveData<List<SaleItemEntity>> getItemsSold(int id);
}
