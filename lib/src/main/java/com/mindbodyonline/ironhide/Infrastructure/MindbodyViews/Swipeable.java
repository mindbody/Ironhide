package com.mindbodyonline.ironhide.Infrastructure.MindbodyViews;

import android.app.Instrumentation;
import android.graphics.Point;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CalendarView;

import com.google.android.apps.common.testing.ui.espresso.ViewAction;
import com.google.android.apps.common.testing.ui.espresso.action.GeneralLocation;
import com.google.android.apps.common.testing.ui.espresso.action.GeneralSwipeAction;
import com.google.android.apps.common.testing.ui.espresso.action.Press;
import com.google.android.apps.common.testing.ui.espresso.action.Swipe;
import com.google.android.apps.common.testing.ui.espresso.action.ViewActions;
import com.google.android.apps.common.testing.ui.espresso.contrib.DrawerActions;
import com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers;
import com.mindbodyonline.ironhide.Infrastructure.Extensions.MindbodyViewActions;
import com.mindbodyonline.ironhide.Infrastructure.Extensions.MindbodyViewMatchers;

import org.hamcrest.Matcher;

import static org.hamcrest.Matchers.allOf;

/**
 * Extends MindbodyView
 * Simple element that allows to perform a swipe on the screen
 * Implements swipeLeft and swipeRight methods
 * Use this when the main purpose of the element will be to swipe the screen
 *
 * @param <T> The model the current element will return when interacted with
 */
public class Swipeable<T> extends MindbodyView<T> {

    public Swipeable(Class<T> type, int resourceId) {
        this.id = resourceId;
        this.type = type;
    }

    public Swipeable(Class<T> type, Matcher<View> selector) {
        this.selector = selector;
        this.type = type;
    }

    public T swipeLeft() {
        return performAction(ViewActions.swipeLeft());
    }

    public T swipeLeft(int numTimes) {
        for(int i = 0; i < numTimes-1 ; i++){
            performAction(ViewActions.swipeLeft());
        }
        return performAction(ViewActions.swipeLeft());
    }

    public T swipeRight() {
        return performAction(ViewActions.swipeRight());
    }

    public T swipeRight(int numTimes) {
        for(int i = 0; i < numTimes-1 ; i++){
            performAction(ViewActions.swipeRight());
        }
        return performAction(ViewActions.swipeRight());
    }

    public T swipeDownFast(){
        return performAction(MindbodyViewActions.swipeDownFast());
    }

    public T swipeDownSlow(){
        return performAction(MindbodyViewActions.swipeDownSlow());
    }

    public T swipeDownSlow(int numTimes){
        for(int i = 0; i < numTimes-1 ; i++){
            performAction(MindbodyViewActions.swipeDownSlow());
        }
        return performAction(MindbodyViewActions.swipeDownSlow());
    }

    public T swipeUpFast(){ return performAction(MindbodyViewActions.swipeUpFast()); }

    public T swipeUpSlow(){ return performAction(MindbodyViewActions.swipeUpSlow()); }

    public T swipeUpSlow(int numTimes){
        for(int i = 0; i < numTimes-1 ; i++){
            performAction(MindbodyViewActions.swipeUpSlow());
        }
        return performAction(MindbodyViewActions.swipeUpSlow());

    }

    public T swipeFullRight(){ return performAction(MindbodyViewActions.swipeFullRight()); }

    public T swipeFullRight(int numTimes){
        for(int i = 0; i < numTimes-1 ; i++){
            performAction(MindbodyViewActions.swipeFullRight());
        }
        return performAction(MindbodyViewActions.swipeFullRight());

    }

    public T swipeFullLeft(){ return performAction(MindbodyViewActions.swipeFullLeft()); }

    public T swipeFullLeft(int numTimes){
        for(int i = 0; i < numTimes-1 ; i++){
            performAction(MindbodyViewActions.swipeFullLeft());
        }
        return performAction(MindbodyViewActions.swipeFullLeft());

    }

    public T scrollUpToClick(MindbodyView<T> element){

        while (true) {
            try {
                element.isDisplayed();
                return element.click();

            }catch (Exception e) {
                performAction(MindbodyViewActions.swipeUpSlowHalf());
                pause(200);
            }
        }
    }

    public T selectFromListWithText(Class type, String string){

        Clickable<T> Title = new Clickable<T>(this.type, allOf(MindbodyViewMatchers.instanceOf(type), ViewMatchers.withText(string)));

        return this.scrollUpToClick(Title);
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
        long downTime = startTime;
        MotionEvent event;
        float eventX1, eventY1, eventX2, eventY2;

        eventX1 = startPoint1.x;
        eventY1 = startPoint1.y;
        eventX2 = startPoint2.x;
        eventY2 = startPoint2.y;

        final int EVENT_MIN_INTERVAL = 1000;

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

        return this.swipeDownSlow(swipeDownNum);

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

}