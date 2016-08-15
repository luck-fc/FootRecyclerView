package com.luck.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.luck.adapter.MainAdapter;
import com.luck.entity.ModelEntity;
import com.luck.view.FootRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FootRecyclerView mFootRecyclerView;
    private MainAdapter mMainAdpter;
    private List<ModelEntity> mData;

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
        mData.add(new ModelEntity(1,"Example1BaseSingleAdater","1.只有一个ViewHolder \n2.无分页加载"));
        mData.add(new ModelEntity(2,"Example2FootViewAdater","1.有多个ViewHolder \n" +"2.无分页加载"));
        mData.add(new ModelEntity(3,"Example3BaseSingleAdater","1.只有一个ViewHolder \n" +"2.有分页加载"));
        mData.add(new ModelEntity(4,"Example4FootViewAdpater","1.有多个ViewHolder \n" +"2.有分页加载"));
        mMainAdpter.setData(mData);
    }
}
