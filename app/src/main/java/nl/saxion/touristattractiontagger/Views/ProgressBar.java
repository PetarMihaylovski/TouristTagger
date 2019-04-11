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
    private int maxvalue = 4; //number of screens in normal login path

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

    private void init() {
        this.backgroundPaint = new Paint();
        this.backgroundPaint.setColor(Color.BLACK);
        this.backgroundPaint.setStyle(Paint.Style.FILL);
        this.foregroundPaint = new Paint();
        this.foregroundPaint.setColor(Color.YELLOW);
    }

    public void setValue(int value) {
        this.value = value;
        invalidate();
    }

    public int getValue() {
        return value;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();

        canvas.drawRect(0, 0, width - 1, height, backgroundPaint);
        canvas.drawRect(0, 0, width * value / maxvalue, height , foregroundPaint);
    }
}
