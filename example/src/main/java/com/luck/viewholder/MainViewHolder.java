package com.luck.viewholder;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.luck.entity.Model;
import com.luck.ui.R;
import com.luck.viewholder.base.BaseViewHolder;

/**
 * 作者：luck on 2016/7/26 12:55
 * 邮箱：fc_dream@163.com
 * FootRecyclerView 首页recyclerview展示的viewholder
 */
public class MainViewHolder extends BaseViewHolder {

    private TextView viewholder_main_text1,viewholder_main_text2;

    public MainViewHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_main, parent, false));
        viewholder_main_text1 = getView(R.id.viewholder_main_text1);
        viewholder_main_text2 = getView(R.id.viewholder_main_text2);
    }

    public void bindDataAndListener(Model data){
        viewholder_main_text1.setText(data.getName());
        viewholder_main_text2.setText(data.getDescribe());
    }
}
