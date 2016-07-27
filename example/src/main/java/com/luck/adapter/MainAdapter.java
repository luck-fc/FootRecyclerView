package com.luck.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.luck.adpater.BaseViewAdapter;
import com.luck.entity.ModelEntity;
import com.luck.viewholder.MainViewHolder;

/**
 * 作者：luck on 2016/7/26 10:20
 * 邮箱：fc_dream@163.com
 * BaseViewAdapter example
 */
public class MainAdapter extends BaseViewAdapter<ModelEntity> {

    public MainAdapter(Activity activity) {
        super(activity);
    }

    @Override
    public RecyclerView.ViewHolder onNewCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainViewHolder(parent);
    }

    @Override
    public void onNewBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MainViewHolder){
            MainViewHolder vh = (MainViewHolder) holder;
            vh.bindDataAndListener(data.get(position));
        }
    }

    @Override
    public int getNewItemCount() {
        return data.size();
    }

    @Override
    public int getNewItemViewType(int position) {
        return 0;
    }
}
