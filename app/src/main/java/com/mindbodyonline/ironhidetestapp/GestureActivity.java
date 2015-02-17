package com.mindbodyonline.ironhidetestapp;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Displays a large touchable area and logs the events it receives.
 */
public class GestureActivity extends Activity {
    private static final String TAG = GestureActivity.class.getSimpleName();


    private View gestureArea;
    private List<MotionEvent> downEvents = new ArrayList<>();
    private List<MotionEvent> scrollEvents = new ArrayList<>();
    private List<MotionEvent> longPressEvents = new ArrayList<>();
    private List<MotionEvent> showPresses = new ArrayList<>();
    private List<MotionEvent> singleTaps = new ArrayList<>();
    private List<MotionEvent> confirmedSingleTaps = new ArrayList<>();
    private List<MotionEvent> doubleTapEvents = new ArrayList<>();
    private List<MotionEvent> doubleTaps = new ArrayList<>();

    public void clearDownEvents() {
        downEvents.clear();
    }

    public void clearScrollEvents() {
        scrollEvents.clear();
    }

    public void clearLongPressEvents() {
        longPressEvents.clear();
    }

    public void clearShowPresses() {
        showPresses.clear();
    }

    public void clearSingleTaps() {
        singleTaps.clear();
    }

    public void clearConfirmedSingleTaps() {
        confirmedSingleTaps.clear();
    }

    public void clearDoubleTapEvents() {
        doubleTapEvents.clear();
    }

    public void clearDoubleTaps() {
        doubleTaps.clear();
    }

    public List<MotionEvent> getDownEvents() {
        return new ArrayList<>(downEvents);
    }

    public List<MotionEvent> getScrollEvents() {
        return new ArrayList<>(scrollEvents);
    }

    public List<MotionEvent> getLongPressEvents() {
        return new ArrayList<>(longPressEvents);
    }

    public List<MotionEvent> getShowPresses() {
        return new ArrayList<>(showPresses);
    }

    public List<MotionEvent> getSingleTaps() {
        return new ArrayList<>(singleTaps);
    }

    public List<MotionEvent> getConfirmedSingleTaps() {
        return new ArrayList<>(confirmedSingleTaps);
    }

    public List<MotionEvent> getDoubleTapEvents() {
        return new ArrayList<>(doubleTapEvents);
    }

    public List<MotionEvent> getDoubleTaps() {
        return new ArrayList<>(doubleTaps);
    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.gesture_activity);
        gestureArea = findViewById(R.id.gesture_area);
        final GestureDetector simpleDetector = new GestureDetector(this, new GestureListener());
        simpleDetector.setIsLongpressEnabled(true);
        simpleDetector.setOnDoubleTapListener(new DoubleTapListener());
        gestureArea.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent m) {
                boolean res = simpleDetector.onTouchEvent(m);
                if (-1 != touchDelay) {
                    Log.i(TAG, "sleeping for: " + touchDelay);
                    SystemClock.sleep(touchDelay);

                }
                return res;
            }
        });
    }

    private volatile long touchDelay = -1;

    public void setTouchDelay(long touchDelay) {
        this.touchDelay = touchDelay;
    }

    public void areaClicked(@SuppressWarnings("unused") View v) {
        Log.v(TAG, "onClick called!");
    }

    private class DoubleTapListener implements GestureDetector.OnDoubleTapListener {
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            doubleTaps.add(MotionEvent.obtain(e));
            Log.v(TAG, "onDoubleTap: " + e);
            setVisible(R.id.text_double_click);
            return false;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            doubleTapEvents.add(MotionEvent.obtain(e));
            Log.v(TAG, "onDoubleTapEvent: " + e);
            return false;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            confirmedSingleTaps.add(MotionEvent.obtain(e));
            Log.v(TAG, "onSingleTapConfirmed: " + e);
            return false;
        }
    }

    private class GestureListener implements GestureDetector.OnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            downEvents.add(MotionEvent.obtain(e));
            Log.v(TAG, "Down: " + e);
            return false;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            singleTaps.add(MotionEvent.obtain(e));
            Log.v(TAG, "on single tap: " + e);
            setVisible(R.id.text_click);
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distX, float distY) {
            scrollEvents.add(MotionEvent.obtain(e1));
            scrollEvents.add(MotionEvent.obtain(e2));
            Log.v(TAG, "Scroll: e1: " + e1 + " e2: " + e2 + " distX: " + distX + " distY: " + distY);
            setVisible(R.id.text_swipe);
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            showPresses.add(MotionEvent.obtain(e));
            Log.v(TAG, "ShowPress: " + e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            longPressEvents.add(MotionEvent.obtain(e));
            Log.v(TAG, "LongPress: " + e);
            setVisible(R.id.text_long_click);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float veloX, float veloY) {
            Log.v(TAG, "Fling: e1: " + e1 + " e2: " + e2 + " veloX: " + veloX + " veloY: " + veloY);
            return false;
        }
        
        
    }

    private void setVisible(int id) {
        hideAll();
        findViewById(id).setVisibility(View.VISIBLE);
    }

    private void hideAll() {
        findViewById(R.id.text_click).setVisibility(View.GONE);
        findViewById(R.id.text_long_click).setVisibility(View.GONE);
        findViewById(R.id.text_swipe).setVisibility(View.GONE);
        findViewById(R.id.text_double_click).setVisibility(View.GONE);
    }
}
