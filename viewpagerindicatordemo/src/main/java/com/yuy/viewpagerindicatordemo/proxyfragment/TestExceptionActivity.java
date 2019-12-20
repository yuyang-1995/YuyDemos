package com.yuy.viewpagerindicatordemo.proxyfragment;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.yuy.viewpagerindicatordemo.R;

/**
 * Class:
 * Other:
 * Create by yuy on  2019/12/21.
 */
public class TestExceptionActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_exception);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                "".substring(1000);
            }
        });
    }

}
