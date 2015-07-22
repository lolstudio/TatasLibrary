package com.tata.android.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.tata.android.R;


public class HoloTopBar extends RelativeLayout {

	public static final double lineHeight = 2;

	public HoloTopBar(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public HoloTopBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				(int) (48 * 1.5)));
		setBackgroundResource(R.color.purple_light);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		Paint paint = new Paint();
		paint.setColor(R.color.purple_dark);
		paint.setAntiAlias(true);
		
		int bar_height = getHeight();
		int bar_width = getWidth();
		canvas.drawLine(0, (float) (bar_height - lineHeight), bar_width,
				(float) (bar_height - lineHeight), paint);
	}
}
