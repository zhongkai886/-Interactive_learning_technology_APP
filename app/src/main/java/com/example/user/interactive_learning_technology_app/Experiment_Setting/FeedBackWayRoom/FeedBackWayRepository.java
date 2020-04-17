package com.example.user.interactive_learning_technology_app.Experiment_Setting.FeedBackWayRoom;

import android.content.Context;

import androidx.room.Room;

public class FeedBackWayRepository {
    private String DB_NAME ="feedbackwaydb";

    private  FeedBackWayDatabase feedBackWayDatabase;

    Context context;
    public FeedBackWayRepository(Context context){
        this.context=context;
//        feedBackWayDatabase= Room.databaseBuilder(context,FeedBackWayDatabase.class,DB_NAME).build();
    }
}
