package com.example.user.interactive_learning_technology_app.Experiment_Setting.FeedBackWayRoom;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

public interface FeedBackWayDAO {
    @Insert
    Long insertTask(FeedBackWayNote feedBackWayNote);

    @Update
    void updateTask(FeedBackWayNote feedBackWayNote);

    @Delete
    void delete(FeedBackWayNote feedBackWayNote);
}
