package com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.DataSearch.Login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.user.interactive_learning_technology_app.R;
import com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.DataSearch.SearchFragment.DataSearchFragment;
import com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.Experiment_Setting.FeeBackFrameSetting.FeedbackFrameSettingsFragment;

public class ResearchLoginFragment extends Fragment {
    private Button  mSubmitButton;
    private EditText mAccountEdit;
    private EditText mPasswordEdit;

    public ResearchLoginFragment() {
        // Required empty public constructor
    }

    public static ResearchLoginFragment newInstance() {
        ResearchLoginFragment fragment = new ResearchLoginFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        mSubmitButton=(Button) view.findViewById(R.id.SubmitButton);
        mAccountEdit=(EditText) view.findViewById(R.id.AccountEdit);
        mPasswordEdit=(EditText) view.findViewById(R.id.PasswordEdit);
        final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        final DataSearchFragment dataSearchFragment = new DataSearchFragment();
        mSubmitButton.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
                if((mAccountEdit.getText().toString().equals("000"))&&(mPasswordEdit.getText().toString().equals("000"))){
                    fragmentTransaction.replace(R.id.center,dataSearchFragment);
                    fragmentTransaction.commit();
                } else{
                    Toast.makeText(getActivity(),"帳號密碼有誤，請再試一次!",Toast.LENGTH_SHORT).show();
                }
                    
            }
        });

        return view;
    }
}
