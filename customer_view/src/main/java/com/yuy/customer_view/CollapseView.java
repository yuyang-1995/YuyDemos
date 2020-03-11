package com.yuy.customer_view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

/**
 * Class:
 * Other:
 * Create by yuy on  2020/3/11.
 */
public class CollapseView extends LinearLayout {

    private long duration = 350;
    private Context mContext;
    private TextView mNumberTextView;
    private TextView mTitleTextView;
    private RelativeLayout mContentRelativeLayout;
    private RelativeLayout mTitleRelativeLayout;
    private ImageView mArrowImageView;
    int parentWidthMeasureSpec;
    int parentHeightMeasureSpec;

    public CollapseView(Context context) {
        this(context, null);
    }

    public CollapseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        LayoutInflater.from(mContext).inflate(R.layout.collapse_layout, this);
        initView();
    }

    private void initView() {
        mNumberTextView=(TextView)findViewById(R.id.numberTextView);
        mTitleTextView =(TextView)findViewById(R.id.titleTextView);
        mTitleRelativeLayout= (RelativeLayout) findViewById(R.id.titleRelativeLayout);
        mContentRelativeLayout=(RelativeLayout)findViewById(R.id.contentRelativeLayout);
        mArrowImageView =(ImageView)findViewById(R.id.arrowImageView);
        mTitleRelativeLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                rotateArrow();
            }
        });
        collapse(mContentRelativeLayout);
    }

    public void setNumber(String number){
        if(!TextUtils.isEmpty(number)){
            mNumberTextView.setText(number);
        }
    }

    public void setTitle(String title){
        if(!TextUtils.isEmpty(title)){
            mTitleTextView.setText(title);
        }
    }

    public void setContent(int resID){
        View view=LayoutInflater.from(mContext).inflate(resID,null);
        RelativeLayout.LayoutParams layoutParams=
                new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        mContentRelativeLayout.addView(view);
    }


    private void rotateArrow() {
        int degree = 0;
        Log.d("mArrowImageView: ", mArrowImageView.getTag() + "");
        if (mArrowImageView.getTag() == null || mArrowImageView.getTag().equals(true)) {
            mArrowImageView.setTag(false);
            degree = -180;
            expand(mContentRelativeLayout);
        } else {
            degree = 0;
            mArrowImageView.setTag(true);
            collapse(mContentRelativeLayout);
        }
        mArrowImageView.animate().setDuration(duration).rotation(degree);
    }

    //展开
    private void expand(final View view) {
//        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
//        DisplayMetrics outMetrics = new DisplayMetrics();
//        wm.getDefaultDisplay().getMetrics(outMetrics);
        view.measure(parentWidthMeasureSpec, parentHeightMeasureSpec);  //w:match_parent, h:warp_content

        final int measureHeight = view.getMeasuredHeight();
        view.setVisibility(VISIBLE);

        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    view.getLayoutParams().height = measureHeight;
                }else {
                    view.getLayoutParams().height = (int) (measureHeight * interpolatedTime);
                }
                view.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        animation.setDuration(duration);
        view.startAnimation(animation);
    }

    //折叠
    private void collapse(final View view) {

        final int measureHeight = view.getMeasuredHeight();

        Animation animation = new Animation() {
            @Override
            public boolean willChangeBounds() {
                return true;
            }

            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {

                if (interpolatedTime == 1) {
                    view.setVisibility(GONE);
                }else {
                    view.getLayoutParams().height = measureHeight - (int) (measureHeight * interpolatedTime);
                    view.requestLayout();
                }
            }
        };

        animation.setDuration(duration);
        view.startAnimation(animation);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        parentWidthMeasureSpec=widthMeasureSpec;
        parentHeightMeasureSpec=heightMeasureSpec;
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
