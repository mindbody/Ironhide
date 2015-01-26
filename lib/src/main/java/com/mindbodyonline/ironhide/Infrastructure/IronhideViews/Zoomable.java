package com.mindbodyonline.ironhide.Infrastructure.IronhideViews;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Instrumentation;
import android.graphics.Point;
import android.os.SystemClock;
import android.support.test.espresso.Root;
import android.support.test.espresso.action.ViewActions;
import android.view.MotionEvent;
import android.view.View;

import org.hamcrest.Matcher;

/**
 * Extends BaseView
 * Simple element that allows to perform a zoom on the screen
 * Provides zoomAllOut, zoomAllIn, and a generic zoom method.
 * Use this when the main purpose of the element will be to zoom in/out
 *
 * @param <T> The model the current element will return when interacted with
 */
@TargetApi(14)
public class Zoomable<T> extends BaseView<T> {

    private static final int EVENT_MIN_INTERVAL = 1000;

    public Zoomable(Class<T> type, int resourceId) {
        super(type, resourceId);
    }

    public Zoomable(Class<T> type, Matcher<View> selector) {
        super(type, selector);
    }

    Point finger1Start;
    Point finger1End;
    Point finger2Start;
    Point finger2End;

    public void generateZoomGesture(Instrumentation inst,
                                    long startTime, boolean ifMove, Point startPoint1,
                                    Point startPoint2, Point endPoint1,
                                    Point endPoint2, int duration) {

        if (inst == null || startPoint1 == null
                || (ifMove && endPoint1 == null)) {
            return;
        }

        long eventTime = startTime;
        @SuppressLint("redundant") // because it really isn't
        long downTime = startTime;
        MotionEvent event;
        float eventX1, eventY1, eventX2, eventY2;

        eventX1 = startPoint1.x;
        eventY1 = startPoint1.y;
        eventX2 = startPoint2.x;
        eventY2 = startPoint2.y;

        // specify the property for the two touch points
        MotionEvent.PointerProperties[] properties = new MotionEvent.PointerProperties[2];
        MotionEvent.PointerProperties pp1 = new MotionEvent.PointerProperties();
        pp1.id = 0;
        pp1.toolType = MotionEvent.TOOL_TYPE_FINGER;
        MotionEvent.PointerProperties pp2 = new MotionEvent.PointerProperties();
        pp2.id = 1;
        pp2.toolType = MotionEvent.TOOL_TYPE_FINGER;

        properties[0] = pp1;
        properties[1] = pp2;

        //specify the coordinations of the two touch points
        //NOTE: you MUST set the pressure and size value, or it doesn't work
        MotionEvent.PointerCoords[] pointerCoords = new MotionEvent.PointerCoords[2];
        MotionEvent.PointerCoords pc1 = new MotionEvent.PointerCoords();
        pc1.x = eventX1;
        pc1.y = eventY1;
        pc1.pressure = 1;
        pc1.size = 1;
        MotionEvent.PointerCoords pc2 = new MotionEvent.PointerCoords();
        pc2.x = eventX2;
        pc2.y = eventY2;
        pc2.pressure = 1;
        pc2.size = 1;
        pointerCoords[0] = pc1;
        pointerCoords[1] = pc2;

        //////////////////////////////////////////////////////////////
        // events sequence of zoom gesture
        // 1. send ACTION_DOWN event of one start point
        // 2. send ACTION_POINTER_2_DOWN of two start points
        // 3. send ACTION_MOVE of two middle points
        // 4. repeat step 3 with updated middle points (x,y),
        //      until reach the end points
        // 5. send ACTION_POINTER_2_UP of two end points
        // 6. send ACTION_UP of one end point
        //////////////////////////////////////////////////////////////

        // step 1
        event = MotionEvent.obtain(downTime, eventTime,
                MotionEvent.ACTION_DOWN, 1, properties,
                pointerCoords, 0, 0, 1, 1, 0, 0, 0, 0);

        inst.sendPointerSync(event);

        //step 2
        event = MotionEvent.obtain(downTime, eventTime,
                MotionEvent.ACTION_POINTER_DOWN + (pp2.id << MotionEvent.ACTION_POINTER_INDEX_SHIFT), 2,
                properties, pointerCoords, 0, 0, 1, 1, 0, 0, 0, 0);

        inst.sendPointerSync(event);

        //step 3, 4
        if (ifMove) {
            int moveEventNumber = 1;
            moveEventNumber = duration / EVENT_MIN_INTERVAL;

            float stepX1, stepY1, stepX2, stepY2;

            stepX1 = (endPoint1.x - startPoint1.x) / moveEventNumber;
            stepY1 = (endPoint1.y - startPoint1.y) / moveEventNumber;
            stepX2 = (endPoint2.x - startPoint2.x) / moveEventNumber;
            stepY2 = (endPoint2.y - startPoint2.y) / moveEventNumber;

            for (int i = 0; i < moveEventNumber; i++) {
                // update the move events
                eventTime += EVENT_MIN_INTERVAL;
                eventX1 += stepX1;
                eventY1 += stepY1;
                eventX2 += stepX2;
                eventY2 += stepY2;

                pc1.x = eventX1;
                pc1.y = eventY1;
                pc2.x = eventX2;
                pc2.y = eventY2;

                pointerCoords[0] = pc1;
                pointerCoords[1] = pc2;
                event = MotionEvent.obtain(downTime, eventTime,
                        MotionEvent.ACTION_MOVE, 2, properties,
                        pointerCoords, 0, 0, 1, 1, 0, 0, 0, 0);

                inst.sendPointerSync(event);
            }
        }

        //step 5
        pc1.x = endPoint1.x;
        pc1.y = endPoint1.y;
        pc2.x = endPoint2.x;
        pc2.y = endPoint2.y;
        pointerCoords[0] = pc1;
        pointerCoords[1] = pc2;

        eventTime += EVENT_MIN_INTERVAL;
        event = MotionEvent.obtain(downTime, eventTime,
                MotionEvent.ACTION_POINTER_DOWN + (pp2.id << MotionEvent.ACTION_POINTER_INDEX_SHIFT), 2, properties,
                pointerCoords, 0, 0, 1, 1, 0, 0, 0, 0);
        inst.sendPointerSync(event);

        // step 6
        eventTime += EVENT_MIN_INTERVAL;
        event = MotionEvent.obtain(downTime, eventTime,
                MotionEvent.ACTION_UP, 1, properties,
                pointerCoords, 0, 0, 1, 1, 0, 0, 0, 0);
        inst.sendPointerSync(event);
    }

