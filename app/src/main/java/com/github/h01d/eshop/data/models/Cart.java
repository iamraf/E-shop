package com.github.h01d.eshop.data.models;

import androidx.room.Embedded;

import com.github.h01d.eshop.data.database.entity.CartEntity;
import com.github.h01d.eshop.data.database.entity.ProductEntity;

public class Cart
{
    @Embedded
    private CartEntity cart;

    @Embedded
    private ProductEntity product;

    public Cart()
    {
        //Required by room
    }

    public void setCart(CartEntity cart)
    {
        this.cart = cart;
    }

    public void setProduct(ProductEntity product)
    {
        this.product = product;
    }

    public CartEntity getCart()
    {
        return cart;
    }

    public ProductEntity getProduct()
    {
        return product;
    }
}
