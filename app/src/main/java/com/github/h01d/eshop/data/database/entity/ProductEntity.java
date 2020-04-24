package com.github.h01d.eshop.data.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "products")
public class ProductEntity implements Serializable
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="product_id")
    @NonNull
    private int id;
    @ColumnInfo(name="product_title")
    @NonNull
    private String title;
    @ColumnInfo(name="product_description")
    @NonNull
    private String description;
    @ColumnInfo(name="product_quantity")
    @NonNull
    private int quantity;
    @ColumnInfo(name="product_price")
    @NonNull
    private float price;

    public ProductEntity()
    {
        //Required by room
    }

    @Ignore
    public ProductEntity(@NonNull String title, @NonNull String description, int quantity, float price)
    {
        this.title = title;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
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
    public String getTitle()
    {
        return title;
    }

    public void setTitle(@NonNull String title)
    {
        this.title = title;
    }

    @NonNull
    public String getDescription()
    {
        return description;
    }

    public void setDescription(@NonNull String description)
    {
        this.description = description;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public float getPrice()
    {
        return price;
    }

    public void setPrice(float price)
    {
        this.price = price;
    }
}
