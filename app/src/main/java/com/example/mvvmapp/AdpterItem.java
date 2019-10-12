package com.example.mvvmapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmapp.Model.NameModel;

import java.util.ArrayList;

public class AdpterItem extends RecyclerView.Adapter<AdpterItem.ViewHolderItem> {
    private ArrayList<NameModel> nameModels;

    public AdpterItem(ArrayList<NameModel> nameModels) {
        this.nameModels = nameModels;
    }

    @NonNull
    @Override
    public ViewHolderItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout
                , parent, false);
        return new ViewHolderItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderItem holder, int position) {

        holder.itemView.setTag(nameModels.get(position));
        holder.textViewName.setText(nameModels.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return nameModels.size();
    }

    class ViewHolderItem extends RecyclerView.ViewHolder {
        TextView textViewName;

        public ViewHolderItem(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.tv_name_item);
        }
    }
}
