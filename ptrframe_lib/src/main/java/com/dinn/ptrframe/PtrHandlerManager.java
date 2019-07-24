package com.dinn.ptrframe;

import android.view.View;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Ptr的Handler管理
 * Created by Steffen on 2017/12/21.
 */
public abstract class PtrHandlerManager implements PtrHandler {
    private View viewContent;

    public PtrHandlerManager(View content) {
        viewContent = content;
    }

    /**
     * 是否刷新
     *
     * @param frame
     * @param content
     * @param header
     * @return 是否刷新
     */
    @Override
    public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
        // 如果content是一个TextView，则可以直接返回true，表示可以进入下拉状态；
        // 如果content是一个ListView/RecyclerView，则当第一条Item在顶部的时候，返回true，表示可以进入下拉状态；
        // 如果content是一个ScrollView，则当ScrollView滑动到顶部顶部的时候，返回true，表示可以进入下拉状态；
        return PtrDefaultHandler.checkContentCanBePulledDown(frame, viewContent, header);
    }

    /**
     * 开始刷新
     *
     * @param frame
     */
    @Override
    public abstract void onRefreshBegin(PtrFrameLayout frame);
}
