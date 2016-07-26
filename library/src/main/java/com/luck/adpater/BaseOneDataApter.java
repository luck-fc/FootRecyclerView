package com.luck.adpater;

import android.app.Activity;

/**
 * 作者：luck on 2016/7/26 10:56
 * 邮箱：fc_dream@163.com
 * BaseOneDataApter (数据的总行数 = data.size()的适配器）
 */
public abstract class BaseOneDataApter<T> extends BaseViewAdapter{

    public BaseOneDataApter(Activity activity) {
        super(activity);
    }

    @Override
    public int getNewItemCount() {
        return data.size();
    }

}
