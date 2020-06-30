package com.uptown.gym.trainee.adapter.trainee;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.uptown.gym.trainee.R;
import com.uptown.gym.trainee.databinding.ItemFitnessTestBinding;
import com.uptown.gym.trainee.listener.TraineeListener;
import com.uptown.gym.trainee.model.fitnesstest.FitnessTest;
import com.uptown.gym.trainee.util.Utils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class FitnessTestsRecyclerViewAdapter extends RecyclerView.Adapter<FitnessTestsRecyclerViewAdapter.FitnessTestViewHolder> {


    private TraineeListener.FitnessTestListener listener;
    private List<FitnessTest> fitnessTests = new ArrayList<>();


    public void setListener(TraineeListener.FitnessTestListener listener) {
        this.listener = listener;
    }

    public void setFitnessTests(List<FitnessTest> fitnessTests) {
        this.fitnessTests = fitnessTests;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FitnessTestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFitnessTestBinding itemFitnessTestBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_fitness_test, parent, false);
        return new FitnessTestViewHolder(itemFitnessTestBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull FitnessTestViewHolder holder, int position) {
        holder.itemFitnessTestBinding.setListener(listener);
        holder.itemFitnessTestBinding.setFitnesstest(fitnessTests.get(position));
        holder.itemFitnessTestBinding.fitnessTestHistory.setText(Utils.convertLongDateToString(fitnessTests.get(position).getCreationTime()));
    }

    @Override
    public int getItemCount() {
        return fitnessTests.size();
    }

    static class FitnessTestViewHolder extends RecyclerView.ViewHolder {
        ItemFitnessTestBinding itemFitnessTestBinding;

        public FitnessTestViewHolder(@NonNull ItemFitnessTestBinding itemFitnessTestBinding) {
            super(itemFitnessTestBinding.getRoot());
            this.itemFitnessTestBinding = itemFitnessTestBinding;
        }
    }
}
