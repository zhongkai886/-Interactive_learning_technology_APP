package com.example.user.interactive_learning_technology_app.base;

import android.app.Activity;
import android.content.Context;

import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {
    private Activity activity;

    public Context getContext(){
        if(activity == null){
//            return BaseFragment.getInstance();
        }
        return activity;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }
}