    public T zoomAllIn(int phoneMaxX, int phoneMaxY){

        int zoomInNum = 6;

        // number to scroll up from 6am (2 hour increments: 6am-8am-10am-12am)
        int swipeDownNum = 3; //scrolls to 12am

        finger1Start = new Point(phoneMaxX/2, phoneMaxY/2);
        finger1End = new Point(phoneMaxX/2, phoneMaxY/3);
        finger2Start = finger1Start;
        finger2End = new Point(phoneMaxX/2, phoneMaxY);

        zoom(zoomInNum);

        while (swipeDownNum-- > 0)
            performAction(ViewActions.swipeDown());

        return returnGeneric();

    }

    public T zoomAllOut(int phoneMaxX, int phoneMaxY){

        int zoomOutNum = 3;

        finger1Start = new Point(phoneMaxX/2, phoneMaxY/3);
        finger1End = new Point(phoneMaxX/2, phoneMaxY/2);
        finger2Start = new Point(phoneMaxX/2, phoneMaxY);
        finger2End = finger1End;

        return this.zoom(zoomOutNum);
    }

    public T zoom(int numTimes) {

        Instrumentation instr = new Instrumentation();

        for (int i = 0; i < numTimes; i++)
            generateZoomGesture(instr, SystemClock.uptimeMillis(), true, finger1Start, finger2Start, finger1End, finger2End, 3000);

        return returnGeneric();
    }

    /**
     * Root Matchers return LayoutView
     */

    /** {@inheritDoc} */
    @Override
    public Zoomable<T> changeRoot() {
        return (Zoomable<T>) super.changeRoot();
    }

    /** {@inheritDoc} */
    @Override
    public Zoomable<T> inRoot(Matcher<Root> rootMatcher) {
        return (Zoomable<T>) super.inRoot(rootMatcher);
    }

    /** {@inheritDoc} */
    @Override
    public Zoomable<T> inDialogRoot() {
        return (Zoomable<T>) super.inDialogRoot();
    }

    /** {@inheritDoc} */
    @Override
    public Zoomable<T> inPlatformPopup() {
        return (Zoomable<T>) super.inPlatformPopup();
    }

    /** {@inheritDoc} */
    @Override
    public Zoomable<T> inTouchableRoot() {
        return (Zoomable<T>) super.inTouchableRoot();
    }

    /** {@inheritDoc} */
    @Override
    public Zoomable<T> inDecorView(Matcher<View> decorViewMatcher) {
        return (Zoomable<T>) super.inDecorView(decorViewMatcher);
    }

    /** {@inheritDoc} */
    @Override
    public Zoomable<T> inFocusableRoot() {
        return (Zoomable<T>) super.inFocusableRoot();
    }
}
