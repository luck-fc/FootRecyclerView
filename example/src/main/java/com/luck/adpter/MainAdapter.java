package com.luck.adpter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.luck.adpater.BaeSingleAdpater;
import com.luck.entity.Model;
import com.luck.viewholder.MainViewHolder;

/**
 * 作者：luck on 2016/7/26 10:20
 * 邮箱：fc_dream@163.com
 * BaseViewAdapter example
 */
public class MainAdapter extends BaeSingleAdpater<Model> {

    public MainAdapter(Activity activity) {
        super(activity);
    }

    @Override
    public void onNewBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MainViewHolder){
            MainViewHolder vh = (MainViewHolder) holder;
            vh.bindDataAndListener(data.get(position));
        }
    }

    @Override
    public RecyclerView.ViewHolder onNewCreateViewHolder(ViewGroup parent) {
        return new MainViewHolder(parent);
    }
}
