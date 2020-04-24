package com.github.h01d.eshop.ui.products;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.h01d.eshop.data.database.entity.ProductEntity;
import com.github.h01d.eshop.databinding.ProductItemBinding;

import java.util.ArrayList;
import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>
{
    private List<ProductEntity> data;

    private ProductsAdapterListener listener;

    public ProductsAdapter(ProductsAdapterListener listener)
    {
        this.listener = listener;

        data = new ArrayList<>();
    }

    public void setData(List<ProductEntity> data)
    {
        this.data.clear();
        this.data.addAll(data);

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        ProductItemBinding binding = ProductItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new ProductsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsViewHolder holder, int position)
    {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount()
    {
        return data.size();
    }

    class ProductsViewHolder extends RecyclerView.ViewHolder
    {
        private final ProductItemBinding binding;

        ProductsViewHolder(@NonNull ProductItemBinding binding)
        {
            super(binding.getRoot());

            this.binding = binding;
        }

        void bind(ProductEntity item)
        {
            binding.getRoot().setOnClickListener(v -> listener.onClicked(binding.getProduct()));
            binding.setProduct(item);
            binding.executePendingBindings();
        }
    }

    interface ProductsAdapterListener
    {
        void onClicked(ProductEntity product);
    }
}
