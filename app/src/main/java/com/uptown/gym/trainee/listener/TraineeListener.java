package com.uptown.gym.trainee.listener;

import com.uptown.gym.trainee.model.fitnesstest.FitnessTest;
import com.uptown.gym.trainee.model.inbody.InBody;
import com.uptown.gym.trainee.model.trainee.Trainee;

public interface TraineeListener {

    interface TraineeCardListener {
        void onTraineeCardClicked(Trainee trainee);
    }

    interface TraineeProfileListener {
        void onFitnessTestClicked();

        void onInBodyClicked();
    }

    interface FitnessTestListener {

        void onFitnessTestClicked(FitnessTest fitnessTest);
    }

    interface InBodiesListener {

        void onInBodyClicked(InBody inBody);
    }
}
