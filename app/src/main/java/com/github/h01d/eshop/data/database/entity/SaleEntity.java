package com.github.h01d.eshop.data.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "sales", foreignKeys = {@ForeignKey(onDelete = CASCADE, onUpdate = CASCADE, entity = CustomerEntity.class, parentColumns = "customer_id", childColumns = "sale_customer_id")})
public class SaleEntity implements Serializable
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="sale_id")
    @NonNull
    private int id;
    @ColumnInfo(name="sale_customer_id")
    @NonNull
    private int customerId;
    @ColumnInfo(name="sale_date")
    @NonNull
    private Date date;

    public SaleEntity()
    {
        //Required by room
    }

    @Ignore
    public SaleEntity(int customerId, @NonNull Date date)
    {
        this.customerId = customerId;
        this.date = date;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId(int customerId)
    {
        this.customerId = customerId;
    }

    @NonNull
    public Date getDate()
    {
        return date;
    }

    public void setDate(@NonNull Date date)
    {
        this.date = date;
    }
}
