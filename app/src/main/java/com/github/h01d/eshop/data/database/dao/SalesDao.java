package com.github.h01d.eshop.data.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.github.h01d.eshop.data.database.entity.SaleEntity;
import com.github.h01d.eshop.data.models.Sale;

import java.util.List;

@Dao
public interface SalesDao
{
    @Insert
    long insert(SaleEntity sale);

    @Delete
    void delete(SaleEntity sale);

    @Update
    void update(SaleEntity sale);

    @Query("SELECT * FROM sales INNER JOIN customers on sale_customer_id = customer_id")
    LiveData<List<Sale>> getSales();
}
