package com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.Detect;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.interactive_learning_technology_app.R;

public class DetectLoginFragment extends Fragment {
    private Button button;
    private EditText loginId;
    private EditText loginName;

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
        final DetectFragment detectFragment = new DetectFragment();
        loginId = (EditText) view.findViewById(R.id.LoginId);
        loginName = (EditText) view.findViewById(R.id.LoginName);
        button = (Button) view.findViewById(R.id.enterButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((loginId.getText().toString().isEmpty())||(loginName.getText().toString().isEmpty())) {
                    Toast.makeText(getActivity(),"請完整輸入編號及姓名!",Toast.LENGTH_SHORT).show();
                } else{

                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.center, detectFragment);
                    fragmentTransaction.commit();

                }

            }

        });
        return view;
    }
}
