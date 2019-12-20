package com.yuy.viewpagerindicatordemo.moretab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.IndicatorViewPager.*;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.DrawableBar;
import com.shizhefei.view.indicator.slidebar.ScrollBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;
import com.yuy.viewpagerindicatordemo.R;

public class MoreTabActivity extends AppCompatActivity {
    private IndicatorViewPager indicatorViewPager;
    private LayoutInflater inflate;
    private String[] names = {"CUPCAKE", "DONUT", "FROYO", "GINGERBREAD", "HONEYCOMB", "ICE CREAM SANDWICH", "JELLY BEAN", "KITKAT"};
    private ScrollIndicatorView scrollIndicatorView;
    private ToggleButton pinnedToggleButton;
    private ToggleButton splitAutotoggleButton;
    private int unSelectTextColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moretab);

        splitAutotoggleButton = (ToggleButton) findViewById(R.id.toggleButton1);
        pinnedToggleButton = (ToggleButton) findViewById(R.id.toggleButton2);
        ViewPager viewPager = (ViewPager) findViewById(R.id.moretab_viewPager);
        scrollIndicatorView = (ScrollIndicatorView) findViewById(R.id.moretab_indicator);
        scrollIndicatorView.setBackgroundColor(Color.RED);

        scrollIndicatorView.setScrollBar(new DrawableBar(this,R.drawable.round_border_white_selector, ScrollBar.Gravity.CENTENT_BACKGROUND){
            @Override
            public int getHeight(int tabHeight) {
                return tabHeight - dipToPix(12);
            }

            @Override
            public int getWidth(int tabWidth) {
                return tabWidth - dipToPix(12);
            }
        });

        unSelectTextColor = Color.WHITE;

        // 设置 tab 滚动监听
        scrollIndicatorView.setOnTransitionListener(new OnTransitionTextListener().setColor(Color.RED, unSelectTextColor));

        viewPager.setOffscreenPageLimit(2);
        indicatorViewPager = new IndicatorViewPager(scrollIndicatorView, viewPager);
        inflate = LayoutInflater.from(getApplicationContext());

        indicatorViewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));

        //默认true, 自动布局
        splitAutotoggleButton.setChecked(scrollIndicatorView.isSplitAuto());
        splitAutotoggleButton.setOnCheckedChangeListener(onCheckedChangeListener);
        pinnedToggleButton.setOnCheckedChangeListener(onCheckedChangeListener);


    }

    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (buttonView == splitAutotoggleButton) {
                // 设置是否自动布局
                scrollIndicatorView.setSplitAuto(isChecked);
            } else if (buttonView == pinnedToggleButton) {
                scrollIndicatorView.setPinnedTabView(isChecked);
                // 设置固定tab的shadow，这里不设置的话会使用默认的shadow绘制
                scrollIndicatorView.setPinnedShadow(R.drawable.tabshadow, dipToPix(4));
                scrollIndicatorView.setPinnedTabBgColor(Color.RED);
            }
        }
    };


    private int size = 3;

    public void on3(View view) {
        size = 3;
        indicatorViewPager.getAdapter().notifyDataSetChanged();
    }

    public void on4(View view) {
        size = 4;
        indicatorViewPager.getAdapter().notifyDataSetChanged();
    }

    public void on5(View view) {
        size = 5;
        indicatorViewPager.getAdapter().notifyDataSetChanged();
    }

    public void on12(View view) {
        size = 12;
        indicatorViewPager.getAdapter().notifyDataSetChanged();
    }

    class C extends PagerAdapter {

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return false;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            super.destroyItem(container, position, object);
        }


    }


    private class MyAdapter extends IndicatorFragmentPagerAdapter {

        public MyAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {//1
            return size;
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {//2
            if (convertView == null) {
                convertView = inflate.inflate(R.layout.tab_top, container, false);
            }
            TextView textView = (TextView) convertView;
            textView.setText(names[position % names.length]);
            int padding = dipToPix(10);
            textView.setPadding(padding, 0, padding, 0);
            return convertView;
        }

        @Override
        public Fragment getFragmentForPage(int position) {
            MoreFragment fragment = new MoreFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(MoreFragment.INTENT_INT_INDEX, position);
            fragment.setArguments(bundle);
            return fragment;
        }
    }

    /**
     *
     * 根据dip值转化成px值
     *
     * @param dip
     * @return
     */
    private int dipToPix(float dip) {
        int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, getResources().getDisplayMetrics());
        return size;
    }


}
