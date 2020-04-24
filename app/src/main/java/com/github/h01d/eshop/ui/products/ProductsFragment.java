package com.github.h01d.eshop.ui.products;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.github.h01d.eshop.R;
import com.github.h01d.eshop.data.database.entity.ProductEntity;
import com.github.h01d.eshop.databinding.ProductsFragmentBinding;

public class ProductsFragment extends Fragment implements ProductsAdapter.ProductsAdapterListener
{
    private ProductsViewModel mViewModel;
    private ProductsFragmentBinding mDataBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        setHasOptionsMenu(true);

        mDataBinding = DataBindingUtil.inflate(inflater, R.layout.products_fragment, container, false);

        mDataBinding.fProductsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mDataBinding.fProductsRecycler.setHasFixedSize(true);
        mDataBinding.fProductsRecycler.setAdapter(new ProductsAdapter(this));

        mDataBinding.fProductsFab.setOnClickListener(v -> Navigation.findNavController(mDataBinding.getRoot()).navigate(R.id.productsToAdd));

        return mDataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(ProductsViewModel.class);

        mViewModel.getProducts().observe(getViewLifecycleOwner(), products ->
        {
            if(products.isEmpty())
            {
                mDataBinding.fProductsRecycler.setVisibility(View.GONE);
                mDataBinding.fProductsMessage.setVisibility(View.VISIBLE);
            }
            else
            {
                mDataBinding.fProductsRecycler.setVisibility(View.VISIBLE);
                mDataBinding.fProductsMessage.setVisibility(View.GONE);

                ((ProductsAdapter) mDataBinding.fProductsRecycler.getAdapter()).setData(products);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.product_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if(item.getItemId() == R.id.m_product_cart)
        {
            Navigation.findNavController(mDataBinding.getRoot()).navigate(R.id.productsToCart);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClicked(ProductEntity product)
    {
        Navigation.findNavController(mDataBinding.getRoot()).navigate(ProductsFragmentDirections.productToDetails(product));
    }
}
