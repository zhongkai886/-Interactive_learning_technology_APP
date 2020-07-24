package com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.DataSearch.SearchFragment;

import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;

import com.google.api.services.drive.model.File;


import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DriveServiceHelper {
    private final Executor mExecutor = Executors.newSingleThreadExecutor();
    private Drive mDriveService;

    public DriveServiceHelper(Drive driveService) {
        this.mDriveService = driveService;
    }

    public Task<String> createFile(String filePath, String name){
        return Tasks.call(mExecutor,() ->{
            File fileMetaData = new File();
            fileMetaData.setName(name);
            Log.d("elolo",filePath+"   "+name);
            java.io.File file = new java.io.File(filePath);

            FileContent mediaContent = new FileContent("text/comma-separated-values",file);


            File myFile = null;
            try{
                myFile = mDriveService.files().create(fileMetaData,mediaContent).execute();

            }catch (Exception e){
                e.printStackTrace();
                Log.d("error",""+e);
            }
//            Log.d("88888",""+fileMetaData+"/////"+myFile);
            if (myFile == null){
                throw new IOException("Null result when request file creation");
            }

            return myFile.getId();

        });
    }

}
