package com.github.h01d.eshop.ui.customers;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.h01d.eshop.R;
import com.github.h01d.eshop.databinding.CustomersFragmentBinding;

public class CustomersFragment extends Fragment
{
    private CustomersFragmentBinding mDataBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        mDataBinding = DataBindingUtil.inflate(inflater, R.layout.customers_fragment, container, false);

        mDataBinding.fCustomersRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mDataBinding.fCustomersRecycler.setHasFixedSize(true);
        mDataBinding.fCustomersRecycler.setAdapter(new CustomersAdapter());

        return mDataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        CustomersViewModel mViewModel = new ViewModelProvider(this).get(CustomersViewModel.class);

        mViewModel.getCustomers().observe(getViewLifecycleOwner(), customers ->
        {
            if(customers.isEmpty())
            {
                mDataBinding.fCustomersRecycler.setVisibility(View.GONE);
                mDataBinding.fCustomersMessage.setVisibility(View.VISIBLE);
            }
            else
            {
                mDataBinding.fCustomersRecycler.setVisibility(View.VISIBLE);
                mDataBinding.fCustomersMessage.setVisibility(View.GONE);

                ((CustomersAdapter) mDataBinding.fCustomersRecycler.getAdapter()).setData(customers);
            }
        });
    }
}
