package com.capybaras.donationtracker;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.capybaras.donationtracker.controllers.LoginActivity;
import com.capybaras.donationtracker.models.Model;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;



/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class attemptLoginTest {
    private Model model;

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule =
            new ActivityTestRule<>(LoginActivity.class);

    @Before
    public void setUp() {
        model = Model.getInstance();
    }

    @Test
    public void allBlankLogin() {
        onView(withId(R.id.sign_in_button)).perform(click());
        assert(model.getLoggedInUser() == null);
    }

    @Test
    public void defaultUserLogin() {
        onView(withId(R.id.username)).perform(typeText("user"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("pass"), closeSoftKeyboard());
        onView(withId(R.id.sign_in_button)).perform(click());
        assert(model.getLoggedInUser() != null);
    }

    @Test
    public void noPasswordLogin() {
        onView(withId(R.id.username)).perform(typeText("user"), closeSoftKeyboard());
        onView(withId(R.id.sign_in_button)).perform(click());
        assert(model.getLoggedInUser() == null);
    }

    @Test
    public void noUsernameLogin() {
        onView(withId(R.id.password)).perform(typeText("pass"), closeSoftKeyboard());
        onView(withId(R.id.sign_in_button)).perform(click());
        assert(model.getLoggedInUser() == null);
    }

}
