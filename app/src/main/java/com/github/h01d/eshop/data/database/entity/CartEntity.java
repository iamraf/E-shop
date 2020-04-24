package com.github.h01d.eshop.data.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "cart", foreignKeys = {@ForeignKey(onDelete = CASCADE, onUpdate = CASCADE, entity = ProductEntity.class, parentColumns = "product_id", childColumns = "cart_product_id")})
public class CartEntity implements Serializable
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="cart_id")
    @NonNull
    private int id;
    @ColumnInfo(name="cart_product_id")
    @NonNull
    private int productId;
    @ColumnInfo(name="cart_quantity")
    @NonNull
    private int quantity;

    public CartEntity()
    {
        //Required by room
    }

    @Ignore
    public CartEntity(int productId, int quantity)
    {
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getProductId()
    {
        return productId;
    }

    public void setProductId(int productId)
    {
        this.productId = productId;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
}
