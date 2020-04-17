package com.example.user.interactive_learning_technology_app.Experiment_Setting.FeeBackFrameSetting;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.interactive_learning_technology_app.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddFeedbackFrameSetting#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddFeedbackFrameSetting extends Fragment {


    public AddFeedbackFrameSetting() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddFeedbackFrameSetting.
     */
    // TODO: Rename and change types and number of parameters
    public static AddFeedbackFrameSetting newInstance(String param1, String param2) {
        AddFeedbackFrameSetting fragment = new AddFeedbackFrameSetting();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_add_feedback_frame_setting, container, false);
    }
}
