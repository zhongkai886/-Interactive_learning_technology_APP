package com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.DataSearch.SearchFragment;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.user.interactive_learning_technology_app.R;
import com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.Main.MainActivity;
import com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBHelper;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Locale;

import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_AttentionHigh;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_AttentionLow;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_AttentionMax;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_AttentionMin;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_AverageAttention;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_AverageRelaxation;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_DetectTime;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_FeedBackCount;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_FeedBackPassSeconds;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_FeedBackSecondsGap;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_ID;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_Item;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_Name;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_Number;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_PointInTime;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_RelaxationHigh;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_RelaxationLow;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_RelaxationMax;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_RelaxationMin;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.TABLE_NAME;

public class DataSearchFragment extends Fragment implements View.OnClickListener {
    public SQLiteDatabase mDatabase;
    public ArrayList<String> mCheckBoxDataList = new ArrayList<String>();
    public ArrayList<DetectData> detectDataList = new ArrayList<DetectData>();
    public RecyclerView recyclerView;
    public SearchAdapter mAdapter;
    public Button BtnUpload;
    public ArrayList<String> row = new ArrayList<>();
    public CharSequence dateTime="";
    public DriveServiceHelper driveServiceHelper;
    public String sdCardDir;
    private static final int PICK_CSV_FROM_GALLERY_REQUEST_CODE = 100;
    public String filename ="";
    public DataSearchFragment() {
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_search, container, false);

        SearchDBHelper dbHelper = new SearchDBHelper(getActivity());
        mDatabase = dbHelper.getWritableDatabase(); //寫入
        mDatabase = dbHelper.getReadableDatabase(); //讀取
        InsertTable();
        LoadData();

        getAuthority();
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new SearchAdapter(detectDataList,this);
        recyclerView.setAdapter(mAdapter);

        BtnUpload = (Button) view.findViewById(R.id.dataUpload);
        BtnUpload.setOnClickListener(this);

