package com.github.h01d.eshop.data.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.github.h01d.eshop.data.database.entity.ProductEntity;

import java.util.List;

@Dao
public interface ProductsDao
{
    @Insert
    void insert(ProductEntity product);

    @Delete
    void delete(ProductEntity product);

    @Update
    void update(ProductEntity product);

    @Query("SELECT * FROM products")
    LiveData<List<ProductEntity>> getProducts();

    @Query("SELECT * FROM products WHERE product_id = :id")
    ProductEntity getProduct(int id);
}