package com.alchemy.mindlibrary.helper;

import com.alchemy.mindlibrary.MindColorScore;
import com.alchemy.mindlibrary.MindColorThanTable;
import com.alchemy.mindlibrary.MindColorType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import wjk.java.array.ArrayMap;

import static com.alchemy.mindlibrary.MindColorType.Blue;
import static com.alchemy.mindlibrary.MindColorType.Green;
import static com.alchemy.mindlibrary.MindColorType.Orange;
import static com.alchemy.mindlibrary.MindColorType.Yellow;
import static com.alchemy.mindlibrary.MindKey.highAlpha;
import static com.alchemy.mindlibrary.MindKey.highBeta;
import static com.alchemy.mindlibrary.MindKey.lowAlpha;
import static com.alchemy.mindlibrary.MindKey.lowBeta;
import static com.alchemy.mindlibrary.MindKey.lowGamma;
import static com.alchemy.mindlibrary.MindKey.midGamma;

public class MindColorRecordSet {
    protected ArrayMap data;
    protected List<MindColorRecord> recordList;
    protected Map<MindColorType, Integer> scoreList;
    protected List<MindColorType> rankList;

    public MindColorRecordSet(ArrayMap d){
        data = d;
    }

    public void analysis() {

        scoreList = new HashMap<>();
        scoreList.put(Orange, 0);
        scoreList.put(Green, 0);
        scoreList.put(Blue, 0);
        scoreList.put(Yellow, 0);

        recordList = new ArrayList<>();

        for(int i = 0, s = data.size(); i<s; i++){
            int ha = data.getInt(highAlpha, i);
            int la = data.getInt(lowAlpha, i);
            int hb = data.getInt(highBeta, i);
            int lb = data.getInt(lowBeta, i);
            int mg = data.getInt(midGamma, i);
            int lg = data.getInt(lowGamma, i);
            MindColorRecord mA = new MindColorRecord(ha, la, hb, lb, lg, mg);
            recordList.add(mA);

            int mAs = mA.colors.size();
            for(MindColorType t:mA.colors)
                scoreList.put(t, scoreList.get(t) + MindColorScore.Calc(mAs));

        }
        rankList = Sort(scoreList);
    }

    public MindColorType rank(int i){
        return rankList.get(i);
    }

    public int score(MindColorType i){
        return scoreList.get(i);
    }
    public int score(int i){
        return scoreList.get(rank(i));
    }

    public MindColorRecord record(int i){
        return recordList.get(i);
    }
    public int recordSize(){
        return recordList.size();
    }


    public boolean isColorNull(){
        if(data.size() < 15) return true;
        return score(0) < 8;
    }

    protected static List<MindColorType> Sort(final Map<MindColorType, Integer> map){

        ArrayList<MindColorType> sort = new ArrayList<>();
        for(MindColorType t: MindColorType.values())sort.add(t);

        final MindColorThanTable mMindColorThan = new MindColorThanTable();
        Collections.sort(sort, new Comparator<MindColorType>(){
            @Override
            public int compare(MindColorType t1, MindColorType t2) {
                int s1 = map.get(t1);
                int s2 = map.get(t2);
                int w = s2 - s1;
                if(w == 0){
                    return mMindColorThan.than(t2, t1)?1:-1;
                }
                return (s2 - s1);
            }
        });

        return sort;
    }
}
