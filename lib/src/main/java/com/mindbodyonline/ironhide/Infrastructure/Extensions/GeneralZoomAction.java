package com.mindbodyonline.ironhide.Infrastructure.Extensions;

import android.support.test.espresso.PerformException;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.CoordinatesProvider;
import android.support.test.espresso.action.PrecisionDescriber;
import android.support.test.espresso.util.HumanReadables;
import android.support.v4.util.Pair;
import android.view.View;
import android.view.ViewConfiguration;

import org.hamcrest.Matcher;

import static android.support.test.espresso.matcher.ViewMatchers.isDisplayingAtLeast;

/**
 * Enables zooming across a view.
 */
public class GeneralZoomAction implements ViewAction {
    /** Maximum number of times to attempt sending a zoom action. */
    private static final int MAX_TRIES = 3;

    /** The minimum amount of a view that must be displayed in order to zoom across it. */
    private static final int VIEW_DISPLAY_PERCENTAGE = 90;

    private final Pair<CoordinatesProvider, CoordinatesProvider> startCoordinatesProviders;
    private final Pair<CoordinatesProvider, CoordinatesProvider> endCoordinatesProviders;
    private final Zoomer zoomer;
    private final PrecisionDescriber precisionDescriber;

    public GeneralZoomAction(Zoomer zoomer,
                             Pair<CoordinatesProvider, CoordinatesProvider> startCoordinatesProviders,
                             Pair<CoordinatesProvider, CoordinatesProvider> endCoordinatesProviders,
                             PrecisionDescriber precisionDescriber) {
        this.zoomer = zoomer;
        this.startCoordinatesProviders = startCoordinatesProviders;
        this.endCoordinatesProviders = endCoordinatesProviders;
        this.precisionDescriber = precisionDescriber;
    }

    @Override
    public Matcher<View> getConstraints() {
        return isDisplayingAtLeast(VIEW_DISPLAY_PERCENTAGE);
    }

    @Override
    public void perform(UiController uiController, View view) {
        float[][] startCoordinateses = new float[][]{startCoordinatesProviders.first.calculateCoordinates(view), startCoordinatesProviders.second.calculateCoordinates(view)};
        float[][] endCoordinateses = new float[][]{endCoordinatesProviders.first.calculateCoordinates(view), endCoordinatesProviders.second.calculateCoordinates(view)};
        float[] precision = precisionDescriber.describePrecision();

        Zoomer.Status status = Zoomer.Status.FAILURE;

        for (int tries = 0; tries < MAX_TRIES && status != Zoomer.Status.SUCCESS; tries++) {
            try {
                status = zoomer.sendZoom(uiController, startCoordinateses, endCoordinateses, precision);
            } catch (RuntimeException re) {
                throw new PerformException.Builder()
                        .withActionDescription(this.getDescription())
                        .withViewDescription(HumanReadables.describe(view))
                        .withCause(re)
                        .build();
            }

            // ensures that all work enqueued to process the zoom has been run.
            uiController.loopMainThreadForAtLeast(ViewConfiguration.getPressedStateDuration());
        }

        if (status == Zoomer.Status.FAILURE) {
            throw new PerformException.Builder()
                    .withActionDescription(getDescription())
                    .withViewDescription(HumanReadables.describe(view))
                    .withCause(new RuntimeException(String.format(
                            "Couldn't zoom from: (%s,%s) (%s,%s) to: (%s,%s) (%s,%s) precision: %s, %s . Zoomer: %s "
                                    + "start coordinate providers: %s,%s precision describer: %s. Tried %s times",
                            startCoordinateses[0][0],
                            startCoordinateses[0][1],
                            startCoordinateses[1][0],
                            startCoordinateses[1][1],
                            endCoordinateses[0][0],
                            endCoordinateses[0][1],
                            endCoordinateses[1][0],
                            endCoordinateses[1][1],
                            precision[0],
                            precision[1],
                            zoomer,
                            startCoordinatesProviders.first,
                            startCoordinatesProviders.second,
                            precisionDescriber,
                            MAX_TRIES)))
                    .build();
        }
    }

    @Override
    public String getDescription() {
        return zoomer.toString().toLowerCase() + " zoom";
    }
}
