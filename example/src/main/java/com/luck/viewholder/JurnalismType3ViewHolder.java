package com.luck.viewholder;

import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.luck.entity.JurnalismEntity;
import com.luck.ui.R;
import com.luck.viewholder.base.BaseViewHolder;

/**
 * 作者：luck on 2016/7/27 09:30
 * 邮箱：fc_dream@163.com
 * FootRecyclerView
 */
public class JurnalismType3ViewHolder extends BaseViewHolder{

    private TextView viewholder_jurnalism_text1,viewholder_jurnalism_text2,viewholder_jurnalism_text3;
    private LinearLayout viewholder_jurnalism_layout1;

    public JurnalismType3ViewHolder(ViewGroup parent) {
        super(parent, R.layout.viewholder_jurnalismtype3);
        viewholder_jurnalism_text1 = getView(R.id.viewholder_jurnalism_text1);
        viewholder_jurnalism_text2 = getView(R.id.viewholder_jurnalism_text2);
        viewholder_jurnalism_text3 = getView(R.id.viewholder_jurnalism_text3);
        viewholder_jurnalism_layout1 = getView(R.id.viewholder_jurnalism_layout1);
    }

    public void bindDataAndListener(final JurnalismEntity data){
        viewholder_jurnalism_text1.setText(data.getTitlte());
        viewholder_jurnalism_text2.setText(data.getSource());
        viewholder_jurnalism_text3.setText(data.getTime());
        viewholder_jurnalism_layout1.removeAllViews();
        viewholder_jurnalism_layout1.addView(new JurnalismType1ViewHolder(viewholder_jurnalism_layout1).itemView);
        viewholder_jurnalism_layout1.addView(new JurnalismType2ViewHolder(viewholder_jurnalism_layout1).itemView);
        viewholder_jurnalism_layout1.addView(new JurnalismType3ViewHolder(viewholder_jurnalism_layout1).itemView);
    }

}
