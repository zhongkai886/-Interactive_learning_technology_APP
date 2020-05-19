package com.alchemy.mindlibrary.helper;

import com.alchemy.mindlibrary.MindColorMBTI;
import com.alchemy.mindlibrary.MindColorThanTable;
import com.alchemy.mindlibrary.MindColorType;
import com.alchemy.mindlibrary.MindColorYijing;
import com.alchemy.mindlibrary.MindColorYijingType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import wjk.java.array.ArrayMap;

import static com.alchemy.mindlibrary.MindColorMBTI.Calc;
import static com.alchemy.mindlibrary.MindColorType.Blue;
import static com.alchemy.mindlibrary.MindColorType.Green;
import static com.alchemy.mindlibrary.MindColorType.Orange;
import static com.alchemy.mindlibrary.MindColorType.Yellow;

public class MindColorRecordMultiSet {

    protected ArrayMap[] data;
    protected MindColorRecordSet[] recordSetList;
    protected Map<MindColorType, Integer> scoreList;
    protected Map<MindColorType, Integer> scoreSumList;
    protected List<MindColorType> rankList;

    public MindColorRecordMultiSet(ArrayMap... d){
        data = d;
    }


    public void analysis() {
        recordSetList = new MindColorRecordSet[data.length];
        for(int i = 0;i<data.length; i++){
            recordSetList[i] = new MindColorRecordSet(data[i]);
            recordSetList[i].analysis();
        }

        scoreList = new HashMap<>();
        scoreList.put(Orange, 0);
        scoreList.put(Green, 0);
        scoreList.put(Blue, 0);
        scoreList.put(Yellow, 0);

        scoreSumList = new HashMap<>();
        scoreSumList.put(Orange, 0);
        scoreSumList.put(Green, 0);
        scoreSumList.put(Blue, 0);
        scoreSumList.put(Yellow, 0);

        for(MindColorRecordSet r: recordSetList){
            MindColorType t = r.rank(0);
            if(r.score(t) == 0)continue;
            scoreList.put(t, scoreList.get(t) + 1);

            scoreSumList.put(MindColorType.Blue, scoreSumList.get(MindColorType.Blue) + r.score(MindColorType.Blue));
            scoreSumList.put(MindColorType.Green, scoreSumList.get(MindColorType.Green) + r.score(MindColorType.Green));
            scoreSumList.put(MindColorType.Orange, scoreSumList.get(MindColorType.Orange) + r.score(MindColorType.Orange));
            scoreSumList.put(MindColorType.Yellow, scoreSumList.get(MindColorType.Yellow) + r.score(MindColorType.Yellow));
        }

        rankList = Sort(scoreList, scoreSumList);
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

    public int scoreSum(MindColorType i){
        return scoreSumList.get(i);
    }
    public int scoreSum(int i){
        return scoreSumList.get(rank(i));
    }

    public MindColorRecordSet recordSet(int i){
        return recordSetList[i];
    }
    public int recordSetSize(){
        return recordSetList.length;
    }

    public MindColorYijingType yijing(MindWaveHelperMulti h){
        if(!h.analysis)h.analysisProportion();
        return yijing((int) (h.bestTheta().Theta * 100 + 0.5f));
    }
    public MindColorYijingType yijing(int theta){
        MindColorType r1 = rank(0);
        MindColorType r2 = score(1) != 0 ? rank(1) : scoreSum(1) != 0 ? rank(1) : r1;

        return MindColorYijing.Calc(r1, r2, theta);
    }

    public int mbti(int theta, int bata){
        MindColorType r1 = rank(0);
        MindColorType r2 = score(1) != 0 ? rank(1) : scoreSum(1) != 0 ? rank(1) : r1;

        return Calc(r1, r2, theta, bata);
    }

    public int[] mbtis(MindWaveHelperMulti mwhm){
        int[] mbtis = new int[recordSetList.length];

        for(int i = 0; i<mbtis.length; i++){
            MindColorRecordSet s =  recordSetList[i];
            MindColorType r1 = s.rank(0);
            MindColorType r2 = s.score(1) != 0 ? s.rank(1) : r1;
            int theta = (int) (mwhm.Helpers[i].Theta * 100);
            int bata = (int) ((mwhm.Helpers[i].HighBeta + mwhm.Helpers[i].LowBeta) * 100 / 2);

            mbtis[i] = MindColorMBTI.Calc(r1, r2, theta, bata);
        }

        return mbtis;
    }

    public boolean isColorNull(){
        boolean b = true;
        for(MindColorRecordSet set: recordSetList){
            if(!set.isColorNull()){
                b = false; break;
            }
        }
        return b;
    }

    protected static List<MindColorType> Sort(final Map<MindColorType, Integer> map, final Map<MindColorType, Integer> score){

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
                    int sc1 = score.get(t1);
                    int sc2 = score.get(t2);
                    int ws = sc2 - sc1;
                    if(ws == 0){
                        return mMindColorThan.than(t2, t1)?1:-1;
                    }
                    return ws;
                }
                return w;
            }
        });

        return sort;
    }

}
