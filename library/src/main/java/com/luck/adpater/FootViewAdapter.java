package com.luck.adpater;

import android.app.Activity;
import android.support.annotation.IntDef;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.luck.finalenum.EFootType;
import com.luck.imp.IFootViewAdapter;
import com.luck.view.R;
import com.luck.viewholder.FooterViewHolder;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;


/**
 * 作者：luck on 2016/1/6 15:34
 * 邮箱：fc_dream@163.com
 * AS_TuHu_android
 * 拥有加载更多的适配器
 */
public abstract class FootViewAdapter<T> extends RecyclerView.Adapter {

    protected List<T> data;
    protected Activity mActivity;

    /**
     * 获取所有数据
     * @return
     */
    public List<T> getData() {
        return data;
    }

    /**
     * 开启关闭Foot
     * @param enabledFoot 为 false 时 不显示Foot
     */
    public void setEnabledFoot(boolean enabledFoot) {
        if (enabledFoot != isEnabledFoot) {
            this.isEnabledFoot = enabledFoot;
            notifyDataSetChanged();
        }
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
     * 开启关闭显示没有更多数据
     * @param enabledFoot 为 false 时 不显示没有更多，但加载更多和点击加载会显示
     */
    public void isShwoNoDataFoot(boolean enabledFoot) {
        if (enabledFoot != isShwoNoDataFoot) {
            this.isShwoNoDataFoot = enabledFoot;
            if (!isShwoNoDataFoot) {
                isCloseShwoNoDataFoot = true;
            }
        }
    }

    private boolean isEnabledFoot = true, isShwoNoDataFoot = true, isCloseShwoNoDataFoot = false;

    public IFootViewAdapter getmIFVA() {
        return mIFVA;
    }

    protected IFootViewAdapter mIFVA;

    public FootViewAdapter(Activity activity, IFootViewAdapter iFVA) {
        this.mActivity = activity;
        this.mIFVA = iFVA;
        this.foottext = mActivity.getResources().getString(R.string.loading_manual);
        this.data = new ArrayList<>();
    }

    @IntDef({EFootType.FOOT_LOADING_ADD, EFootType.FOOT_ERROR_LOADDATA,EFootType.FOOT_LOADING_MORE,EFootType.FOOT_NO_LOADDATA})
    @Retention(RetentionPolicy.SOURCE)
    private @interface FootType{}
    /**
     * FOOT_LOADING_ADD 点击加载更多
     * FOOT_LOADING_MORE 正在加载更多
     * FOOT_NO_LOADDATA 没有更多数据
     * FOOT_ERROR_LOADDATA 加载数据失败
     */
    @FootType
    public void setFootType(int type) {
        if (!isEnabledFoot) {
            return;
        }
        if (this.type == EFootType.FOOT_NO_LOADDATA && type == EFootType.FOOT_LOADING_MORE) {//如果是没有更多数据就不能显示正在加载了
            return;
        }
        if (isCloseShwoNoDataFoot) {
            isShwoNoDataFoot(true);
        }
        this.type = type;
        switch (type) {
            case EFootType.FOOT_LOADING_ADD:
                this.foottext = mActivity.getResources().getString(R.string.loading_manual);
                break;
            case EFootType.FOOT_LOADING_MORE:
                this.foottext = mActivity.getResources().getString(R.string.loading_more);
                break;
            case EFootType.FOOT_NO_LOADDATA:
                this.foottext = mActivity.getResources().getString(R.string.loading_end);
                if (isCloseShwoNoDataFoot) {
                    isShwoNoDataFoot(false);
                }
                break;
            case EFootType.FOOT_ERROR_LOADDATA:
                this.foottext = mActivity.getResources().getString(R.string.loading_end);
                break;
        }
        notifyItemChanged(getItemCount() - 1);
    }

    /**
     * 是否显示进度条 0 点击加载更多 1 正在加载更多 2 没有更多数据 3 加载数据失败
     */
    @FootType
    protected int type = EFootType.FOOT_LOADING_ADD;
    /**
     * foot的文字
     */
    protected String foottext;

    public int getItemCount() {
        return getNewItemCount() + (isEnabledFoot ? 1 : 0);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        if (viewType == 9999) {
            vh = new FooterViewHolder(parent);
        } else {
            vh = onNewCreateViewHolder(parent, viewType);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof FooterViewHolder) {
            FooterViewHolder footer = ((FooterViewHolder) holder);
            footer.bindDataandListener(this, isShwoNoDataFoot ?type==EFootType.FOOT_LOADING_MORE?false:getItemCount() == 1 : true, mIFVA, type, foottext);
        } else {
            onNewBindViewHolder(holder, position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        int footerPosition = getItemCount() - 1;
        if (position == footerPosition && isEnabledFoot) {
            return 9999;
        }
        int itemtype_result = getNewItemViewType(position);
        if (itemtype_result == 9999) {
            throw new RuntimeException("该 itemType 已经存在值为 9999 的情况，不能再返回9999");
        }
        return itemtype_result;
    }


    /**
     * 要显示的item
     *
     * @param parent
     * @param viewType 对应的viewType
     * @return
     */
    public abstract RecyclerView.ViewHolder onNewCreateViewHolder(ViewGroup parent, int viewType);

    /**
     * 绑定item
     *
     * @param holder   对应viewType的holder
     * @param position list的下标
     */
    public abstract void onNewBindViewHolder(RecyclerView.ViewHolder holder, final int position);

    /**
     * 要展示的item个数
     *
     * @return
     */
    public abstract int getNewItemCount();

    /**
     * 在需要foot时要展示的itemtype 不可返回9999
     *
     * @param position
     * @return
     */
    public abstract int getNewItemViewType(int position);

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


}
