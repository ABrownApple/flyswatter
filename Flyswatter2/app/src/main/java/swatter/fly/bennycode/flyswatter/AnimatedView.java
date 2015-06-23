package swatter.fly.bennycode.flyswatter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.os.Handler;

import java.util.Random;

/**
 * Created by ABrownApple on 2015-06-23.
 */
public class AnimatedView extends ImageView {
    private Context mContext;
    int x = -1;
    int y = -1;
    private int xVelocity = 10;
    private int yVelocity = 5;
    private Handler h;
    private final int FRAME_RATE = 30;

    public AnimatedView(Context context, AttributeSet attrs)  {
        super(context, attrs);
        mContext = context;
        Random rand = new Random();
        xVelocity = rand.nextInt((10 - 5) + 1) + 5;
        yVelocity = rand.nextInt((13 - 9) + 1) + 9;
        h = new Handler();
    }
    private Runnable r = new Runnable() {
        @Override
        public void run() {
            invalidate();
        }
    };
    protected void onDraw(Canvas c) {
        BitmapDrawable fly = (BitmapDrawable) mContext.getResources().getDrawable(R.drawable.small_fly);
        
        if (x<0 && y <0) {
            x = this.getWidth()/2;
            y = this.getHeight()/2;
        } else {
            x += xVelocity;
            y += yVelocity;
            if ((x > this.getWidth() - fly.getBitmap().getWidth()) || (x < 0)) {
                xVelocity = xVelocity*-1;
            }
            if ((y > this.getHeight() - fly.getBitmap().getHeight()) || (y < 0)) {
                yVelocity = yVelocity*-1;
            }
        }
        c.drawBitmap(fly.getBitmap(), x, y, null);
        h.postDelayed(r, FRAME_RATE);
    }
}
