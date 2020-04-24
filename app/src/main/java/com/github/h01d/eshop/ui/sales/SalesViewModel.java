package com.github.h01d.eshop.ui.sales;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.github.h01d.eshop.data.database.DatabaseManager;
import com.github.h01d.eshop.data.database.dao.SalesDao;
import com.github.h01d.eshop.data.models.Sale;

import java.util.List;

public class SalesViewModel extends AndroidViewModel
{
    private SalesDao salesDao;

    private LiveData<List<Sale>> sales;

    public SalesViewModel(@NonNull Application application)
    {
        super(application);

        DatabaseManager db = DatabaseManager.getDatabase(application);

        salesDao = db.getSalesDao();

        sales = salesDao.getSales();
    }

    public LiveData<List<Sale>> getSales()
    {
        return sales;
    }
}
