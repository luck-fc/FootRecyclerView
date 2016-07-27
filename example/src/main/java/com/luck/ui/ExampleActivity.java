package com.luck.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.luck.adpater.BaseSingleAdpater;
import com.luck.adpater.FootViewAdapter;
import com.luck.entity.JurnalismEntity;
import com.luck.imp.IFootViewAdapter;
import com.luck.uitl.PageUtil;
import com.luck.view.FootRecyclerView;
import com.luck.view.RecycleViewDivider;
import com.luck.viewholder.JurnalismType1ViewHolder;
import com.luck.viewholder.JurnalismType2ViewHolder;
import com.luck.viewholder.JurnalismType3ViewHolder;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ExampleActivity extends AppCompatActivity implements IFootViewAdapter {

    private int type;
    private FootRecyclerView example_footrv;
    private SwipeRefreshLayout example_swipe;
    private PageUtil mPageUtil;
    private FootViewAdapter<JurnalismEntity> mAdpter;
    private WeakReference<Handler> mWeak;
    private Handler mHandler;
    private int TotalPage = 10;//设置默认页码数为10

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        getTopData();
        initView();
        initData();
    }

    private void getTopData() {
        this.type = getIntent().getIntExtra("type", 1);
    }

    private void initView() {
        example_footrv = (FootRecyclerView) findViewById(R.id.example_footrv);
        example_footrv.addItemDecoration(new RecycleViewDivider(5, this, LinearLayoutManager.VERTICAL));
        example_swipe = (SwipeRefreshLayout) findViewById(R.id.example_swipe);
        example_swipe.setColorSchemeColors(Color.BLUE,Color.RED,Color.GREEN);
        example_swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPageUtil.init(mAdpter);
                getData();
            }
        });
    }

    private void initData() {
        switch (type) {
            case 1:
            case 3:
                mAdpter = new BaseSingleAdpater<JurnalismEntity>(this, type == 3 ? this : null, type == 1 ? false : true) {

                    @Override
                    public void onNewBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                        if (holder instanceof JurnalismType1ViewHolder) {
                            JurnalismType1ViewHolder vh = (JurnalismType1ViewHolder) holder;
                            vh.bindDataAndListener(data.get(position));
                        }
                    }

                    @Override
                    public RecyclerView.ViewHolder onNewCreateViewHolder(ViewGroup parent) {
                        return new JurnalismType1ViewHolder(parent);
                    }
                };
                break;
            case 2:
            case 4:
                mAdpter = new FootViewAdapter<JurnalismEntity>(this, type == 4 ? this : null, type == 2 ? false : true) {

                    @Override
                    public RecyclerView.ViewHolder onNewCreateViewHolder(ViewGroup parent, int viewType) {
                        if (viewType == 1) {
                            return new JurnalismType1ViewHolder(parent);
                        } else if (viewType == 2) {
                            return new JurnalismType2ViewHolder(parent);
                        } else if (viewType == 3) {
                            return new JurnalismType3ViewHolder(parent);
                        }
                        return null;
                    }

                    @Override
                    public void onNewBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                        if (holder instanceof JurnalismType1ViewHolder) {
                            JurnalismType1ViewHolder vh = (JurnalismType1ViewHolder) holder;
                            vh.bindDataAndListener(data.get(position));
                        } else if (holder instanceof JurnalismType2ViewHolder) {
                            JurnalismType2ViewHolder vh = (JurnalismType2ViewHolder) holder;
                            vh.bindDataAndListener(data.get(position));
                        } else if (holder instanceof JurnalismType3ViewHolder) {
                            JurnalismType3ViewHolder vh = (JurnalismType3ViewHolder) holder;
                            vh.bindDataAndListener(data.get(position));
                        }
                    }

                    @Override
                    public int getNewItemCount() {
                        return data.size();
                    }

                    @Override
                    public int getNewItemViewType(int position) {
                        return data.get(position).getType();
                    }
                };
                break;
        }
        example_footrv.init(mAdpter);
        getData();
    }

    private void getData() {
        if (mPageUtil == null) {
            mPageUtil = new PageUtil();
        }
        if (mPageUtil.isLoadDataFail(mAdpter)) {
            return;
        }
        if (mPageUtil.getPage() == 1) {
            example_swipe.setRefreshing(true);
        }
        if (mWeak == null || mWeak.get() == null) {
            mHandler = new Handler();
            mWeak = new WeakReference<>(mHandler);
        }
        mWeak.get().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mPageUtil.getPage() == 1) {
                    mPageUtil.setTotalPage(TotalPage, mAdpter);
                    example_swipe.setRefreshing(false);
                }
                if (mPageUtil.getPage() > 1 && new Random().nextInt(10) % 5 == 0) {
                    mPageUtil.loadFail(mAdpter);
                } else {
                    mAdpter.addData(getPageData(mPageUtil.getPage()));
                    mPageUtil.loadSuccess();
                }
            }
        }, 500);
    }

    private List<JurnalismEntity> getPageData(int page) {
        List<JurnalismEntity> mDatas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            JurnalismEntity data = new JurnalismEntity();
            if (type % 3 == 1) {
                data.setTitlte("巴基斯坦高官：中国公民安全比自己生命更重要" + ((type == 1 || type == 3) ? "" : "第" + (page + 1) + "页数据第" + (i + 1) + "条数据"));
                data.setSource("中国新闻网");
                data.setTitlte("2016年07月26日 17:01:20");
                data.setType(1);
            } else if (type % 3 == 2) {
                data.setTitlte("待宰老牛跪地哭泣如婴儿 感动屠厂工人凑钱买下送佛寺" + ((type == 1 || type == 3) ? "" : "第" + (page + 1) + "页数据第" + (i + 1) + "条数据"));
                data.setSource("凤凰科技");
                data.setTitlte("2016年07月25日 09:13");
                data.setType(2);
            } else if (type % 3 == 0 && type % 2 == 1) {
                data.setTitlte("猫咪走失11年后终与主人团聚：这些年吃得很好" + ((type == 1 || type == 3) ? "" : "第" + (page + 1) + "页数据第" + (i + 1) + "条数据"));
                data.setSource("中国新闻网");
                data.setTitlte("2016年07月26日 13:10");
                data.setType(3);
            } else {
                data.setTitlte("超级黑洞竟然挑食 不吞噬僵尸行星震惊" + ((type == 1 || type == 3) ? "" : "第" + (page + 1) + "页数据第" + (i + 1) + "条数据"));
                data.setSource("科技讯");
                data.setTitlte("2016年07月26日 08:58");
                data.setType(type % 3 + 1);
            }
            mDatas.add(data);
        }
        return mDatas;
    }

    @Override
    public void onLoadMore() {
        getData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWeak != null) {
            mWeak.clear();
            mWeak = null;
        }
    }
}
