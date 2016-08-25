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


/**
 * 作者：luck on 2016/1/6 15:34
 * 邮箱：fc_dream@163.com
 * AS_TuHu_android
 * 拥有加载更多的适配器
 */
public abstract class FootViewAdapter<T> extends BaseViewAdapter<T> {


    /**
     * Foot是否可用(是否展示)
     */
    private boolean isEnabledFoot = true;
    /**
     * 是否显示没有数据的Foot
     */
    private boolean isShowNoDataFoot = true;
    /**
     * 用于在不显示没有数据的foot的判断正在加载的foot可用
     */
    private boolean isCloseFoot = false;

    /**
     * 开启关闭Foot
     *
     * @param enabledFoot
     */
    public void setEnabledFoot(boolean enabledFoot) {
        if (enabledFoot != isEnabledFoot) {
            this.isEnabledFoot = enabledFoot;
            notifyDataSetChanged();
        }
    }

    /**
     * 开启关闭显示没有更多数据
     *
     * @param enabledFoot 为 false 时 不显示没有更多，但加载更多和点击加载会显示
     */
    public void isShwoNoDataFoot(boolean enabledFoot) {
        if (enabledFoot != isShowNoDataFoot) {
            this.isShowNoDataFoot = enabledFoot;
            if (!isShowNoDataFoot) {
                isCloseFoot = true;
            }
        }
    }

    public IFootViewAdapter getmIFVA() {
        return mIFVA;
    }

    protected IFootViewAdapter mIFVA;
    public FootViewAdapter(Activity activity) {
        super(activity);
        init(null);
    }
    public FootViewAdapter(Activity activity, IFootViewAdapter iFVA) {
        super(activity);
        init(iFVA);
    }

    public FootViewAdapter(Activity activity, IFootViewAdapter iFVA,boolean enabledFoot) {
        super(activity);
        init(iFVA);
        setEnabledFoot(enabledFoot);
    }

    private void init(IFootViewAdapter iFVA) {
        this.mIFVA = iFVA;
        this.foottext = mActivity.getResources().getString(R.string.loading_manual);
    }

    @IntDef({EFootType.FOOT_LOADING_ADD, EFootType.FOOT_ERROR_LOADDATA, EFootType.FOOT_LOADING_MORE, EFootType.FOOT_NO_LOADDATA})
    @Retention(RetentionPolicy.SOURCE)
    private @interface FootType {
    }

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
        if (isCloseFoot) {
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
                if (isCloseFoot) {
                    isShwoNoDataFoot(false);
                }
                break;
            case EFootType.FOOT_ERROR_LOADDATA:
                this.foottext = mActivity.getResources().getString(R.string.loading_fail);
                break;
            default:
                this.foottext = mActivity.getResources().getString(R.string.loading_manual);
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
            footer.bindDataandListener(this, isShowNoDataFoot ? type == EFootType.FOOT_LOADING_MORE ? false : getItemCount() == 1 : true, mIFVA, type, foottext);
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


}
