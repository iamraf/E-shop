package com.github.h01d.eshop.ui.productdetails;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.github.h01d.eshop.R;
import com.github.h01d.eshop.data.database.entity.CartEntity;
import com.github.h01d.eshop.data.database.entity.ProductEntity;
import com.github.h01d.eshop.data.database.entity.SaleItemEntity;
import com.github.h01d.eshop.databinding.ProductDetailsFragmentBinding;
import com.github.h01d.eshop.ui.products.ProductsViewModel;

public class ProductDetailsFragment extends Fragment
{
    private ProductsViewModel mViewModel;
    private ProductDetailsFragmentBinding mDataBinding;

    private ProductEntity mProduct;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        setHasOptionsMenu(true);

        mDataBinding = DataBindingUtil.inflate(inflater, R.layout.product_details_fragment, container, false);

        mProduct = ProductDetailsFragmentArgs.fromBundle(getArguments()).getProduct();
        mDataBinding.setProduct(mProduct);

        mDataBinding.fProductdetailsFab.setOnClickListener(v ->
        {
            final EditText input = new EditText(getContext());
            input.setInputType(InputType.TYPE_CLASS_NUMBER);

            new AlertDialog.Builder(getContext())
                    .setTitle("Enter Quantity")
                    .setView(input)
                    .setPositiveButton("Add", (dialogInterface, i) ->
                    {
                        if(input.length() == 0)
                        {
                            Toast.makeText(getContext(), "Please fill the field", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            int quantity = Integer.parseInt(input.getText().toString());

                            if(quantity == 0 || quantity > mProduct.getQuantity())
                            {
                                Toast.makeText(getContext(), "Quantity unavailable", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                if(mViewModel.insertCart(new CartEntity(mProduct.getId(), quantity)))
                                {
                                    Toast.makeText(getContext(), "Item added to cart", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(getContext(), "Could not add item", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    })
                    .setNegativeButton("Dismiss", null)
                    .show();
        });

        return mDataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(ProductsViewModel.class);
        mViewModel.getItemsSold(mProduct.getId()).observe(getViewLifecycleOwner(), items ->
        {
            int sold = 0;

            for(SaleItemEntity item : items)
            {
                sold += item.getQuantity();
            }

            mDataBinding.setSold(sold);
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.productdetails_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if(item.getItemId() == R.id.m_productdetails_delete)
        {
            new AlertDialog.Builder(getContext())
                    .setTitle("Delete")
                    .setMessage("Are you sure you want to delete this product?")
                    .setPositiveButton("YES", (dialogInterface, i) ->
                    {
                        mViewModel.delete(mProduct);
                        Navigation.findNavController(mDataBinding.getRoot()).popBackStack();
                    })
                    .setNegativeButton("NO", null)
                    .show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
