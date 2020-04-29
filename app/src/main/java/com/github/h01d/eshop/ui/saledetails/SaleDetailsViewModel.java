package com.github.h01d.eshop.ui.saledetails;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.github.h01d.eshop.data.database.DatabaseManager;
import com.github.h01d.eshop.data.database.dao.ProductsDao;
import com.github.h01d.eshop.data.database.dao.SaleItemsDao;
import com.github.h01d.eshop.data.database.dao.SalesDao;
import com.github.h01d.eshop.data.models.Sale;
import com.github.h01d.eshop.data.models.SaleItem;

import java.util.List;

public class SaleDetailsViewModel extends AndroidViewModel
{
    private SaleItemsDao saleItemsDao;
    private SalesDao salesDao;
    private ProductsDao productsDao;

    public SaleDetailsViewModel(@NonNull Application application)
    {
        super(application);

        DatabaseManager db = DatabaseManager.getDatabase(application);

        saleItemsDao = db.getSaleItemsDao();
        salesDao = db.getSalesDao();
        productsDao = db.getProductsDao();
    }

    public LiveData<List<SaleItem>> getItems(int id)
    {
        return saleItemsDao.getItems(id);
    }

    public void delete(Sale sale, List<SaleItem> items)
    {
        for(SaleItem item : items)
        {
            item.getProductEntity().setQuantity(item.getProductEntity().getQuantity() + item.getSaleItemEntity().getQuantity());
            productsDao.update(item.getProductEntity());
        }

        salesDao.delete(sale.getSale());
    }
}
