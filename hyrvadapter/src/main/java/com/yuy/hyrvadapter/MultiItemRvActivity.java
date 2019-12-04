package com.yuy.hyrvadapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.yuy.hyrvadapter.adapter.ChatAdapterForRv;
import com.yuy.hyrvadapter.bean.ChatMessage;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * from : yuy
 * Date :  2019/12/4
 * Description : BaseRvAdapter 多布局管理器
 * Version :
 */
public class MultiItemRvActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private LoadMoreWrapper mLoadMoreWrapper;  //上滑加载, 集成Adapter
    private List<ChatMessage> mDatas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_item_rv);

        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview); //

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mDatas.addAll(ChatMessage.MOCK_DATAS); //准备数据
        ChatAdapterForRv chatAdapterForRv = new ChatAdapterForRv(this, mDatas); //创建适配器
        mLoadMoreWrapper = new LoadMoreWrapper(chatAdapterForRv); //注入适配器 创建上滑加载UI

        View loadView = LayoutInflater.from(this).inflate(R.layout.default_loading, mRecyclerView, false);

        mLoadMoreWrapper.setLoadMoreView(loadView); //设置上滑刷新的 刷新布局

        //设置下滑加载监听
        mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run()
                    {
                        boolean coming = Math.random() > 0.5;
                        ChatMessage msg = null;
                        msg = new ChatMessage(coming ? R.drawable.renma : R.drawable.xiaohei, coming ? "人马" : "xiaohei", "where are you " + mDatas.size(),
                                null, coming);
                        mDatas.add(msg);

                        mLoadMoreWrapper.notifyDataSetChanged(); //刷新数据
                    }
                }, 3000);
            }
        });

        //设置 item 点击
        chatAdapterForRv.setOnItemClickListener(new CommonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                Toast.makeText(MultiItemRvActivity.this, "Click:" + i , Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                Toast.makeText(MultiItemRvActivity.this, "LongClick:" + i , Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        //设置 有 上滑加载的 Adapter
        mRecyclerView.setAdapter(mLoadMoreWrapper);
    }
}
