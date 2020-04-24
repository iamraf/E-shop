package com.github.h01d.eshop.ui.saledetails;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.github.h01d.eshop.data.database.DatabaseManager;
import com.github.h01d.eshop.data.database.dao.SaleItemsDao;
import com.github.h01d.eshop.data.models.SaleItem;

import java.util.List;

public class SaleDetailsViewModel extends AndroidViewModel
{
    private SaleItemsDao saleItemsDao;

    public SaleDetailsViewModel(@NonNull Application application)
    {
        super(application);

        DatabaseManager db = DatabaseManager.getDatabase(application);

        saleItemsDao = db.getSaleItemsDao();
    }

    public LiveData<List<SaleItem>> getItems(int id)
    {
        return saleItemsDao.getItems(id);
    }
}
