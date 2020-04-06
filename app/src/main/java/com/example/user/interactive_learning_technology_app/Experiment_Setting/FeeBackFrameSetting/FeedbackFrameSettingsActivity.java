package com.example.user.interactive_learning_technology_app.Experiment_Setting.FeeBackFrameSetting;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.interactive_learning_technology_app.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FeedbackFrameSettingsActivity#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FeedbackFrameSettingsActivity extends Fragment {

    public FeedbackFrameSettingsActivity() {
        // Required empty public constructor
    }

    public static FeedbackFrameSettingsActivity newInstance() {
        FeedbackFrameSettingsActivity fragment = new FeedbackFrameSettingsActivity();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_feedback_frame_settings_activity, container, false);
        return view;
    }
}