        return view;
    }
    public void LoadData(){
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM searchDataList", null);
        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex(COLUMN_ID));
            String number = cursor.getString(cursor.getColumnIndex(COLUMN_Number));
            String name = cursor.getString(cursor.getColumnIndex(COLUMN_Name));
            String detectTime = cursor.getString(cursor.getColumnIndex(COLUMN_DetectTime));
            String item = cursor.getString(cursor.getColumnIndex(COLUMN_Item));
            String feedBackCount = cursor.getString(cursor.getColumnIndex(COLUMN_FeedBackCount));
            String attentionHigh = cursor.getString(cursor.getColumnIndex(COLUMN_AttentionHigh));
            String attentionLow = cursor.getString(cursor.getColumnIndex(COLUMN_AttentionLow));
            String relaxationHigh = cursor.getString(cursor.getColumnIndex(COLUMN_RelaxationHigh));
            String relaxationLow = cursor.getString(cursor.getColumnIndex(COLUMN_RelaxationLow));
            String attentionMax = cursor.getString(cursor.getColumnIndex(COLUMN_AttentionMax));
            String attentionMin = cursor.getString(cursor.getColumnIndex(COLUMN_AttentionMin));
            String relaxationMax = cursor.getString(cursor.getColumnIndex(COLUMN_RelaxationMax));
            String relaxationMin = cursor.getString(cursor.getColumnIndex(COLUMN_RelaxationMin));
            String feedBackSecondsGap = cursor.getString(cursor.getColumnIndex(COLUMN_FeedBackSecondsGap));
            String feedBackPassSeconds = cursor.getString(cursor.getColumnIndex(COLUMN_FeedBackPassSeconds));
            String averageAttention = cursor.getString(cursor.getColumnIndex(COLUMN_AverageAttention));
            String averageRelaxation = cursor.getString(cursor.getColumnIndex(COLUMN_AverageRelaxation));
            String pointInTime = cursor.getString(cursor.getColumnIndex(COLUMN_PointInTime));

            DetectData detectData = new DetectData(id,number,name,detectTime,item,
                    feedBackCount,attentionHigh,attentionLow,
                    relaxationHigh,relaxationLow,attentionMax,attentionMin,
                    relaxationMax,relaxationMin,feedBackSecondsGap,feedBackPassSeconds,
                    averageAttention,averageRelaxation,pointInTime);
            detectDataList.add(detectData);
            Log.d("刷新",""+detectData.getDetectTime());
            Log.d("LOLOLOLO",""+relaxationMax);

        }
        cursor.close();
    }
    public void InsertTable(){ //19欄位  Id資料辨識用不須顯示
        final String SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "( " +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_Number + " VARCHAR(250), " +
                COLUMN_Name + " VARCHAR(250), " +
                COLUMN_DetectTime + " VARCHAR(250)," +
                COLUMN_Item + " VARCHAR(250)," +
                COLUMN_FeedBackCount + " VARCHAR(250)," +
                COLUMN_AttentionHigh + " VARCHAR(250)," +
                COLUMN_AttentionLow + " VARCHAR(250)," +
                COLUMN_RelaxationHigh + " VARCHAR(250)," +
                COLUMN_RelaxationLow + " VARCHAR(250)," +
                COLUMN_AttentionMax + " VARCHAR(250)," +
                COLUMN_AttentionMin + " VARCHAR(250)," +
                COLUMN_RelaxationMax + " VARCHAR(250)," +
                COLUMN_RelaxationMin + " VARCHAR(250)," +
                COLUMN_FeedBackSecondsGap + " VARCHAR(250)," +
                COLUMN_FeedBackPassSeconds + " VARCHAR(250)," +
                COLUMN_AverageAttention + " VARCHAR(250)," +
                COLUMN_AverageRelaxation + " VARCHAR(250)," +
                COLUMN_PointInTime + " VARCHAR(250)" +
                ");";
        mDatabase.execSQL(SQL);
        //18欄位 扣掉自動生成ID
        String sql = "INSERT into '" + TABLE_NAME + "' ( '" + COLUMN_Number
                + "','" + COLUMN_Name
                + "','" + COLUMN_DetectTime
                + "','" + COLUMN_Item
                + "','" + COLUMN_FeedBackCount
                + "','" + COLUMN_AttentionHigh
                + "','" + COLUMN_AttentionLow
                + "','" + COLUMN_RelaxationHigh
                + "','" + COLUMN_RelaxationLow
                + "','" + COLUMN_AttentionMax
                + "','" + COLUMN_AttentionMin
                + "','" + COLUMN_RelaxationMax
                + "','" + COLUMN_RelaxationMin
                + "','" + COLUMN_FeedBackSecondsGap
                + "','" + COLUMN_FeedBackPassSeconds
                + "','" + COLUMN_AverageAttention
                + "','" + COLUMN_AverageRelaxation
                + "','" + COLUMN_PointInTime + "' ) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//        for (int i = 0 ; i<5 ; i++){
//            Object[] mValue = new Object[]{"安安","2001","Attention",
//                    "5",
//                    "70","20",
//                    "80","30",
//                    "100","5",
//                    "98","3",
//                    "2","5",
//                    "45","30",
//                    "wnfwefnwefnewfnwe"};
//            mDatabase.execSQL(sql,mValue);
//        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dataUpload:

                row = mAdapter.getCheckId();
                Calendar mCal = Calendar.getInstance();
                dateTime = DateFormat.format("yyyy-MM-dd kk:mm:ss", mCal.getTime());    // kk:24小時制, hh:12小時制
                Log.d("cvcvcvcvcv",row.size()+"size/////row"+mAdapter.getCheckId());
                SearchDBHelper dbHelper = new SearchDBHelper(getActivity());
                mDatabase = dbHelper.getWritableDatabase(); //寫入
                mDatabase = dbHelper.getReadableDatabase(); //讀取
                Cursor c = null;
                        try {
                            c = mDatabase.rawQuery("select * from searchDataList", null);
                            int rowcount = 0;//資料量
                            int colcount = 0;//欄位數量
//                            String sdCardDir2 = Environment.getExternalStorageDirectory().toString();
                            sdCardDir = getActivity().getExternalFilesDir(null).getAbsolutePath();
//                            Log.d("sdCardDir",sdCardDir2+"////"+sdCardDir);
                            filename = dateTime+".csv";
                            // the name of the file to export with
                            File saveFile = new File(sdCardDir, filename);
                            FileWriter fw = new FileWriter(saveFile);

                            BufferedWriter bw = new BufferedWriter(fw);
                            rowcount = c.getCount();
                            colcount = c.getColumnCount();
                            Log.d("excel",""+rowcount+"////"+colcount);
                            if (rowcount > 0) {
                                c.moveToFirst();

                                for (int i = 0; i < colcount; i++) {
                                    if (i != colcount - 1) {

                                        bw.write(c.getColumnName(i) + ",");

                                    } else {

                                        bw.write(c.getColumnName(i));

                                    }
                                }
                                bw.newLine();

                                for (int i = 0; i <= row.size()-1; i++) {
                                    Log.d("cvcvcvcvcv",""+Integer.valueOf(row.get(i)));

                                    c.moveToPosition(Integer.valueOf(row.get(i)));

                                    for (int j = 0; j < colcount; j++) {
                                        if (j != colcount - 1)
                                            bw.write(c.getString(j) + ",");
                                        else
                                            bw.write(c.getString(j));
                                    }
                                    bw.newLine();
                                }
                                bw.flush();
                                Toast.makeText(getActivity(), "Exported Successfully.", Toast.LENGTH_SHORT).show();

                                uploadFile();
                            }
                        } catch (Exception ex) {
                            if (mDatabase.isOpen()) {
                                mDatabase.close();
                                Log.d("有吧?", "onClick: "+ex);
                            }

                        } finally {

                        }
        }
    }
    public void getAuthority(){
        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                    getActivity(),
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    0
            );

        }
    }

    public void requestSignIn(){
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestScopes(new Scope(DriveScopes.DRIVE_FILE),
                        new Scope(DriveScopes.DRIVE_APPDATA))
                .build();

        GoogleSignInClient client = GoogleSignIn.getClient(getActivity(),signInOptions);

        startActivityForResult(client.getSignInIntent(),400);
    }

    public void uploadFile(){
//        String filePath = "/storage/emulated/0/acx1.csv";
//        driveServiceHelper.createFile(filePath)
//        int j = filepa.size();

        //Loading
            ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setTitle("Uploading to Google Drive");
            progressDialog.setMessage("Please wait...");
            progressDialog.show();


//        driveServiceHelper.fileName(fileName.get(i));
        Log.d("88888",""+sdCardDir+"/"+filename+"/////"+filename);
                driveServiceHelper.createFile(sdCardDir+"/"+filename,filename)
                        .addOnSuccessListener(new OnSuccessListener<String>() {
                            @Override
                            public void onSuccess(String s) {
                                progressDialog.dismiss();
                                Toast.makeText(getActivity(),"Uploaded successfully",Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(),"Check your google api key",Toast.LENGTH_LONG).show();
                        Log.d("aaa888",""+e);
                    }
                });
    }
    private void handleSignInIntent(Intent data) {
        GoogleSignIn.getSignedInAccountFromIntent(data)
                .addOnSuccessListener(googleSignInAccount -> {
                    GoogleAccountCredential credential = GoogleAccountCredential
                            .usingOAuth2(getActivity(), Collections.singleton(DriveScopes.DRIVE_FILE));

                    credential.setSelectedAccount(googleSignInAccount.getAccount());

                    Drive googleDriveService =
                            new Drive.Builder(
                                    AndroidHttp.newCompatibleTransport(),
                                    new GsonFactory(),
                                    credential)
                                    .setApplicationName("AppName")
                                    .build();

                    driveServiceHelper = new DriveServiceHelper(googleDriveService);

                })
                .addOnFailureListener(e -> e.printStackTrace());
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode)
        {
            case 400:
                if(resultCode == getActivity().RESULT_OK ) {
                    handleSignInIntent(data);
                }
                break;
//            case PICK_CSV_FROM_GALLERY_REQUEST_CODE:
//                Uri selectedCsv =null;
//                ClipData clipData = null;
//                if (resultCode == Activity.RESULT_OK && data != null){
//                    selectedCsv = data.getData();
//                    if (clipData != null){
//                        catchFileInApp(selectedCsv,clipData);
//                    }
//                    else if (Build.VERSION.SDK_INT>=16 && clipData== null){
//                        clipData = data.getClipData();
//                        catchFileInApp(selectedCsv,clipData);
//                    }
//                }
//                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    public void exportCSV(){
        File carpeta = new File(Environment.getExternalStorageDirectory() + "/ExportarSQLiteCSV");
        String archivoAgenda = carpeta.toString() + "/" + "Usuarios.csv";

        boolean isCreate = false;
        if(!carpeta.exists()) {
            isCreate = carpeta.mkdir();
        }

        try {
            FileWriter fileWriter = new FileWriter(archivoAgenda);
//
//            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getActivity(), "dbSistema", null, 1);
//
//            SQLiteDatabase db = admin.getWritableDatabase();

            Cursor fila = mDatabase.rawQuery("select * from usuarios", null);

            if(fila != null && fila.getCount() != 0) {
                fila.moveToFirst();
                do {

                    fileWriter.append(fila.getString(0));
                    fileWriter.append(",");
                    fileWriter.append(fila.getString(1));
                    fileWriter.append(",");
                    fileWriter.append(fila.getString(2));
                    fileWriter.append("\n");

                } while(fila.moveToNext());
            } else {
                Toast.makeText(getActivity(), "No hay registros.", Toast.LENGTH_LONG).show();
            }

            mDatabase.close();
            fileWriter.close();
            Toast.makeText(getActivity(), "SE CREO EL ARCHIVO CSV EXITOSAMENTE", Toast.LENGTH_LONG).show();

        }
        catch (Exception e) {
            Log.d("dadada",""+e);
        }
    }
}

