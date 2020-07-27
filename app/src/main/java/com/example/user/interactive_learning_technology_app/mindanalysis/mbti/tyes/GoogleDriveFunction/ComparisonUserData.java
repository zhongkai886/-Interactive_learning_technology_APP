package com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.GoogleDriveFunction;

import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

import org.apache.log4j.jmx.LoggerDynamicMBean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ComparisonUserData {

    private final Executor mExecutor = Executors.newSingleThreadExecutor();
    protected com.google.api.services.drive.Drive mDriveService;
    FileList result = null;
    Boolean isSuccess = null;
    String mCheckAccount = "";
    public ComparisonUserData(Drive mDriveService) {
        this.mDriveService = mDriveService;
    }

    public Task createFile(String account) {

        return Tasks.call(mExecutor, () -> {


            try {
                //問題點 取不到帳號管理csv
                result = mDriveService.files().list()
                        .setQ("name = 'account.csv'")
                        .execute();
                Log.d("name", "name = " + result.getFiles().get(0).getId());

                InputStream inputStream1 = mDriveService.files()
                        .get(result.getFiles().get(0).getId()).executeMediaAsInputStream();
//                        .export(result.getFiles().get(0).getId(), "text/csv")
//                        .executeMediaAsInputStream();

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream1));
//                BufferedReader reader2 = new BufferedReader(new InputStreamReader(inputStream1));

                String line = "";
//                String line2 = "";
                Integer mAccountPosition = 0;
                while ((line = reader.readLine()) != null) {
                    line = line.split(",")[0];
                    mAccountPosition++;
                    Log.e("line", String.valueOf(line));
                    if (line.equals(account)) {
                        Log.d("正確", "createFile: "+mAccountPosition+"////"+line);
                        mCheckAccount=line;
                        isSuccess = true;
                        break;
                    }else{
                        Log.d("正確嗎?", "createFile: error");
                        isSuccess = false;
                    }
                }

//                Log.d("進沒", "createFile: "+line2);
//                while ((line2=reader2.readLine())!=null){
//                    line2 =line2.split(",")[1];
//                    Log.d("進", "createFile: "+line2);
//                    if (line2.equals("000")){
//                        Log.d("正確2", "createFile: "+line2);
//                        break;
//                    }
//                }
            } catch (UserRecoverableAuthIOException e) {
                e.printStackTrace();
                Log.e("err",e.getMessage());
                isSuccess = false;
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("err",""+e);
                isSuccess = false;
            }
            Log.e("result","result = " +isSuccess);
            return isSuccess;

        });
    }
    public Boolean getReturn(){
        return isSuccess;
    }
    public String getCheckAccount(){
        return mCheckAccount;
    }
}
