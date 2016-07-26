package com.luck.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.luck.adpter.MainAdapter;
import com.luck.entity.Model;
import com.luck.view.FootRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FootRecyclerView mFootRecyclerView;
    private MainAdapter mMainAdpter;
    private List<Model> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView(){
        mFootRecyclerView = (FootRecyclerView) findViewById(R.id.main_footrv);
        mMainAdpter = new MainAdapter(this);
        mFootRecyclerView.init(mMainAdpter);
    }

    private void initData(){
        mData = new ArrayList<>();
        mData.add(new Model(1,"Example1BaseSingleAdater","这是一个 （不带加载更多） 同时 （只有一个ViewHolder） 的适配器演示"));
        mData.add(new Model(2,"Example2BaseViewAdater","这是一个 （不带加载更多） 同时 （有多个ViewHolder） 的适配器演示"));
        mData.add(new Model(3,"Example3BaseSingleAdater","这是一个 （带加载更多） 同时 （只有一个ViewHolder） 的适配器演示"));
        mData.add(new Model(4,"Example4FootViewAdpater","这是一个 （带加载更多） 同时 （有多个ViewHolder） 的适配器演示"));
        mMainAdpter.setData(mData);
    }
}
