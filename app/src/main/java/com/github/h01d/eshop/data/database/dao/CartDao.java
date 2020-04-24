package com.github.h01d.eshop.data.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.github.h01d.eshop.data.models.Cart;
import com.github.h01d.eshop.data.database.entity.CartEntity;

import java.util.List;

@Dao
public interface CartDao
{
    @Insert
    void insert(CartEntity cart);

    @Delete
    void delete(CartEntity cart);

    @Update
    void update(CartEntity cart);

    @Query("SELECT * FROM cart")
    LiveData<List<CartEntity>> getCart();

    @Query("SELECT * FROM cart INNER JOIN products ON cart_product_id = product_id")
    LiveData<List<Cart>> getJoinedCart();

    @Query("SELECT * FROM cart WHERE cart_product_id = :id")
    CartEntity getSyncCartProductId(int id);
}