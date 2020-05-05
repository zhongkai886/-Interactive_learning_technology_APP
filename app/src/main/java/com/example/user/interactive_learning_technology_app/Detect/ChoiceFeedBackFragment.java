package com.example.user.interactive_learning_technology_app.Detect;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import com.example.user.interactive_learning_technology_app.R;
import com.example.user.interactive_learning_technology_app.database.SettingDBHelper;

import java.util.ArrayList;
import java.util.List;

import static com.example.user.interactive_learning_technology_app.database.SettingDBContract.SettingDataEntry.COLUMN_ID;
import static com.example.user.interactive_learning_technology_app.database.SettingDBContract.SettingDataEntry.COLUMN_Item;
import static com.example.user.interactive_learning_technology_app.database.SettingDBContract.SettingDataEntry.COLUMN_Name;

public class ChoiceFeedBackFragment extends Fragment {
    private Button button;
    private Spinner spinner;
    private SettingDBHelper settingDBHelper;
    private SQLiteDatabase  mDatabase;
    private ArrayList<String> ListId = new ArrayList<String>();
    private ArrayList<String> ListName = new ArrayList<String>();
    private ArrayList<String> ListItem = new ArrayList<String>();
    public ChoiceFeedBackFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        SettingDBHelper dbHelper = new SettingDBHelper(getActivity());
        mDatabase = dbHelper.getWritableDatabase();

        View view = inflater.inflate(R.layout.fragment_choice_feed_back, container, false);
        LoadData();
        final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        final DetectLoginFragment detectLoginFragment = new DetectLoginFragment();

        button = view.findViewById(R.id.enterButton);
        spinner = view.findViewById(R.id.FeedBackSpinner);
        spinner.setAdapter(new SpinnerAdapter(view.getContext(),ListId,ListName,ListItem));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.center, detectLoginFragment);
                fragmentTransaction.commit();
            }
        });
        return view;
    }
    //將資料庫資料Load進去SpinnerAdapter
    public void LoadData() {

        Cursor cursor = mDatabase.rawQuery("SELECT * FROM settingDataList", null);

        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex(COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(COLUMN_Name));
            String item = cursor.getString(cursor.getColumnIndex(COLUMN_Item));
            ListId.add(id);
            ListName.add(name);
            ListItem.add(item);
            Log.d("comeIn", "LoadData: "+ListId.size()+"///"+ListName.size()+"////"+ListItem);

        }
        cursor.close();
    }
}
