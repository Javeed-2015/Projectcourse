package com.example.my.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by My on 31-07-2015.
 */
public class DrawView extends View{
    private int x=900;
    private int y=900;
    public DrawView( Context context, AttributeSet attributeSets)



    {
        super(context, attributeSets);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        Paint p = new Paint();
        p.setColor(Color.GREEN);
        canvas.drawCircle(x, y, 100, p);


    }
        public void setPoint(int x, int y)
        {
            this.x=x;
            this.y=y;
        }
}
