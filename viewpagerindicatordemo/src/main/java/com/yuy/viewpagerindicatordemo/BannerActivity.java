package com.yuy.viewpagerindicatordemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.shizhefei.view.indicator.BannerComponent;
import com.shizhefei.view.indicator.Indicator;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.slidebar.ScrollBar;

public class BannerActivity extends AppCompatActivity {

    private BannerComponent bannerComponent;
    private int[] images = {R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4};
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);

        //
        ViewPager viewPager = findViewById(R.id.id_viewpager_banner);
        Indicator indicator = findViewById(R.id.id_banner_indicator);

        indicator.setScrollBar(new ColorBar(getApplicationContext(), Color.WHITE, 0, ScrollBar.Gravity.CENTENT_BACKGROUND));
        viewPager.setOffscreenPageLimit(2);


        bannerComponent = new BannerComponent(indicator, viewPager, false);

        myAdapter = new MyAdapter();

        bannerComponent.setAdapter(myAdapter);

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                images = new int[]{};
                myAdapter.notifyDataSetChanged();
            }
        });

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                images = new int[]{R.drawable.p2};
                myAdapter.notifyDataSetChanged();
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                images = new int[]{R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4};
                myAdapter.notifyDataSetChanged();
            }
        });
           //默认就是800毫秒，设置单页滑动效果的时间
        bannerComponent.setScrollDuration(800);
        //设置播放间隔时间，默认情况是3000毫秒
        bannerComponent.setAutoPlayTime(5000);

        bannerComponent.setOnIndicatorPageChangeListener(new IndicatorViewPager.OnIndicatorPageChangeListener() {
            @Override
            public void onIndicatorPageChange(int preItem, int currentItem) {
                Toast.makeText(BannerActivity.this, "position" + currentItem +"",Toast.LENGTH_SHORT).show();
            }
        });

        bannerComponent.setOnIndicatorItemClickListener(new Indicator.OnIndicatorItemClickListener() {
            @Override
            public boolean onItemClick(View clickItemView, int position) {
                Toast.makeText(BannerActivity.this, "position" + position +"",Toast.LENGTH_SHORT).show();
                return false;
            }
        });

             indicator.setOnIndicatorItemClickListener(new Indicator.OnIndicatorItemClickListener() {
            @Override
            public boolean onItemClick(View clickItemView, int position) {
                Toast.makeText(BannerActivity.this, "position" + position +"",Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        //开启Banner播放
        bannerComponent.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        bannerComponent.stopAutoPlay();
    }

    //
   class MyAdapter extends IndicatorViewPager.IndicatorViewPagerAdapter {
        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = new View(container.getContext());
            }
            return convertView;
        }

        @Override
        public View getViewForPage(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = new ImageView(getApplicationContext());
                convertView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            }
            ImageView imageView = (ImageView) convertView;
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(images[position]);
            return convertView;
        }
    }
}
