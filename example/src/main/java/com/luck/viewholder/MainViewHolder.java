package com.luck.viewholder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.luck.entity.ModelEntity;
import com.luck.ui.ExampleActivity;
import com.luck.ui.R;
import com.luck.viewholder.base.BaseViewHolder;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者：luck on 2016/7/26 12:55
 * 邮箱：fc_dream@163.com
 * FootRecyclerView 首页recyclerview展示的viewholder
 */
public class MainViewHolder extends BaseViewHolder {

    private TextView viewholder_main_text1,viewholder_main_text2;

    public MainViewHolder(ViewGroup parent) {
        super(parent,R.layout.viewholder_main);
        viewholder_main_text1 = getView(R.id.viewholder_main_text1);
        viewholder_main_text2 = getView(R.id.viewholder_main_text2);
    }

    public void bindDataAndListener(final ModelEntity data){
        viewholder_main_text1.setText(data.getName());
        viewholder_main_text2.setText(data.getDescribe());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String,Object> map=new HashMap<>();
                map.put("type",data.getId());
                goActivityByBaseType(map,ExampleActivity.class);
            }
        });
    }
}
