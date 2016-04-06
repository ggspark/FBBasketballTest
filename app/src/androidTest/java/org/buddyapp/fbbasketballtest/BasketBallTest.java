package org.buddyapp.fbbasketballtest;

import android.graphics.Rect;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.util.Log;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static java.lang.Thread.sleep;

/**
 * @author Gaurav Gupta <gaurav@thegauravgupta.com>
 * @since 05/Apr/2016
 */

@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class BasketBallTest {

    public static final double TIME = 40;
    private UiDevice mDevice;
    private int initialCenterX;
    private int initialCenterY;
    private UiObject ball;
    private UiObject basket;
    private static final int BASKET_WINDOW = 20;

    @Before
    public void startMainActivityFromHomeScreen() throws UiObjectNotFoundException {
        // Initialize UiDevice instance
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        ball = mDevice.findObject(new UiSelector().index(6));
        basket = mDevice.findObject(new UiSelector().index(7));
        initialCenterX = basket.getVisibleBounds().centerX();
        initialCenterY = basket.getVisibleBounds().centerY();
    }

    @Test
    public void putBall() throws UiObjectNotFoundException, InterruptedException {
        swipeToBasket(0, 0);
        swipeToBasket(3.5 * TIME, 0);
        swipeToBasket(7 * TIME, 0);
        swipeToBasket(7 * TIME, 2 * TIME);

        swipeToBasket(7 * TIME, 4 * TIME);
    }

    private void swipeToBasket(double speedX, double speedY) throws UiObjectNotFoundException, InterruptedException {
        for (int i = 0; i < 10; i++) {
            Rect srcRect = ball.getVisibleBounds();

            Rect dstRect1 = basket.getVisibleBounds();
            Rect dstRect2 = basket.getVisibleBounds();
            while (notInWindow(initialCenterX, initialCenterY, dstRect2)) {
                dstRect1 = new Rect(dstRect2);
                dstRect2 = basket.getVisibleBounds();
            }

            int diffX = (dstRect2.centerX() - dstRect1.centerX());
            int diffY = (dstRect2.centerY() - dstRect1.centerY());
            int offsetX = (int) (Math.signum(diffX) * speedX);
            int offsetY = (int) (Math.signum(diffY) * speedY);
            mDevice.swipe(srcRect.centerX(), srcRect.centerY(), dstRect2.centerX() + offsetX, dstRect2.centerY() + offsetY, 4);
            Log.w("BasketBallTest", "DiffX: " + diffX + " DiffY: " + diffY + " OffsetX: " + offsetX + " OffsetY: " + offsetY);
            sleep(4000);
        }
    }

    private boolean notInWindow(int centerX, int centerY, Rect dstRect2) {
        return !(dstRect2.centerX() > (centerX - BASKET_WINDOW) && dstRect2.centerX() < (centerX + BASKET_WINDOW) && dstRect2.centerY() > (centerY - BASKET_WINDOW) && dstRect2.centerY() < (centerY + BASKET_WINDOW));
    }


}