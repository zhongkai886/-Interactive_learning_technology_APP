package com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.Experiment_Setting.FeeBackFrameSetting;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.user.interactive_learning_technology_app.R;

import java.util.ArrayList;
import java.util.List;


public class SettingAdapter extends RecyclerView.Adapter<SettingAdapter.SettingViewHolder> {
    private List<FeedbackData> mFeedbackDataList;
    FeedbackFrameSettingsFragment feedbackFrameSettingsActivity;
    public ArrayList<String> mCheckBoxDataList = new ArrayList<String>();
    public ArrayList<Integer> mCheckBoxPositionList = new ArrayList<Integer>();
    private Context mContext;
    private Cursor mCursor;

    public SettingAdapter(List<FeedbackData> mFeedbackDataList,
                          FeedbackFrameSettingsFragment feedbackFrameSettingsActivity){
        this.mFeedbackDataList =  mFeedbackDataList;
        this.feedbackFrameSettingsActivity = feedbackFrameSettingsActivity;
    }

    public class SettingViewHolder extends RecyclerView.ViewHolder{
        public CheckBox mCheckbox;
        public TextView mId;
        public TextView mWay;
        public Button mButton;


        public SettingViewHolder(@NonNull View itemView) {
            super(itemView);
            mCheckbox = itemView.findViewById(R.id.settingCheckbox);
            mId = itemView.findViewById(R.id.settingId);
            mWay = itemView.findViewById(R.id.settingWay);
            mButton = itemView.findViewById(R.id.settingButton);
        }
    }

    @NonNull
    @Override
    public SettingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_feedback_setting,parent,false);
        final SettingViewHolder holder = new SettingViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final SettingViewHolder holder, final int position) {
//        if(!mCursor.move(position)){
//            return;
//        }
        Log.d("notifyyyyy","test");
        final FeedbackData mFeedbackData = this.mFeedbackDataList.get(position);

        holder.mId.setText(mFeedbackDataList.get(position).getId());
        Log.d("voice",mFeedbackDataList.get(position).getAttentionWay());
        if (mFeedbackDataList.get(position).getAttentionWay().equals("0")){
            holder.mWay.setText("視覺");
        }
        else if (mFeedbackDataList.get(position).getAttentionWay().equals("1")){
            holder.mWay.setText("震動");
        }else if (mFeedbackDataList.get(position).getAttentionWay().equals("2")){
            holder.mWay.setText("聲音");
        }

        holder.mCheckbox.setTag(position);

        //由mFeedbackData中的Check欄位取是否打勾
        holder.mCheckbox.setChecked(mFeedbackData.getCheck());
        //點擊後把mFeedbackData中的Check欄位設定為true(勾選)
        holder.mCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean b = ((CheckBox) v).isChecked();
                holder.mCheckbox.setChecked(b);
                mFeedbackData.setCheck(b);
            }
        });
//        holder.mCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
////                Object position1 = compoundButton.getTag();
//                mCheckBoxPositionList.clear();
//                mCheckBoxDataList.clear();
//                if (holder.mCheckbox.isChecked()){
//                    mCheckBoxDataList.add(mFeedbackData.getId());
//                    mCheckBoxPositionList.add(position);
//                } else{
//                    mCheckBoxDataList.remove(mFeedbackData.getId());
//                    mCheckBoxPositionList.remove(position);
//
//                }
//                Log.d("YOYOYO",""+mCheckBoxDataList.size()+"///"+mCheckBoxPositionList);
//            }
//        });


        holder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = feedbackFrameSettingsActivity.getActivity().getSupportFragmentManager();
                Edit_FeedbackWay edit_FeedbackWay = new Edit_FeedbackWay(
                        mFeedbackData.getId(),
                        mFeedbackData.getName(),
                        mFeedbackData.getItem(),
                        mFeedbackData.getAttentionHigh(),
                        mFeedbackData.getAttentionLow(),
                        mFeedbackData.getAttentionWay(),
                        mFeedbackData.getRelaxationHigh(),
                        mFeedbackData.getRelaxationLow(),
                        mFeedbackData.getRelaxationWay(),
                        mFeedbackData.getWaySecond(),
                        mFeedbackData.getHoldSecond(),
                        mFeedbackData.getWayStopTipSecond());

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.center, edit_FeedbackWay);
                fragmentTransaction.commit();
            }
        }
        );
//
//        String mId = mCursor.getString(mCursor.getColumnIndex(SettingDBContract.SettingDataEntry.COLUMN_ID));
//        String mItem = mCursor.getString(mCursor.getColumnIndex(SettingDBContract.SettingDataEntry.COLUMN_Item));
//        String mButton = mCursor.getString(mCursor.getColumnIndex(SettingDBContract.SettingDataEntry.COLUMN_ID));

//        holder.mId.setText(mId);
//        holder.mItem.setText(mItem);


    }
    public ArrayList<String> getId(){
        return  mCheckBoxDataList;
    }
    public ArrayList<Integer> getCheckBoxPositionList(){
        return mCheckBoxPositionList;
    }

    @Override
    public int getItemCount() {
        Log.d("77777777",""+mFeedbackDataList.size());
        return mFeedbackDataList.size();

    }

    public void removeItem(int position){
        mFeedbackDataList.remove(position);
        notifyItemRemoved(position);
    }

    public List<FeedbackData> getmFeedbackDataList(){
        return mFeedbackDataList;
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
