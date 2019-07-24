package com.dinn.ptrframe;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.MaterialHeader;
import in.srain.cube.views.ptr.util.PtrCLog;
import in.srain.cube.views.ptr.util.PtrLocalDisplay;

/**
 * 下拉刷新 自定义PtrFrameLayout
 * <p/>Style：a circle
 * <p/>Created by Steffen on 2017/12/21.
 */
public class PtrFrameALayout extends PtrFrameLayout {

    public enum MoveConflict {
        NONE, HORIZONTAL
    }

    private MoveConflict moveConflict = MoveConflict.NONE;

    public PtrFrameALayout(Context context) {
        super(context);
        initPtrFrameLayout();
    }

    public PtrFrameALayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPtrFrameLayout();
    }

    public PtrFrameALayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initPtrFrameLayout();
    }

    /**
     * 初始化
     */
    protected void initPtrFrameLayout() {
        MaterialHeader header = new MaterialHeader(this.getContext());
        int[] colors = getResources().getIntArray(R.array.google_colors);
        header.setColorSchemeColors(colors);
        header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        header.setPadding(0, PtrLocalDisplay.dp2px(15), 0, 20);
        header.setPtrFrameLayout(this);

        PtrCLog.setLogLevel(PtrCLog.LEVEL_VERBOSE); // 屏蔽所有日志

        this.setLoadingMinTime(1000);
        this.setDurationToCloseHeader(1500);
        this.setHeaderView(header);
        this.addPtrUIHandler(header);
    }

    /**
     * 收齐下拉
     **/
    public void closePtr() {
        refreshComplete();
    }

    private int downX = 0, downY = 0;

    @Override
    public boolean dispatchTouchEvent(MotionEvent e) {
        if (moveConflict == MoveConflict.NONE) return super.dispatchTouchEvent(e);
        int x = (int) e.getX();
        int y = (int) e.getY();
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //将按下时的坐标存储
                downX = x;
                downY = y;
                break;
            case MotionEvent.ACTION_UP:
                //获取到距离差
                downX = 0;
                downY = 0;
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = Math.abs(x - downX);
                int dy = Math.abs(y - downY);
                downX = x;
                downY = y;

                if (dx > 1.5 * dy) {
                    return super.dispatchTouchEventSupper(e);
                } else {
                    return super.dispatchTouchEvent(e);
                }
        }
        return super.dispatchTouchEvent(e);
    }

    /**
     * 设置左滑/右滑冲突解决
     * @param type MoveConflict
     */
    public void setMoveConflict(MoveConflict type) {
        moveConflict = type;
    }
}
