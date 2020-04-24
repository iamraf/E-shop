package com.github.h01d.eshop.ui.customers;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.h01d.eshop.data.database.entity.CustomerEntity;
import com.github.h01d.eshop.databinding.CustomerItemBinding;

import java.util.ArrayList;
import java.util.List;

public class CustomersAdapter extends RecyclerView.Adapter<CustomersAdapter.CustomersViewHolder>
{
    private List<CustomerEntity> data;

    private CustomersAdapterListener listener;

    public CustomersAdapter(CustomersAdapterListener listener)
    {
        this.listener = listener;

        data = new ArrayList<>();
    }

    public void setData(List<CustomerEntity> data)
    {
        this.data.clear();
        this.data.addAll(data);

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CustomersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        CustomerItemBinding binding = CustomerItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new CustomersViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomersViewHolder holder, int position)
    {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount()
    {
        return data.size();
    }

    class CustomersViewHolder extends RecyclerView.ViewHolder
    {
        private final CustomerItemBinding binding;

        CustomersViewHolder(@NonNull CustomerItemBinding binding)
        {
            super(binding.getRoot());

            this.binding = binding;
        }

        void bind(CustomerEntity item)
        {
            binding.getRoot().setOnClickListener(v -> listener.onClicked(binding.getCustomer()));
            binding.setCustomer(item);
            binding.executePendingBindings();
        }
    }

    interface CustomersAdapterListener
    {
        void onClicked(CustomerEntity customer);
    }
}
