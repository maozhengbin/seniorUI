package com.example.maomao.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.lang.ref.WeakReference;

public class ScreenUtil {
    private DisplayMetrics dm;
    private static ScreenUtil instance;
    private WeakReference<Activity> reference;
    public Activity getBaseActivity(){
        return reference.get();
    }

    public static synchronized ScreenUtil getInstance(Activity activity) {
        if (instance == null) {
            instance = new ScreenUtil(activity);
//            Logger.i(Logger.UTIL, "Screen instance");
        }
        return instance;
    }

    public static DisplayMetrics getDensity(Context context){
        return context.getApplicationContext().getResources().getDisplayMetrics();
    }

    private ScreenUtil(Activity activity) {
        dm = new DisplayMetrics();
        reference = new WeakReference<>(activity);
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
    }

    public int getStatusHigh(){
        Rect outRect = new Rect();
        getBaseActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect);
        return dm.heightPixels-outRect.height();
    }

    public DisplayMetrics getDm() {
        return dm;
    }

    public void setLayoutParam(View view, int width, int height) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) layoutParams = new ViewGroup.LayoutParams(width, height);
        else {
            if (width > 0) layoutParams.width = width;
            if (height > 0) layoutParams.height = height;
        }
        view.setLayoutParams(layoutParams);
    }

    public void setMarginParam(View view, int left, int right, int top, int bottom) {
        ViewGroup.LayoutParams layoutParams =   view.getLayoutParams();
        if( layoutParams instanceof LinearLayout.LayoutParams){
            LinearLayout.LayoutParams linParams = (LinearLayout.LayoutParams) view.getLayoutParams();
            if (left > 0) linParams.leftMargin = left;
            if (right > 0) linParams.rightMargin = right;
            if (top > 0) linParams.topMargin = top;
            if (bottom > 0) linParams.bottomMargin = bottom;
        }else if( layoutParams instanceof RelativeLayout.LayoutParams){
            RelativeLayout.LayoutParams relParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
            if (left > 0) relParams.leftMargin = left;
            if (right > 0) relParams.rightMargin = right;
            if (top > 0) relParams.topMargin = top;
            if (bottom > 0) relParams.bottomMargin = bottom;
        }else if( layoutParams instanceof FrameLayout.LayoutParams){
            FrameLayout.LayoutParams relParams = (FrameLayout.LayoutParams) view.getLayoutParams();
            if (left > 0) relParams.leftMargin = left;
            if (right > 0) relParams.rightMargin = right;
            if (top > 0) relParams.topMargin = top;
            if (bottom > 0) relParams.bottomMargin = bottom;
        }

        view.setLayoutParams(layoutParams);
    }


    /**
     * @param context
     * @return
     * @description 获取屏幕宽度
     */
    public static final int getScreenWidth(Context context) {
        final float width = context.getResources().getDisplayMetrics().widthPixels;
        return (int) width;
    }

    /**
     * @param context
     * @return
     * @description 获取屏幕高度
     */
    public static final int getScreenHeight(Context context) {
        final float height = context.getResources().getDisplayMetrics().heightPixels;
        return (int) height;
    }

    public static final int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     *
     * @param context
     * @param pxValue （DisplayMetrics类中属性density）
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);

    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
