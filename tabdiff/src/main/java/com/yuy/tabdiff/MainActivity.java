package com.yuy.tabdiff;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Guideline;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    RelativeLayout relativeLayout;
    ImageView imageView, imageView1;
    Button btn;
    int screenWidth, screenHeight;
    private int pos;
    View guideline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {

        relativeLayout = findViewById(R.id.main_ll);
        imageView = findViewById(R.id.guide_find);
        imageView1 = findViewById(R.id.guide_find1);
        guideline = findViewById(R.id.line2);
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
        imageView1.setImageResource(R.drawable.icon);
        ConstraintSet set = new ConstraintSet();
        set.clone(constraintLayout);
        switch (pos) {
            case 0:
                set.setHorizontalBias(R.id.guide_find, 0f);
                break;
            case 1:
                set.setHorizontalBias(R.id.line2, 0.375f);
                break;
            case 2:
                set.setHorizontalBias(R.id.line2, 0.625f);
                break;
            case 3:
                set.setHorizontalBias(R.id.guide_find, 1f);
                break;
                default:
                break;
        }
        set.applyTo(constraintLayout);
    }



}
