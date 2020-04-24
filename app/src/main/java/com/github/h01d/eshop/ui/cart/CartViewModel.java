package com.github.h01d.eshop.ui.cart;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.github.h01d.eshop.data.database.DatabaseManager;
import com.github.h01d.eshop.data.database.dao.CartDao;
import com.github.h01d.eshop.data.database.dao.CustomersDao;
import com.github.h01d.eshop.data.database.dao.ProductsDao;
import com.github.h01d.eshop.data.database.dao.SaleItemsDao;
import com.github.h01d.eshop.data.database.dao.SalesDao;
import com.github.h01d.eshop.data.database.entity.SaleEntity;
import com.github.h01d.eshop.data.database.entity.SaleItemEntity;
import com.github.h01d.eshop.data.models.Cart;
import com.github.h01d.eshop.data.database.entity.CartEntity;
import com.github.h01d.eshop.data.database.entity.CustomerEntity;

import java.util.Date;
import java.util.List;

public class CartViewModel extends AndroidViewModel
{
    private CartDao cartDao;
    private CustomersDao customerDao;
    private SalesDao salesDao;
    private SaleItemsDao saleItemsDao;
    private ProductsDao productsDao;

    private LiveData<List<CartEntity>> cart;

    public CartViewModel(@NonNull Application application)
    {
        super(application);

        DatabaseManager db = DatabaseManager.getDatabase(application);

        cartDao = db.getCartDao();
        customerDao = db.getCustomerDao();
        salesDao = db.getSalesDao();
        saleItemsDao = db.getSaleItemsDao();
        productsDao = db.getProductsDao();

        cart = cartDao.getCart();
    }

    public LiveData<List<CartEntity>> getCart()
    {
        return cart;
    }

    public LiveData<List<Cart>> getJoinedCart()
    {
        return cartDao.getJoinedCart();
    }

    public void insert(CartEntity cart)
    {
        cartDao.insert(cart);
    }

    public void delete(CartEntity cart)
    {
        cartDao.delete(cart);
    }

    public boolean buy(CustomerEntity customer, List<Cart> cartList)
    {
        int id = customerDao.getCustomerId(customer.getFullname());

        if(id == 0)
        {
            id = (int) customerDao.insert(customer);
        }

        int saleId = (int) salesDao.insert(new SaleEntity(id, new Date()));

        for(Cart cart : cartList)
        {
            saleItemsDao.insert(new SaleItemEntity(saleId, cart.getCart().getProductId(), cart.getCart().getQuantity()));

            cart.getProduct().setQuantity(cart.getProduct().getQuantity() - cart.getCart().getQuantity());
            productsDao.update(cart.getProduct());

            cartDao.delete(cart.getCart());
        }

        return true;
    }
}
