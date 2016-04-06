package org.buddyapp.fbbasketballtest;

import android.graphics.Rect;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

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

    private UiDevice mDevice;
    private int initialCenterX;
    private int initialCenterY;
    private UiObject ball;
    private UiObject basket;
    private static final int BASKET_WINDOW = 25;

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
        swipeToBasket(110, 0);
        swipeToBasket(110 * 2, 0);

        swipeToBasket(110 * 2, 110);
    }

    private void swipeToBasket(int speedX, int speedY) throws UiObjectNotFoundException, InterruptedException {
        for (int i = 0; i < 10; i++) {
            Rect srcRect = ball.getVisibleBounds();

            Rect dstRect = basket.getVisibleBounds();
            Rect dstRect2 = basket.getVisibleBounds();
            while (notInWindow(initialCenterX, initialCenterY, dstRect2)) {
                dstRect = dstRect2;
                dstRect2 = basket.getVisibleBounds();
            }

            int diffX = (dstRect2.centerX() - dstRect.centerX());
            int diffY = (dstRect2.centerY() - dstRect.centerY());
            mDevice.swipe(srcRect.centerX(), srcRect.centerY(), dstRect2.centerX() + ((int) Math.signum(diffX) * speedX), dstRect2.centerY() + ((int) Math.signum(diffY) * speedY), 5);
            sleep(4000);
        }
    }

    private boolean notInWindow(int centerX, int centerY, Rect dstRect2) {
        return !(dstRect2.centerX() > (centerX - BASKET_WINDOW) && dstRect2.centerX() < (centerX + BASKET_WINDOW) && dstRect2.centerY() > (centerY - BASKET_WINDOW) && dstRect2.centerY() < (centerY + BASKET_WINDOW));
    }


}