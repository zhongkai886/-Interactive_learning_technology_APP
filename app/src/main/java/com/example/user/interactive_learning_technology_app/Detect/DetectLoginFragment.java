package com.example.user.interactive_learning_technology_app.Detect;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.user.interactive_learning_technology_app.R;

public class DetectLoginFragment extends Fragment {
    private Button button;
    public DetectLoginFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detect_loginfragment, container, false);
        final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        final DetectTestFragment detectTestFragment = new DetectTestFragment();
        button = (Button) view.findViewById(R.id.enterButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.center, detectTestFragment);
                fragmentTransaction.commit();
            }
        });
        return view;
    }
}
