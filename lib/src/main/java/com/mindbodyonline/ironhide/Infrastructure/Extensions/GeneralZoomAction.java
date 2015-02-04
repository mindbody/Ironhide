package com.mindbodyonline.ironhide.Infrastructure.Extensions;

import android.support.test.espresso.PerformException;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.CoordinatesProvider;
import android.support.test.espresso.action.PrecisionDescriber;
import android.view.View;
import android.view.ViewConfiguration;

import com.mindbodyonline.ironhide.Infrastructure.Extensions.Zoomer.Status;

import org.hamcrest.Matcher;

import java.util.Arrays;

import static android.support.test.espresso.matcher.ViewMatchers.isDisplayingAtLeast;
import static android.support.test.espresso.util.HumanReadables.describe;
import static com.mindbodyonline.ironhide.Infrastructure.Extensions.Zoomer.Status.FAILURE;
import static com.mindbodyonline.ironhide.Infrastructure.Extensions.Zoomer.Status.SUCCESS;
import static java.lang.String.format;

/**
 * Enables zooming across a view.
 */
public class GeneralZoomAction implements ViewAction {

    /** Maximum number of times to attempt sending a zoom action. */
    private static final int MAX_TRIES = 3;

    /** The minimum amount of a view that must be displayed in order to zoom across it. */
    private static final int VIEW_DISPLAY_PERCENTAGE = 90;

    private final CoordinatesProvider[] startCoordinatesProviders;
    private final CoordinatesProvider[] endCoordinatesProviders;
    private final Zoomer zoomer;
    private final PrecisionDescriber precisionDescriber;

    public GeneralZoomAction(Zoomer zoomer,
                             CoordinatesProvider[] startCoordinatesProviders,
                             CoordinatesProvider[] endCoordinatesProviders,
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
        float[][] startCoordinates = new float[][]{
                startCoordinatesProviders[0].calculateCoordinates(view),
                startCoordinatesProviders[1].calculateCoordinates(view)
        };
        float[][] endCoordinates = new float[][]{
                endCoordinatesProviders[0].calculateCoordinates(view),
                endCoordinatesProviders[1].calculateCoordinates(view)
        };
        float[] precision = precisionDescriber.describePrecision();

        Status status = FAILURE;

        for (int tries = 0; tries < MAX_TRIES && status != SUCCESS; tries++) {
            try {
                status = zoomer.sendZoom(uiController, startCoordinates, endCoordinates, precision);

            } catch (RuntimeException e) {
                throw new PerformException.Builder()
                        .withActionDescription(getDescription())
                        .withViewDescription(describe(view))
                        .withCause(e)
                        .build();
            }

            // ensures that all work queued to process the zoom has been run.
            uiController.loopMainThreadForAtLeast(ViewConfiguration.getPressedStateDuration());
        }

        if (status == FAILURE) {
            String failure = format("Couldn't zoom from: %s to: %s"
                            + " precision: %s. Zoomer: %s start coordinate providers: %s"
                            + " precision describer: %s. Tried %s times",
                    Arrays.deepToString(startCoordinates), Arrays.deepToString(endCoordinates),
                    Arrays.toString(precision), zoomer, Arrays.toString(startCoordinatesProviders),
                    precisionDescriber, MAX_TRIES);

            throw new PerformException.Builder()
                    .withActionDescription(getDescription())
                    .withViewDescription(describe(view))
                    .withCause(new RuntimeException(failure))
                    .build();
        }
    }

    @Override
    public String getDescription() {
        return zoomer.toString().toLowerCase() + " zoom";
    }
}
