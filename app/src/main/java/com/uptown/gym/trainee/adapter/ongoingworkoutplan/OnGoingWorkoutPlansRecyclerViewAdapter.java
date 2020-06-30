package com.uptown.gym.trainee.adapter.ongoingworkoutplan;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uptown.gym.trainee.R;
import com.uptown.gym.trainee.databinding.ItemOngoingWorkoutPlanBinding;
import com.uptown.gym.trainee.listener.OnGoingWorkoutPlanListener;
import com.uptown.gym.trainee.model.ongoingworkoutplan.OnGoingWorkoutPlan;
import com.uptown.gym.trainee.util.Utils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

public class OnGoingWorkoutPlansRecyclerViewAdapter extends RecyclerView.Adapter<OnGoingWorkoutPlansRecyclerViewAdapter.ActiveProgramsViewHolder> {


    private List<OnGoingWorkoutPlan> onGoingWorkoutPlans = new ArrayList<>();
    private OnGoingWorkoutPlanListener.OnGoingWorkoutPlansRecyclerViewListener listener;
    private final Context context;


    public OnGoingWorkoutPlansRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setOngoingWorkoutPlans(List<OnGoingWorkoutPlan> onGoingWorkoutPlans) {
        this.onGoingWorkoutPlans = onGoingWorkoutPlans;
        notifyDataSetChanged();
    }

    public void setListener(OnGoingWorkoutPlanListener.OnGoingWorkoutPlansRecyclerViewListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ActiveProgramsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemOngoingWorkoutPlanBinding itemOngoingWorkoutPlanBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_ongoing_workout_plan,
                parent,
                false);
        return new ActiveProgramsViewHolder(itemOngoingWorkoutPlanBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ActiveProgramsViewHolder holder, int position) {
        holder.bind(onGoingWorkoutPlans.get(position));

        switch (onGoingWorkoutPlans.get(position).getWorkoutPlan().getCategory()) {
            case "Build Muscle":
                holder.itemOngoingWorkoutPlanBinding.mainLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.build_muscle));
                break;
            case "Gain Strength":
                holder.itemOngoingWorkoutPlanBinding.mainLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.gain_strength));
                break;
            case "Get Fit":
                holder.itemOngoingWorkoutPlanBinding.mainLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.get_fit));
                break;
            case "Weight Loss":
                holder.itemOngoingWorkoutPlanBinding.mainLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.weight_oss));
                break;
            default:
                holder.itemOngoingWorkoutPlanBinding.mainLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.performance));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return onGoingWorkoutPlans.size();
    }

    class ActiveProgramsViewHolder extends RecyclerView.ViewHolder {

        ItemOngoingWorkoutPlanBinding itemOngoingWorkoutPlanBinding;

        ActiveProgramsViewHolder(@NonNull ItemOngoingWorkoutPlanBinding itemOngoingWorkoutPlanBinding) {
            super(itemOngoingWorkoutPlanBinding.getRoot());
            this.itemOngoingWorkoutPlanBinding = itemOngoingWorkoutPlanBinding;
        }

        void bind(OnGoingWorkoutPlan onGoingWorkoutPlan) {
            itemOngoingWorkoutPlanBinding.ongoingWorkoutPlanNameTextView.setText(onGoingWorkoutPlan.getWorkoutPlan().getName());

            // convert long to date and time
            itemOngoingWorkoutPlanBinding.ongoingWorkoutPlanStartTextview.setText(context.getString(R.string.from) + context.getString(R.string.colon) + Utils.convertLongDateToString(onGoingWorkoutPlan.getStartTime()));
            itemOngoingWorkoutPlanBinding.ongoingWorkoutPlanEndTextview.setText(context.getString(R.string.to) + context.getString(R.string.colon) + Utils.convertLongDateToString(onGoingWorkoutPlan.getEndTime()));

            itemOngoingWorkoutPlanBinding.ongoingWorkoutPlanDescriptionTextview.setText(onGoingWorkoutPlan.getWorkoutPlan().getDescription());
            itemOngoingWorkoutPlanBinding.ongoingWorkoutPlanTargetTextView.setText(onGoingWorkoutPlan.getTarget());
            itemOngoingWorkoutPlanBinding.ongoingWorkoutPlanLevelTextview.setText(onGoingWorkoutPlan.getWorkoutPlan().getFitnessLevel());
            itemOngoingWorkoutPlanBinding.ongoingWorkoutPlanCategoryTextview.setText(onGoingWorkoutPlan.getWorkoutPlan().getCategory());

            // card expand animation
            itemOngoingWorkoutPlanBinding.ongoingWorkoutPlanCardExpand.setOnClickListener(view -> {
                if (itemOngoingWorkoutPlanBinding.expandedLayout.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(itemOngoingWorkoutPlanBinding.ongoingWorkoutPlanCard, new AutoTransition());
                    itemOngoingWorkoutPlanBinding.expandedLayout.setVisibility(View.VISIBLE);
                    itemOngoingWorkoutPlanBinding.ongoingWorkoutPlanCardExpand.setBackgroundResource(R.drawable.ic_up_arrow);
                } else {
                    TransitionManager.beginDelayedTransition(itemOngoingWorkoutPlanBinding.ongoingWorkoutPlanCard, new AutoTransition());
                    itemOngoingWorkoutPlanBinding.expandedLayout.setVisibility(View.GONE);
                    itemOngoingWorkoutPlanBinding.ongoingWorkoutPlanCardExpand.setBackgroundResource(R.drawable.ic_down_arrow);
                }
            });

            itemOngoingWorkoutPlanBinding.ongoingWorkoutPlanCard.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onOnGoingWorkoutPlanCardClicked(onGoingWorkoutPlans.get(position));
                }
            });
        }
    }


}
