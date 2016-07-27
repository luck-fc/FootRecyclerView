package com.luck.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.luck.adpater.FootViewAdapter;
import com.luck.entity.JurnalismEntity;
import com.luck.imp.IFootViewAdapter;

/**
 * 作者：luck on 2016/7/27 09:19
 * 邮箱：fc_dream@163.com
 * FootRecyclerView
 */
public class JurnalismAdapter extends FootViewAdapter<JurnalismEntity>{

    public JurnalismAdapter(Activity activity, IFootViewAdapter iFVA) {
        super(activity, iFVA);
    }

    @Override
    public RecyclerView.ViewHolder onNewCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onNewBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getNewItemCount() {
        return 0;
    }

    @Override
    public int getNewItemViewType(int position) {
        return 0;
    }

}
