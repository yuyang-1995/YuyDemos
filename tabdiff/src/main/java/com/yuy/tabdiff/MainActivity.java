package com.yuy.tabdiff;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    RelativeLayout relativeLayout;
    ImageView imageView;
    Button btn;
    int screenWidth, screenHeight;
    private int pos;
    private int[] tabpos = new int[]{0, 1, 2, 3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenHeight = displayMetrics.heightPixels; //屏幕高度
        screenWidth = displayMetrics.widthPixels; //屏幕宽度
        System.out.println(screenWidth + " 屏幕宽度 " + screenHeight);
        relativeLayout = findViewById(R.id.main_ll);
        imageView = findViewById(R.id.guide_find);


        btn = findViewById(R.id.changetab);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = getPos();
                changeAdTab(pos);
            }
        });
    }

    private int getPos() {
        int res = pos % 4;
        ++pos;
        return res;
    }

    private void changeAdTab(int pos) {
        ConstraintLayout constraintLayout = findViewById(R.id.v_main);
        imageView.setImageResource(R.drawable.i2);
        ConstraintSet set = new ConstraintSet();
        set.clone(constraintLayout);
        switch (pos) {
            case 0:{

                set.setHorizontalBias(R.id.guide_find, 0f);

            }
                break;
            case 1:
                imageView.setImageResource(R.drawable.icon);

                set.setHorizontalBias(R.id.guide_find, 0.325f);

                break;
            case 2:
                imageView.setImageResource(R.drawable.icon);
                set.setHorizontalBias(R.id.guide_find, 0.675f);
                break;

            case 3:    set.setHorizontalBias(R.id.guide_find, 1f);break;
                default:
                break;


        }                set.applyTo(constraintLayout);
    }



}
