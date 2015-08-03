package net.randomsort.simpledraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by fx on 31-07-2015.
 */
public class DrawView extends View {
    private int x = 100;
    private int y = 100;

    public DrawView(Context context, AttributeSet attributeSets)
    {
        super(context, attributeSets);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        p.setColor(Color.RED);
        canvas.drawCircle(x, y, 5, p);
    }

    public void setPoint(int x, int y)
    {
        this.x = x;
        this.y = y;

    }
}
