package com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.GoogleDriveFunction;

import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SearchFile {
    private final Executor mExecutor = Executors.newSingleThreadExecutor();
    //    private Drive mDriveService;
    protected com.google.api.services.drive.Drive mDriveService;
    //    private String name;
//    String folderId = "1N6Z-WaCx3-6zOadMC2tGvZuCwfZ-Iqnk";
    String folderId = "";

    FileList result = null;

    public SearchFile(Drive driveService) {
        this.mDriveService = driveService;
    }

    public Task<String> searchFile(String FiledName){
        return Tasks.call(mExecutor,() ->{
            String pageToken = null;

            try{
                do {
                    FileList reset =mDriveService.files().list()
//                            .setQ("'root' in parents and mimeType != 'application/vnd.google-apps.folder' and trashed = false")
//                            .setQ("mimeType='application/vnd.google-apps.folder' ")
//                            .setQ("Id = '1frxuLY0zAu_XFrpVf8ULLQpMZtM5j9Ua'")
//                            .setQ("mimeType='text/comma-separated-values'")
//                            .setQ("name = '000' and mimeType='application/vnd.google-apps.folder'")
                            .setQ("name = '"+FiledName+"' and mimeType='application/vnd.google-apps.folder'")
//                            .setQ("'zx0933779547@gmail.com' in writers")
                            .setSpaces("drive")
//                            .setFields("nextPageToken, files(id,name)")
                            .setFields("nextPageToken, files(id,name,mimeType)")
//                            .setFields("nextPageToken, files(id,name)")
                            .setPageToken(pageToken)
                            .execute();
                    //存取登入帳號名稱的資料夾Id file.getId()
                    for (File file : reset.getFiles()){
                        Log.e("File", "File: "+file.getName()+","+file.getId()+","+file.getMimeType());
//                        Log.e("File", "File: "+file.getName()+","+file.getId()+","+file.getMimeType());
                        folderId=file.getId();
                    }

                    pageToken = reset.getNextPageToken();
                }while (pageToken !=null);


            }catch (UserRecoverableAuthIOException e) {
                e.printStackTrace();
                Log.e("err",e.getMessage());

            } catch (IOException e) {
                e.printStackTrace();
                Log.e("err",e.getMessage());

            }

            return null;
        });
    }
    public String getFiledId(){
        return folderId;
    }
}
