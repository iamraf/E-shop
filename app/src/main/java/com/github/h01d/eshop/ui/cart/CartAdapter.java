package com.github.h01d.eshop.ui.cart;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.h01d.eshop.data.models.Cart;
import com.github.h01d.eshop.databinding.CartItemBinding;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>
{
    private List<Cart> data;

    private CartAdapterListener listener;

    public CartAdapter(CartAdapterListener listener)
    {
        this.listener = listener;

        data = new ArrayList<>();
    }

    public void setData(List<Cart> data)
    {
        this.data.clear();
        this.data.addAll(data);

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        CartItemBinding binding = CartItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new CartViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position)
    {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount()
    {
        return data.size();
    }

    class CartViewHolder extends RecyclerView.ViewHolder
    {
        private final CartItemBinding binding;

        CartViewHolder(@NonNull CartItemBinding binding)
        {
            super(binding.getRoot());

            this.binding = binding;
        }

        void bind(Cart item)
        {
            binding.getRoot().setOnClickListener(v -> listener.onClicked(binding.getCart()));
            binding.setCart(item);
            binding.executePendingBindings();
        }
    }

    interface CartAdapterListener
    {
        void onClicked(Cart cart);
    }
}
