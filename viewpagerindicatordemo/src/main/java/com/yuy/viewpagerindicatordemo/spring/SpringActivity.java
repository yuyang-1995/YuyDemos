package com.yuy.viewpagerindicatordemo.spring;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.shizhefei.view.indicator.Indicator;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.slidebar.SpringBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;
import com.yuy.viewpagerindicatordemo.DisplayUtil;
import com.yuy.viewpagerindicatordemo.R;

public class SpringActivity extends AppCompatActivity {
    private IndicatorViewPager indicatorViewPager;
    private LayoutInflater inflater;
    private int unSelectColor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spring);

        ViewPager viewPager = findViewById(R.id.spring_viewPager);
        Indicator indicator = findViewById(R.id.spring_indicator);
        int selectColor = Color.parseColor("#f8f8f8");
        unSelectColor = Color.parseColor("#010101");

        //设置 tab 颜色
        indicator.setOnTransitionListener(new OnTransitionTextListener().setColor(selectColor, unSelectColor));
        //设置 SpringActivity Tab
        indicator.setScrollBar(new SpringBar(getApplicationContext(), Color.GRAY));

        viewPager.setOffscreenPageLimit(4);
        indicatorViewPager = new IndicatorViewPager(indicator, viewPager);
        inflater = LayoutInflater.from(getApplicationContext());
        indicatorViewPager.setAdapter(new MyAdapter());
        indicatorViewPager.setCurrentItem(5, true);

    }

    class MyAdapter extends IndicatorViewPager.IndicatorViewPagerAdapter {

        @Override
        public int getCount() {
            return 16;
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.tab_top, container, false);
            }
            TextView textView = (TextView) convertView;
            int padding = DisplayUtil.dipToPix(getApplicationContext(), 30);
            textView.setPadding(padding, 0, padding, 0);
            textView.setText(String.valueOf(position));
            textView.setTextColor(unSelectColor);
            return convertView;

        }

        @Override
        public View getViewForPage(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.fragment_tabmain_item, container, false);
            }
            final TextView textView = (TextView) convertView.findViewById(R.id.fragment_mainTab_item_textView);

            textView.setText(" " + position + " 界面加载完毕");
            final ProgressBar progressBar = (ProgressBar) convertView.findViewById(R.id.fragment_mainTab_item_progressBar);
            new Handler() {
                public void handleMessage(android.os.Message msg) {
                    textView.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }
            }.sendEmptyMessageDelayed(1, 3000);
            return convertView;
        }

        @Override
        public int getItemPosition(Object object) {
            //这是ViewPager适配器的特点,有两个值 POSITION_NONE，POSITION_UNCHANGED，默认就是POSITION_UNCHANGED,
            // 表示数据没变化不用更新.notifyDataChange的时候重新调用getViewForPage
            return PagerAdapter.POSITION_UNCHANGED;
        }


    }




}
