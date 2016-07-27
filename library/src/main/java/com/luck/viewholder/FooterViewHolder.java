package com.luck.viewholder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.luck.adpater.FootViewAdapter;
import com.luck.finalenum.EFootType;
import com.luck.imp.IFootViewAdapter;
import com.luck.view.R;
import com.luck.viewholder.base.BaseViewHolder;

/**
 * 作者：luck on 2016/1/15 16:53
 * 邮箱：fc_dream@163.com
 * 加载更多的 ViewHolder
 */
public class FooterViewHolder extends BaseViewHolder {
    private LinearLayout footview_layout;
    private ProgressBar footview_pro;
    private TextView footview_text;

    public FooterViewHolder(ViewGroup parent) {
        super(parent,R.layout.recycler_footerview);
        footview_layout = getView(R.id.footview_layout);
        footview_text = getView(R.id.footview_text);
        footview_pro = getView(R.id.footview_pro);
    }

    public void bindDataandListener(final FootViewAdapter footViewAdapter, boolean isFirst, final IFootViewAdapter iFVA, int type, String foottext) {
        isShow(isFirst);
        boolean isShow = false;
        footview_layout.setOnClickListener(null);
        if (type == EFootType.FOOT_LOADING_ADD || type == EFootType.FOOT_ERROR_LOADDATA) {
            footview_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (iFVA != null) {
                        footViewAdapter.setFootType(EFootType.FOOT_LOADING_MORE);
                        iFVA.onLoadMore();
                    }
                }
            });
        }
        if (type == EFootType.FOOT_LOADING_MORE) {
            isShow = true;
        }
        footview_pro.setVisibility(isShow ? View.VISIBLE : View.GONE);
        footview_text.setText(foottext);
    }

    public void isShow(boolean isFirst) {
        if (isFirst) {
            footview_layout.setVisibility(View.GONE);
        } else {
            footview_layout.setVisibility(View.VISIBLE);
        }
    }
}
