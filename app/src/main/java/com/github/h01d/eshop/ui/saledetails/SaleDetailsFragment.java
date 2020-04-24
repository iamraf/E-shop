package com.github.h01d.eshop.ui.saledetails;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.h01d.eshop.R;
import com.github.h01d.eshop.data.models.Cart;
import com.github.h01d.eshop.data.models.Sale;
import com.github.h01d.eshop.data.models.SaleItem;
import com.github.h01d.eshop.databinding.SaleDetailsFragmentBinding;

public class SaleDetailsFragment extends Fragment
{
    private SaleDetailsViewModel mViewModel;
    private SaleDetailsFragmentBinding mDataBinding;

    private Sale mSale;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        mDataBinding = DataBindingUtil.inflate(inflater, R.layout.sale_details_fragment, container, false);

        mSale = SaleDetailsFragmentArgs.fromBundle(getArguments()).getSale();
        mDataBinding.setSale(mSale);

        mDataBinding.fSaledetailsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mDataBinding.fSaledetailsRecycler.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        mDataBinding.fSaledetailsRecycler.setHasFixedSize(true);
        mDataBinding.fSaledetailsRecycler.setAdapter(new SaleDetailsAdapter());

        return mDataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(SaleDetailsViewModel.class);

        mViewModel.getItems(mSale.getSale().getId()).observe(getViewLifecycleOwner(), items ->
        {
            ((SaleDetailsAdapter) mDataBinding.fSaledetailsRecycler.getAdapter()).setData(items);

            float total = 0;

            for(SaleItem tmp : items)
            {
                total += tmp.getSaleItemEntity().getQuantity() * tmp.getProductEntity().getPrice();
            }

            mDataBinding.setTotal(total);
        });
    }
}
