package com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.DataSearch.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.user.interactive_learning_technology_app.R;
import com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.DataSearch.SearchFragment.DataSearchFragment;
import com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.DataSearch.SearchFragment.DriveServiceHelper;
import com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.Experiment_Setting.FeeBackFrameSetting.FeedbackFrameSettingsFragment;
import com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.Experiment_Setting.Login.LoginFragment;
import com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.GoogleDriveFunction.ComparisonUserData;
import com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.GoogleDriveFunction.SearchFile;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;

import java.util.Collections;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

public class ResearchLoginFragment extends Fragment {
    private ComparisonUserData mComparisonUserData = null;
    private DriveServiceHelper driveServiceHelper;
    private Drive googleDriveService;
    private Button  mSubmitButton;
    private EditText mAccountEdit;
    private EditText mPasswordEdit;
    ProgressDialog progressDialog ;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    DataSearchFragment dataSearchFragment;
    Boolean checkAccount=false;



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
        fragmentManager = getActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        dataSearchFragment = new DataSearchFragment();
        requestSignIn();

        mSubmitButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {

                mComparisonUserData.createFile(mAccountEdit.getText().toString());

                progressDialog = new ProgressDialog(getActivity());
                progressDialog.setMessage("驗證中");
                progressDialog.setIndeterminate(true);
                progressDialog.show();

                new checkTimer().start();

//                if((mAccountEdit.getText().toString().equals("000"))&&(mPasswordEdit.getText().toString().equals("000"))){
//                    fragmentTransaction.replace(R.id.center,dataSearchFragment);
//                    fragmentTransaction.commit();
//                } else{
//                    Toast.makeText(getActivity(),"帳號密碼有誤，請再試一次!",Toast.LENGTH_SHORT).show();
//                }
                    
            }
        });

        return view;
    }
    public void requestSignIn(){
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
//                .requestId()
//                .requestIdToken("501226046516-19p6m11sefn604ngarvn50q95fikpnfa.apps.googleusercontent.com")
//                .requestScopes(new Scope(DriveScopes.DRIVE_FILE),
//                        new Scope(DriveScopes.DRIVE_APPDATA)).
                .requestScopes(new Scope(DriveScopes.DRIVE_FILE),
                        new Scope(DriveScopes.DRIVE_APPDATA),
                        new Scope(DriveScopes.DRIVE))
//                .requestScopes(new Scopes(DriveScopes.))
//                .requestScopes(new Scope(DriveScopes.all().toString()))

                .build();

        GoogleSignInClient client = GoogleSignIn.getClient(getActivity(),signInOptions);

        startActivityForResult(client.getSignInIntent(),400);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode)
        {
            case 400:
                Log.d("dddddddddddd", "onActivityResult: "+resultCode);
                if(resultCode == RESULT_OK ) {
                    handleSignInIntent(data);
                }
                break;

            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void handleSignInIntent(Intent data) {
        Log.d("data", "data:"+data);
        GoogleSignIn.getSignedInAccountFromIntent(data)
                .addOnSuccessListener(new OnSuccessListener<GoogleSignInAccount>() {
                    @Override
                    public void onSuccess(GoogleSignInAccount googleSignInAccount) {
                        GoogleAccountCredential credential = GoogleAccountCredential
//                            .usingOAuth2(MainActivity.this, DriveScopes.a);

                                .usingOAuth2(getActivity(), Collections.singleton(DriveScopes.DRIVE_FILE));

                        credential.setSelectedAccount(googleSignInAccount.getAccount());


                        googleDriveService =
                                new Drive.Builder(
                                        AndroidHttp.newCompatibleTransport(),
                                        new GsonFactory(),
                                        credential)
                                        .setApplicationName("AppName")
                                        .build();

                        driveServiceHelper = new DriveServiceHelper(googleDriveService);
                        mComparisonUserData =new ComparisonUserData(googleDriveService);
//                        mSearchFile = new SearchFile(googleDriveService);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        e.printStackTrace();
                        Log.d("可以啦", "onFailure: "+e);
                    }
                });
    }
    public class checkTimer extends Thread{
        public void run(){
            try {
                sleep(2000);
                progressDialog.dismiss();
                checkAccount =mComparisonUserData.getReturn();
                Log.d("可以通過嗎",""+mComparisonUserData.getReturn());
                if (checkAccount.equals(true)){
                    fragmentTransaction.replace(R.id.center,dataSearchFragment);
                    fragmentTransaction.commit();
                }else if(checkAccount.equals(false)){
                    Log.d("可以",""+mComparisonUserData.getReturn());
                    Toast.makeText(getActivity(),"帳號密碼有誤，請再試一次!",Toast.LENGTH_SHORT).show();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
