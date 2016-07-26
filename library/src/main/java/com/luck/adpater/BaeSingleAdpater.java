package com.luck.adpater;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.luck.imp.IFootViewAdapter;

/**
 * 作者：luck on 2016/7/26 11:11
 * 邮箱：fc_dream@163.com
 * FootRecyclerView 只有一个ViewHolder的Adpater
 */
public abstract class BaeSingleAdpater<T> extends FootViewAdapter<T>{

    public BaeSingleAdpater(Activity activity) {
        super(activity,null);
        isShwoNoDataFoot(false);
    }
    public BaeSingleAdpater(Activity activity, IFootViewAdapter iFVA) {
        super(activity,iFVA);
    }

    @Override
    public RecyclerView.ViewHolder onNewCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder mViewHolder=null;
        if(viewType==0){
            mViewHolder = onNewCreateViewHolder(parent);
        }
        return mViewHolder;
    }

    public abstract RecyclerView.ViewHolder onNewCreateViewHolder(ViewGroup parent);

    @Override
    public int getNewItemCount() {
        return data.size();
    }

    @Override
    public int getNewItemViewType(int position) {
        return 0;
    }
}
