package org.buddyapp.fbbasketballtest;

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
public class MarshamallowAndroidTest {

    private UiDevice mDevice;
    private int targetCenterX;
    private int targetCenterY;
    private int droidCenterY;
    private UiObject bar1;
    private UiObject bar2;
    private UiObject droid;
    int deviceX, deviceY;
    private static final int OFFSET = 100;

    @Before
    public void startMainActivityFromHomeScreen() throws UiObjectNotFoundException {
        // Initialize UiDevice instance
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        deviceX = mDevice.getDisplayWidth() / 2;
        deviceY = mDevice.getDisplayHeight() / 2;

        mDevice.click(mDevice.getDisplayWidth() / 2, mDevice.getDisplayHeight() / 2);
    }

    @Test
    public void clickAndroid() throws UiObjectNotFoundException, InterruptedException {
        bar1 = mDevice.findObject(new UiSelector().className(android.view.View.class).index(1));
        bar2 = mDevice.findObject(new UiSelector().className(android.view.View.class).index(2));
        droid = mDevice.findObject(new UiSelector().className(android.widget.ImageView.class));

        targetCenterY = (bar1.getVisibleBounds().centerY() + bar2.getVisibleBounds().centerY()) / 2;

        droidCenterY = droid.getVisibleBounds().centerY();
        Log.w("MarshamallowAndroidTest", "droidCenterY: " + droidCenterY);
        Log.w("MarshamallowAndroidTest", "targetCenterY: " + targetCenterY);

//        while (droidCenterY > targetCenterY + OFFSET) {
//            droidCenterY = droid.getVisibleBounds().centerY();//fall down
//        }
        maintain();
//
//        while (droidCenterY < targetCenterY - OFFSET) {
//            goUp();
//            droidCenterY = droid.getVisibleBounds().centerY();
//        }

    }

    public void maintain() throws InterruptedException {
        while (true) {
            goUp();
            sleep(600);
        }
    }

    public void goUp() throws InterruptedException {
        mDevice.swipe(deviceX, deviceY, deviceX, deviceY, 1);
    }


}