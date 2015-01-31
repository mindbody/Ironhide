package com.mindbodyonline.ironhide.Infrastructure.Extensions;

import android.annotation.TargetApi;
import android.graphics.Point;
import android.graphics.PointF;
import android.support.test.espresso.InjectEventSecurityException;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.CoordinatesProvider;
import android.view.MotionEvent;
import android.view.MotionEvent.PointerCoords;
import android.view.MotionEvent.PointerProperties;
import android.view.View;

import org.hamcrest.Matcher;

import static android.os.SystemClock.uptimeMillis;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayingAtLeast;
import static android.text.format.DateUtils.SECOND_IN_MILLIS;
import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_POINTER_DOWN;
import static android.view.MotionEvent.ACTION_POINTER_INDEX_SHIFT;
import static android.view.MotionEvent.ACTION_POINTER_UP;
import static android.view.MotionEvent.ACTION_UP;
import static android.view.MotionEvent.TOOL_TYPE_FINGER;
import static android.view.MotionEvent.obtain;

/**
 * Enables zooming into/out of a view.
 */
@TargetApi(14)
public class ZoomAction implements ViewAction {

    private static final float DEFAULT_PRESSURE = 1;
    private static final float DEFAULT_SIZE = 1;
    private static final int DEFAULT_DURATION = 3;

    private PointerCoords[] coordinates;
    private PointerProperties[] properties;
    private CoordinatesProvider start[], end[];
    private long startTime;
    
    public ZoomAction(CoordinatesProvider start1, CoordinatesProvider start2, CoordinatesProvider end1, CoordinatesProvider end2) {
        this.start = new CoordinatesProvider[] {start1, start2};
        this.end = new CoordinatesProvider[] {end1, end2};
        this.properties = new PointerProperties[] {generatePointerProperties(0), generatePointerProperties(1)};
        this.coordinates = new PointerCoords[2];
    }
    
    @Override
    public Matcher<View> getConstraints() {
        return isDisplayingAtLeast(90);
    }

    @Override
    public String getDescription() {
        return String.format("zoom from <%s,%s> to <%s,%s>",
                start[0].toString(), start[1].toString(),
                end[0].toString(), end[1].toString());
    }

    @Override
    public void perform(UiController uiController, View view) {
        int action2Mod = properties[1].id << ACTION_POINTER_INDEX_SHIFT;

        // TODO: refactor this
        PointF[] steps = new PointF[2];

        for (int i = 0; i < steps.length; i++) {
            float[] tmp = end[i].calculateCoordinates(view);

            steps[i] = new PointF(tmp[0], tmp[1]);
            steps[i].offset(-coordinates[i].x, coordinates[i].y);
            steps[i].x /= DEFAULT_DURATION;
            steps[i].y /= DEFAULT_DURATION;
        }

        for (int i = 0; i < coordinates.length; i++) {
            float tmp[] = start[i].calculateCoordinates(view);

            coordinates[i].x = tmp[0];
            coordinates[i].y = tmp[1];
        }
        // end refactor this

        startTime = uptimeMillis();

        try {
            uiController.injectMotionEvent(obtainWrapper(startTime, ACTION_DOWN, 1));
            uiController.injectMotionEvent(obtainWrapper(startTime, ACTION_POINTER_DOWN + action2Mod, 2));


            for (int i = 0; i < DEFAULT_DURATION; i++) {
                coordinates[0].x += steps[0].x;
                coordinates[0].y += steps[0].y;
                coordinates[1].x += steps[1].x;
                coordinates[1].y += steps[1].y;

                uiController.injectMotionEvent(obtainWrapper(startTime + i * SECOND_IN_MILLIS, ACTION_MOVE, 2));
            }

            // TODO: refactor this
            for (int i = 0; i < coordinates.length; i++) {
                float tmp[] = end[i].calculateCoordinates(view);

                coordinates[i].x = tmp[0];
                coordinates[i].y = tmp[1];
            }
            // end refactor this

            uiController.injectMotionEvent(obtainWrapper(startTime + DEFAULT_DURATION, ACTION_POINTER_UP + action2Mod, 2));
            uiController.injectMotionEvent(obtainWrapper(startTime, ACTION_UP, 1));
        } catch (InjectEventSecurityException e) {
            // something something shut up espresso
        }
    }

    private MotionEvent obtainWrapper(int action, int num) {
        return obtainWrapper(uptimeMillis(), action, num);
    }

    // TODO: I like magic numbers and I cannot lie; magic numbers and I can't deny
    private MotionEvent obtainWrapper(long eventTime, int action, int num) {
        return obtain(startTime, eventTime, action, num, properties, coordinates, 0, 0, 1, 1, 0, 0, 0, 0);
    }
    
    private static PointerCoords getPointerCoordsFrom(Point source) {
        PointerCoords coords = new PointerCoords();
        
        coords.x = source.x;
        coords.y = source.y;
        coords.pressure = DEFAULT_PRESSURE;
        coords.size = DEFAULT_SIZE;
        
        return coords;
    }

    private static PointerProperties generatePointerProperties(int id) {
        PointerProperties properties = new PointerProperties();

        properties.id = id;
        properties.toolType = TOOL_TYPE_FINGER;

        return properties;
    }
}
