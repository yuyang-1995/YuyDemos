package com.yuy.yaoyiyaopage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ShakeUtils shakeUtils;
    private int showTimes = 5;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.id_tv_time);
        textView.setText(showTimes + " ");

        shakeUtils = new ShakeUtils(this);

        shakeUtils.setOnShakeListener(new ShakeUtils.OnShakeListener() {
            @Override
            public void onShake() {
                    if (showTimes > 0 ) {
                        toShowDialog();
                    }else{
                        System.out.println("已用完5次机会");
                        Toast.makeText(MainActivity.this, "已用完5次机会", Toast.LENGTH_SHORT).show();
                    }


            }
        });
    }

    private void toShowDialog() {
        shakeUtils.setCanSnake(false);
        showTimes--;
        final int count = (int) (Math.random() * 10 - 1) + 1;// 1~10
        System.out.println("摇一摇");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog alertDialog = builder.create();
        //屏蔽物理back键返回 和触摸外部返回
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);


        //
        View view = View.inflate(this, R.layout.alert_dialog, null);
        TextView countTv = view.findViewById(R.id.id_tv_count);
        countTv.setText(count+"");

        //
        final DialogInterface.OnClickListener sureListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "您已领取" + count + "个金币", Toast.LENGTH_SHORT).show();
                textView.setText(showTimes + "");
                alertDialog.dismiss();
            }
        };

        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                shakeUtils.setCanSnake(true);
            }
        });

        //领取金币
        Button button = view.findViewById(R.id.id_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sureListener.onClick(alertDialog, DialogInterface.BUTTON_POSITIVE);
            }
        });

        alertDialog.show();
        alertDialog.setContentView(view);
    }


    @Override
    protected void onResume() {
        super.onResume();
        shakeUtils.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        shakeUtils.onPause();

    }
}
