package com.example.maomao.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.Region;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：11:16
 * modify developer：  admin
 * modify time：11:16
 * modify remark：
 *
 * @version 2.0
 */


public class RingImageView extends AppCompatImageView {

    Path path;
    public PaintFlagsDrawFilter mPaintFlagsDrawFilter;// 毛边过滤
    Paint paint;
    private int mBorderColor = Color.WHITE;
    private int strokeColor = Color.WHITE;
    private Context context;
    private float strokeWidth = 0;
    private float clearance = 0;

    private static final int DEFAULT_BORDER_COLOR = Color.WHITE;//边界颜色


    public RingImageView(Context context) {
        super(context);
    }

    public RingImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RingImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RingImageView, defStyle, 0);
        mBorderColor = a.getColor(R.styleable.RingImageView_circle_background, DEFAULT_BORDER_COLOR);
        strokeColor = a.getColor(R.styleable.RingImageView_circle_stroke_color, DEFAULT_BORDER_COLOR);
        strokeWidth = a.getDimension(R.styleable.RingImageView_circle_strokeWidth, 0);
        clearance = a.getDimension(R.styleable.RingImageView_circle_clearance, 0);

        init();
    }

    public void init() {
        mPaintFlagsDrawFilter = new PaintFlagsDrawFilter(0,
                Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);

    }

    @Override
    protected void onDraw(Canvas cns) {
        float h = getHeight();
        float w = getWidth();
        if (path == null) {
            path = new Path();
            path.addCircle(
                    w / 2.0f
                    , h / 2.0f
                    , (float) Math.min(w / 2.0f, (h / 2.0)) - (clearance + strokeWidth)//收缩内圈
                    , Path.Direction.CCW);
            path.close();
        }
        cns.drawCircle(w / 2.0f, h / 2.0f, Math.min(w / 2.0f, h / 2.0f) - 1, paint);//收缩外圈
        int saveCount = cns.getSaveCount();
        cns.save();
        cns.clipPath(path, Region.Op.REPLACE);

        cns.setDrawFilter(mPaintFlagsDrawFilter);
        cns.drawColor(mBorderColor);

        this.paint.setColor(strokeColor);
        this.paint.setStrokeWidth(strokeWidth);
        cns.drawCircle(w / 2.0f, h / 2.0f, Math.min(w / 2.0f, h / 2.0f), this.paint);//back的收缩外圈


        super.onDraw(cns);
        cns.restoreToCount(saveCount);
    }
}
