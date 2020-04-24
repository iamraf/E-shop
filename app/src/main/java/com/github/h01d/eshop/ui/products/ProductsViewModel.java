package com.github.h01d.eshop.ui.products;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.github.h01d.eshop.data.database.DatabaseManager;
import com.github.h01d.eshop.data.database.dao.CartDao;
import com.github.h01d.eshop.data.database.dao.ProductsDao;
import com.github.h01d.eshop.data.database.dao.SaleItemsDao;
import com.github.h01d.eshop.data.database.entity.CartEntity;
import com.github.h01d.eshop.data.database.entity.ProductEntity;
import com.github.h01d.eshop.data.database.entity.SaleItemEntity;

import java.util.List;

public class ProductsViewModel extends AndroidViewModel
{
    private ProductsDao productsDao;
    private CartDao cartDao;
    private SaleItemsDao saleItemsDao;

    private LiveData<List<ProductEntity>> products;

    public ProductsViewModel(@NonNull Application application)
    {
        super(application);

        DatabaseManager db = DatabaseManager.getDatabase(application);

        productsDao = db.getProductsDao();
        cartDao = db.getCartDao();
        saleItemsDao = db.getSaleItemsDao();

        products = productsDao.getProducts();
    }

    public LiveData<List<ProductEntity>> getProducts()
    {
        return products;
    }

    public LiveData<List<SaleItemEntity>> getItemsSold(int id)
    {
        return saleItemsDao.getItemsSold(id);
    }

    public void insert(ProductEntity product)
    {
        productsDao.insert(product);
    }

    public void delete(ProductEntity product)
    {
        productsDao.delete(product);
    }

    public void update(ProductEntity product)
    {
        productsDao.update(product);
    }

    public boolean insertCart(CartEntity cart)
    {
        CartEntity tmp = cartDao.getSyncCartProductId(cart.getProductId());

        if(tmp == null)
        {
            cartDao.insert(cart);
        }
        else
        {
            ProductEntity productEntity = productsDao.getProduct(cart.getProductId());

            if(tmp.getQuantity() + cart.getQuantity() > productEntity.getQuantity())
            {
                return false;
            }
            else
            {
                tmp.setQuantity(tmp.getQuantity() + cart.getQuantity());
                cartDao.update(tmp);
            }
        }

        return true;
    }
}
