package com.bw.animationdemo.myview;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.core.content.ContextCompat;

import com.bw.animationdemo.R;

import java.util.Random;

/**
 * MineStudy
 * name: MyAnimationView
 * time: 2021/8/24 16:26.
 * author: 王益德
 * Describe:
 */
public class MyAnimationView extends RelativeLayout {

    private Drawable[] drawables = new Drawable[5];
    private int width;
    private int height;

    /**
     * 构造方法
     * @param context
     */
    public MyAnimationView(Context context) {
        super(context);
        init();
    }

    /**
     * 构造方法
     * @param context
     * @param attrs
     */
    public MyAnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        drawables[0] = ContextCompat.getDrawable(getContext(), R.mipmap.ic_launcher);
        drawables[1] = ContextCompat.getDrawable(getContext(),R.mipmap.ic_launcher);
        drawables[2] = ContextCompat.getDrawable(getContext(),R.mipmap.ic_launcher);
        drawables[3] = ContextCompat.getDrawable(getContext(),R.mipmap.ic_launcher);
        drawables[4] = ContextCompat.getDrawable(getContext(),R.mipmap.ic_launcher);
    }

    /**
     * 获取当前宽高
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
    }

    /**
     * 点击出现动画效果
     */
    public void addImageView(){
        ImageView imageView = new ImageView(getContext());
        imageView.setImageDrawable(drawables[new Random().nextInt(drawables.length)]);
        addView(imageView);
        //给view设置一个动画
        bezierValueAnimation(imageView).start();
    }

    private ValueAnimator bezierValueAnimation(final ImageView imageView) {
        //初始化估值其
        MyEvaluator myEvaluator = new MyEvaluator(getPointF(),getPointF());

        //设置起始终止位置
        PointF randomEndPoint = new PointF((float) (Math.random()*1000),(float)(Math.random())*500);
        PointF randomStartPoint = new PointF((float)(width),(float)height);

        //设置动画
        ValueAnimator valueAnimator = ValueAnimator.ofObject(myEvaluator,randomStartPoint,randomEndPoint);
        //动画时间
        valueAnimator.setDuration(3000);
        //设置动画对象
        valueAnimator.setTarget(imageView);

        //动画运行时监听
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF = (PointF) animation.getAnimatedValue();
                //更新位置
                imageView.setX(pointF.x);
                imageView.setY(pointF.y);

                //更新透明度
//                imageView.setAlpha(1-animation.getAnimatedFraction());
            }
        });
        return valueAnimator;
    }

    /**
     * 获取随机位置
     * @return
     */
    public PointF getPointF(){
        PointF pointF = new PointF();
        pointF.x = (float) (Math.random()*width);
        pointF.y = (float) (Math.random()*height/4);
        return pointF;
    }

    /**
     * 删除所有视图（用于防止重复new对象）
     */
    public void removeImg(){
        removeAllViews();
    }

    /**
     * 估值器内部类
     */
    public class MyEvaluator implements TypeEvaluator<PointF> {

        private PointF pointF1;
        private PointF pointF2;


        public MyEvaluator(PointF pointF1, PointF pointF2) {
            this.pointF1 = pointF1;
            this.pointF2 = pointF2;
        }

        @Override
        public PointF evaluate(float time, PointF startValue, PointF endValue) {
            float timeLeft = 1.0f-time;
            PointF point = new PointF();//结果

            point.x = timeLeft * timeLeft * timeLeft * (startValue.x)
                    + 3 * timeLeft * timeLeft * time * (pointF1.x)
                    + 3 * timeLeft * time * time * (pointF2.x)
                    + time * time * time * (endValue.x);

//            Log.i(TAG, "evaluate: "+point.x);
            point.y = timeLeft * timeLeft * timeLeft * (startValue.y)
                    + 3 * timeLeft * timeLeft * time * (pointF1.y)
                    + 3 * timeLeft * time * time * (pointF2.y)
                    + time * time * time * (endValue.y);

//            Log.i(TAG, "evaluate: "+point.y);
            return point;
        }
    }


}
