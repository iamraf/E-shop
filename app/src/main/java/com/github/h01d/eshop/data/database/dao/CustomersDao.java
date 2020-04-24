package com.github.h01d.eshop.data.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.github.h01d.eshop.data.database.entity.CustomerEntity;

import java.util.List;

@Dao
public interface CustomersDao
{
    @Insert
    long insert(CustomerEntity customer);

    @Delete
    void delete(CustomerEntity customer);

    @Update
    void update(CustomerEntity customer);

    @Query("SELECT * FROM customers")
    LiveData<List<CustomerEntity>> getCustomers();

    @Query("SELECT customer_id FROM customers WHERE customer_fullname = :fullname")
    int getCustomerId(String fullname);
}
