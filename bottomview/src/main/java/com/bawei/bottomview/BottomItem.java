package com.bawei.bottomview;

import android.graphics.drawable.Drawable;

/**
 * +----------------------------------------------------------------------
 * | 项   目: MineStudy
 * +----------------------------------------------------------------------
 * | 包   名: com.bawei.bottomview
 * +----------------------------------------------------------------------
 * | 类   名: BottomItem
 * +----------------------------------------------------------------------
 * | 时　　间: 2021/9/13 19:39
 * +----------------------------------------------------------------------
 * | 代码创建: 王益德
 * +----------------------------------------------------------------------
 * | 版本信息: V1.0.0
 * +----------------------------------------------------------------------
 * | 功能描述:
 * +----------------------------------------------------------------------
 **/
public class BottomItem {
    private String title;
    private Drawable drawable;

    public BottomItem(String title, Drawable drawable) {
        this.title = title;
        this.drawable = drawable;
    }

    public BottomItem() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    @Override
    public String toString() {
        return "BottomItem{" +
                "title='" + title + '\'' +
                ", drawable=" + drawable +
                '}';
    }
}
