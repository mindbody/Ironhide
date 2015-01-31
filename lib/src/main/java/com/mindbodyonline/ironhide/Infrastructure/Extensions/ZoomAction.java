package com.mindbodyonline.ironhide.Infrastructure.Extensions;

import android.annotation.TargetApi;
import android.graphics.Point;
import android.graphics.PointF;
import android.support.test.espresso.InjectEventSecurityException;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.v4.util.Pair;
import android.text.format.DateUtils;
import android.view.MotionEvent;
import android.view.MotionEvent.PointerCoords;
import android.view.MotionEvent.PointerProperties;
import android.view.View;

import org.hamcrest.Matcher;

import static android.support.test.espresso.matcher.ViewMatchers.isDisplayingAtLeast;
import static android.view.MotionEvent.*;
import static android.os.SystemClock.uptimeMillis;
import static android.text.format.DateUtils.SECOND_IN_MILLIS;

/**
 * Created by Kyle.Lozier on 1/30/2015.
 */
@TargetApi(14)
public class ZoomAction implements ViewAction {
    
    private static final float DEFAULT_PRESSURE = 1, DEFAULT_SIZE = 1;

    // not sure why
    private static final int DEFAULT_DURATION = 3;
    
    private PointerCoords[] coordinates;
    private PointerProperties[] properties;
    private PointF[] steps;

    private Pair<Point, Point> endPoints;
    
    public ZoomAction(Pair<Point, Point> startPoints, Pair<Point, Point> endPoints) {
        this.coordinates = new PointerCoords[2];
        this.properties = new PointerProperties[2];
        this.steps = new PointF[2];
        this.endPoints = endPoints;
        
        coordinates[0] = getPointerCoordsFrom(startPoints.first);
        coordinates[1] = getPointerCoordsFrom(startPoints.second);

        properties[0] = generatePointerProperties(0);
        properties[1] = generatePointerProperties(1);

        steps[0] = new PointF(endPoints.first);
        steps[0].offset(-coordinates[0].x, -coordinates[0].y);
        steps[0].x /= DEFAULT_DURATION;
        steps[0].y /= DEFAULT_DURATION;

        steps[1] = new PointF(endPoints.second);
        steps[1].offset(-coordinates[1].x, -coordinates[1].y);
        steps[1].x /= DEFAULT_DURATION;
        steps[1].y /= DEFAULT_DURATION;
    }
    
    @Override
    public Matcher<View> getConstraints() {
        return isDisplayingAtLeast(90);
    }

    // TODO: be a little more descriptive
    @Override
    public String getDescription() {
        return "zoom";
    }

    @Override
    public void perform(UiController uiController, View view) {
        long startTime = uptimeMillis();
        int action2Mod = properties[1].id << ACTION_POINTER_INDEX_SHIFT;

        try {
            uiController.injectMotionEvent(obtainWrapper(startTime, ACTION_DOWN, 1));
            uiController.injectMotionEvent(obtainWrapper(startTime, ACTION_POINTER_DOWN + action2Mod, 2));

            for (int i = 0; i < DEFAULT_DURATION; i++) {
                coordinates[0].x += steps[0].x;
                coordinates[0].y += steps[0].y;
                coordinates[1].x += steps[1].x;
                coordinates[1].y += steps[1].y;

                uiController.injectMotionEvent(obtainWrapper(startTime, startTime + i * SECOND_IN_MILLIS, ACTION_MOVE, 2));
            }

            coordinates[0].x = endPoints.first.x;
            coordinates[0].y = endPoints.first.y;
            coordinates[1].x = endPoints.second.x;
            coordinates[1].y = endPoints.second.y;

            uiController.injectMotionEvent(obtainWrapper(startTime, startTime + DEFAULT_DURATION, ACTION_POINTER_UP + action2Mod, 2));
            uiController.injectMotionEvent(obtainWrapper(startTime, ACTION_UP, 1));
        } catch (InjectEventSecurityException e) {
            // something something shut up espresso
        }
    }

    // TODO: I like magic numbers and I cannot lie; magic numbers and I can't deny
    private MotionEvent obtainWrapper(long startTime, int action, int num) {
        return obtainWrapper(startTime, uptimeMillis(), action, num);
    }

    private MotionEvent obtainWrapper(long startTime, long eventTime, int action, int num) {
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
