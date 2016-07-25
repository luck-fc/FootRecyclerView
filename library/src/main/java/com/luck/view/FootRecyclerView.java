package com.luck.view;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.luck.adpater.FootViewAdapter;
import com.luck.finalenum.EFootType;
import com.luck.imp.IFootViewAdapter;


/**
 * 作者：luck on 2016/1/6 17:10
 * 邮箱：fc_dream@163.com
 * 支持加载更多的RecyclerView
 */
public class FootRecyclerView extends RecyclerView {

    private Context mcontext;
    private LinearLayoutManager mLayoutManager;

    public FootRecyclerView(Context context) {
        this(context, null);
    }

    public FootRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FootRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mcontext = context;
    }

    /**
     * 上下滑动无加载更多
     * @param mAdapter 适配器
     */
    public void init(Adapter mAdapter) {
        mLayoutManager = new LinearLayoutManager(mcontext);
        setAdapter(mAdapter,mLayoutManager);
    }

    /**
     * 左右滑动无加载更多
     * @param mAdapter 适配器
     */
    public void initAbout(Adapter mAdapter) {
        mLayoutManager = new LinearLayoutManager(mcontext);
        mLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        setAdapter(mAdapter,mLayoutManager);
    }

    /**
     * 上下有加载更多
     * @param mAdapter 适配器
     */
    public void init(final FootViewAdapter mAdapter) {
        init(mAdapter,null);
    }

    /**
     * 上下有加载更多 如果需要设置 addOnScrollListener 请加入第3个参数
     * @param mAdapter 适配器
     * @param onScrollListener 滑动事件回调
     */
    public void init(final FootViewAdapter mAdapter,final OnScrollListener onScrollListener) {
        mLayoutManager = new LinearLayoutManager(mcontext);
        setAdapter(mAdapter,mLayoutManager);
        addOnScrollListener(new OnScrollListener() {
            private int lastVisibleItem;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView,
                                             int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == mAdapter.getItemCount() && mAdapter.getItemCount() > 1) {
                    IFootViewAdapter iFVA=mAdapter.getmIFVA();
                    if (iFVA != null) {
                        mAdapter.setFootType(EFootType.FOOT_LOADING_MORE);
                        iFVA.onLoadMore();
                    }
                }
                if(onScrollListener!=null){
                    onScrollListener.onScrollStateChanged(recyclerView,newState);
                }
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
                if(onScrollListener!=null){
                    onScrollListener.onScrolled(recyclerView,dx,dy);
                }
            }
        });
    }

    /**
     * 初始化RecyclerView
     * @param mAdapter 适配器
     * @param layoutManager 布局管理
     */
    private void setAdapter(Adapter mAdapter,RecyclerView.LayoutManager layoutManager){
        if (mAdapter == null) {
            return;
        }
        setLayoutManager(layoutManager);
        setItemAnimator(new DefaultItemAnimator());
        setAdapter(mAdapter);
    }

    /**
     * 滑动RecyclerView到某个item的位置
     * @param position
     */
    public void scrollToPosition(int position) {
        if (mLayoutManager != null) {
            mLayoutManager.scrollToPositionWithOffset(position, 0);
        }
    }
}
