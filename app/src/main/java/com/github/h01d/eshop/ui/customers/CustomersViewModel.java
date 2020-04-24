package com.github.h01d.eshop.ui.customers;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.github.h01d.eshop.data.database.DatabaseManager;
import com.github.h01d.eshop.data.database.dao.CustomersDao;
import com.github.h01d.eshop.data.database.entity.CustomerEntity;

import java.util.List;

public class CustomersViewModel extends AndroidViewModel
{
    private CustomersDao customerDao;

    private LiveData<List<CustomerEntity>> customers;

    public CustomersViewModel(@NonNull Application application)
    {
        super(application);

        DatabaseManager db = DatabaseManager.getDatabase(application);

        customerDao = db.getCustomerDao();

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
        customerDao.delete(customer);
    }
}
