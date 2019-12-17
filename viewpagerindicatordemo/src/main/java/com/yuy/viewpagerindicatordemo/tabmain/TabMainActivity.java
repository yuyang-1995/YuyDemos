package com.yuy.viewpagerindicatordemo.tabmain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.Indicator;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;
import com.shizhefei.view.viewpager.SViewPager;
import com.yuy.viewpagerindicatordemo.R;

public class TabMainActivity extends AppCompatActivity {

    private IndicatorViewPager indicatorViewPager;
    private View centerView;
    private FixedIndicatorView indicator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_main);

        SViewPager viewPager = findViewById(R.id.id_viewpager_banner);
        indicator = findViewById(R.id.ind);
        indicator.setOnTransitionListener(new OnTransitionTextListener().setColor(Color.RED, Color.GRAY));

        //这里可以添加一个View 作为centerView， 会位于Indicator 的tab 中间
        centerView = getLayoutInflater().inflate(R.layout.activity_tab_main, null, false);
        indicator.setCenterView(centerView);
        centerView.setOnClickListener(onClickListener);

        indicatorViewPager = new IndicatorViewPager(indicator, viewPager);




    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (v == centerView) {
                //可移除
//                indicator.removeCenterView();
                Toast.makeText(getApplicationContext(), "点击了CenterView", Toast.LENGTH_SHORT).show();

            }
        }
    };


    private class MyAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {

        private String[] tabNames = {"主页", "消息", "发现", "我"};
        private int[] tabIcons = {R.drawable.maintab_1_selector, R.drawable.maintab_2_selector, R.drawable.maintab_3_selector,
                R.drawable.maintab_4_selector};
        private LayoutInflater inflater;


        public MyAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
            inflater = LayoutInflater.from(getApplicationContext());
        }

        @Override
        public int getCount() {
            return tabIcons.length;
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.tab_main, container, false);
            }
            TextView textView = (TextView) convertView;
            textView.setText(tabNames[position]);
            textView.setCompoundDrawablesWithIntrinsicBounds(0, tabIcons[position], 0, 0);
            return textView;
        }

        @Override
        public Fragment getFragmentForPage(int position) {

            FirstLayerFragment firstLayerFragment = new FirstLayerFragment();
            Bundle bundle = new Bundle();
//            bundle.putString();

            return null;
        }
    }



}
