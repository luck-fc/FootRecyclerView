[![](https://jitpack.io/v/luck-fc/FootRecyclerView.svg)](https://jitpack.io/#luck-fc/FootRecyclerView)
[![API](https://img.shields.io/badge/API-14%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=14)
# FootRecyclerView
Loaded with more FootRecyclerView

## 效果图
<img src="https://github.com/luck-fc/FootRecyclerView/blob/master/screenshot/device-2016-08-16-112026.png" width="33%"/> 
<img src="https://github.com/luck-fc/FootRecyclerView/blob/master/screenshot/device-2016-08-16-112616.png" width="33%"/> 
<img src="https://github.com/luck-fc/FootRecyclerView/blob/master/screenshot/device-2016-08-16-112652.png" width="33%"/> 

## 用法
引入library 
root build.gradle加入
```xml
allprojects {
    repositories {
        jcenter()
        maven { url "https://jitpack.io" }
    }
}
```
项目 build.gradle加入
```xml
    compile 'com.github.luck-fc:FootRecyclerView:tag1.0'
```
（1）.xml加入布局
```xml
<com.luck.view.FootRecyclerView
            android:id="@+id/example_footrv"
            android:layout_width="match_parent"
            android:layout_height="match_parent" />
```
（2）适配器使用 
<pre>
    1.按业务选择继承的适配器 （一）（不需要展示多种类型的View适配器可继承自BaseViewAdapter<T> 重写onNewCreateViewHolder方法） （二）（需要展示多种类型的View适配器可继承自FootViewAdapter<T> 重写onNewBindViewHolder  getNewItemCount getNewItemViewType 方法 ) 注： T实体类 
    2.重新自己需要的ViewHolder 为了更加方便建议继承自BaseViewHolder(封装了 （一）Intent跳转的goActivityByBaseType方法 （二）getActivity得到当前activity的方法 （三）getView初始化View的方法)
</pre>
（3）FootRecyclerView初始化
<pre><code>//初始化绑定
FootRecyclerView example_footrv = (FootRecyclerView) findViewById(R.id.example_footrv);
FootViewAdapter mAdpter = new FootViewAdapter<T>//(自定义的适配器 继承自BaseSingleAdpater<T>或FootViewAdapter<T>)
example_footrv.init(mAdpter);//(更多重载方法请参考源码)
//需要分页 实现IFootViewAdapter接口
//重新onLoadMore();方法并调用
getData();
//全局初始化 
PageUtil mPageUtil=new PageUtil();
//在下拉刷新时 或加载第一页之前调用
mPageUtil.init(mAdpter);
getData();
//在getData中 调用加载下一页，页码数会加1。
f (mPageUtil.isLoadDataFail(mAdpter)) {
    return;
}
//mPageUtil.getPage() 获取当前页码数，比如是第一页用于显示刷新
if (mPageUtil.getPage() == 1) {
    example_swipe.setRefreshing(true);
}
//在获取到数据后
//（1）第一获取的分页的总页码数设置总页码
 if (mPageUtil.getPage() == 1) {
    mPageUtil.setTotalPage(TotalPage, mAdpter);
}
//加载失败调用
mPageUtil.loadFail(mAdpter);
//加载成功后调用
mAdpter.addData(getPageData(mPageUtil.getPage()));
mPageUtil.loadSuccess(mAdpter);
</code></pre>
##其他
 如有疑问，请提[issue](https://github.com/luck-fc/FootRecyclerView/issues)
##以后
    该libary将会继续被维护，相信以后会封装得更方便便捷，敬请期待！
    如有更好的方式，欢迎随时Pull requests
    
开发者 (Developer)
----------------

* luck-fc - <xiaoorchao@gmail.com>

**License**
=======

    Copyright 2016 luck-fc.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
