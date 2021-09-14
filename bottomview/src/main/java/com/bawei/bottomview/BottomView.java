package com.bawei.bottomview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * +----------------------------------------------------------------------
 * | 项   目: MineStudy
 * +----------------------------------------------------------------------
 * | 包   名: com.bawei.bottomview
 * +----------------------------------------------------------------------
 * | 类   名: BottomView
 * +----------------------------------------------------------------------
 * | 时　　间: 2021/9/13 19:36
 * +----------------------------------------------------------------------
 * | 代码创建: 王益德
 * +----------------------------------------------------------------------
 * | 版本信息: V1.0.0
 * +----------------------------------------------------------------------
 * | 功能描述:
 * +----------------------------------------------------------------------
 **/
public class BottomView extends FrameLayout implements View.OnClickListener {

    private List<View> viewList;
    private List<BottomItem> itemList;
    private FrameLayout frameLayout;
    private LinearLayout linearLayout;
    private LinearLayout.LayoutParams layoutParams;

    private int unSelectedTextColor = Color.parseColor("#B8B2B1");
    private int selectedTextColor = Color.WHITE;


    public BottomView(@NonNull Context context) {
        super(context);
        init();
    }

    public BottomView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * 初始化 一般用于设置总布局 实例化集合 动态实现每个item的大小
     */
    private void init() {
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));

        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.activity_main, this, true);
        frameLayout = inflate.findViewById(R.id.container);
        linearLayout = inflate.findViewById(R.id.ll_container);

        frameLayout.setBackgroundColor(Color.GRAY);

        layoutParams = new LinearLayout.LayoutParams(0,ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.weight = 1;
        layoutParams.gravity = Gravity.CENTER;

        viewList = new ArrayList<>();
        itemList = new ArrayList<>();
    }

    /**
     * 对外提供（用于添加条目）
     * @param item
     * @return
     */
    public BottomView addItem(BottomItem item){
        itemList.add(item);
        return this;
    }

    /**
     * 构造 循环每条已添加的数据 设置他们的属性
     */
    public void build(){
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getTitle()!=null){
                TextView textView = new TextView(getContext());
                textView.setText(itemList.get(i).getTitle());
                textView.setGravity(Gravity.CENTER);
                textView.setLayoutParams(layoutParams);
                textView.setTextColor(unSelectedTextColor);
                textView.setTag(R.id.itemClick,i);
                textView.setTextSize(16);
                textView.setOnClickListener(this);

                linearLayout.addView(textView);
                viewList.add(textView);
            }

            if (itemList.get(i).getDrawable()!=null){
                ImageView imageView = new ImageView(getContext());
                imageView.setLayoutParams(layoutParams);
                imageView.setTag(R.id.itemClick,i);
                imageView.setImageDrawable(itemList.get(i).getDrawable());
                imageView.setOnClickListener(this);

                linearLayout.addView(imageView);
                viewList.add(imageView);
            }
        }
    }

    /**
     * 设置选中突出颜色（用于内部设置）
     * @param position
     */
    private void defSelectedColor(int position){
        for (int i = 0; i < viewList.size(); i++) {
            View view = viewList.get(i);
            if (view instanceof TextView){
                if (position == i){
                    ((TextView) view).setTextSize(18);
                    ((TextView) view).setTextColor(selectedTextColor);
                    ((TextView) view).setTypeface(Typeface.DEFAULT);    //设置字体
                }else {
                    ((TextView) view).setTextSize(16);
                    ((TextView) view).setTextColor(unSelectedTextColor);
                }
            }
        }
    }

    private ItemClickListener listener;

    /**
     * 内部接口 用于用户监听点击事件
     */
    public interface ItemClickListener{
        void onSelected(int position);
    }

    /**
     * item的点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        int tag = (int) v.getTag(R.id.itemClick);

        listener.onSelected(tag);

        defSelectedColor(tag);
    }

    /**
     * 用于设置监听事件
     * @param listener
     */
    public void setItemOnclickListener(ItemClickListener listener){
        this.listener = listener;
    }
}
