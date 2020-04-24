package com.github.h01d.eshop.ui.sales;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.h01d.eshop.R;
import com.github.h01d.eshop.data.models.Sale;
import com.github.h01d.eshop.databinding.SalesFragmentBinding;

public class SalesFragment extends Fragment implements SalesAdapter.SalesAdapterListener
{
    private SalesViewModel mViewModel;
    private SalesFragmentBinding mDataBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        mDataBinding = DataBindingUtil.inflate(inflater, R.layout.sales_fragment, container, false);

        mDataBinding.fSalesRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mDataBinding.fSalesRecycler.setHasFixedSize(true);
        mDataBinding.fSalesRecycler.setAdapter(new SalesAdapter(this));

        return mDataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(SalesViewModel.class);

        mViewModel.getSales().observe(getViewLifecycleOwner(), sales ->
        {
            if(sales.isEmpty())
            {
                mDataBinding.fSalesRecycler.setVisibility(View.GONE);
                mDataBinding.fSalesMessage.setVisibility(View.VISIBLE);
            }
            else
            {
                mDataBinding.fSalesRecycler.setVisibility(View.VISIBLE);
                mDataBinding.fSalesMessage.setVisibility(View.GONE);

                ((SalesAdapter) mDataBinding.fSalesRecycler.getAdapter()).setData(sales);
            }
        });
    }

    @Override
    public void onClicked(Sale sale)
    {
        Navigation.findNavController(mDataBinding.getRoot()).navigate(SalesFragmentDirections.salesToDetails(sale));
    }
}
