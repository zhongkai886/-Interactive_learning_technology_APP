//package com.example.user.interactive_learning_technology_app.widget;
//
//import android.content.Context;
//import android.util.AttributeSet;
//import android.view.MotionEvent;
//
//import java.util.HashSet;
//
//public class ViewPagerSlideLimit extends android.support.v4.view.ViewPager {
//
//    private static final String ATTR_PAGELIMT = "pagelimt";
//
//    HashSet<Integer> LimitSet = new HashSet<Integer>();
//    boolean _LimitFlag = true, direction;
//    float xDistance = 0, yDistance = 0, xLast = 0, yLast = 0;
//
//    public ViewPagerSlideLimit(Context context, AttributeSet attrs) {
//        super(context, attrs);
//
//        for (int i = 0, s = attrs.getAttributeCount(); i < s; i++) {
//            if (attrs.getAttributeName(i).equals(ATTR_PAGELIMT)) {
//                setLimit(attrs.getAttributeIntValue(i, 0));
//            }
//        }
//    }
//
////    @Override
////    public boolean onTouchEvent(MotionEvent ev) {
////    	if(_LimitFlag = LimitSet.contains(getCurrentItem()))return false;
////    	return super.onTouchEvent(ev);
////    }
//
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        if (_LimitFlag = LimitSet.contains(getCurrentItem())) {
//            final int action = ev.getAction();
//            if (action == MotionEvent.ACTION_DOWN) {
//                xLast = ev.getX();
//            } else if (action == MotionEvent.ACTION_MOVE) {
//                final float curX = ev.getX();
//                boolean paht = (curX - xLast) > 0;
//                xDistance = Math.abs(curX - xLast);
//                if (direction != paht && xDistance > 2) {
//                    direction = paht;
//                    xLast = curX;
//                }
////              Log.e("onInterceptTouchEvent", "xLast="+xLast);
////              Log.e("onInterceptTouchEvent", "curX="+curX);
////              Log.e("onInterceptTouchEvent", "xDistance="+xDistance);
////              Log.e("onInterceptTouchEvent", "===========");
//
//                if (direction == false) return false;
//            }
//        }
//        return super.onInterceptTouchEvent(ev);
//    }
//
//    //設置限制不能進行滑動的頁面
//    public void setLimit(int... args) {
//        for (int l : args) {
//            LimitSet.add(l);
//        }
//    }
//
//}
