package zarag.drawingapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zara on 31.07.2015.
 */
public class DrawView extends View {

    private float x = 100, y = 100;
    private int pointSize = 10;

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        canvas.drawCircle(x, y, pointSize, paint);
    }

    protected void setPoint(float x, float y) {
        this.x = x;
        this.y = y;
    }

    protected void setPointSize(int size) {
        pointSize = size;
    }
}
