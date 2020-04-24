package com.github.h01d.eshop.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.github.h01d.eshop.data.database.dao.CartDao;
import com.github.h01d.eshop.data.database.dao.CustomersDao;
import com.github.h01d.eshop.data.database.dao.ProductsDao;
import com.github.h01d.eshop.data.database.dao.SaleItemsDao;
import com.github.h01d.eshop.data.database.dao.SalesDao;
import com.github.h01d.eshop.data.database.entity.CartEntity;
import com.github.h01d.eshop.data.database.entity.CustomerEntity;
import com.github.h01d.eshop.data.database.entity.ProductEntity;
import com.github.h01d.eshop.data.database.entity.SaleEntity;
import com.github.h01d.eshop.data.database.entity.SaleItemEntity;
import com.github.h01d.eshop.util.Converters;

@Database(entities = {ProductEntity.class, CartEntity.class, CustomerEntity.class, SaleEntity.class, SaleItemEntity.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class DatabaseManager extends RoomDatabase
{
    public abstract ProductsDao getProductsDao();
    public abstract CartDao getCartDao();
    public abstract CustomersDao getCustomerDao();
    public abstract SalesDao getSalesDao();
    public abstract SaleItemsDao getSaleItemsDao();

    private static volatile DatabaseManager instance;

    public static DatabaseManager getDatabase(final Context context)
    {
        if(instance == null)
        {
            synchronized(DatabaseManager.class)
            {
                if(instance == null)
                {
                    instance = Room.databaseBuilder(context.getApplicationContext(), DatabaseManager.class, "eshop_database").allowMainThreadQueries().fallbackToDestructiveMigration().build();
                }
            }
        }
        return instance;
    }
}
