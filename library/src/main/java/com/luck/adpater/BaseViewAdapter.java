package com.luck.adpater;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：luck on 2016/1/6 15:34
 * 邮箱：fc_dream@163.com
 * AS_TuHu_android
 * 拥有加载更多没有Foot的适配器
 */
public abstract class BaseViewAdapter<T> extends RecyclerView.Adapter {

    protected List<T> data;
    protected Activity mActivity;

    public BaseViewAdapter(Activity activity) {
        this.mActivity = activity;
        this.data = new ArrayList<>();
    }

    /**
     * 获取所有数据
     * @return
     */
    public List<T> getData() {
        return data;
    }

    /**
     * 获取数据的单个对象
     * @param p 下标
     * @return 单个数据对象
     */
    public T getDataT(int p) {
        if(p>=getItemCount() || p<0){
            return null;
        }
        return data.get(p);
    }

    /**
     * 修改data的某个对象下标的对象
     * @param p 下标
     * @param t 对象
     */
    public void setDataT(int p, T t) {
        if(p<getItemCount() && p>0) {
            data.set(p, t);
            notifyItemChanged(p);
        }
    }
    /**
     * 清空所有数据并刷新
     */
    public void clear() {
        if (data != null) {
            data.clear();
            notifyDataSetChanged();
        }
    }

    /**
     * 添加集合对象数据
     * @param datas 需要添加的集合数据
     */
    public void addData(List<T> datas) {
        if (datas != null) {
            data.addAll(datas);
            notifyDataSetChanged();
        }
    }

    /**
     * 添加数据不刷新 （一般用于会继续添加数据的情况下）
     * @param datas 需要添加的集合数据
     */
    public void addDataNotNotify(List<T> datas) {
        if (datas != null) {
            data.addAll(datas);
        }
    }

    /**
     * 重新设置更新数据
     * @param data 需要添加的集合数据
     */
    public void setData(List<T> data) {
        if (data != null) {
            this.data = data;
        } else {
            this.data.clear();
        }
        notifyDataSetChanged();
    }

    public int getItemCount() {
        return getNewItemCount();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh = onNewCreateViewHolder(parent, viewType);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        onNewBindViewHolder(holder, position);
    }

    @Override
    public int getItemViewType(int position) {
        int itemtype_result = getNewItemViewType(position);
        return itemtype_result;
    }

    /**
     * 要显示的item 同 NewCreateViewHolder
     * @param parent
     * @param viewType 对应的viewType
     * @return
     */
    public abstract RecyclerView.ViewHolder onNewCreateViewHolder(ViewGroup parent, int viewType);

    /**
     * 绑定item 同 onNewBindViewHolder
     * @param holder   对应viewType的holder
     * @param position list的下标
     */
    public abstract void onNewBindViewHolder(RecyclerView.ViewHolder holder, final int position);

    /**
     * 要展示的item个数 同 getItemCount
     * @return
     */
    public abstract int getNewItemCount();

    /**
     * 用于区分要展示的ViewHolder的类型 同 getItemViewType
     * @param position
     * @return
     */
    public abstract int getNewItemViewType(int position);



}
