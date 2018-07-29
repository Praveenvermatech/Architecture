package developervisits.com.universalapplication.common.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;

import developervisits.com.universalapplication.R;

/**
 * Created by developervisits on 29/7/18.
 * @author Praveen.verma | Noida
 */

public class BadgeDrawable extends Drawable {

    private float mTextSize;
    private Paint mBadgePaint;
    private Paint mTextPaint;
    private Rect mTxtRect = new Rect();
    private int notificationCount=0;
    private String mCount = "";
    private String mType = "";
    private boolean mWillDraw = false;

    public BadgeDrawable(Context context, int count, String type) {
        this.notificationCount = count;
        this.mType = type;
        mTextSize = context.getResources().getDimension(R.dimen.textSize_10);


        mBadgePaint = new Paint();
        mBadgePaint.setColor(Color.RED);
        mBadgePaint.setAntiAlias(true);
        mBadgePaint.setStyle(Paint.Style.FILL);

        mTextPaint = new Paint();
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    public void draw(Canvas canvas) {
        if (!mWillDraw) {
            return;
        }

        Rect bounds = getBounds();
        float width = bounds.right - bounds.left;
        float height = bounds.bottom - bounds.top;

        // Position the badge in the top-right quadrant of the icon.
        float radius = ((Math.min(width, height) / 2) - 1) / 2;
        float centerX = width - radius - 1;
        float centerY = radius + 1;


        // Draw badge circle.
        if(notificationCount>99) {
            canvas.drawCircle(centerX+10, centerY - 7, radius + 10, mBadgePaint);
        }else  if(notificationCount>9){
            canvas.drawCircle(centerX+5, centerY - 6, radius + 5, mBadgePaint);
        }else if(notificationCount>0){
            canvas.drawCircle(centerX+4, centerY -5, radius +2, mBadgePaint);
        }
        // Draw badge count text inside the circle.
        mTextPaint.getTextBounds(mCount, 0, mCount.length(), mTxtRect);
        float textHeight = mTxtRect.bottom - mTxtRect.top;
        float textY = centerY + (textHeight / 2f);
        if(notificationCount>99) {
            canvas.drawText(mCount, centerX+10, textY-7, mTextPaint);
        }else if(notificationCount>9) {
//        canvas.drawText(mCount, centerX+2, textY-3, mTextPaint);
            canvas.drawText(mCount, centerX + 4, textY - 6, mTextPaint);
        }else if(notificationCount>0){
            canvas.drawText(mCount, centerX + 3, textY - 6, mTextPaint);
        }
    }

    /*
    Sets the count (i.e notifications) to display.
     */
    public void setCount(int count) {
        mCount = Integer.toString(count);
        notificationCount = Integer.parseInt(mCount);

        // Only draw a badge if there are notifications.
        if(count>99){
            mCount = "99+";
        }
        mWillDraw = count > 0;
        invalidateSelf();
    }

    @Override
    public void setAlpha(int alpha) {
        // do nothing
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        // do nothing
    }

    @Override
    public int getOpacity() {
        return PixelFormat.UNKNOWN;
    }
}