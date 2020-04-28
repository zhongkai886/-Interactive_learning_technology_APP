package com.example.user.interactive_learning_technology_app.Experiment_Setting.FeeBackFrameSetting;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.user.interactive_learning_technology_app.R;
import com.example.user.interactive_learning_technology_app.database.SettingDBContract;


public class SettingAdapter extends RecyclerView.Adapter<SettingAdapter.SettingViewHolder> {
    private Context mContext;
    private Cursor mCursor;
    public SettingAdapter(Context context , Cursor cursor){
        mContext = context;
        mCursor =cursor;
    }

    public class SettingViewHolder extends RecyclerView.ViewHolder{
        public TextView mId;
        public TextView mItem;
        public Button mButton;

        public SettingViewHolder(@NonNull View itemView) {
            super(itemView);
            mId = itemView.findViewById(R.id.settingId);
            mItem = itemView.findViewById(R.id.settingItem);
            mButton = itemView.findViewById(R.id.settingButton);
        }
    }

    @NonNull
    @Override
    public SettingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_feedback_setting,parent,false);
        return new SettingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SettingViewHolder holder, int position) {
        if(!mCursor.move(position)){
            return;
        }
        String mId = mCursor.getString(mCursor.getColumnIndex(SettingDBContract.SettingDataEntry.COLUMN_ID));
        String mItem = mCursor.getString(mCursor.getColumnIndex(SettingDBContract.SettingDataEntry.COLUMN_Item));
        String mButton = mCursor.getString(mCursor.getColumnIndex(SettingDBContract.SettingDataEntry.COLUMN_ID));

        holder.mId.setText(mId);
        holder.mItem.setText(mItem);
//        holder.mItem.setText();


    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }
    public void swapCursor (Cursor newCursor ){
        if (mCursor!=null){
            mCursor.close();
        }
        mCursor = newCursor;
        if (newCursor!=null){
            notifyDataSetChanged();
        }
    }


}
