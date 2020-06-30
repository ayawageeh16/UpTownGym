package com.uptown.gym.trainee.adapter.trainee;

import android.view.LayoutInflater;
import android.view.ViewGroup;


import com.uptown.gym.trainee.R;
import com.uptown.gym.trainee.databinding.ItemInbodyBinding;
import com.uptown.gym.trainee.listener.TraineeListener;
import com.uptown.gym.trainee.model.inbody.InBody;
import com.uptown.gym.trainee.util.Utils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class InBodiesRecyclerViewAdapter extends RecyclerView.Adapter<InBodiesRecyclerViewAdapter.InBodiesViewHolder> {

    private List<InBody> inBodies = new ArrayList<>();
    private TraineeListener.InBodiesListener listener;

    public void setInBodies(List<InBody> inBodies) {
        this.inBodies = inBodies;
        notifyDataSetChanged();
    }

    public void setListener(TraineeListener.InBodiesListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public InBodiesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemInbodyBinding itemInbodyBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_inbody, parent, false);
        return new InBodiesViewHolder(itemInbodyBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull InBodiesViewHolder holder, int position) {
        holder.itemInbodyBinding.setListener(listener);
        holder.itemInbodyBinding.setInbody(inBodies.get(position));
        holder.itemInbodyBinding.inbodyHistory.setText(Utils.convertLongDateToString(inBodies.get(position).getCreationTime()));
    }

    @Override
    public int getItemCount() {
        return inBodies.size();
    }

    static class InBodiesViewHolder extends RecyclerView.ViewHolder {

        ItemInbodyBinding itemInbodyBinding;

        public InBodiesViewHolder(@NonNull ItemInbodyBinding itemInbodyBinding) {
            super(itemInbodyBinding.getRoot());
            this.itemInbodyBinding = itemInbodyBinding;
        }
    }
}
