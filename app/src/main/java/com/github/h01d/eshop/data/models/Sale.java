package com.github.h01d.eshop.data.models;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.room.Embedded;

import com.github.h01d.eshop.data.database.entity.CustomerEntity;
import com.github.h01d.eshop.data.database.entity.SaleEntity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Sale implements Serializable
{
    @Embedded
    private SaleEntity sale;

    @Embedded
    private CustomerEntity customer;

    public Sale()
    {
        //Required by room
    }

    public SaleEntity getSale()
    {
        return sale;
    }

    public void setSale(SaleEntity sale)
    {
        this.sale = sale;
    }

    public CustomerEntity getCustomer()
    {
        return customer;
    }

    public void setCustomer(CustomerEntity customer)
    {
        this.customer = customer;
    }

    @BindingAdapter(value = "date")
    public static void setDate(TextView textView, Date date)
    {
        try
        {
            textView.setText(new SimpleDateFormat("dd/MM/yy HH:mm", Locale.US).format(date));
        }
        catch(Exception e)
        {
            textView.setText("");
        }
    }
}
