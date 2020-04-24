package com.github.h01d.eshop.ui.add;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.h01d.eshop.R;
import com.github.h01d.eshop.data.database.entity.ProductEntity;
import com.github.h01d.eshop.databinding.AddProductFragmentBinding;
import com.github.h01d.eshop.ui.products.ProductsViewModel;

public class AddProductFragment extends Fragment
{
    private ProductsViewModel mViewModel;
    private AddProductFragmentBinding mDataBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        mDataBinding = DataBindingUtil.inflate(inflater, R.layout.add_product_fragment, container, false);

        mDataBinding.fAddproductFab.setOnClickListener(v ->
        {
            if(mDataBinding.fAddproductTitle.length() == 0 || mDataBinding.fAddproductDescription.length() == 0 || mDataBinding.fAddproductQuantity.length() == 0 || mDataBinding.fAddproductPrice.length() == 0)
            {
                Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            }
            else
            {
                mViewModel.insert(new ProductEntity(mDataBinding.fAddproductTitle.getText().toString().trim(), mDataBinding.fAddproductDescription.getText().toString().trim(), Integer.parseInt(mDataBinding.fAddproductQuantity.getText().toString()), Float.parseFloat(mDataBinding.fAddproductPrice.getText().toString())));
                Navigation.findNavController(mDataBinding.getRoot()).popBackStack();
            }
        });

        return mDataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(ProductsViewModel.class);
    }
}
