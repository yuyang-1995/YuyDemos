package com.yuy.hyrvadapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * from : yuy
 * Date :  2019/12/4
 * Description : 
 * Version : 
 */
public class MainActivity extends AppCompatActivity{

    private List<String> mDatas = new ArrayList<>(Arrays.asList(
            "RecyclerView",
            "MultiItem RecyclerView"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {

         RecyclerView mRecyclerView = findViewById(R.id.id_rv_activity_main);

         //布局管理器
         mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
         //单布局使用
        CommonAdapter<String> commonAdapter = new CommonAdapter<String>(this, R.layout.item_rv_activity_main, mDatas) {
            @Override
            protected void convert(ViewHolder holder, final String s, int position) {
                //在convert方法中完成数据、事件绑定即可

                //ViewHolder中封装了大量的常用的方法，比如holder.setText(id,text)，holder.setOnClickListener(id,listener)等
                holder.setText(R.id.id_item_list_title, s);

                //控件事件
                holder.setOnClickListener(R.id.id_item_list_title, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "" + s, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        //Item 点击事件(点击事件长按事件)
        commonAdapter.setOnItemClickListener(new CommonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                Intent intent = null;
                switch (position) {
                    case 0:
                        Toast.makeText(MainActivity.this, "SS" , Toast.LENGTH_SHORT).show();
                        intent = new Intent(MainActivity.this, RecyclerViewActivity.class);
                        break;
                    case 1:
                        Toast.makeText(MainActivity.this, "SS" , Toast.LENGTH_SHORT).show();
                        intent = new Intent(MainActivity.this, MultiItemRvActivity.class);
                        break;
                }

                if (intent != null) {
                    startActivity(intent);
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                Intent intent = null;
                switch (position) {
                    case 0:
                        Toast.makeText(MainActivity.this, "SS" , Toast.LENGTH_SHORT).show();
                        intent = new Intent(MainActivity.this, RecyclerViewActivity.class);
                        break;
                    case 2:
                        Toast.makeText(MainActivity.this, "SS" , Toast.LENGTH_SHORT).show();
                        intent = new Intent(MainActivity.this, MultiItemRvActivity.class);
                        break;
                }

                if (intent != null) {
                    startActivity(intent);
                }
                return true;
            }
        });
        //设置适配器
        mRecyclerView.setAdapter(commonAdapter);

    }

}
