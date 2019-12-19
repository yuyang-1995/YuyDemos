package com.yuy.viewpagerindicatordemo.moretab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ToggleButton;

import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.DrawableBar;
import com.shizhefei.view.indicator.slidebar.ScrollBar;
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
                return super.getHeight(tabHeight);
            }

            @Override
            public int getWidth(int tabWidth) {
                return super.getWidth(tabWidth);
            }
        });

    }


}
