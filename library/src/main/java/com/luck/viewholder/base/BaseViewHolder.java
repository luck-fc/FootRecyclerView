package com.luck.viewholder.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luck.view.R;

import java.util.Map;

/**
 * 作者：luck on 2016/1/17 17:00
 * 邮箱：fc_dream@163.com
 * ViewHolder 基类
 * 推荐使用 bindDataAndListener 来绑定数据
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {

    protected Context context;

    public BaseViewHolder(ViewGroup parent, @LayoutRes int layoutid) {
        super(LayoutInflater.from(parent.getContext()).inflate(layoutid, parent, false));
        this.context = itemView.getContext();
    }

    /**
     * 得到当前view绑定的activity
     * @return
     */
    protected Activity getActivity(){
        return (Activity) context;
    }

    /**
     * itemView的子view 的 findViewById
     * @param viewId id
     * @return
     */
    protected <T extends View> T getView(int viewId) {
        return (T) (itemView.findViewById(viewId));
    }

    /**
     * 非itemView 的 findViewById
     * @param view 需要findViewById的父View
     * @param viewId id
     * @param <T> 指定要转换的view类型
     * @return 返回View
     */
    protected <T extends View> T getView(View view,int viewId) {
        if(view==null){
            return null;
        }
        return (T) (view.findViewById(viewId));
    }

    /**
     * 支持 String Int Boolean 基本类型的数据传递跳转指定activity
     * @param map 参数
     * @param toclass 跳转的类型
     */
    protected void goActivityByBaseType(Map<String,Object> map, Class<?> toclass){
        Intent intent = new Intent(getActivity(),toclass);
        if(map!=null){
            for (String key : map.keySet()) {
                Object o = map.get(key);
                if (o instanceof Integer) {
                    int value = ((Integer) o).intValue();
                    intent.putExtra(key, value);
                } else if (o instanceof Boolean) {
                    boolean value = ((Boolean) o).booleanValue();
                    intent.putExtra(key, value);
                } else {
                    intent.putExtra(key, ""+o);
                }
            }
        }
        getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        getActivity().startActivity(intent);
    }

}
