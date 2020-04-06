package com.example.user.interactive_learning_technology_app.Experiment_Setting.FeedBackWayRoom;

import androidx.room.Database;

@Database(entities = (FeedBackWayNote.class),version = 1,exportSchema = false)
public abstract class FeedBackWayDatabase implements FeedBackWayDAO {
    public abstract FeedBackWayDAO feedBackWayDAO();


}
