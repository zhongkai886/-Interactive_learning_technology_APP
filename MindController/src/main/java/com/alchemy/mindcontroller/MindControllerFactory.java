package com.alchemy.mindcontroller;

import android.content.Context;
import android.os.Handler;

public class MindControllerFactory {

    public static MindController obtain(Context context, Handler handler){
        return new com.alchemy.wjk.mind.MindControllerImpl(context, handler);
    }

}
