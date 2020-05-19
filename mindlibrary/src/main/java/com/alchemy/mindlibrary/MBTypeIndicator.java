package com.alchemy.mindlibrary;

/**
 * 邁爾斯-布里格斯性格分類法
 * <p>
 * ESTJ: Extraversion (E), Sensing (S), Thinking (T), Judging (J)<BR>
 * INFP: Introversion (I), iNtuition (N), Feeling (F), Perceiving (P)
 * <p>
 * @params
 * 性格: mbti<BR>
 * 動態: dominant > auxiliary > tertiary > inferior
 */
public class MBTypeIndicator {

    /**
     * 0b1000 4位元<BR>
     * 0 = Extraversion 外向型
     * 1 = Introversion 內向型
     */
    final static private int EI = 8;

    /**
     * 0b0100 3位元<BR>
     * 0 = Sensing 實感型
     * 1 = iNtuition 直覺型
     */
    final static private int SN = 4;

    /**
     * 0b0010 2位元<BR>
     * 0 = Thinking 思考型
     * 1 = Feeling 情感型
     */
    final static private int TF = 2;

    /**
     * 0b0001 1位元<BR>
     * 0 = Judging 判斷型
     * 1 = Perceiving 理解型
     */
    final static private int JP = 1;

    /**性格 外向 實感 思考 判斷*/
    final static public int ESTJ = 0;// 0000
    /**性格 外向 實感 思考 理解*/
    final static public int ESTP = 1;// 0001
    /**性格 外向 實感 情感 判斷*/
    final static public int ESFJ = 2;// 0010
    /**性格 外向 實感 情感 理解*/
    final static public int ESFP = 3;// 0011
    /**性格 外向 直覺 思考 判斷*/
    final static public int ENTJ = 4;// 0100
    /**性格 外向 實感 思考 理解*/
    final static public int ENTP = 5;// 0101
    /**性格 外向 直覺 情感 判斷*/
    final static public int ENFJ = 6;// 0110
    /**性格 外向 直覺 情感 理解*/
    final static public int ENFP = 7;// 0111
    /**性格 內向 實感 思考 判斷*/
    final static public int ISTJ = 8;// 1000
    /**性格 內向 實感 思考 理解*/
    final static public int ISTP = 9;// 1001
    /**性格 內向 實感 情感 判斷*/
    final static public int ISFJ = 10;//1010
    /**性格 內向 實感 情感 理解*/
    final static public int ISFP = 11;//1011
    /**性格 內向 直覺 思考 判斷*/
    final static public int INTJ = 12;//1100
    /**性格 內向 直覺 思考 理解*/
    final static public int INTP = 13;//1101
    /**性格 內向 直覺 情感 判斷*/
    final static public int INFJ = 14;//1110
    /**性格 內向 直覺 情感 理解*/
    final static public int INFP = 15;//1111

    /**動態 外向 實感*/
    final static public int Se = 0;
    /**動態 內向 實感*/
    final static public int Si = 1;
    /**動態 外向 直覺*/
    final static public int Ne = 2;
    /**動態 內向 直覺*/
    final static public int Ni = 3;
    /**動態 外向 思考*/
    final static public int Te = 4;
    /**動態 內向 思考*/
    final static public int Ti = 5;
    /**動態 外向 情感*/
    final static public int Fe = 6;
    /**動態 內向 情感*/
    final static public int Fi = 7;

    /**性格*/
    final public int mbti;
    /**動態 主導*/
    final public int dominant;
    /**動態 附屬*/
    final public int auxiliary;
    /**動態 三級*/
    final public int tertiary;
    /**動態 四級*/
    final public int inferior;

    public MBTypeIndicator(int mbti){
        this.mbti = mbti;
        dominant = dominant();
        auxiliary = auxiliary();
        tertiary = tertiary();
        inferior = inferior();
    }

    /**主導*/
    int dominant(){

        int d = (mbti & EI) == 0 ? 0 : 1;
        int jp = ((mbti & EI) == 0) ? mbti ^ 0xF : mbti;
        if((jp & JP) == 0){
            d |= (mbti & SN) == 0 ? 0 : 2;
        }else{
            d |= (mbti & TF) == 0 ? 0 : 2;
            d |= 4;
        }
        return d;
    }

    /**附屬*/
    int auxiliary(){

        int d = (mbti & EI) != 0 ? 0 : 1;
        int jp = ((mbti & EI) == 0) ? mbti ^ 0xF : mbti;
        if((jp & JP) != 0){
            d |= (mbti & SN) == 0 ? 0 : 2;
        }else{
            d |= (mbti & TF) == 0 ? 0 : 2;
            d |= 4;
        }
        return d;
    }

