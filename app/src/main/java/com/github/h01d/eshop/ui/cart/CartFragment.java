package com.github.h01d.eshop.ui.cart;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.github.h01d.eshop.R;
import com.github.h01d.eshop.data.models.Cart;
import com.github.h01d.eshop.data.database.entity.CustomerEntity;
import com.github.h01d.eshop.databinding.CartFragmentBinding;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment implements CartAdapter.CartAdapterListener
{
    private CartViewModel mViewModel;
    private CartFragmentBinding mDataBinding;

    private List<Cart> mCartList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        mDataBinding = DataBindingUtil.inflate(inflater, R.layout.cart_fragment, container, false);

        mCartList = new ArrayList<>();

        mDataBinding.fCartRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mDataBinding.fCartRecycler.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        mDataBinding.fCartRecycler.setHasFixedSize(true);
        mDataBinding.fCartRecycler.setAdapter(new CartAdapter(this));

        mDataBinding.fCartFab.setOnClickListener(v ->
        {
            final LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setOrientation(LinearLayout.VERTICAL);

            final EditText fullname = new EditText(getContext());
            fullname.setHint("Enter full name");

            final EditText address = new EditText(getContext());
            address.setHint("Enter address");

            linearLayout.addView(fullname);
            linearLayout.addView(address);

            new AlertDialog.Builder(getContext())
                    .setTitle("Enter Details")
                    .setView(linearLayout)
                    .setPositiveButton("Buy", (dialogInterface, i) ->
                    {
                        if(fullname.length() == 0 || address.length() == 0)
                        {
                            Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            if(mViewModel.buy(new CustomerEntity(fullname.getText().toString(), address.getText().toString()), mCartList))
                            {
                                Toast.makeText(getContext(), "Purchase successful", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(getContext(), "Purchase failed", Toast.LENGTH_SHORT).show();
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

        mViewModel = new ViewModelProvider(this).get(CartViewModel.class);

        mViewModel.getJoinedCart().observe(getViewLifecycleOwner(), cart ->
        {
            if(cart.isEmpty())
            {
                mDataBinding.fCartLayout.setVisibility(View.GONE);
                mDataBinding.fCartFab.setVisibility(View.GONE);
                mDataBinding.fCartMessage.setVisibility(View.VISIBLE);
            }
            else
            {
                mCartList.clear();
                mCartList.addAll(cart);

                mDataBinding.fCartLayout.setVisibility(View.VISIBLE);
                mDataBinding.fCartFab.setVisibility(View.VISIBLE);
                mDataBinding.fCartMessage.setVisibility(View.GONE);

                ((CartAdapter) mDataBinding.fCartRecycler.getAdapter()).setData(cart);

                float total = 0;

                for(Cart tmp : cart)
                {
                    total += tmp.getCart().getQuantity() * tmp.getProduct().getPrice();
                }

                mDataBinding.setTotal(total);
            }
        });
    }

    @Override
    public void onClicked(Cart cart)
    {
        new AlertDialog.Builder(getContext())
                .setTitle("Remove")
                .setMessage("Do you want to remove this from cart?")
                .setPositiveButton("YES", (dialogInterface, i) -> mViewModel.delete(cart.getCart()))
                .setNegativeButton("NO", null)
                .show();
    }
}
