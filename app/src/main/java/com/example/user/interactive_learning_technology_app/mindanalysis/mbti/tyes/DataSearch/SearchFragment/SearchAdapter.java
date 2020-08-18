package com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.DataSearch.SearchFragment;

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


public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    private ArrayList<DetectData> mDetectDataList;
    DataSearchFragment dataSearchFragment;
    public ArrayList<String> mCheckBoxDataList = new ArrayList<String>();
    private Context mContext;
    private Cursor mCursor;

    public SearchAdapter(ArrayList<DetectData> detectData,
                         DataSearchFragment dataSearchFragment){
        this.mDetectDataList =  detectData;
        this.dataSearchFragment = dataSearchFragment;
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder{
        public CheckBox mCheckbox;
        public TextView mId;
        public TextView mWay;
        public TextView mCount;
        public TextView mTimeDate;
        public Button mButton;


        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            mCheckbox = itemView.findViewById(R.id.searchCheckbox);
            mId = itemView.findViewById(R.id.searchId);
            mWay = itemView.findViewById(R.id.searchItem);
            mCount = itemView.findViewById(R.id.count);
            mTimeDate = itemView.findViewById(R.id.timeDate);
            mButton = itemView.findViewById(R.id.searchButton);
        }
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_feedback_search,parent,false);
        final SearchViewHolder holder = new SearchViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final SearchViewHolder holder, final int position) {

        final DetectData mDetectData = this.mDetectDataList.get(position);

        holder.mId.setText(mDetectDataList.get(position).getId());
        if (mDetectDataList.get(position).getItem().equals("0")){
            holder.mWay.setText("視覺");
        }
        else if (mDetectDataList.get(position).getItem().equals("1")){
            holder.mWay.setText("震動");
        }else if (mDetectDataList.get(position).getItem().equals("2")){
            holder.mWay.setText("聲音");
        }
//        holder.mWay.setText(mDetectDataList.get(position).getItem());
        holder.mCount.setText(mDetectDataList.get(position).getFeedBackCount());
        holder.mTimeDate.setText(mDetectDataList.get(position).getDetectTime());
        holder.mCheckbox.setTag(position);
        holder.mCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Object position1 = compoundButton.getTag();

                if (holder.mCheckbox.isChecked()){
                    mCheckBoxDataList.add(mDetectData.getId());
                    Log.d("yoyo",""+position1);
                    Log.d("yoyoCheck",""+mCheckBoxDataList);
                } else{
                    mCheckBoxDataList.remove(position1.toString());
                    Log.d("yoyo",""+position1);
                    Log.d("yoyoCheck",""+mCheckBoxDataList);

                }
            }
        });


        holder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = dataSearchFragment.getActivity().getSupportFragmentManager();
                Edit_DetectDataFragment edit_detectDataFragment = new Edit_DetectDataFragment(
                        mDetectData.getId(),
                        mDetectData.getNumber(),
                        mDetectData.getName(),
                        mDetectData.getDetectTime(),
                        mDetectData.getItem(),
                        mDetectData.getFeedBackCount(),
                        mDetectData.getAttentionHigh(),
                        mDetectData.getAttentionLow(),
                        mDetectData.getRelaxationHigh(),
                        mDetectData.getRelaxationLow(),
                        mDetectData.getAttentionMax(),
                        mDetectData.getAttentionMin(),
                        mDetectData.getRelaxationMax(),
                        mDetectData.getRelaxationMin(),
                        mDetectData.getFeedbackSecondsGap(),
                        mDetectData.getFeedbackPassSecond(),
                        mDetectData.getAverageAttention(),
                        mDetectData.getAverageRelaxation(),
                        mDetectData.getPointInTime()
                );
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.center, edit_detectDataFragment);
                fragmentTransaction.commit();
                Log.d("question",""+mDetectData.getFeedbackSecondsGap()+mDetectData.getFeedbackPassSecond());
            }
        }
        );
    }

    public ArrayList<String> getId(){
        return  mCheckBoxDataList;
    }

    @Override
    public int getItemCount() {
        return mDetectDataList.size();

    }

    public ArrayList<String> getCheckId(){
        return mCheckBoxDataList;
    }


}
