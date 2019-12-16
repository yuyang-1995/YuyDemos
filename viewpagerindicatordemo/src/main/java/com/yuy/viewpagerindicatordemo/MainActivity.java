package com.yuy.viewpagerindicatordemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yuy.viewpagerindicatordemo.guide.GuideActivity;
import com.yuy.viewpagerindicatordemo.moretab.MoreTab2Activity;
import com.yuy.viewpagerindicatordemo.moretab.MoreTabActivity;
import com.yuy.viewpagerindicatordemo.proxyfragment.TestProxyFragmentActivity;
import com.yuy.viewpagerindicatordemo.spring.SpringActivity;
import com.yuy.viewpagerindicatordemo.tabmain.TabMainActivity;
import com.yuy.viewpagerindicatordemo.year.YearActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * BannerComponent(FixedIndicatorView)
     *
     * @param view
     */
    public void onClickBanner(View view) {
        startActivity(new Intent(getApplicationContext(), BannerActivity.class));
    }

    /**
     * 引导界面(FixedIndicatorView)
     *
     * @param view
     */
    public void onClickGuide(View view) {
        startActivity(new Intent(getApplicationContext(), GuideActivity.class));
    }

    /**
     * tab主界面(FixedIndicatorView)
     *
     * @param view
     */
    public void onClickTabMain(View view) {
        startActivity(new Intent(getApplicationContext(), TabMainActivity.class));
    }

    /**
     * 可滑动tab界面(ScrollIndicatorView)
     *
     * @param view
     */
    public void onClickSlideTab(View view) {
        startActivity(new Intent(getApplicationContext(), MoreTab2Activity.class));
    }

    /**
     * 可滑动tab界面(ScrollIndicatorView详细配置)
     *
     * @param view
     */
    public void onClickSlideTab2(View view) {
        startActivity(new Intent(getApplicationContext(), MoreTabActivity.class));
    }


    /**
     * springtab界面(ScrollIndicatorView)
     *
     * @param view
     */
    public void onClickSpringTab(View view) {
        startActivity(new Intent(getApplicationContext(), SpringActivity.class));
    }


    /**
     * 无数tab的界面(RecyclerIndicatorView)
     *
     * @param view
     */
    public void onClickYear(View view) {
        startActivity(new Intent(getApplicationContext(), YearActivity.class));
    }

    /**
     * 不集成ViewPager的Indicator使用方式
     *
     * @param view
     */
    public void onClickTabs(View view) {
        startActivity(new Intent(getApplicationContext(), SingleTabActivity.class));
    }
    /**
     * 不集成ViewPager的Indicator使用方式
     *
     * @param view
     */
    public void onClickTestProxyLazyFragment(View view) {
        startActivity(new Intent(getApplicationContext(), TestProxyFragmentActivity.class));
    }
}
