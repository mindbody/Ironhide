package com.mindbodyonline.ironhide.Infrastructure.IronhideViews;

import android.annotation.TargetApi;
import android.app.Instrumentation;
import android.graphics.Point;
import android.os.SystemClock;
import android.support.test.espresso.Root;
import android.support.test.espresso.action.ViewActions;
import android.view.MotionEvent.PointerCoords;
import android.view.MotionEvent.PointerProperties;
import android.view.View;

import com.mindbodyonline.ironhide.PageObjects.PageObject;

import org.hamcrest.Matcher;

import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_POINTER_DOWN;
import static android.view.MotionEvent.ACTION_UP;
import static android.view.MotionEvent.ACTION_POINTER_UP;
import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_POINTER_INDEX_SHIFT;
import static android.view.MotionEvent.TOOL_TYPE_FINGER;
import static android.view.MotionEvent.obtain;

/**
 * Simple element that allows to perform a zoom on the screen.
 * Provides zoomAllOut, zoomAllIn, and a generic zoom method.
 * Use this when the main purpose of the element will be to zoom in/out
 *
 * @param <T> The model the current element will return when interacted with
 */
@TargetApi(14)
public class Zoomable<T extends PageObject> extends BaseView<T> {

    private static final int EVENT_MIN_INTERVAL = 1000;

    /** @see com.mindbodyonline.ironhide.Infrastructure.IronhideViews.BaseView#BaseView(Class, org.hamcrest.Matcher) */
    public Zoomable(Class<T> type, Matcher<View> selector) {
        super(type, selector);
    }

    /** @see com.mindbodyonline.ironhide.Infrastructure.IronhideViews.BaseView#BaseView(int) */
    public Zoomable(int resourceId) {
        super(resourceId);
    }

    /** @see com.mindbodyonline.ironhide.Infrastructure.IronhideViews.BaseView#BaseView(org.hamcrest.Matcher) */
    public Zoomable(Matcher<View> selector) {
        super(selector);
    }


    /** {@inheritDoc} */
    @Override
    protected <E extends PageObject> Zoomable<E> goesTo(Class<E> type) {
        return (Zoomable<E>) super.goesTo(type);
    }

    Point finger1Start;
    Point finger1End;
    Point finger2Start;
    Point finger2End;

    private PointerProperties getPointerProperties(int id, int toolType) {
        PointerProperties tmp = new PointerProperties();
        tmp.id = id;
        tmp.toolType = toolType;

        return tmp;
    }

    private PointerCoords getPointerCoords(float x, float y, float pressure, float size) {
        PointerCoords tmp = new PointerCoords();
        tmp.x = x;
        tmp.y = y;
        tmp.pressure = pressure;
        tmp.size = size;

        return tmp;
    }

    private void pointerSync(long downTime, long eventTime, int action, int pointerCount,
                             PointerProperties[] properties,
                             PointerCoords[] coords, Instrumentation inst) {
        inst.sendPointerSync(obtain(downTime, eventTime, action, pointerCount, properties, coords, 0, 0, 1, 1, 0, 0, 0, 0));
    }

    // TODO 71
    public void generateZoomGesture(Instrumentation inst,
                                    long startTime, boolean ifMove, Point startPoint1,
                                    Point startPoint2, Point endPoint1,
                                    Point endPoint2, int duration) {

        if (inst == null
                || startPoint1 == null
                || (ifMove && endPoint1 == null)) {
            return;
        }

        long eventTime = startTime;

        FPoint event1 = new FPoint(startPoint1);
        FPoint event2 = new FPoint(startPoint2);

        // specify the property for the two touch points
        PointerProperties[] properties = new PointerProperties[] {
                getPointerProperties(0, TOOL_TYPE_FINGER),
                getPointerProperties(1, TOOL_TYPE_FINGER)
        };

        //specify the coordinations of the two touch points
        //NOTE: you MUST set the pressure and size value, or it doesn't work
        PointerCoords[] coords = new PointerCoords[] {
                getPointerCoords(event1.x, event1.y, 1, 1),
                getPointerCoords(event2.x, event2.y, 1, 1)
        };
        
        int action2Mod = properties[1].id << ACTION_POINTER_INDEX_SHIFT;

        // step 1 - send ACTION_DOWN event of start points
        pointerSync(startTime, eventTime, ACTION_DOWN, 1, properties, coords, inst);
        pointerSync(startTime, eventTime, ACTION_POINTER_DOWN + action2Mod, 2, properties, coords, inst);

        //step 2 - send ACTION_MOVE of two middle points until reach the end points
        if (ifMove) {
            int moveEventNumber = duration / EVENT_MIN_INTERVAL;

            FPoint step1 = new FPoint(endPoint1);
            step1.offset(-startPoint1.x, -startPoint1.y);
            step1.scale(1 / moveEventNumber);

            FPoint step2 = new FPoint(endPoint2);
            step2.offset(-startPoint2.x, -startPoint2.y);
            step2.scale(1 / moveEventNumber);

            while (moveEventNumber-- > 0) {
                // update the move events
                eventTime += EVENT_MIN_INTERVAL;
                event1.offset(step1.x, step1.y);
                event1.offset(step2.x, step2.y);

                event1.putXY(coords[0]);
                event2.putXY(coords[1]);

                pointerSync(startTime, eventTime, ACTION_MOVE, 2, properties, coords, inst);
            }
        }

        //step 3 - send ACTION_POINTER_2_UP of two end points
        new FPoint(endPoint1).putXY(coords[0]);
        new FPoint(endPoint2).putXY(coords[1]);

        eventTime += EVENT_MIN_INTERVAL;
        pointerSync(startTime, eventTime, ACTION_POINTER_UP + action2Mod, 2, properties, coords, inst);

        // step 4 - send ACTION_UP of one end point
        eventTime += EVENT_MIN_INTERVAL;
        pointerSync(startTime, eventTime, ACTION_UP, 1, properties, coords, inst);
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

    private class FPoint {
        float x, y;

        public FPoint(float x, float y) {
            this.x = x;
            this.y = y;
        }

        public FPoint(Point integerPoint) {
            this(integerPoint.x, integerPoint.y);
        }

        public void offset(float x, float y) {
            this.x = x;
            this.y = y;
        }

        public void scale(float scale) {
            this.x *= scale;
            this.y *= scale;
        }

        public void putXY(PointerCoords coords) {
            coords.x = this.x;
            coords.y = this.y;
        }
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
