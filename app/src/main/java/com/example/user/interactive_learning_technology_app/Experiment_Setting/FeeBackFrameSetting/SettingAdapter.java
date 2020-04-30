package com.example.user.interactive_learning_technology_app.Experiment_Setting.FeeBackFrameSetting;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.user.interactive_learning_technology_app.R;
import com.example.user.interactive_learning_technology_app.database.SettingDBContract;

import java.util.ArrayList;
import java.util.List;


public class SettingAdapter extends RecyclerView.Adapter<SettingAdapter.SettingViewHolder> {
    private ArrayList<FeebackData> mFeedbackData;
    FeedbackFrameSettingsActivity feedbackFrameSettingsActivity;

    private Context mContext;
    private Cursor mCursor;
    public SettingAdapter(ArrayList<FeebackData> feedbackData,FeedbackFrameSettingsActivity feedbackFrameSettingsActivity){
        this.mFeedbackData =  feedbackData;
        this.feedbackFrameSettingsActivity = feedbackFrameSettingsActivity;
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
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_feedback_setting,parent,false);
        final SettingViewHolder holder = new SettingViewHolder(view);
        holder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                FeebackData feebackData = mFeedbackData.get(position);
                Toast.makeText(view.getContext(),"點點"+feebackData.getId(),Toast.LENGTH_LONG);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SettingViewHolder holder, int position) {
//        if(!mCursor.move(position)){
//            return;
//        }
        FeebackData mFeebackData = mFeedbackData.get(position);
        holder.mId.setText(mFeebackData.getId());
        holder.mItem.setText(mFeebackData.getItem());
        holder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final FragmentManager fragmentManager = feedbackFrameSettingsActivity.getActivity().getSupportFragmentManager();
                final aaa aaa = new  aaa("55555");

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.center, aaa);
                fragmentTransaction.commit();
            }
        });
//
//        String mId = mCursor.getString(mCursor.getColumnIndex(SettingDBContract.SettingDataEntry.COLUMN_ID));
//        String mItem = mCursor.getString(mCursor.getColumnIndex(SettingDBContract.SettingDataEntry.COLUMN_Item));
//        String mButton = mCursor.getString(mCursor.getColumnIndex(SettingDBContract.SettingDataEntry.COLUMN_ID));

//        holder.mId.setText(mId);
//        holder.mItem.setText(mItem);


    }

    @Override
    public int getItemCount() {
        return mFeedbackData.size();

    }

//    public void swapCursor (Cursor newCursor ){
//        if (mCursor!=null){
//            mCursor.close();
//        }
//        mCursor = newCursor;
//        if (newCursor!=null){
//            notifyDataSetChanged();
//        }

//    }


}
