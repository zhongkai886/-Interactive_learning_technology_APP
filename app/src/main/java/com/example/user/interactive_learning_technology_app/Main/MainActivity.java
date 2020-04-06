package com.example.user.interactive_learning_technology_app.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.user.interactive_learning_technology_app.Experiment_Setting.Login.LoginActivity;
import com.example.user.interactive_learning_technology_app.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View mBottomView = (View) findViewById(R.id.bottom_view_main);
        final Button mExperimentSearchButton = (Button) mBottomView.findViewById(R.id.ExperimentSearchButton);
        final Button mExperimentSettingButton = (Button) mBottomView.findViewById(R.id.ExperimentSettingButton);
        final Button mBackHomeButton = (Button) mBottomView.findViewById(R.id.BackHomeButton);

        final FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MainFragment fragment = new MainFragment();
        fragmentTransaction.add(R.id.center,fragment);
        fragmentTransaction.commit();


        mExperimentSettingButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                LoginActivity loginActivity =new LoginActivity();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.center, loginActivity);
                fragmentTransaction.commit();
                mExperimentSettingButton.setVisibility(View.INVISIBLE); //隱藏
                mExperimentSearchButton.setVisibility(View.INVISIBLE);
                mBackHomeButton.setVisibility(View.VISIBLE);
            }
        });
        mBackHomeButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainFragment mainFragment =new MainFragment();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.center, mainFragment);
                fragmentTransaction.commit();
                mExperimentSettingButton.setVisibility(View.VISIBLE); //隱藏
                mExperimentSearchButton.setVisibility(View.VISIBLE);
                mBackHomeButton.setVisibility(View.INVISIBLE);

            }
        });
    }
}
