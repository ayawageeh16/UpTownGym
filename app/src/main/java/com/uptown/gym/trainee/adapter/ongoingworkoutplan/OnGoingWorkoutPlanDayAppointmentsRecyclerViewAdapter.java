package com.uptown.gym.trainee.adapter.ongoingworkoutplan;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.uptown.gym.trainee.R;
import com.uptown.gym.trainee.databinding.ItemOngoingWorkoutPlanDayAppointmentCardBinding;
import com.uptown.gym.trainee.listener.OnGoingWorkoutPlanListener;
import com.uptown.gym.trainee.model.ongoingworkoutplan.OnGoingWorkoutPlanDayTime;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class OnGoingWorkoutPlanDayAppointmentsRecyclerViewAdapter extends RecyclerView.Adapter<OnGoingWorkoutPlanDayAppointmentsRecyclerViewAdapter.ActiveProgramDayHourViewHolder> {

    private List<OnGoingWorkoutPlanDayTime> onGoingWorkoutPlanDayTimes = new ArrayList<>();
    private OnGoingWorkoutPlanListener.OnGoingWorkoutPlanDayAppointmentListener listener;


    public void setOnGoingWorkoutPlanDayTimes(List<OnGoingWorkoutPlanDayTime> onGoingWorkoutPlanDayTimes) {
        this.onGoingWorkoutPlanDayTimes = onGoingWorkoutPlanDayTimes;
        notifyDataSetChanged();
    }

    public void setListener(OnGoingWorkoutPlanListener.OnGoingWorkoutPlanDayAppointmentListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ActiveProgramDayHourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemOngoingWorkoutPlanDayAppointmentCardBinding dataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_ongoing_workout_plan_day_appointment_card,
                parent,
                false);
        return new ActiveProgramDayHourViewHolder(dataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ActiveProgramDayHourViewHolder holder, int position) {
        holder.itemActiveProgramDayHourCardBinding.setOnGoingPlanDayTime(onGoingWorkoutPlanDayTimes.get(position));
        holder.itemActiveProgramDayHourCardBinding.setListener(listener);
    }

    @Override
    public int getItemCount() {
        return onGoingWorkoutPlanDayTimes.size();
    }

    static class ActiveProgramDayHourViewHolder extends RecyclerView.ViewHolder {

        ItemOngoingWorkoutPlanDayAppointmentCardBinding itemActiveProgramDayHourCardBinding;

        ActiveProgramDayHourViewHolder(@NonNull ItemOngoingWorkoutPlanDayAppointmentCardBinding itemActiveProgramDayHourCardBinding) {
            super(itemActiveProgramDayHourCardBinding.getRoot());
            this.itemActiveProgramDayHourCardBinding = itemActiveProgramDayHourCardBinding;
        }

    }

}