    /**三級*/
    int tertiary(){

        int d = (mbti & EI) == 0 ? 0 : 1;
        int jp = ((mbti & EI) == 0) ? mbti ^ 0xF : mbti;
        if((jp & JP) != 0){
            d |= (mbti & SN) != 0 ? 0 : 2;
        }else{
            d |= (mbti & TF) != 0 ? 0 : 2;
            d |= 4;
        }
        return d;
    }

    /**四級*/
    int inferior(){

        int d = (mbti & EI) != 0 ? 0 : 1;
        int jp = ((mbti & EI) == 0) ? mbti ^ 0xF : mbti;
        if((jp & JP) == 0){
            d |= (mbti & SN) != 0 ? 0 : 2;
        }else{
            d |= (mbti & TF) != 0 ? 0 : 2;
            d |= 4;
        }
        return d;
    }

    /**Extraversion 外向型*/
    public boolean isExtraversion(){
        return (mbti & EI) == 0;
    }

    /**Introversion 內向型*/
    public boolean isIntroversion(){
        return (mbti & EI) > 0;
    }

    /**Sensing 實感型*/
    public boolean isSensing(){
        return (mbti & SN) == 0;
    }

    /**iNtuition 直覺型*/
    public boolean isiNtuition(){
        return (mbti & SN) > 0;
    }

    /**Thinking 思考型*/
    public boolean isThinking(){
        return (mbti & TF) == 0;
    }

    /**Feeling 情感型*/
    public boolean isFeeling(){
        return (mbti & TF) > 0;
    }

    /**Judging 判斷型*/
    public boolean isJudging(){
        return (mbti & JP) == 0;
    }

    /**Perceiving 理解型*/
    public boolean isPerceiving(){
        return (mbti & JP) > 0;
    }

    @Override
    public String toString(){
        String log = "";
        log += "mbti:";
        switch(mbti){
            case ESTJ:
                log += "ESTJ";
                break;
            case ESTP:
                log += "ESTP";
                break;
            case ESFJ:
                log += "ESFJ";
                break;
            case ESFP:
                log += "ESFP";
                break;
            case ENTJ:
                log += "ENTJ";
                break;
            case ENTP:
                log += "ENTP";
                break;
            case ENFJ:
                log += "ENFJ";
                break;
            case ENFP:
                log += "ENFP";
                break;
            case ISTJ:
                log += "ISTJ";
                break;
            case ISTP:
                log += "ISTP";
                break;
            case ISFJ:
                log += "ISFJ";
                break;
            case ISFP:
                log += "ISFP";
                break;
            case INTJ:
                log += "INTJ";
                break;
            case INTP:
                log += "INTP";
                break;
            case INFJ:
                log += "INFJ";
                break;
            case INFP:
                log += "INFP";
                break;
        }

        for(int i = 0, d = 0; i<4; i++){

            switch(i){
                case 0:
                    d = dominant;
                    log += "\ndominant:";
                    break;
                case 1:
                    d = auxiliary;
                    log += "\nauxiliary:";
                    break;
                case 2:
                    d = tertiary;
                    log += "\ntertiary:";
                    break;
                case 3:
                    d = inferior;
                    log += "\ninferior:";
                    break;
            }

            switch(d){
                case Se:
                    log += "Se";
                    break;
                case Si:
                    log += "Si";
                    break;
                case Ne:
                    log += "Ne";
                    break;
                case Ni:
                    log += "Ni";
                    break;
                case Te:
                    log += "Te";
                    break;
                case Ti:
                    log += "Ti";
                    break;
                case Fe:
                    log += "Fe";
                    break;
                case Fi:
                    log += "Fi";
                    break;
            }
        }
        return log;
    }

    public static String toStringMBTI(int mbti){
        String log = "";
        switch(mbti){
            case ESTJ:
                log += "ESTJ";
                break;
            case ESTP:
                log += "ESTP";
                break;
            case ESFJ:
                log += "ESFJ";
                break;
            case ESFP:
                log += "ESFP";
                break;
            case ENTJ:
                log += "ENTJ";
                break;
            case ENTP:
                log += "ENTP";
                break;
            case ENFJ:
                log += "ENFJ";
                break;
            case ENFP:
                log += "ENFP";
                break;
            case ISTJ:
                log += "ISTJ";
                break;
            case ISTP:
                log += "ISTP";
                break;
            case ISFJ:
                log += "ISFJ";
                break;
            case ISFP:
                log += "ISFP";
                break;
            case INTJ:
                log += "INTJ";
                break;
            case INTP:
                log += "INTP";
                break;
            case INFJ:
                log += "INFJ";
                break;
            case INFP:
                log += "INFP";
                break;
        }
        return log;
    }
}
