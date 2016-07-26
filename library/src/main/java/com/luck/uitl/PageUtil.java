package com.luck.uitl;

import android.support.annotation.IntDef;

import com.luck.adpater.FootViewAdapter;
import com.luck.finalenum.EFootType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 作者：luck on 2016/6/16 16:11
 * 邮箱：fc_dream@163.com
 * 分页工具封装
 */
public class PageUtil {

    private int page = 0;//当前页面
    private boolean isLoad = false;//是否正在加载

    public void setTotalPage(int totalPage, FootViewAdapter mdapter) {
        TotalPage = totalPage;
        if (page >= TotalPage) {
            mdapter.setFootType(EFootType.FOOT_NO_LOADDATA);
        }
    }


    private int TotalPage = -1;//总页码数
    //这是分页的三种状态
    public static final int loading = 0X1;
    public static final int end = 0X2;
    public static final int beg = 0X3;
    @IntDef({beg, loading, end})
    @Retention(RetentionPolicy.SOURCE)
    private @interface State {
    }

    public int getPage() {
        return page;
    }

    public void loadSuccess() {
        this.isLoad = false;
    }

    public void loadFail() {
        if (this.page > 0) {
            this.page--;
        }
        isLoad = false;
    }

    /**
     * 初始化
     *
     * @return
     */
    public void init() {
        page = 0;
        isLoad = false;
        TotalPage = -1;
    }

    /**
     * 是否正在加载
     *
     * @return
     */
    private boolean isLoading() {
        if (isLoad && page != 0) {
            return true;
        }
        return false;
    }

    /**
     * 加载一页数据，返回当前状态
     *
     * @return
     */
    @State
    private int isCanNext(FootViewAdapter mdapter) {
        if (isLoading()) {
            return loading;
        }
        if (page >= TotalPage && page != 0 && TotalPage != -1) {
            return end;
        }
        if (page == 0) {
            TotalPage = -1;
            mdapter.clear();
        }
        isLoad = true;
        page++;
        return beg;
    }

    /**
     * 是否不可以加载下一页数据
     *
     * @param mdapter
     * @return
     */
    public boolean isLoadDataFail(FootViewAdapter mdapter) {
        switch (isCanNext(mdapter)) {
            case beg:
                if (page >= TotalPage && page != 0 && TotalPage != -1) {
                    mdapter.setFootType(EFootType.FOOT_NO_LOADDATA);
                } else {
                    if (page == 1) {
                        mdapter.setFootType(EFootType.FOOT_LOADING_ADD);
                    } else {
                        mdapter.setFootType(EFootType.FOOT_LOADING_MORE);
                    }
                }
                break;
            case PageUtil.loading:
                return true;
            case PageUtil.end:
                mdapter.setFootType(EFootType.FOOT_NO_LOADDATA);
                return true;
        }
        return false;
    }
}
