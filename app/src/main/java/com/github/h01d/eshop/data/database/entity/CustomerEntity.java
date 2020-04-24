package com.github.h01d.eshop.data.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "customers")
public class CustomerEntity implements Serializable
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="customer_id")
    @NonNull
    private int id;
    @ColumnInfo(name="customer_fullname")
    @NonNull
    private String fullname;
    @ColumnInfo(name="customer_address")
    @NonNull
    private String address;

    public CustomerEntity()
    {
        //Required by room
    }

    @Ignore
    public CustomerEntity(@NonNull String fullname, @NonNull String address)
    {
        this.fullname = fullname;
        this.address = address;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    @NonNull
    public String getFullname()
    {
        return fullname;
    }

    public void setFullname(@NonNull String fullname)
    {
        this.fullname = fullname;
    }

    @NonNull
    public String getAddress()
    {
        return address;
    }

    public void setAddress(@NonNull String address)
    {
        this.address = address;
    }
}
