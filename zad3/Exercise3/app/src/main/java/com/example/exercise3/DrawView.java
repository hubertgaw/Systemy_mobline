package com.example.exercise3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DrawView extends View {//implements View.OnTouchListener {

    public static int BRUSH_SIZE = 10;
    public static final int DEFAULT_COLOR = Color.RED; //default color of pen
    public static final int DEFAULT_BG_COLOR = Color.WHITE; //default color for background
    private static final float TOUCH_TOLERANCE = 4; //use to get when the user starts to draw
    private Paint paint;
    private List<Point> points = new ArrayList<>(); //point which represent coordinates of user's finger
    private Path path; //single path
    private ArrayList<FingerPath> paths = new ArrayList<>(); //our user path
    private int currentColor;
    private int backgroundColor = DEFAULT_BG_COLOR;
    private int strokeWidth;
    private boolean emboss;
    private boolean blur;
    private MaskFilter mEmboss;
    private MaskFilter mBlur;
    private Bitmap bitmap;
    private Canvas canvas;
    private Paint bitmapPaint = new Paint(Paint.DITHER_FLAG);
    private float mX, mY;

    public DrawView(Context context) {
        this(context, null);
//        paint = new Paint();
//        setFocusable(true);
//        setFocusableInTouchMode(true);
//        this.setOnTouchListener(this);
//        paint.setColor(Color.RED);
    }

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(DEFAULT_COLOR);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setXfermode(null);
        paint.setAlpha(0xff);

        mEmboss = new EmbossMaskFilter(new float[] {1, 1, 1}, 0.5f, 0.6f, 2f);
        mBlur = new BlurMaskFilter(5, BlurMaskFilter.Blur.OUTER);
    }

    public void init(DisplayMetrics metrics) {
        int height = metrics.heightPixels;
        int width = metrics.widthPixels;

        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);

        currentColor = DEFAULT_COLOR;
        strokeWidth = BRUSH_SIZE;
    }

    public void normal() {
        emboss = false;
        blur = false;
    }

    public void emboss() {
        emboss = true;
        blur = false;
    }

    public void blur() {
        emboss = false;
        blur = true;
    }

    public void clear() {
        backgroundColor = DEFAULT_BG_COLOR;
        paths.clear();
        normal();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.drawColor(backgroundColor);

        for (FingerPath fp : paths) {
            paint.setColor(fp.color);
            paint.setStrokeWidth(fp.strokeWidth);
            paint.setMaskFilter(null);

            if (fp.emboss)
                paint.setMaskFilter(mEmboss);
            else if (fp.blur)
                paint.setMaskFilter(mBlur);

            canvas.drawPath(fp.path, paint);

        }

        canvas.drawBitmap(bitmap, 0, 0, bitmapPaint);
        canvas.restore();
    }

    private void touchStart(float x, float y) {
        path = new Path();
        FingerPath fp = new FingerPath(currentColor, emboss, blur, strokeWidth, path);
        paths.add(fp);

        path.reset();
        path.moveTo(x, y);
        mX = x;
        mY = y;
    }

    private void touchMove(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);

        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            path.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
    }

    private void touchUp() {
        path.lineTo(mX, mY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN :
                touchStart(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE :
                touchMove(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP :
                touchUp();
                invalidate();
                break;
        }

        return true;
    }

//    @Override
//    protected void onDraw(Canvas canvas) {
//        for (Point point:points){
//            canvas.drawCircle(point.x, point.y, 30, paint);
//
//        }
//    }

//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//        Point point = new Point();
//        point.x = (int) event.getX();
//        point.y = (int) event.getY();
//        points.add(point);
//        invalidate();
//        return true;
//    }
}
