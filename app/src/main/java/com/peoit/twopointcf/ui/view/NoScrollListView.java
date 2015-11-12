package com.peoit.twopointcf.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class NoScrollListView extends ListView {
    public NoScrollListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        int mExpandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, mExpandSpec);
   }

//    ScrollView parentScrollView;
//
//    public ScrollView getParentScrollView() {
//        return parentScrollView;
//    }
//
//    public void setParentScrollView(ScrollView parentScrollView) {
//        this.parentScrollView = parentScrollView;
//    }
//
//    float old_y=0;
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//
//        switch (ev.getAction()) {
//
//            case MotionEvent.ACTION_DOWN:
//                setParentScrollAble(false);
//            case MotionEvent.ACTION_MOVE:
//                break;
//            case MotionEvent.ACTION_UP:
//
//            case MotionEvent.ACTION_CANCEL:
//                setParentScrollAble(true);
//                break;
//            default:
//                break;
//        }
//        return super.onInterceptTouchEvent(ev);
//    }
//
//
//
//    /**
//     * @param flag
//     */
//    private void setParentScrollAble(boolean flag) {
//
//        parentScrollView.requestDisallowInterceptTouchEvent(!flag);
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()) {
//
//            case MotionEvent.ACTION_DOWN:
//                old_y=ev.getY();
//            case MotionEvent.ACTION_MOVE:
//                MyLogger.i("onInterceptTouchEvent_ACTION_MOVE"+getScrollY()+"--"+ev.getY());
////                if(old_y==0){
////                    old_y=ev.getY();
////                }
//                if(getFirstVisiblePosition()==0&&old_y-ev.getY()<-getChildAt(0).getHeight()){
//                    MyLogger.i("onInterceptTouchEvent");
//                    parentScrollView.smoothScrollBy(0, (int) (old_y-ev.getY()));
//                    //old_y=ev.getY();
//                    //parentScrollView.smoothScrollTo(0,0);
//                    setParentScrollAble(true);
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//
//            case MotionEvent.ACTION_CANCEL:
//                setParentScrollAble(true);
//                break;
//            default:
//                break;
//        }
//        return super.onTouchEvent(ev);
//    }
}
