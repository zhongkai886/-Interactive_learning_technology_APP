package com.alchemy.mindlibrary;

public class MindColorMBTI {

    public static int Calc(MindColorType c1, MindColorType c2, int t, int b){
        switch(c1){
            case Orange:
                switch(c2){
                    case Yellow:
                    case Orange:
                        if(t > b) return MBTypeIndicator.ESTP;
                        else return MBTypeIndicator.ESFP;
                    case Green:
                        return MBTypeIndicator.ISTP;
                    case Blue:
                        return MBTypeIndicator.ISFP;
                }
                break;
            case Green:
                switch(c2){
                    case Orange:
                        return MBTypeIndicator.INTP;
                    case Green:
                        if(t > THRESHOLD_THETA){

                            if(t > b) return MBTypeIndicator.INTJ;
                            else return MBTypeIndicator.INFJ;
                        }else{

                            if(t > b) return MBTypeIndicator.ENTP;
                            else return MBTypeIndicator.ENFP;
                        }
                    case Blue:
                        if(t > THRESHOLD_THETA) return MBTypeIndicator.INTJ;
                        else return MBTypeIndicator.ENTP;
                    case Yellow:
                        return MBTypeIndicator.ENTJ;
                }
                break;
            case Blue:
                switch(c2){
                    case Orange:
                        return MBTypeIndicator.INFP;
                    case Green:
                        if(t > THRESHOLD_THETA) return MBTypeIndicator.INFJ;
                        else return MBTypeIndicator.ENFP;
                    case Blue:
                        if(t > THRESHOLD_THETA){

                            if(t > b) return MBTypeIndicator.INTJ;
                            else return MBTypeIndicator.INFJ;
                        }else{

                            if(t > b) return MBTypeIndicator.ENTP;
                            else return MBTypeIndicator.ENFP;
                        }
                    case Yellow:
                        return MBTypeIndicator.ENFJ;
                }
                break;
            case Yellow:
                switch(c2){
                    case Green:
                        return MBTypeIndicator.ESTJ;
                    case Blue:
                        return MBTypeIndicator.ESFJ;
                    case Orange:
                    case Yellow:
                        if(t > b) return MBTypeIndicator.ISTJ;
                        else return MBTypeIndicator.ISFJ;
                }
                break;
        }
        return -1;
    }

    static int THRESHOLD_THETA = 75;

    public static void SetUp(int t){
        THRESHOLD_THETA = t;
    }
}
