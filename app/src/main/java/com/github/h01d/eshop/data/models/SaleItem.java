package com.github.h01d.eshop.data.models;

import androidx.room.Embedded;

import com.github.h01d.eshop.data.database.entity.ProductEntity;
import com.github.h01d.eshop.data.database.entity.SaleItemEntity;

public class SaleItem
{
    @Embedded
    private SaleItemEntity saleItemEntity;

    @Embedded
    private ProductEntity productEntity;

    public SaleItem()
    {
        //Required by room
    }

    public SaleItemEntity getSaleItemEntity()
    {
        return saleItemEntity;
    }

    public void setSaleItemEntity(SaleItemEntity saleItemEntity)
    {
        this.saleItemEntity = saleItemEntity;
    }

    public ProductEntity getProductEntity()
    {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity)
    {
        this.productEntity = productEntity;
    }
}
