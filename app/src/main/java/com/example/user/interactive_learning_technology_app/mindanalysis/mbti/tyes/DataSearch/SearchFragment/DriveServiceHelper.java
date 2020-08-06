package com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.DataSearch.SearchFragment;

import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DriveServiceHelper {
    private final Executor mExecutor = Executors.newSingleThreadExecutor();
    //    private Drive mDriveService;
    private com.google.api.services.drive.Drive mDriveService;
//    private String name;

    String folderId = "1N6Z-WaCx3-6zOadMC2tGvZuCwfZ-Iqnk";

    public DriveServiceHelper(Drive driveService) {
        this.mDriveService = driveService;
    }

    //目前FiledId沒接收到東西  所以用指定固定用000資料夾的ID

    public Task<String> createFile(final String filePath, final String name,String FiledId){
        return Tasks.call(mExecutor, new Callable<String>() {
            @Override
            public String call() throws Exception {
                File fileMetaData = new File();
                fileMetaData.setName(name);
                Log.d("filedId", "call: "+FiledId);

            fileMetaData.setParents((Collections.singletonList(folderId)));//folderId 轉換 FiledId


                java.io.File file = new java.io.File(filePath);

                FileContent mediaContent = new FileContent("application/octet-stream", file);

                File myFile = null;
                try {
                    myFile = mDriveService.files().create(fileMetaData, mediaContent)
                            .setFields("id")
                            .execute();

                    Log.e("File", "Flie ID = " + myFile.getId());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (myFile == null) {
                    throw new IOException("Null result when request file creation");
                }

                return myFile.getId();

            }
        });
    }
}
