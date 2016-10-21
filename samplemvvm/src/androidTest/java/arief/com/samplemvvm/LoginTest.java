package arief.com.samplemvvm;

import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Copyright (C) PT. Sebangsa Bersama - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Originally written by Author Name sebangsa, 21/10/16
 */

@RunWith(AndroidJUnit4.class)
@MediumTest
public class LoginTest {

    public static String failed_username = "jhony";
    public static String username = "cerminuser";
    public static String failed_password = "123456";
    public static String password = "cermin12345";


    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void testLoginFailed(){

        onView(withId(R.id.username)).perform(typeText(failed_username), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(failed_password), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());

        // Verify empty tasks snackbar is shown
        String emptyTaskMessageText = "User does not exists ";

        onView(allOf(withId(android.support.design.R.id.snackbar_text), withText(emptyTaskMessageText)))
                .check(matches(isDisplayed()));

    }

    @Test
    public void passwordFailed(){
        onView(withId(R.id.username)).perform(typeText(username), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(failed_password), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());

        // Verify empty tasks snackbar is shown
        String emptyTaskMessageText = "Wrong password";

        onView(allOf(withId(android.support.design.R.id.snackbar_text), withText(emptyTaskMessageText)))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testLoginSuccess(){

        onView(withId(R.id.username)).perform(typeText(username), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(password), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());

        // Verify empty tasks snackbar is shown
        String selamat = "Selamat datang";
        onView(allOf(withId(android.support.design.R.id.snackbar_text), withText(selamat)))
                .check(matches(isDisplayed()));
    }
}
