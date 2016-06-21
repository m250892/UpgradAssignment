package com.manoj.upgradassignment;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by manoj on 22/06/16.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MovieGridActivityTest {
    @Rule
    public ActivityTestRule<MovieGridActivity> mActivityRule = new ActivityTestRule<>(
            MovieGridActivity.class);


    @Test
    public void testOnSortBtnClickDialogShouldBeVisible() {
        onView(withId(R.id.action_sort)).perform(click());
        onView(withText(R.string.dialog_sort)).check(matches(isDisplayed()));
    }

    @Test
    public void textAfterDialogOpenPopularityBtnClickDialogShouldBeDismiss() {
        onView(withId(R.id.action_sort)).perform(click());

        //perform click opeation
        onView(withText("Popularity")).perform(click());
        //sort toolbar visible agaim
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
    }


}
