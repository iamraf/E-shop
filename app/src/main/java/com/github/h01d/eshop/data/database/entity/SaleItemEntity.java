package com.github.h01d.eshop.data.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "sale_items", foreignKeys = {@ForeignKey(onDelete = CASCADE, onUpdate = CASCADE, entity = SaleEntity.class, parentColumns = "sale_id", childColumns = "item_sale_id"),
        @ForeignKey(onDelete = CASCADE, onUpdate = CASCADE, entity = ProductEntity.class, parentColumns = "product_id", childColumns = "item_product_id")})
public class SaleItemEntity implements Serializable
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="item_id")
    @NonNull
    private int id;
    @ColumnInfo(name="item_sale_id")
    @NonNull
    private int saleId;
    @ColumnInfo(name="item_product_id")
    @NonNull
    private int productId;
    @ColumnInfo(name="item_quantity")
    @NonNull
    private int quantity;

    public SaleItemEntity()
    {
        //Required by room
    }

    @Ignore
    public SaleItemEntity(int saleId, int productId, int quantity)
    {
        this.saleId = saleId;
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

    public int getSaleId()
    {
        return saleId;
    }

    public void setSaleId(int saleId)
    {
        this.saleId = saleId;
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
