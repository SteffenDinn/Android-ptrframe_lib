package com.dinn.ptrframe.header;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.PtrUIHandlerHook;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * Created by SteffenDinn on 2018/10/17.
 * Copyright (c) WANZHONG INTERNET GROUP. All rights reserved.
 */
public class MaterialNoneHeader extends View implements PtrUIHandler {

    private PtrFrameLayout mPtrFrameLayout;

    private Animation mScaleAnimation = new Animation() {
        @Override
        public void applyTransformation(float interpolatedTime, Transformation t) {
            invalidate();
        }
    };

    public MaterialNoneHeader(Context context) {
        super(context);
    }

    public MaterialNoneHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MaterialNoneHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setPtrFrameLayout(PtrFrameLayout layout) {

        final PtrUIHandlerHook mPtrUIHandlerHook = new PtrUIHandlerHook() {
            @Override
            public void run() {
                startAnimation(mScaleAnimation);
            }
        };

        mScaleAnimation.setDuration(200);
        mScaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mPtrUIHandlerHook.resume();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mPtrFrameLayout = layout;
        mPtrFrameLayout.setRefreshCompleteHook(mPtrUIHandlerHook);
    }

    public void setColorSchemeColors() {
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    }

    /**
     * When the content view has reached top and refresh has been completed, view will be reset.
     *
     * @param frame
     */
    @Override
    public void onUIReset(PtrFrameLayout frame) {
    }

    /**
     * prepare for loading
     *
     * @param frame
     */
    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
    }

    /**
     * perform refreshing UI
     *
     * @param frame
     */
    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
    }

    /**
     * perform UI after refresh
     *
     * @param frame
     */
    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
    }
}
