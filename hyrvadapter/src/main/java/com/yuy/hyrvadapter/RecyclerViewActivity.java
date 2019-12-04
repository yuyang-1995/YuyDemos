package com.yuy.hyrvadapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.EmptyWrapper;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

import java.util.ArrayList;
import java.util.List;
/**
 * from : yuy
 * Date :  2019/12/4
 * Description : 多布局管理器
 * Version : 
 */
public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<String> mDatas = new ArrayList<>();
    private CommonAdapter<String> mAdapter; //适配器
    //一下三种包装均继承自 Rv.Adapter
    private HeaderAndFooterWrapper mHeaderAndFooterWrapper; //头脚布局
    private EmptyWrapper mEmptyWrapper;  //空布局
    private LoadMoreWrapper mLoadMoreWrapper; //上滑加载布局

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_item_rv);

        initDatas();

        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
//        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this)); //线性布局
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2)); //网格布局
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));  //设置分割线

        mAdapter = new CommonAdapter<String>(this, R.layout.item_list, mDatas) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                holder.setText(R.id.id_item_list_title, s + " : " + holder.getAdapterPosition() + " , " + holder.getLayoutPosition());
            }
        };

        //添加头布局
        initHeaderAndFooter();

        //添加空布局
        initEmptyView();

        //上滑加载 布局
        mLoadMoreWrapper = new LoadMoreWrapper(mHeaderAndFooterWrapper);
        mLoadMoreWrapper.setLoadMoreView(R.layout.default_loading);
        mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 10; i++)
                        {
                            mDatas.add("Add:" + i);
                        }
                        mLoadMoreWrapper.notifyDataSetChanged();

                    }
                }, 3000);
            }
        });


        //item 点击
        mAdapter.setOnItemClickListener(new CommonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Toast.makeText(RecyclerViewActivity.this, "pos = " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

        mRecyclerView.setAdapter(mLoadMoreWrapper);
    }

    private void initEmptyView() {
        mEmptyWrapper = new EmptyWrapper(mAdapter);
        mEmptyWrapper.setEmptyView(LayoutInflater.from(this).inflate(R.layout.empty_view, mRecyclerView, false));
    }

    private void initHeaderAndFooter() {
        mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(mAdapter);

        TextView t1 = new TextView(this);
        t1.setText("Header 1");
        TextView t2 = new TextView(this);
        t2.setText("Header 2");
        //头布局
        mHeaderAndFooterWrapper.addHeaderView(t1);
        mHeaderAndFooterWrapper.addHeaderView(t2);
    }

    private void initDatas() {

        for (int i = 'A'; i <= 'z'; i++) {
            mDatas.add((char) i + "");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recycler_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            //设置布局管理器
            case R.id.action_linear:
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                break;
            case R.id.action_grid:
                mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
                break;
            case R.id.action_staggered:
                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                break;
        }
        //设置适配器
        mRecyclerView.setAdapter(mLoadMoreWrapper);

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart()
    {
        super.onStart();


    }

    @Override
    public void onStop()
    {
        super.onStop();


    }
}
