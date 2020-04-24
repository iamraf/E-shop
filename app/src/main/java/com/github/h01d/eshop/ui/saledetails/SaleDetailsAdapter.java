package com.github.h01d.eshop.ui.saledetails;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.h01d.eshop.data.models.SaleItem;
import com.github.h01d.eshop.databinding.SaleDetailsItemBinding;

import java.util.ArrayList;
import java.util.List;

public class SaleDetailsAdapter extends RecyclerView.Adapter<SaleDetailsAdapter.SaleDetailsViewHolder>
{
    private List<SaleItem> data;

    public SaleDetailsAdapter()
    {
        data = new ArrayList<>();
    }

    public void setData(List<SaleItem> data)
    {
        this.data.clear();
        this.data.addAll(data);

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SaleDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        SaleDetailsItemBinding binding = SaleDetailsItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new SaleDetailsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SaleDetailsViewHolder holder, int position)
    {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount()
    {
        return data.size();
    }

    class SaleDetailsViewHolder extends RecyclerView.ViewHolder
    {
        private final SaleDetailsItemBinding binding;

        SaleDetailsViewHolder(@NonNull SaleDetailsItemBinding binding)
        {
            super(binding.getRoot());

            this.binding = binding;
        }

        void bind(SaleItem item)
        {
            binding.setItem(item);
            binding.executePendingBindings();
        }
    }
}
