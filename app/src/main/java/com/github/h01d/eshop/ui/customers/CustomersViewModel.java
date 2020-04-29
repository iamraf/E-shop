package com.github.h01d.eshop.ui.customers;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.github.h01d.eshop.data.database.DatabaseManager;
import com.github.h01d.eshop.data.database.dao.CustomersDao;
import com.github.h01d.eshop.data.database.dao.ProductsDao;
import com.github.h01d.eshop.data.database.dao.SaleItemsDao;
import com.github.h01d.eshop.data.database.dao.SalesDao;
import com.github.h01d.eshop.data.database.entity.CustomerEntity;
import com.github.h01d.eshop.data.database.entity.SaleEntity;
import com.github.h01d.eshop.data.models.SaleItem;

import java.util.List;

public class CustomersViewModel extends AndroidViewModel
{
    private CustomersDao customerDao;
    private SalesDao salesDao;
    private SaleItemsDao saleItemsDao;
    private ProductsDao productsDao;

    private LiveData<List<CustomerEntity>> customers;

    public CustomersViewModel(@NonNull Application application)
    {
        super(application);

        DatabaseManager db = DatabaseManager.getDatabase(application);

        customerDao = db.getCustomerDao();
        salesDao = db.getSalesDao();
        saleItemsDao = db.getSaleItemsDao();
        productsDao = db.getProductsDao();

        customers = customerDao.getCustomers();
    }

    public LiveData<List<CustomerEntity>> getCustomers()
    {
        return customers;
    }

    public void insert(CustomerEntity customer)
    {
        customerDao.insert(customer);
    }

    public void delete(CustomerEntity customer)
    {
        List<SaleEntity> sales = salesDao.getCustomerSales(customer.getId());

        for(SaleEntity sale : sales)
        {
            List<SaleItem> items = saleItemsDao.getSaleItems(sale.getId());

            for(SaleItem item : items)
            {
                item.getProductEntity().setQuantity(item.getProductEntity().getQuantity() + item.getSaleItemEntity().getQuantity());
                productsDao.update(item.getProductEntity());
            }
        }

        customerDao.delete(customer);
    }
}
