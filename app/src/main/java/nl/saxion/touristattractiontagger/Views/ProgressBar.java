package nl.saxion.touristattractiontagger.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class ProgressBar extends View {

    private Paint backgroundPaint;
    private Paint foregroundPaint;
    private int value = 0;
    private int maxvalue = 4; //Number of screens in normal login path.

    /**
     * Constructors.
     *
     * @param context The current context.
     */
    public ProgressBar(Context context) {
        super(context);
        init();
    }

    public ProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ProgressBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    /**
     * Initializing the colors of the paints.
     */
    private void init() {
        this.backgroundPaint = new Paint();
        this.backgroundPaint.setColor(Color.BLACK);
        this.backgroundPaint.setStyle(Paint.Style.FILL);
        this.foregroundPaint = new Paint();
        this.foregroundPaint.setColor(Color.YELLOW);
    }

    /**
     * Setting the value of the progress bar.
     * If the value is bigger than the maximum amount of screens
     * the value is not getting set.
     *
     * @param value The value to be set.
     */
    public void setValue(int value) {
        if (value >= 0 && value <= maxvalue) {
            this.value = value;
        }
        invalidate();
    }

    /**
     * Drawing a progress bar.
     *
     * @param canvas The view, the drawing is drawn.
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();

        canvas.drawRect(0, 0, width, height, backgroundPaint);
        canvas.drawRect(0, 0, width * value / maxvalue, height, foregroundPaint);
    }
}
