package com.gui.toledo.accelerometersample;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class AccelerometerSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder surfaceHolder;
    private Paint paintX;
    private Paint paintY;
    private Float velocityX = 0f, velocityY = 0f;

    public AccelerometerSurfaceView(Context context) {
        super(context);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        paintX = new Paint();
        paintX.setTextSize(65f);
        paintX.setColor(Color.RED);

        paintY = new Paint();
        paintY.setTextSize(65f);
        paintY.setColor(Color.BLACK);

        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        DrawingThread drawingThread = new DrawingThread();
        drawingThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }

    public void updateValues(float x, float y) {
        velocityX = x;
        velocityY = y;
    }

    private void drawText() {
        Canvas canvas = null;

        try {
            canvas = surfaceHolder.lockCanvas();

            if (canvas != null) {
                synchronized (surfaceHolder) {
                    canvas.drawColor(Color.WHITE);
                    canvas.drawText("x: " + velocityX.intValue(), getWidth() / 3, getHeight() / 4, paintX);
                    canvas.drawText("y: " + velocityY.intValue(), getWidth() / 3, getHeight() / 3, paintY);
                }
            }
        } finally {
            if (canvas != null) {
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }

    private class DrawingThread extends Thread {
        @Override
        public void run() {
            while (true) {
                drawText();
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}