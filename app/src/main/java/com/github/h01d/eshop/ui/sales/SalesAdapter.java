package com.github.h01d.eshop.ui.sales;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.h01d.eshop.data.models.Sale;
import com.github.h01d.eshop.databinding.SalesItemBinding;

import java.util.ArrayList;
import java.util.List;

public class SalesAdapter extends RecyclerView.Adapter<SalesAdapter.SalesViewHolder>
{
    private List<Sale> data;

    private SalesAdapterListener listener;

    public SalesAdapter(SalesAdapterListener listener)
    {
        this.listener = listener;

        data = new ArrayList<>();
    }

    public void setData(List<Sale> data)
    {
        this.data.clear();
        this.data.addAll(data);

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SalesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        SalesItemBinding binding = SalesItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new SalesViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SalesViewHolder holder, int position)
    {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount()
    {
        return data.size();
    }

    class SalesViewHolder extends RecyclerView.ViewHolder
    {
        private final SalesItemBinding binding;

        SalesViewHolder(@NonNull SalesItemBinding binding)
        {
            super(binding.getRoot());

            this.binding = binding;
        }

        void bind(Sale item)
        {
            binding.getRoot().setOnClickListener(v -> listener.onClicked(binding.getSale()));
            binding.setSale(item);
            binding.executePendingBindings();
        }
    }

    interface SalesAdapterListener
    {
        void onClicked(Sale sale);
    }
}
